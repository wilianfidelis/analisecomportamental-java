package com.tecnodata.loja.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public abstract class RestApi {

    private final RestTemplate restTemplate = new RestTemplate();

    public <T> T get(Class<T> clazz, String uri, HttpHeaders header) {
        return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(header), clazz).getBody();
    }

    public <T> T post(Class<T> clazz, String uri, HttpEntity entity) {
        return restTemplate.exchange(uri, HttpMethod.POST, entity, clazz).getBody();
    }

    public <T> List<T> getList(String uri, HttpHeaders header) {
        return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(header), List.class).getBody();
    }

    public <T> List<T> postList(String uri, HttpEntity entity) {
        return restTemplate.exchange(uri, HttpMethod.POST, entity, List.class).getBody();
    }

    public <T> List<T> putList(String uri, HttpEntity entity) {
        return restTemplate.exchange(uri, HttpMethod.POST, entity, List.class).getBody();
    }

    public <T> List<T> deleteList(String uri, HttpEntity entity) {
        return restTemplate.exchange(uri, HttpMethod.POST, entity, List.class).getBody();
    }
}
