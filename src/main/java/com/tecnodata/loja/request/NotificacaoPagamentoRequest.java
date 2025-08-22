package com.tecnodata.loja.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoPagamentoRequest {
    private String apiKey;
    private String notification_id;
    private String transaction_id;
    private String notification_date;
    private String source_api;
    private String token;
}
