package com.netcracker.demo.service;


import com.netcracker.demo.models.UserEntityTO;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("UserService")
public class UserService implements MyService<UserEntityTO> {

    @Autowired
    private UncRestTemplate restTemplate;
    private static final String ADDITION_URL = "/admin/users";


    @Override
    public void save(UserEntityTO object) {
        restTemplate.postForObject(ADDITION_URL,object,UserEntityTO.class);
    }

    @Override
    public void update(UserEntityTO object) {
        restTemplate.patchForObject(ADDITION_URL + "/" + object.getLogin(),object,UserEntityTO.class);
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
        ResponseEntity<UserEntityTO[]> response = restTemplate.exchange(
                ADDITION_URL, HttpMethod.GET, UserEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public void delete(String login) {
        ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL + "/" + login, HttpMethod.DELETE, String.class);
    }

    public UserEntityTO getUserByLogin(String login){
        ResponseEntity<UserEntityTO> response = restTemplate.exchange(ADDITION_URL +"/"+ login, HttpMethod.GET, UserEntityTO.class);
        return response.getBody();
    }

}
