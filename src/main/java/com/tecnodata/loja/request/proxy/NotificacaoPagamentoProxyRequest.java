package com.tecnodata.loja.request.proxy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoPagamentoProxyRequest {
    private String token;
    private String apiKey;
    private String transaction_id;
    private String notification_id;
}

