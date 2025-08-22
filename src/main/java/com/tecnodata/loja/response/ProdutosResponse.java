package com.tecnodata.loja.response;

import com.tecnodata.loja.entities.Produtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutosResponse {
    private Integer id;
    private String codigo;
    private String nome;
    private String descricao;
    private String imagem;
    private Float preco;
    private String uf;
    private String categoria;
    private Integer quantidade;
    private Boolean disponivel;
    private String avaliacao;

    private Boolean status;

    private Boolean aplicarDesconto;

    public ProdutosResponse(Produtos obj) {
        this.id = obj.id;
        this.codigo = obj.codigo;
        this.nome = obj.nome;
        this.descricao = obj.descricao;
        this.imagem = obj.imagem;
        this.preco = obj.preco;
        this.uf = obj.uf;
        this.categoria = obj.categoria;
        this.quantidade = obj.quantidade;
        this.disponivel = obj.disponivel;
        this.avaliacao = obj.avaliacao;
        this.status = obj.status;
        this.aplicarDesconto = obj.aplicarDesconto;
    }
}
