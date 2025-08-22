package com.tecnodata.loja.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PixRequest {

    private String apiKey;

    private String order_id;

    private String payer_email;

    private String payer_name;

    private String payer_cpf_cnpj;

    private String payer_phone;

    private String notification_url;

    private String discount_cents;

    private String shipping_price_cents;

    private String shipping_methods;

    private String number_ntfiscal;

    private String fixed_description;

    private String days_due_date;

    private List<items> items;

    @Data
    @ToString
    public static class items {

        private String description;

        private Long quantity;

        private Long item_id;

        private Long price_cents;
    }
}
