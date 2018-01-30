package com.netcracker.demo.service;

import com.netcracker.demo.models.CarEntityTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("carService")
public class CarService implements MyService<CarEntityTO> {

    RestTemplate restTemplate = new RestTemplate();
    static final String URL = "http://localhost:8082/cars";


    @Override
    public void save(CarEntityTO object) {

    }

    @Override
    public void update(CarEntityTO object) {

    }

    @Override
    public boolean isExist(CarEntityTO object) {
        return false;
    }

    @Override
    public void delete(CarEntityTO object) {

    }

    @Override
    public List<CarEntityTO> findAll() {
        ResponseEntity<CarEntityTO[]> response = restTemplate.getForEntity(
                URL, CarEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public CarEntityTO findById(long id) {
        ResponseEntity<CarEntityTO> response = restTemplate.getForEntity(URL + "/" + id, CarEntityTO.class);
        return response.getBody();
    }
}
