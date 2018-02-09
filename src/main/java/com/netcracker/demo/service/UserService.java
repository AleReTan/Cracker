package com.netcracker.demo.service;

import com.netcracker.demo.models.DriverEntityTO;
import com.netcracker.demo.models.UserEntityTO;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class UserService implements MyService<UserEntityTO> {

    RestTemplate restTemplate = new RestTemplate();
    static final String URL = "http://localhost:8082/admin/users";

    public HttpHeaders getHeaders(String login,String password){
        HttpHeaders headers = new HttpHeaders();
        String originalInput = login + ":" + password;
        String token = "Basic " + Base64.getEncoder().encodeToString(originalInput.getBytes());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }

    @Override
    public void save(UserEntityTO object) {
        HttpEntity<UserEntityTO> entity = new HttpEntity<UserEntityTO>(object,getHeaders("Irina","1234"));
        restTemplate.postForObject(URL,entity,UserEntityTO.class);
    }

    @Override
    public void update(UserEntityTO object) {
        HttpEntity<UserEntityTO> entity = new HttpEntity<UserEntityTO>(getHeaders("Irina","1234"));
        ResponseEntity<UserEntityTO[]> response = restTemplate.exchange(
                URL + "/" + object.getLogin(), HttpMethod.DELETE, entity, UserEntityTO[].class);
    }

    @Override
    public boolean isExist(UserEntityTO object) {
        return false;
    }

    @Override
    public void delete(UserEntityTO object) {
        HttpEntity<UserEntityTO> entity = new HttpEntity<UserEntityTO>(getHeaders("Irina","1234"));
        ResponseEntity<UserEntityTO[]> response = restTemplate.exchange(
                URL + "/" + object.getLogin(), HttpMethod.DELETE, entity, UserEntityTO[].class);
    }

    @Override
    public List<UserEntityTO> findAll() {
        HttpEntity<UserEntityTO> entity = new HttpEntity<UserEntityTO>(getHeaders("Irina","1234"));
        ResponseEntity<UserEntityTO[]> response = restTemplate.exchange(
                URL, HttpMethod.GET, entity, UserEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public UserEntityTO getUserByLogin(String login){
        HttpEntity<UserEntityTO> entity = new HttpEntity<UserEntityTO>(getHeaders("Irina","1234"));
        ResponseEntity<UserEntityTO> response = restTemplate.exchange(URL +"/"+ login, HttpMethod.GET, entity, UserEntityTO.class);
        return response.getBody();
    }

}
