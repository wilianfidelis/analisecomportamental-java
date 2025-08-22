package com.tecnodata.loja.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String precoUnitario;
    public String quantidade;

    @ManyToOne
    public Produtos produto;

    @JsonBackReference(value = "**")
    @OneToMany(mappedBy = "itemPedido")
    private List<Pedidos> pedidos;

    public ItemPedido(String precoUnitario, String quantidade, Produtos produto) {
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.produto = produto;
    }
}