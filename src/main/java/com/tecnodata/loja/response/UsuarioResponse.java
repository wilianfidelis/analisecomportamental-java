package com.tecnodata.loja.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
}
