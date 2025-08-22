package com.tecnodata.loja.service;

import com.tecnodata.loja.entities.Usuario;
import com.tecnodata.loja.exceptions.TecnodataException;
import com.tecnodata.loja.repository.AutenticacaoRepository;
import com.tecnodata.loja.request.DadosPessoaisRequest;
import com.tecnodata.loja.response.DadosPessoaisResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@Slf4j
public class DadosPessoaisService {

    @Autowired
    AutenticacaoRepository autenticacaoRepository;

    public String salvarDadosPessoais(DadosPessoaisRequest request) {
        try {
            Usuario usuario = this.autenticacaoRepository.buscarUsuarioEmail(request.getEmail());
            if (usuario == null) {
                usuario = new Usuario();
            }

            usuario.setNome(request.getNome());
            usuario.setSobrenome(request.getSobrenome());
            usuario.setCpf(request.getCpf_cnpj());
            usuario.setCep(request.getCep());
            usuario.setEndereco(request.getEndereco());
            usuario.setNumero(request.getNumero());
            usuario.setCidade(request.getCidade());
            usuario.setBairro(request.getBairro());
            usuario.setUf(request.getUf());
            usuario.setTelefone(request.getTelefone());
            autenticacaoRepository.save(usuario);

            // Salvar ou atualizar o usuário no banco de dados

            return "Sucesso";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    public DadosPessoaisResponse buscarUsuario(@Valid String email) {
        try {
            Usuario usuario = autenticacaoRepository.buscarUsuarioEmail(email);
            return DadosPessoaisResponse.builder()
                    .cpf_cnpj(usuario.getCpf())
                    .nome(usuario.getNome())
                    .cep(usuario.getCep())
                    .uf(usuario.getUf())
                    .bairro(usuario.getBairro())
                    .cidade(usuario.getCidade())
                    .numero(usuario.getNumero())
                    .email(usuario.getEmail())
                    .endereco(usuario.getEndereco())
                    .sobrenome(usuario.getSobrenome())
                    .telefone(usuario.getTelefone())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }
}
