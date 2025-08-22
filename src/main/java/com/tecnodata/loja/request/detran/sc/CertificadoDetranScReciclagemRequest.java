package com.tecnodata.loja.request.detran.sc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificadoDetranScReciclagemRequest {
    private Integer cargaHoraria;
    private String categoria;
    private String chaveCurso;
    private String cnpjInstituicao;
    private String cpfCondutor;
    private String cpfInstrutor;
    private String dataFimCurso;
    private String dataInicioCurso;
    private String numeroCertificadoStr;
}
