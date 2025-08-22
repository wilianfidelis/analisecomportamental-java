package com.tecnodata.loja.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoodleCertificarRequest {

    private Integer matricula;

    private String usuario;
}
