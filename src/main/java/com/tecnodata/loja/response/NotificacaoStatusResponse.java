package com.tecnodata.loja.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NotificacaoStatusResponse {
    @JsonProperty(value = "status_request")
    private StatusBoletoResponse.StatusRequest statusRequest;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class StatusRequest {
        private String result;
        private String response_message;
        private String transaction_id;
        private String order_id;
        private String status;
        private String status_date;
        private Date paid_date;
        private String due_date;
        private Long value_cents;
        @JsonProperty(value = "bank_slip")
        private StatusBoletoResponse.BankSlip bankSlip;
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
