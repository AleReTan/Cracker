package com.netcracker.demo.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netcracker.demo.models.CarEntityTO;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("jsonService")
public class JsonService {

    @Autowired
    UncRestTemplate restTemplate;

    public ObjectNode getAllDriversJson() {
        ResponseEntity<ObjectNode> response = restTemplate.exchange(
                "/" + "allDriversJson", HttpMethod.GET, ObjectNode.class);
        return response.getBody();
    }

    public ObjectNode getAvailableDriversJson() {
        ResponseEntity<ObjectNode> response = restTemplate.exchange(
                "/" + "availableDriversJson", HttpMethod.GET, ObjectNode.class);
        return response.getBody();
    }


}
