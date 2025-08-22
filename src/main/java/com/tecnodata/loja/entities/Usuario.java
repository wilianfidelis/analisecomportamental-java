package com.tecnodata.loja.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String senha;

    private String cnpj_cfc;

    private String cpf;

    private String cnh;

    private String dtns;

    private String sexo;

    private String cep;

    private String endereco;

    private String numero;

    private String complemento;

    private String cidade;

    private String bairro;

    private String uf;

    private String telefone;

    private String celular;

    private String usuario;

    private String dt_cadastro;

    private String rg;

    @ManyToOne
    public Perfil perfil;

}
