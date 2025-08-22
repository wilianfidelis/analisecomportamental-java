package com.tecnodata.loja.service;

import com.tecnodata.loja.entities.*;
import com.tecnodata.loja.enums.TipoPagamentoEnum;
import com.tecnodata.loja.exceptions.TecnodataException;
import com.tecnodata.loja.repository.ItemPedidoRepository;
import com.tecnodata.loja.repository.PedidosRepository;
import com.tecnodata.loja.repository.ProdutosRepository;
import com.tecnodata.loja.request.ConsultarBoletoRequest;
import com.tecnodata.loja.request.EmitirPagamentoRequest;
import com.tecnodata.loja.request.NotificacaoPagamentoRequest;
import com.tecnodata.loja.request.PixRequest;
import com.tecnodata.loja.request.proxy.NotificacaoPagamentoProxyRequest;
import com.tecnodata.loja.response.*;
import com.tecnodata.loja.service.proxy.PagamentoCartaoProxyService;
import com.tecnodata.loja.util.RestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Slf4j
public class PagamentosService extends RestApi {

    private static final String URL_NOTIFICACAO_TECNODATA = "https://api.parceiros.tecnodataead.com.br:8043/pagamentos/notificacao-api";

    private static final String PROPRIETARIO = "TECNODATA";
    private static final String ASSUNTO = "Notificação automatica paghiper";

    private static final String CATEGORIA_CREDITO = "AR";

    private static final String STATUS_PAGAMENTO_PAGO = "paid";

    private static final String CREDITOS = "crdt";

    @Value("${emissao-boleto.endpoint-emissao}")
    private String endPointEmissaoBoleto;

    @Value("${emissao-boleto.endpoint-consulta}")
    private String endPointConsultaBoleto;

    @Value("${emissao-pix.endpoint-emissao}")
    private String endPointEmissaoPix;

    @Value("${chaves-paghiper.apiKey}")
    private String apiKey;

    @Value("${chaves-paghiper.token}")
    private String token;

    @Value("${chaves-paghiper.notificacao}")
    private String endPointNotificacao;

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PagamentoCartaoProxyService pagamentoCartaoProxyService;

    private double valorTotal;

    private HttpHeaders adicionarHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public PagamentoResponse emitirPagamento(EmitirPagamentoRequest request) {
        if (TipoPagamentoEnum.CARTAO.getDescricao().equals(request.getTipoPagamento())) {
            return emitirCompraCartao(request);
        } else {
            tratarValorPagamentoCentavos(request);
            return emitirBoleto(request);
        }
    }

    private void tratarValorPagamentoCentavos(EmitirPagamentoRequest request) {
        if (Objects.nonNull(request.getItems())) {
            request.getItems().forEach(item -> {
                float valorCentavos = Float.parseFloat(item.getPrice_cents()) * 100;
                item.setPrice_cents(String.valueOf(valorCentavos));
            });
        }
    }

    public PagamentoResponse emitirBoleto(EmitirPagamentoRequest request) {
        try {
            request.setApiKey(apiKey);
            request.setNotification_url(URL_NOTIFICACAO_TECNODATA);

            ItemPedido itemPedido = getItemPedido(request);

            if (!Objects.nonNull(itemPedido)) {
                throw new TecnodataException("Erro ao realizar operação, entre em contato com o suporte");
            }

            Long orderId = definirOrderId(request);
            request.setOrder_id(String.valueOf(orderId + 1));

            PagamentoResponse boleto = post(PagamentoResponse.class, endPointEmissaoBoleto, new HttpEntity<>(request, adicionarHeader()));

            salvarPedido(boleto, request, itemPedido);

            return boleto;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    public PagamentoResponse emitirCompraCartao(EmitirPagamentoRequest request) {
        return PagamentoResponse.builder()
                .mensagem(pagamentoCartaoProxyService.emitirCompraCartao(request).getMensagem())
                .build();
    }

    public StatusBoletoResponse consultarBoleto(ConsultarBoletoRequest request) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            request.setApiKey(apiKey);
            request.setToken(token);
            return restTemplate.exchange(endPointConsultaBoleto, HttpMethod.POST, new HttpEntity<>(request, adicionarHeader()), StatusBoletoResponse.class).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    public PixResponse emitirPix(PixRequest request) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            request.setApiKey(apiKey);
            return restTemplate.exchange(endPointEmissaoPix, HttpMethod.POST, new HttpEntity<>(request, adicionarHeader()), PixResponse.class).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    private Long definirOrderId(EmitirPagamentoRequest request) {
        try {
            return pedidosRepository.retornaUltimoPedidoId() != null ? pedidosRepository.retornaUltimoPedidoId() : 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException("Erro ao realizar operação contate o suporte!!");
        }
    }

    private void salvarPedido(PagamentoResponse boleto, EmitirPagamentoRequest request, ItemPedido itemPedido) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(new Date());
            Pedidos pedido = Pedidos.builder()
                    .cnpj(request.getPayer_cpf_cnpj())
                    .razao(request.getPayer_name())
                    .dataPedido(date)
                    .formaPagamento(TipoPagamentoEnum.BOLETO.getDescricao())
                    .status(Objects.requireNonNull(boleto).getCreateRequest().getStatus())
                    .transaction_id(Objects.requireNonNull(boleto).getCreateRequest().getTransaction_id())
                    .valorTotal(valorTotal)
                    .itemPedido(itemPedido)
                    .build();
            pedidosRepository.save(pedido);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
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
    public void notificacaoAutomaticaPagamento(NotificacaoPagamentoRequest request) throws MessagingException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        request.setApiKey(apiKey);
        request.setToken(token);
        NotificacaoPagamentoProxyRequest notificacaoRequest = NotificacaoPagamentoProxyRequest.builder()
                .token(request.getToken())
                .apiKey(request.getApiKey())
                .transaction_id(request.getTransaction_id())
                .notification_id(request.getNotification_id())
                .build();
        NotificacaoStatusResponse notificacao = restTemplate.exchange(endPointNotificacao, HttpMethod.POST, new HttpEntity<>(notificacaoRequest, adicionarHeader()), NotificacaoStatusResponse.class).getBody();
        enviarEmail(request);
        if (Objects.nonNull(notificacao)) {
            atualizarPedido(notificacao);
        }
    }

    private void atualizarPedido(NotificacaoStatusResponse notificacao) {
        try {
            List<Pedidos> pedidosConsultados = new ArrayList<>();
            Optional<Pedidos> pedido = pedidosRepository.findById(Integer.valueOf(notificacao.getStatusRequest().getOrder_id()));
            if (pedido.isPresent()) {
                Pedidos pedidoSalvar = pedido.get();
                pedidoSalvar.setStatus(notificacao.getStatusRequest().getStatus());
                pedidosRepository.save(pedidoSalvar);
                pedidosConsultados.add(pedido.get());
            }
        } catch (Exception e) {
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    private void enviarEmail(NotificacaoPagamentoRequest request) throws MessagingException, IOException {
        EmailEntity emailModel = criarEmailModel(request);
        emailService.sendEmail(emailModel);
    }

    private EmailEntity criarEmailModel(NotificacaoPagamentoRequest request) {
        return EmailEntity.builder()
                .proprietario(PROPRIETARIO)
                .remetente("reciclagem@tecnodataead.com.br")
                .destinatario("julio@tecnodatacfc.com.br")
                .assunto(ASSUNTO)
                .text(String.valueOf(request))
                .build();
    }

}

