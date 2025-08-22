package com.tecnodata.loja.request.detran.pr;

import com.tecnodata.loja.request.detran.ElegibilidadeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElegibilidadePrRequest  {
    private String cnpjEntidade;
    private String codigoCurso;
    private String cpfCondutor;
    private String numeroRegistroStr;
    private String dataNascimento;
}
