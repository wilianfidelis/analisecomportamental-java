package com.tecnodata.loja.request;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastroRequest {
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

}


