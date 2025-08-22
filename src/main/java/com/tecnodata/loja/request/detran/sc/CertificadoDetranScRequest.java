package com.tecnodata.loja.request.detran.sc;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CertificadoDetranScRequest {
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
