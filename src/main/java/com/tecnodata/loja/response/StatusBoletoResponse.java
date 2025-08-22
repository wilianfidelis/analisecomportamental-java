package com.tecnodata.loja.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StatusBoletoResponse {

    @JsonProperty(value = "status_request")
    private StatusRequest statusRequest;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class StatusRequest {
        private String result;
        private String response_message;
        private String order_id;
        private String status;
        private String status_date;
        private String due_date;
        private Long value_cents;
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
    }
}
