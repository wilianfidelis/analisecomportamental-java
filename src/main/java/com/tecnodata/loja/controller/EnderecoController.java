package com.tecnodata.loja.controller;

import com.tecnodata.loja.response.EnderecoResponse;
import com.tecnodata.loja.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/endereco")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<EnderecoResponse> buscarCep(@Validated @Valid @PathVariable String cep) {
        return ResponseEntity.ok(enderecoService.buscarCep(cep));
    }
}
