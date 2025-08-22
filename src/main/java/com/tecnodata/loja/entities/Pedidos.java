package com.tecnodata.loja.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String cnpj;

    public String razao;

    public String dataPedido;

    public String formaPagamento;

    public String transaction_id;

    public String status;

    public double valorTotal;

    public Integer parcelas;

    @ManyToOne
    public ItemPedido itemPedido;

}
