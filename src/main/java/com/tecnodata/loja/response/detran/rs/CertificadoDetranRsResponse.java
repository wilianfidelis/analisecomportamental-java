package com.tecnodata.loja.response.detran.rs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificadoDetranRsResponse {
    private String mensagemDesenvolvedor;
    private String mensagemUsuario;
    private String numeroHomologacao;
}
