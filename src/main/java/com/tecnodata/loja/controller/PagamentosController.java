package com.tecnodata.loja.controller;

import com.tecnodata.loja.request.ConsultarBoletoRequest;
import com.tecnodata.loja.request.EmitirPagamentoRequest;
import com.tecnodata.loja.request.NotificacaoPagamentoRequest;
import com.tecnodata.loja.request.PixRequest;
import com.tecnodata.loja.response.PagamentoResponse;
import com.tecnodata.loja.response.PixResponse;
import com.tecnodata.loja.response.StatusBoletoResponse;
import com.tecnodata.loja.service.PagamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping(value = "/pagamentos")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class PagamentosController {

    @Autowired
    PagamentosService pagamentosService;

    @GetMapping
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> emitirPagamento(@RequestBody final EmitirPagamentoRequest request) throws IOException {
        return ResponseEntity.ok(pagamentosService.emitirPagamento(request));
    }

    @PostMapping("/boleto")
    public ResponseEntity<PagamentoResponse> emitirBoleto(@RequestBody final EmitirPagamentoRequest request) throws IOException {
        return ResponseEntity.ok(pagamentosService.emitirBoleto(request));
    }

    @PostMapping("/cartao")
    public ResponseEntity<PagamentoResponse> emitirCartao(@RequestBody final EmitirPagamentoRequest request) throws IOException {
        return ResponseEntity.ok(pagamentosService.emitirCompraCartao(request));
    }

    @PostMapping("/consultar-boleto")
    public ResponseEntity<StatusBoletoResponse> consultarBoleto(@RequestBody @Validated ConsultarBoletoRequest request) {
        return ResponseEntity.ok(pagamentosService.consultarBoleto(request));
    }

    @PostMapping("/gerar-pix")
    public ResponseEntity<PixResponse> emitirPix(@RequestBody @Validated PixRequest request) {
        return ResponseEntity.ok(pagamentosService.emitirPix(request));
    }

    @PostMapping("/notificacao")
    public void emitirPix(@RequestBody @Validated NotificacaoPagamentoRequest request) throws MessagingException, IOException {
        pagamentosService.notificacaoAutomaticaPagamento(request);
    }

    @PostMapping("/notificacao-api")
    public void emitirPix(@RequestParam("apiKey") String apiKey,
                          @RequestParam("transaction_id") String transaction_id,
                          @RequestParam("notification_id") String notification_id,
                          @RequestParam("notification_date") String notification_date,
                          @RequestParam("source_api") String source_api
    ) throws MessagingException, IOException {

        pagamentosService.notificacaoAutomaticaPagamento(NotificacaoPagamentoRequest.builder()
                .apiKey(apiKey)
                .transaction_id(transaction_id)
                .notification_date(notification_date.toString())
                .notification_id(notification_id)
                .source_api(source_api)
                .build());
    }
}
