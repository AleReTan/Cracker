package com.netcracker.demo.service;


import com.netcracker.demo.UncRestTemplate;
import com.netcracker.demo.models.DriverEntityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("driverService")
public class DriverService implements MyService<DriverEntityTO> {

    static final String ADDITION_URL = "/drivers";
    @Autowired
    UncRestTemplate restTemplate;

    @Override
    public void save(DriverEntityTO object) {
        restTemplate.postForObject(ADDITION_URL, object, DriverEntityTO.class);
    }

    @Override
    public void update(DriverEntityTO object) {
        restTemplate.patchForObject(ADDITION_URL, object, DriverEntityTO.class);
    }

    @Override
    public boolean isExist(DriverEntityTO object) {
        return false;
    }

    @Override
    public void delete(DriverEntityTO object) {

    }

    public void delete(long id) {
        ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
    }

    @Override
    public List<DriverEntityTO> findAll() {
        ResponseEntity<DriverEntityTO[]> response = restTemplate.exchange(
                ADDITION_URL, HttpMethod.GET, DriverEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public DriverEntityTO findById(long id) {
        ResponseEntity<DriverEntityTO> response = restTemplate.exchange(
                ADDITION_URL + "/" + id, HttpMethod.GET, DriverEntityTO.class);
        return response.getBody();
    }

}
