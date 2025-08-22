package com.tecnodata.loja.service;

import com.tecnodata.loja.entities.Pedidos;
import com.tecnodata.loja.exceptions.ObjectNotFoundException;
import com.tecnodata.loja.exceptions.TecnodataException;
import com.tecnodata.loja.repository.PedidosRepository;
import com.tecnodata.loja.request.ConsultarBoletoRequest;
import com.tecnodata.loja.response.PedidosResponse;
import com.tecnodata.loja.response.StatusBoletoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PedidosService {

    @Autowired
    PedidosRepository pedidosRepository;

    @Autowired
    PagamentosService pagamentosService;

    public List<PedidosResponse> buscarTodosPedidosCnpj(String cnpj){
        List<Pedidos> pedidos = pedidosRepository.retornaPedidosPeloCnpj(cnpj);
        try {
            return pedidos.stream().map(obj ->
                            PedidosResponse.builder()
                                    .id(obj.id)
                                    .cnpj(obj.cnpj)
                                    .razao(obj.razao)
                                    .dataPedido(obj.dataPedido)
                                    .formaPagamento(obj.formaPagamento)
                                    .transaction_id(obj.transaction_id)
                                    .status(obj.status)
                                    .valorTotal(obj.valorTotal)
                                    .itemPedido(obj.itemPedido)
                                    .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ObjectNotFoundException("Pedidos não encontrados! ");
        }

    }

    public List<PedidosResponse> allPedidos(@Valid Integer quantidade){
        Page<Pedidos> pedidos = pedidosRepository.findAll(PageRequest.of(1, quantidade, Sort.by(Sort.Direction.DESC, "dataPedido")));
        try {
            return pedidos.stream().map(obj ->
                            PedidosResponse.builder()
                                    .id(obj.id)
                                    .cnpj(obj.cnpj)
                                    .razao(obj.razao)
                                    .dataPedido(obj.dataPedido)
                                    .formaPagamento(obj.formaPagamento)
                                    .transaction_id(obj.transaction_id)
                                    .status(obj.status)
                                    .valorTotal(obj.valorTotal)
                                    .itemPedido(obj.itemPedido)
                                    .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ObjectNotFoundException("Pedidos não encontrados! ");
        }
    }

    public List<Pedidos> atualizarPedidos(List<ConsultarBoletoRequest> request) {
        try {
            List<Pedidos> pedidosConsultados = new ArrayList<>();
            request.forEach( boletoRequest -> {
                StatusBoletoResponse statusBoletoResponse = pagamentosService.consultarBoleto(boletoRequest);
                Pedidos pedido = pedidosRepository.retornaPedidosPeloTransactionId(boletoRequest.getTransaction_id());
                pedido.setStatus(statusBoletoResponse.getStatusRequest().getStatus());
                pedidosConsultados.add(pedido);
            });
            return pedidosRepository.saveAll(pedidosConsultados);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }
}
