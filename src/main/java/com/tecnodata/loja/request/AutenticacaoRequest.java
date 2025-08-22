package com.tecnodata.loja.request;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutenticacaoRequest {
    private String cnpj;
    private String email;
    private String senha;
}


