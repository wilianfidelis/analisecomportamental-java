package com.tecnodata.loja.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RetornoMensagemGenerica {
    private String mensagem;
}
