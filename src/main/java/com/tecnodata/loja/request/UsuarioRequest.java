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
public class UsuarioRequest {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "senha")
    private String senha;

}
