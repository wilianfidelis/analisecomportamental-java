package com.tecnodata.loja.service;

import com.tecnodata.loja.response.EnderecoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EnderecoService {

    private static final String NUMERO_CNPJ = "{cep}";

    @Value("${endereco.cep}")
    private String endPointCep;

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders adicionarHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public EnderecoResponse buscarCep(String cep){
        return restTemplate.exchange(this.endPointCep.replace(NUMERO_CNPJ, cep), HttpMethod.GET, new HttpEntity<>(adicionarHeader()), EnderecoResponse.class).getBody();
    }
}
