package com.netcracker.demo.service;

import com.netcracker.demo.models.DriverEntityTO;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Service("driverService")
public class DriverService implements MyService<DriverEntityTO> {

    static final String ADDITION_URL = "/drivers";
    @Autowired
    UncRestTemplate restTemplate;

    @Override
    public void save(HttpServletRequest req, HttpServletResponse res, DriverEntityTO object) {
        restTemplate.postForObject(req, res, ADDITION_URL, object, DriverEntityTO.class);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse res, DriverEntityTO object) {
        restTemplate.patchForObject(req, res, ADDITION_URL, object, DriverEntityTO.class);
    }

    @Override
    public boolean isExist(DriverEntityTO object) {
        return false;
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res, DriverEntityTO object) {

    }

    public void delete(HttpServletRequest req, HttpServletResponse res, long id) {

        ResponseEntity<String> response = restTemplate.exchange(req, res, ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
    }

    @Override
    public List<DriverEntityTO> findAll(HttpServletRequest req, HttpServletResponse res) {

        ResponseEntity<DriverEntityTO[]> response = restTemplate.exchange(req, res,
                ADDITION_URL, HttpMethod.GET, DriverEntityTO[].class);
        return (response == null)? null: Arrays.asList(response.getBody());
    }

    public DriverEntityTO findById(HttpServletRequest req, HttpServletResponse res, long id) {

        ResponseEntity<DriverEntityTO> response = restTemplate.exchange(req, res,
                ADDITION_URL + "/" + id, HttpMethod.GET, DriverEntityTO.class);
        return (response == null) ? null : response.getBody();
    }

    public List<DriverEntityTO> findAllAvailableDrivers(HttpServletRequest req, HttpServletResponse res) {
        ResponseEntity<DriverEntityTO[]> response = restTemplate.exchange(req, res,
                ADDITION_URL + "/" + "available", HttpMethod.GET, DriverEntityTO[].class);
        return (response == null) ? null : Arrays.asList(response.getBody());
    }

}
