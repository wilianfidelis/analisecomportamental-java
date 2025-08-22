package com.tecnodata.loja.controller;

import com.tecnodata.loja.service.DadosPessoaisService;
import com.tecnodata.loja.request.DadosPessoaisRequest;
import com.tecnodata.loja.response.DadosPessoaisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/dados-pessoais")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class DadosPessoaisController {

    @Autowired
    DadosPessoaisService dadosPessoaisService;

    @PostMapping
    public ResponseEntity<String> salvarDadosUsuario(@RequestBody @Validated DadosPessoaisRequest request) {
        return ResponseEntity.ok(dadosPessoaisService.salvarDadosPessoais(request));
    }

    @GetMapping("{email}")
    public ResponseEntity<DadosPessoaisResponse> buscarUsuarioEmail(@Validated @Valid @PathVariable String email) {
        return ResponseEntity.ok(dadosPessoaisService.buscarUsuario(email));
    }
}
