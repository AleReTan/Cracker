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
//    ClientHttpRequestFactory requestFactory = new
//            HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
//    RestTemplate restTemplate = new RestTemplate(requestFactory);
    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()));
    static final String URL = "http://localhost:8082/drivers";

    @Override
    public void save(DriverEntityTO object) {
        HttpHeaders headers = new HttpHeaders();
        String originalInput = "Irina:1234";
        String token = "Basic " + Base64.getEncoder().encodeToString(originalInput.getBytes());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity<DriverEntityTO> entity = new HttpEntity<DriverEntityTO>(object,headers);
        restTemplate.postForObject(URL,entity,DriverEntityTO.class);
    }

    @Override
    public void update(DriverEntityTO object) {
        restTemplate.patchForObject(URL, object, DriverEntityTO.class);
    }

    @Override
    public boolean isExist(DriverEntityTO object) {
        return false;
    }

    @Override
    public void delete(DriverEntityTO object) {
        restTemplate.delete(URL + "/" + object.getId(), object, DriverEntityTO.class);
    }

    @Override
    public List<DriverEntityTO> findAll() {
        HttpHeaders headers = new HttpHeaders();
        String originalInput = "Irina:1234";
        String token = "Basic " + Base64.getEncoder().encodeToString(originalInput.getBytes());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);

//        ResponseEntity<DriverEntityTO[]> response = restTemplate.getForEntity(
//                URL, DriverEntityTO[].class);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<DriverEntityTO[]> response = restTemplate.exchange(
                URL, HttpMethod.GET, entity, DriverEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public DriverEntityTO findById(long id) {
        HttpHeaders headers = new HttpHeaders();
        String originalInput = "Irina:1234";
        String token = "Basic " + Base64.getEncoder().encodeToString(originalInput.getBytes());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<DriverEntityTO> response = restTemplate.exchange(
                URL + "/" + id, HttpMethod.GET, entity, DriverEntityTO.class);
        //ResponseEntity<DriverEntityTO> response = restTemplate.getForEntity(URL + "/" + id, DriverEntityTO.class);
        return response.getBody();
    }
}
