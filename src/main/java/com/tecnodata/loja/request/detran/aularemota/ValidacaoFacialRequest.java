package com.tecnodata.loja.request.detran.aularemota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidacaoFacialRequest {

    private String numProcesso;

    private String numCpf;

    private String midia;

    private String codCfc;

    private String tot;
}
