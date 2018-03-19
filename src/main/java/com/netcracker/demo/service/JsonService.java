package com.netcracker.demo.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netcracker.demo.models.CarEntityTO;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Service("jsonService")
public class JsonService {

    static final String ADDITION_URL = "/allCarsJson";
    @Autowired
    UncRestTemplate restTemplate;

    public ObjectNode getJson(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try{
        ResponseEntity<ObjectNode> response = restTemplate.exchange(
                ADDITION_URL, HttpMethod.GET, ObjectNode.class);
        return response.getBody();
        }
        catch (HttpStatusCodeException e){
            AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
            return null;
        }
    }

}
