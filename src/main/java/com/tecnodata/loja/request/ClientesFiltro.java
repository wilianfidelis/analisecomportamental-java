package com.tecnodata.loja.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientesFiltro {
    @JsonProperty("cnpj_cpf")
    private String cnpjCpf;
}
