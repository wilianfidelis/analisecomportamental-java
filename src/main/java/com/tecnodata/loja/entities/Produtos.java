package com.tecnodata.loja.entities;

import com.tecnodata.loja.entities.converter.BooleanToCharConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Convert(converter = BooleanToCharConverter.class)
    public Boolean status = Boolean.FALSE;

    public String codigo;

    public String nome;

    public String descricao;
    public String imagem;

    public Float preco;

    public String uf;

    public String categoria;

    public Integer quantidade;

    @Convert(converter = BooleanToCharConverter.class)
    public Boolean disponivel = Boolean.FALSE;

    public String avaliacao;

    private Integer id_lms;

    @Convert(converter = BooleanToCharConverter.class)
    public Boolean aplicarDesconto = Boolean.FALSE;

}
