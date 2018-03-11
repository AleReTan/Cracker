package com.netcracker.demo.utility;


import com.netcracker.demo.models.AuthThreadLocalTO;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Component
public class UncRestTemplate {

    public static final String BASE_URL = "http://localhost:8082";

    private RestTemplate restTemplate;

    public UncRestTemplate() {
        this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()));
    }

    public <T> ResponseEntity<T> exchange(String additionUrl,
                                          HttpMethod method,
                                          Class<T> responseType,
                                          Object... uriVariables) throws RestClientException {
        HttpEntity<String> entity = new HttpEntity<>(addHeaders());

        return restTemplate.exchange(BASE_URL + additionUrl, method, entity, responseType, uriVariables);
    }


    public <T> T postForObject(String additionUrl,
                               T requestBody,
                               Class<T> responseType) throws RestClientException {
        HttpEntity<T> entity = new HttpEntity<>(requestBody, addHeaders());
        return restTemplate.postForObject(BASE_URL + additionUrl, entity, responseType);
    }

    public <T> T patchForObject(String additionUrl,
                                T requestBody,
                                Class<T> responseType) throws RestClientException {
        HttpEntity<T> entity = new HttpEntity<>(requestBody, addHeaders());
        return restTemplate.patchForObject(BASE_URL + additionUrl, entity, responseType);
    }


    //остальные методы так же

    private HttpHeaders addHeaders() {
        HttpHeaders headers = new HttpHeaders();
       // String originalInput = "Irina:1234";//тут вызывается сервис владеющий данными о сессии
       // String token = "Base " + Base64.getEncoder().encodeToString(originalInput.getBytes());
        String token = AuthThreadLocalTO.getAuth();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        AuthThreadLocalTO.remove();
        return headers;
    }
}
