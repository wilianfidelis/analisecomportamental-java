package com.tecnodata.loja.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "REGRASFIN")
public class RegrasFin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer pk_regrasfin;

    private String tipo;

    private String descricao;
}
