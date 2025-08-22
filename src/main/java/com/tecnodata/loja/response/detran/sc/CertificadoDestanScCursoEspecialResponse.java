package com.tecnodata.loja.response.detran.sc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificadoDestanScCursoEspecialResponse {
    private String mensagemDesenvolvedor;
    private String mensagemUsuario;
    private String numeroHomologacao;
}
