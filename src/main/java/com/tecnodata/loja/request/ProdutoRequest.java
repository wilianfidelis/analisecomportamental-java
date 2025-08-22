package com.tecnodata.loja.request;

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
public class ProdutoRequest {


    public String codigo;

    public String nome;

    public String descricao;

    public String imagem;

    public Float preco;

    public Integer quantidade;

    public Boolean status = Boolean.FALSE;

    public Boolean disponivel = Boolean.FALSE;

    public Boolean aplicarDesconto = Boolean.FALSE;
}
