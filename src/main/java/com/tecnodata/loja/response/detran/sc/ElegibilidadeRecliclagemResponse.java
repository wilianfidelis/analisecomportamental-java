package com.tecnodata.loja.response.detran.sc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElegibilidadeRecliclagemResponse {
    private String mensagemDesenvolvedor;
    private String mensagemUsuario;
}
