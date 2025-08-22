package com.tecnodata.loja.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private String pagina;

    private String total_de_paginas;

    private String registros;

    private String total_de_registros;

    @JsonProperty(value = "clientes_cadastro")
    private List<Cliente> dadosCliente;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Cliente {

        private String bairro;

        private String bloquear_faturamento;

        private String cep;

        private String cidade;

        private String cidade_ibge;

        private String cnae;

        private String cnpj_cpf;

        private String codigo_cliente_integracao;

        private float codigo_cliente_omie;

        private String codigo_pais;

        private String complemento;

        private String contato;

        private String contribuinte;

        private String email;

        private String endereco;

        private String endereco_numero;

        private String estado;

        private String exterior;

        private String inativo;

        private String inscricao_estadual;

        private String inscricao_municipal;

        private String nome_fantasia;

        private String optante_simples_nacional;

        private String pessoa_fisica;

        private String razao_social;

        private String telefone1_ddd;

        private String telefone1_numero;

        private String tipo_atividade;
    }
}

