package com.tecnodata.loja.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosPessoaisRequest {

    private String nome;

    private String sobrenome;

    private String cpf_cnpj;

    private String cep;

    private String endereco;

    private String numero;

    private String cidade;

    private String bairro;

    private String uf;

    private String telefone;

    private String email;

}
