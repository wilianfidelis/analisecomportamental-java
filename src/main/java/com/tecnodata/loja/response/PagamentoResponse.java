package com.tecnodata.loja.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoResponse {

    @JsonProperty(value = "create_request")
    private CreateRequest createRequest;

    private String mensagem;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateRequest {

        private String transaction_id;
        private String created_date;
        private String value_cents;
        private String order_id;
        private String due_date;
        private String result;
        private String status;
        private String response_message;

        @JsonProperty(value = "bank_slip")
        private BankSlip bankSlip;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BankSlip {

        private String digitable_line;
        private String url_slip;
        private String url_slip_pdf;
        private String bar_code_number_to_image;
    }
}
