package com.tecnodata.loja.request;

import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmitirBoletoRequest {

    private List<Items> items;

    private String apiKey;

    private Integer id_aluno;

    private String order_id;

    private String payer_email;

    private String payer_name;

    private String payer_cpf_cnpj;

    private String payer_phone;

    private String days_due_date;

    private String type_bank_slip;

    private String payer_street;

    private String payer_number;

    private String payer_complement;

    private String payer_district;

    private String payer_city;

    private String payer_state;

    private String payer_zip_code;

    private String notification_url;

    private String cpf_aluno;

    @Data
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Items {

        private String description;

        private String quantity;

        private String item_id;

        private String price_cents;
    }
}
