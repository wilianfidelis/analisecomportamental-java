package com.tecnodata.loja.service;

import com.tecnodata.loja.entities.Usuario;
import com.tecnodata.loja.exceptions.TecnodataException;
import com.tecnodata.loja.repository.AutenticacaoRepository;
import com.tecnodata.loja.request.UsuarioRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AutenticacaoService {

    private static final String MENSAGEM_EMAIL_DUPLICADO = "E-mail duplicado, insira outro e-mail.";
    private static final String MENSAGEM_USUARIO_NAO_ENCONTRADO = "Usuario não encontrado";
    private static final String MENSAGEM_USUARIO_SENHA_INVALIDA = "Usuario ou senha inválida";
    @Autowired
    AutenticacaoRepository autenticacaoRepository;
    @Autowired
    UtilService utilService;

    public Usuario salvarUsuario(UsuarioRequest request) {
        try {

            if (emailDuplicado(request.getEmail())) {
                throw new TecnodataException(MENSAGEM_EMAIL_DUPLICADO);
            }


            return autenticacaoRepository.save(Usuario.builder()
                    .senha(utilService.criptografaSenha(request.getSenha()))
                    .email(request.getEmail())
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    private boolean emailDuplicado(String email) {
        Integer resultSet = autenticacaoRepository.recuperaQtdeEmailCadastrado(email);
        return resultSet >= 1;
    }

    public ResponseEntity<Boolean> autorizaLogin(String email, String senha) {
        try {
            boolean usuarioOk = false;
            String senhaUsuario = autenticacaoRepository.recuperaSenhaUsuarioPorLogin(email);
            if (senhaUsuario == null) {
                throw new TecnodataException(MENSAGEM_USUARIO_NAO_ENCONTRADO);
            } else {
                usuarioOk = utilService.validaUsuario(senha, senhaUsuario);
                if (!usuarioOk) {
                    throw new TecnodataException(MENSAGEM_USUARIO_SENHA_INVALIDA);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(usuarioOk);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    public Usuario buscarUsuario(String email) {
        try {
            return autenticacaoRepository.buscarUsuarioEmail(email);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

}
