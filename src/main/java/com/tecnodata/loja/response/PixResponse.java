package com.tecnodata.loja.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PixResponse {

    @JsonProperty(value = "pix_create_request")
    private PixCreateRequest pixCreateRequest;

    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class PixCreateRequest {

        private String result;

        private String response_message;

        private String transaction_id;

        private String created_date;

        private Long value_cents;

        private String status;

        private String order_id;

        private String due_date;

        @JsonProperty(value = "pix_code")
        private PixCode pixCode;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PixCode {

        private String qrcode_base64;

        private String qrcode_image_url;

        private String emv;

        private String pix_url;

        private String bacen_url;
    }
}
