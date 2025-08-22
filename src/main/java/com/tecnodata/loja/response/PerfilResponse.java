package com.tecnodata.loja.response;

import com.tecnodata.loja.entities.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PerfilResponse {

    private Integer id;

    private String nome;

    private Boolean status;

    public PerfilResponse(Perfil obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.status = obj.getStatus();
    }
}
