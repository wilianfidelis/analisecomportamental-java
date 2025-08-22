package com.tecnodata.loja.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientesListRequest {

    @JsonProperty(value = "pagina")
    private String pagina;

    @JsonProperty(value = "registros_por_pagina")
    private String registrosPorPagina;

    @JsonProperty(value = "apenas_importado_api")
    private String apenasImportadoApi;

    @JsonProperty(value = "clientesFiltro")
    private List<ClientesFiltro> clientesFiltroList = new ArrayList<>();
}
