package com.netcracker.demo.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("jsonService")
public class JsonService {

    static final String ADDITION_URL = "/allCarsJson";
    @Autowired
    UncRestTemplate restTemplate;

    public ObjectNode getJson(HttpServletRequest req, HttpServletResponse res) {

        ResponseEntity<ObjectNode> response = restTemplate.exchange(req, res,
                ADDITION_URL, HttpMethod.GET, ObjectNode.class);
        return (response == null) ? null :response.getBody();
    }

}
