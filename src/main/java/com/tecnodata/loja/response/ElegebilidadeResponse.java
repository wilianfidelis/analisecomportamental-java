package com.tecnodata.loja.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElegebilidadeResponse {
    private String mensagemDesenvolvedor;
    private String mensagemUsuario;
}
