package com.netcracker.demo.service;


import com.netcracker.demo.models.DriverEntityTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("driverService")
public class DriverService implements MyService<DriverEntityTO> {
    RestTemplate restTemplate = new RestTemplate();
    static final String URL = "http://localhost:8082/drivers";

    @Override
    public void save(DriverEntityTO object) {

    }

    @Override
    public void update(DriverEntityTO object) {
        restTemplate.patchForObject(URL + "/" + object.getId(), object, DriverEntityTO.class);
    }

    @Override
    public boolean isExist(DriverEntityTO object) {
        return false;
    }

    @Override
    public void delete(DriverEntityTO object) {

    }

    @Override
    public List<DriverEntityTO> findAll() {
        ResponseEntity<DriverEntityTO[]> response = restTemplate.getForEntity(
                URL, DriverEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public DriverEntityTO findById(long id) {
        ResponseEntity<DriverEntityTO> response = restTemplate.getForEntity(URL + "/" + id, DriverEntityTO.class);
        return response.getBody();
    }
}
