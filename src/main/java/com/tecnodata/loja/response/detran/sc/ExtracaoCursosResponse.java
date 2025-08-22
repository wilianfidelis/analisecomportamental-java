package com.tecnodata.loja.response.detran.sc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ExtracaoCursosResponse {
    private String chave;
    private String descricao;
}
