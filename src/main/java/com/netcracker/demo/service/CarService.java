package com.netcracker.demo.service;

import com.netcracker.demo.models.CarEntityTO;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service("carService")
public class CarService implements MyService<CarEntityTO> {

    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()));
    static final String URL = "http://localhost:8082/cars";


    @Override
    public void save(CarEntityTO object) {

    }

    @Override
    public void update(CarEntityTO object) {

    }

    @Override
    public boolean isExist(CarEntityTO object) {
        return false;
    }

    @Override
    public void delete(CarEntityTO object) {

    }

    @Override
    public List<CarEntityTO> findAll() {
        HttpEntity<String> entity = new HttpEntity<String>(addHeaders());
        ResponseEntity<CarEntityTO[]> response = restTemplate.exchange(
                URL, HttpMethod.GET, entity, CarEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public CarEntityTO findById(long id) {
        HttpEntity<String> entity = new HttpEntity<String>(addHeaders());
        ResponseEntity<CarEntityTO> response = restTemplate.exchange(
                URL + "/" + id, HttpMethod.GET, entity, CarEntityTO.class);
        return response.getBody();
    }

    @SuppressWarnings("Duplicates")
    private HttpHeaders addHeaders(){
        HttpHeaders headers = new HttpHeaders();
        String originalInput = "Irina:1234";
        String token = "Basic " + Base64.getEncoder().encodeToString(originalInput.getBytes());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }
}
