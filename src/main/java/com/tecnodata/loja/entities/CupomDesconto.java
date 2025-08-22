package com.tecnodata.loja.entities;

import com.tecnodata.loja.entities.converter.BooleanToCharConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class CupomDesconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = BooleanToCharConverter.class)
    public Boolean status = Boolean.FALSE;

    private String codigo;

    private String desconto;
}
