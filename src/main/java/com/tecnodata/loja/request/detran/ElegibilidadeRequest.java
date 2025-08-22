package com.tecnodata.loja.request.detran;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ElegibilidadeRequest {
    private String cnpjEntidade;
    private String codigoCurso;
    private String cpfCondutor;
    private String numeroRegistroStr;
    private String dataNascimento;
}
