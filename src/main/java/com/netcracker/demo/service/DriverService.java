package com.netcracker.demo.service;


import com.netcracker.demo.models.DriverEntityTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("driverService")
public class DriverService {
    RestTemplate restTemplate = new RestTemplate();
    static final String URL = "http://localhost:8082/";

    public List<DriverEntityTO> getAllDrivers() {
        ResponseEntity<DriverEntityTO[]> response = restTemplate.getForEntity(
                URL + "drivers", DriverEntityTO[].class);
        return Arrays.asList(response.getBody());
    }
}
