package com.tecnodata.loja.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltroCliente {

    @JsonProperty(value = "call")
    private String call;

    @JsonProperty(value = "app_key")
    private String appKey;

    @JsonProperty(value = "app_secret")
    private String appSecret;

    @JsonProperty(value = "param")
    private List<ClientesListRequest> clientesListRequest;
}

