package com.netcracker.demo.service;


import com.netcracker.demo.models.UserEntityTO;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Service("UserService")
public class UserService implements MyService<UserEntityTO> {

    @Autowired
    private UncRestTemplate restTemplate;
    private static final String ADDITION_URL = "/admin/users";


    @Override
    public void save( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserEntityTO object) {
       try{
        restTemplate.postForObject(ADDITION_URL,object,UserEntityTO.class);
       } catch (HttpStatusCodeException e){
           AuthService.sendRedirectIfError( e, httpServletRequest, httpServletResponse);
       }
    }

    @Override
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserEntityTO object) {
        try{
        restTemplate.patchForObject(ADDITION_URL + "/" + object.getLogin(),object,UserEntityTO.class);
        } catch (HttpStatusCodeException e){
            AuthService.sendRedirectIfError( e, httpServletRequest, httpServletResponse);
        }
        }

    @Override
    public boolean isExist(UserEntityTO object) {
        return false;
    }

    @Override
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserEntityTO object) {

    }

    @Override
    public List<UserEntityTO> findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try{
            ResponseEntity<UserEntityTO[]> response = restTemplate.exchange(
                    ADDITION_URL, HttpMethod.GET, UserEntityTO[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpStatusCodeException e){
            AuthService.sendRedirectIfError( e, httpServletRequest, httpServletResponse);
            return null;
        }
    }

    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String login) {
       try{
        ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL + "/" + login, HttpMethod.DELETE, String.class);
       } catch (HttpStatusCodeException e){
           AuthService.sendRedirectIfError( e, httpServletRequest, httpServletResponse);
       }
       }

    public UserEntityTO getUserByLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String login){
       try {
           ResponseEntity<UserEntityTO> response = restTemplate.exchange(ADDITION_URL + "/" + login, HttpMethod.GET, UserEntityTO.class);
           return response.getBody();
       } catch (HttpStatusCodeException e){
        AuthService.sendRedirectIfError( e, httpServletRequest, httpServletResponse);
        return null;
    }
    }

}
