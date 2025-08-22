package com.tecnodata.loja.request.detran.rs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificadoDetranRsRequest {
    private String codTicket;
    private String codEmpresa;
    private String codCurso;
    private String cpfProfissional;
    private String cpfAluno;
    private String renach;
    private String codCertificadoEmpresa;
    private String dthInicio;
    private String dthFim;
    private String cargaHoraria;
}
