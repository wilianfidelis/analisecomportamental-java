package com.tecnodata.loja.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmailDto {

    @NotBlank
    private String proprietario;
    @NotBlank
    @Email
    private String remetente;
    @NotBlank
    @Email
    private String destinatario;
    @NotBlank
    private String assunto;
    @NotBlank
    private String text;

}
