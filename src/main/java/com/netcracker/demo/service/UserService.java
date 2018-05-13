package com.netcracker.demo.service;


import com.netcracker.demo.models.UserEntityTO;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Service("UserService")
public class UserService implements DispatcherService<UserEntityTO> {

    @Autowired
    private UncRestTemplate restTemplate;
    private static final String ADDITION_URL = "/admin/users";


    @Override
    public void save(HttpServletRequest req, HttpServletResponse res, UserEntityTO object) {
        restTemplate.postForObject(req, res, ADDITION_URL, object, UserEntityTO.class);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse res, UserEntityTO object) {

        restTemplate.patchForObject(req, res, ADDITION_URL + "/" + object.getLogin(), object, UserEntityTO.class);
    }

    @Override
    public boolean isExist(UserEntityTO object) {
        return false;
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res, UserEntityTO object) {

    }

    @Override
    public List<UserEntityTO> findAll(HttpServletRequest req, HttpServletResponse res) {

        ResponseEntity<UserEntityTO[]> response = restTemplate.exchange(req, res,
                ADDITION_URL, HttpMethod.GET, UserEntityTO[].class);
        return (response == null) ? null : Arrays.asList(response.getBody());
    }

    public void delete(HttpServletRequest req, HttpServletResponse res, String login) {

        ResponseEntity<String> response = restTemplate.exchange(req, res, ADDITION_URL + "/" + login, HttpMethod.DELETE, String.class);
    }

    public UserEntityTO getUserByLogin(HttpServletRequest req, HttpServletResponse res, String login) {
        ResponseEntity<UserEntityTO> response = restTemplate.exchange(req, res, ADDITION_URL + "/" + login, HttpMethod.GET, UserEntityTO.class);
        return (response == null) ? null : response.getBody();
    }

}
