package com.tecnodata.loja.request.detran.pr;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CertificadoDetranPrRequest {
    private String cnpjEntidade;
    private String numCpfInstrutor;
    private String numRegCnh;
    private String numCpfCondutor;
    private String dataNascimento;
    private String dataInicioCurso;
    private String dataFimCurso;
    private String codCurso;
}
