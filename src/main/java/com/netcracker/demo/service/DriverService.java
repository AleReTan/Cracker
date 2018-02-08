package com.netcracker.demo.service;


import com.netcracker.demo.models.DriverEntityTO;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service("driverService")
public class DriverService implements MyService<DriverEntityTO> {
    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()));
    static final String URL = "http://localhost:8082/drivers";

    @Override
    public void save(DriverEntityTO object) {
        HttpEntity<DriverEntityTO> entity = new HttpEntity<DriverEntityTO>(object, addHeaders());
        restTemplate.postForObject(URL, entity, DriverEntityTO.class);
    }

    @Override
    public void update(DriverEntityTO object) {
        HttpEntity<DriverEntityTO> entity = new HttpEntity<DriverEntityTO>(object, addHeaders());
        restTemplate.patchForObject(URL, entity, DriverEntityTO.class);
    }

    @Override
    public boolean isExist(DriverEntityTO object) {
        return false;
    }

    @Override
    public void delete(DriverEntityTO object) {

    }

    public void delete(long id) {

        HttpEntity<String> entity = new HttpEntity<>(addHeaders());
        //restTemplate.delete(URL + "/" + id); //не работает, хз почему
        ResponseEntity<String> response = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
    }

    @Override
    public List<DriverEntityTO> findAll() {
        HttpEntity<String> entity = new HttpEntity<String>(addHeaders());
        ResponseEntity<DriverEntityTO[]> response = restTemplate.exchange(
                URL, HttpMethod.GET, entity, DriverEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public DriverEntityTO findById(long id) {
        HttpEntity<String> entity = new HttpEntity<String>(addHeaders());
        ResponseEntity<DriverEntityTO> response = restTemplate.exchange(
                URL + "/" + id, HttpMethod.GET, entity, DriverEntityTO.class);
        return response.getBody();
    }

    @SuppressWarnings("Duplicates")
    private HttpHeaders addHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String originalInput = "Irina:1234";
        String token = "Basic " + Base64.getEncoder().encodeToString(originalInput.getBytes());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }
}
