package com.netcracker.demo.service;

import com.netcracker.demo.utility.UncRestTemplate;
import com.netcracker.demo.models.CarEntityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("carService")
public class CarService implements MyService<CarEntityTO> {

    static final String ADDITION_URL = "/cars";
    @Autowired
    UncRestTemplate restTemplate;

    @Override
    public void save(CarEntityTO car) {
        restTemplate.postForObject(ADDITION_URL, car, CarEntityTO.class);
    }

    @Override
    public void update(CarEntityTO car) {
        restTemplate.patchForObject(ADDITION_URL, car, CarEntityTO.class);
    }

    @Override
    public boolean isExist(CarEntityTO car) {
        return false;
    }

    @Override
    public void delete(CarEntityTO car) {
    }

    public void delete(long id) {
        ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
    }

    @Override
    public List<CarEntityTO> findAll() {
        ResponseEntity<CarEntityTO[]> response = restTemplate.exchange(
                ADDITION_URL, HttpMethod.GET, CarEntityTO[].class);
        return Arrays.asList(response.getBody());
    }


    public CarEntityTO findById(long id) {
        ResponseEntity<CarEntityTO> response = restTemplate.exchange(
                ADDITION_URL + "/" + id, HttpMethod.GET, CarEntityTO.class);
        return response.getBody();
    }

    public List<CarEntityTO> findAllAvailableCars() {
        ResponseEntity<CarEntityTO[]> response = restTemplate.exchange(
                ADDITION_URL + "/" + "available", HttpMethod.GET, CarEntityTO[].class);
        return Arrays.asList(response.getBody());
    }
}
