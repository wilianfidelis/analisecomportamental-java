package com.tecnodata.loja.controller;

import com.tecnodata.loja.entities.Usuario;
import com.tecnodata.loja.request.UsuarioRequest;
import com.tecnodata.loja.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/autenticacao")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @PostMapping("/cadastro/usuario")
    @ResponseBody
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Validated UsuarioRequest request) {
        return ResponseEntity.ok(autenticacaoService.salvarUsuario(request));
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> realizaLogin(@RequestParam String email, @RequestParam String senha) {
       return autenticacaoService.autorizaLogin(email, senha);
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarDadosUsuario(@RequestParam String email) {
        return ResponseEntity.ok(autenticacaoService.buscarUsuario(email));
    }
}
