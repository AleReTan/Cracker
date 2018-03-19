package com.netcracker.demo.service;

import com.netcracker.demo.models.AuthThreadLocalTO;
import com.netcracker.demo.utility.UncRestTemplate;
import com.netcracker.demo.models.DriverEntityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service("driverService")
public class DriverService implements MyService<DriverEntityTO> {

    static final String ADDITION_URL = "/drivers";
    @Autowired
    UncRestTemplate restTemplate;

    @Override
    public void save(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,DriverEntityTO object) {
        try {
            restTemplate.postForObject(ADDITION_URL, object, DriverEntityTO.class);

        } catch (HttpStatusCodeException e){
            AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
        }
    }

    @Override
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, DriverEntityTO object) {
        try {
            restTemplate.patchForObject(ADDITION_URL, object, DriverEntityTO.class);
        } catch (HttpStatusCodeException e){
            AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
        }
    }

    @Override
    public boolean isExist(DriverEntityTO object) {
        return false;
    }

    @Override
    public void delete(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,DriverEntityTO object) {

    }

    public void delete(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, long id) {
       try {
           ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
       } catch (HttpStatusCodeException e){
           AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
       }
       }

    @Override
    public List<DriverEntityTO> findAll(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
        try{
        ResponseEntity<DriverEntityTO[]> response = restTemplate.exchange(
                ADDITION_URL, HttpMethod.GET, DriverEntityTO[].class);
        return Arrays.asList(response.getBody());
        } catch (HttpStatusCodeException e){
            AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
            return null;
        }
    }

    public DriverEntityTO findById(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, long id) {
        try {
            ResponseEntity<DriverEntityTO> response = restTemplate.exchange(
                    ADDITION_URL + "/" + id, HttpMethod.GET, DriverEntityTO.class);
            return response.getBody();
        } catch (HttpStatusCodeException e){
            AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
            return null;
        }
    }
}
