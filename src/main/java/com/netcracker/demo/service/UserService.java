package com.netcracker.demo.service;

import com.netcracker.demo.models.DriverEntityTO;
import com.netcracker.demo.models.UserEntityTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements MyService<UserEntityTO> {
    RestTemplate restTemplate = new RestTemplate();
    static final String URL_SERVER = "http://localhost:8082";

    @Override
    public void save(UserEntityTO object) {

    }

    @Override
    public void update(UserEntityTO object) {

    }

    @Override
    public boolean isExist(UserEntityTO object) {
        return false;
    }

    @Override
    public void delete(UserEntityTO object) {
       /* final String uri = URL_SERVER + ;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject( uri, object, UserEntityTO.class);*/
    }

    @Override
    public List<UserEntityTO> findAll() {
        String url = URL_SERVER + "/admin/users";
        ResponseEntity<UserEntityTO[]> response = restTemplate.getForEntity(
                url, UserEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public UserEntityTO getUserByLogin(String login){
        String url = URL_SERVER + "/admin/users/" + login;
        ResponseEntity<UserEntityTO> response = restTemplate.getForEntity(
                url, UserEntityTO.class);
        return response.getBody();
    }

}
