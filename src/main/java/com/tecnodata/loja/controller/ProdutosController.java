package com.tecnodata.loja.controller;

import com.tecnodata.loja.entities.Produtos;
import com.tecnodata.loja.request.ProdutoRequest;
import com.tecnodata.loja.response.ProdutosResponse;
import com.tecnodata.loja.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class ProdutosController {

    @Autowired
    ProdutosService produtosService;

    @GetMapping("/all")
    public ResponseEntity<List<ProdutosResponse>> getAll() {
        return ResponseEntity.ok(produtosService.buscarListaProdutos());
    }

    @GetMapping("{idProduto}")
    public ResponseEntity<ProdutosResponse> getProdutoId(@Validated @Valid @PathVariable String idProduto) {
        return ResponseEntity.ok(produtosService.buscarProdutoId(idProduto));
    }

    @PostMapping("/criar")
    public ResponseEntity<Produtos> criar(@RequestBody @Validated ProdutoRequest request) {
        return ResponseEntity.ok(produtosService.criarProduto(request));
    }
}
