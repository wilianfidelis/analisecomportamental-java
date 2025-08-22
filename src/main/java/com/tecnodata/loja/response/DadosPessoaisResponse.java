package com.tecnodata.loja.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaisResponse {
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
