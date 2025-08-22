package com.tecnodata.loja.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultarBoletoRequest {

    private String token;

    private String apiKey;

    private String transaction_id;
}
