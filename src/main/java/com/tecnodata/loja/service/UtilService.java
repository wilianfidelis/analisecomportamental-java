package com.tecnodata.loja.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String criptografaSenha(String string){
        return encoder.encode(string);
    }

    public boolean validaUsuario(CharSequence senha, String senhaCripto) {
        return encoder.matches(senha,senhaCripto );
    }

}
