package com.tecnodata.loja.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditosRequest {
    private String creditos;
    private String data;
    private String cnpj;
    private String formaPagamento;
}
