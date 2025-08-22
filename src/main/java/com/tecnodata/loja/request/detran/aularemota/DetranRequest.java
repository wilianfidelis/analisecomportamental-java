package com.tecnodata.loja.request.detran.aularemota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetranRequest {

    private String numCnpj;

    private String numCpf;

    private String codCfc;

    private String numProcesso;

    private String codTurmaCfc;

    private String horaInicio;

    private String dataPesquisa;
}
