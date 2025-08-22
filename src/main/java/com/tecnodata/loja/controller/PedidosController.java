package com.tecnodata.loja.controller;

import com.tecnodata.loja.entities.Pedidos;
import com.tecnodata.loja.request.ConsultarBoletoRequest;
import com.tecnodata.loja.response.PedidosResponse;
import com.tecnodata.loja.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
public class PedidosController {

    @Autowired
    PedidosService pedidosService;

    @GetMapping("/all/{cnpj}")
    public ResponseEntity<List<PedidosResponse>> allPedidosCnpj(@Validated @Valid @PathVariable String cnpj) {
        return ResponseEntity.ok(pedidosService.buscarTodosPedidosCnpj(cnpj));
    }

    @GetMapping("/all-adm/{quantidade}")
    public ResponseEntity<List<PedidosResponse>> allPedidos(@Validated @Valid @PathVariable Integer quantidade) {
        return ResponseEntity.ok(pedidosService.allPedidos(quantidade));
    }

    @PostMapping("/atualizar-pedidos")
    public ResponseEntity<List<Pedidos>> atualizarPedidos(@RequestBody @Validated List<ConsultarBoletoRequest> request) {
        return ResponseEntity.ok(pedidosService.atualizarPedidos(request));
    }
}
