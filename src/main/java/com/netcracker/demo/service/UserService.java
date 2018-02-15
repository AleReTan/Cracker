package com.netcracker.demo.service;

import com.netcracker.demo.models.UserEntityTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class UserService implements MyService<UserEntityTO> {

    private RestTemplate restTemplate = new RestTemplate();
    static final String URL = "http://localhost:8082/admin/users";
//    static final String AUTH_URL = "http://localhost:8082/auth";

    @Override
    public void save(UserEntityTO object) {
        HttpEntity<UserEntityTO> entity = new HttpEntity<>(object,getHeaders(getToken("Irina","1234")));
        restTemplate.postForObject(URL,entity,UserEntityTO.class);
    }

    @Override
    public void update(UserEntityTO object) {
        HttpEntity<UserEntityTO> entity = new HttpEntity<>(getHeaders(getToken("Irina","1234")));
        ResponseEntity<UserEntityTO[]> response = restTemplate.exchange(
                URL + "/" + object.getLogin(), HttpMethod.DELETE, entity, UserEntityTO[].class);
    }

    @Override
    public boolean isExist(UserEntityTO object) {
        return false;
    }

    @Override
    public void delete(UserEntityTO object) {

    }

    @Override
    public List<UserEntityTO> findAll() {
        HttpEntity<UserEntityTO> entity = new HttpEntity<>(getHeaders(getToken("Irina","1234")));
        ResponseEntity<UserEntityTO[]> response = restTemplate.exchange(
                URL, HttpMethod.GET, entity, UserEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public void delete(String login) {
        HttpEntity<String> entity = new HttpEntity<>(getHeaders(getToken("Irina","1234")));
        ResponseEntity<String> response = restTemplate.exchange(
                URL + "/" + login, HttpMethod.DELETE, entity, String.class);
    }

    public UserEntityTO getUserByLogin(String login){
        HttpEntity<UserEntityTO> entity = new HttpEntity<>(getHeaders(getToken("Irina","1234")));
        ResponseEntity<UserEntityTO> response = restTemplate.exchange(URL +"/"+ login, HttpMethod.GET, entity, UserEntityTO.class);
        return response.getBody();
    }


    //Захардкожено
    private HttpHeaders getHeaders(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }

    //Захардкожено
    private String getToken(String login,String password){
        String originalInput = login + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(originalInput.getBytes());
    }








































/*
* Осуществляет запрос к серверу с целью выявить аутентификацию пользователя.
*/
    /*public boolean Authenticate(String token){
        HttpEntity<String> entity = new HttpEntity<String>(getHeaders(token));
        ResponseEntity<String> response = restTemplate.exchange(AUTH_URL , HttpMethod.GET, entity, String.class);
        HttpStatus status = response.getStatusCode();
        if(status != HttpStatus.OK){
            if (status == HttpStatus.UNAUTHORIZED){
                return false;
            }
            else {
                //здесь надо подумать
            }
        }
        //засовываем данные в куки
        return true;
    }*/
}
