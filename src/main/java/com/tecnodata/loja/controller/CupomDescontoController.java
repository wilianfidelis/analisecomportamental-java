package com.tecnodata.loja.controller;

import com.tecnodata.loja.entities.CupomDesconto;
import com.tecnodata.loja.request.CupomDescontoRequest;
import com.tecnodata.loja.service.CupomDescontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cupom")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class CupomDescontoController {
    @Autowired
    private CupomDescontoService cupomDescontoService;

    @PostMapping
    public ResponseEntity<CupomDesconto> salvarCupom(@RequestBody CupomDescontoRequest request) {
        return ResponseEntity.ok().body(cupomDescontoService.salvarCupomDesconto(request));
    }

    @GetMapping("{codigo}")
    public ResponseEntity<CupomDesconto> buscarCupom(@PathVariable(value = "codigo") String codigo) {
        return ResponseEntity.ok().body(cupomDescontoService.buscarCupomDesconto(codigo));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CupomDesconto>> buscarCupom() {
        return ResponseEntity.ok().body(cupomDescontoService.buscarListaCupomDesconto());
    }

    @PostMapping("/inativar/{codigo}")
    public ResponseEntity<CupomDesconto> inativarCupom(@PathVariable(value = "codigo") String codigo) {
        return ResponseEntity.ok().body(cupomDescontoService.inativarCupom(codigo));
    }
}
