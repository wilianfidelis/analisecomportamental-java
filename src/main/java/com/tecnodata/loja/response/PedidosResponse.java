package com.tecnodata.loja.response;

import com.tecnodata.loja.entities.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidosResponse {

    private Integer id;

    private String cnpj;

    private String razao;

    private String dataPedido;

    private String formaPagamento;

    private String transaction_id;

    private String status;

    private double valorTotal;

    private ItemPedido itemPedido;

}
