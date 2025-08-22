package com.tecnodata.loja.service.proxy;

import br.com.userede.erede.Store;
import br.com.userede.erede.Transaction;
import br.com.userede.erede.TransactionResponse;
import br.com.userede.erede.eRede;
import br.com.userede.erede.service.error.RedeException;
import com.tecnodata.loja.entities.ItemPedido;
import com.tecnodata.loja.entities.Pedidos;
import com.tecnodata.loja.entities.Produtos;
import com.tecnodata.loja.enums.TipoPagamentoEnum;
import com.tecnodata.loja.exceptions.TecnodataException;
import com.tecnodata.loja.repository.ItemPedidoRepository;
import com.tecnodata.loja.repository.PedidosRepository;
import com.tecnodata.loja.repository.ProdutosRepository;
import com.tecnodata.loja.request.CreditosRequest;
import com.tecnodata.loja.request.EmitirPagamentoRequest;
import com.tecnodata.loja.response.RetornoMensagemGenerica;
import com.tecnodata.loja.util.RestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class PagamentoCartaoProxyService extends RestApi {
    private static final String TRASACAO_AUTORIZADA = "Pagamento autorizada com sucesso";
    private static final String TRASACAO_NAO_AUTORIZADA = "Pagamento nao autorizada";

    private static final String CREDITOS = "crdt";

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidosRepository pedidosRepository;

    @Value("${emissao-cartao.token}")
    private String token;

    @Value("${emissao-cartao.filiation}")
    private String filiation;

    @Value("${emissao-cartao.url}")
    private String url;

    @Value("${aula-remota.envio-creditos}")
    private String urlEnvioCreditos;

    private double valorTotal;

    private HttpHeaders adicionarHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public RetornoMensagemGenerica emitirCompraCartao(EmitirPagamentoRequest request) {
        try {
            ItemPedido itemPedido = getItemPedido(request);
            if (!Objects.nonNull(itemPedido)) {
                throw new TecnodataException("Erro ao realizar operação, entre em contato com o suporte");
            }
            Long orderId = definirOrderId(request) + 1;
            request.setOrder_id(String.valueOf(orderId));
            return compraCartao(request, itemPedido);
        } catch (RedeException e) {
            log.error(e.getRedeError().getReturnMessage());
            throw new TecnodataException(e.getRedeError().getReturnMessage());
        }
    }

    private ItemPedido getItemPedido(EmitirPagamentoRequest request) {

        AtomicReference<ItemPedido> itemPedido = new AtomicReference<>(new ItemPedido());

        request.getItems().forEach(item -> {
            Optional<Produtos> produto = produtosRepository.findById(Integer.valueOf(item.getItem_id()));
            if (produto.isPresent()) {
                ItemPedido itemSalvar = ItemPedido.builder().quantidade(item.getQuantity()).precoUnitario(item.getPrice_cents()).produto(produto.get()).build();
                valorTotal = Double.parseDouble(item.getQuantity()) * Double.parseDouble(item.getPrice_cents());
                itemPedido.set(itemSalvar);
                itemPedidoRepository.save(itemSalvar);
            }
        });

        return itemPedido.get();
    }

    private Long definirOrderId(EmitirPagamentoRequest request) {
        try {
            return pedidosRepository.retornaUltimoPedidoId();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException("Erro ao realizar operação contate o suporte!!");
        }
    }

    public RetornoMensagemGenerica compraCartao(EmitirPagamentoRequest request, ItemPedido itemPedido) {
        // Configuração da loja
        Store store = new Store(filiation, token);
        store.getEnvironment().setEndpoint(url);
        RetornoMensagemGenerica retornoCartao = verificaTransacaoPorTipoCartao(request, store);
        if (retornoCartao.getMensagem().contains(TRASACAO_AUTORIZADA)) {
            salvarPedido(request, itemPedido);
        }
        return retornoCartao;
    }

    private void salvarPedido(EmitirPagamentoRequest request, ItemPedido itemPedido) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(new Date());
            Pedidos pedido = Pedidos.builder()
                    .cnpj(request.getPayer_cpf_cnpj())
                    .razao(request.getPayer_name())
                    .dataPedido(date)
                    .formaPagamento(TipoPagamentoEnum.CARTAO.getDescricao())
                    .parcelas(request.getCartaoRequest().getParcelamento())
                    .status("completed")
                    .transaction_id(request.getCartaoRequest().getTransacaoId())
                    .valorTotal(valorTotal)
                    .itemPedido(itemPedido)
                    .build();
            pedidosRepository.save(pedido);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }


    public RetornoMensagemGenerica verificaTransacaoPorTipoCartao(EmitirPagamentoRequest request, Store store) {

        String mesAno = request.getCartaoRequest().getMes_ano_expiracao().replace("/", "");
        Transaction transaction;
        TransactionResponse transactionResponse;

        // Transação que será autorizada
        transaction = criaTransacaoCredito(request, mesAno);
        // Autoriza a transação
        transactionResponse = new eRede(store).authorize(transaction);

        if (transactionResponse.getReturnCode().equals("00") && request.isAulaRemota()) {
            request.getCartaoRequest().setTransacaoId(transaction.getTid());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(new Date());
            enviaCreditos(CreditosRequest
                    .builder()
                    .creditos(request
                            .getItems()
                            .get(0)
                            .getQuantity())
                    .data(date)
                    .cnpj(request.getPayer_cpf_cnpj())
                    .build());
            return RetornoMensagemGenerica.builder().mensagem(TRASACAO_AUTORIZADA).build();

        } else if (transactionResponse.getReturnCode().equals("00")) {
            request.getCartaoRequest().setTransacaoId(transaction.getTid());
            return RetornoMensagemGenerica.builder().mensagem(TRASACAO_AUTORIZADA).build();
        }

        return RetornoMensagemGenerica.builder().mensagem(TRASACAO_NAO_AUTORIZADA).build();
    }

    private static Transaction criaTransacaoCredito(EmitirPagamentoRequest request, String mesAno) {
        Transaction transaction = new Transaction(Double.parseDouble(request.getCartaoRequest().getValor()), request.getOrder_id())
                .creditCard(
                        request.getCartaoRequest().getNumero_cartao(),
                        request.getCartaoRequest().getCodigo_seguranca(),
                        mesAno.substring(0, 2),
                        mesAno.substring(2, 6),
                        request.getCartaoRequest().getNome_titular()
                );
        if (Objects.nonNull(request.getCartaoRequest().getParcelamento())) {
            transaction.setInstallments(request.getCartaoRequest().getParcelamento());
        }
        return transaction;
    }

    public void enviaCreditos(CreditosRequest request) {
        try {
            post(String.class, this.urlEnvioCreditos, new HttpEntity<>(request, adicionarHeader()));
        } catch (Exception e) {
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }
}
