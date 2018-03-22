package com.netcracker.demo.service;

import com.netcracker.demo.utility.UncRestTemplate;
import com.netcracker.demo.models.CarEntityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Service("carService")
public class CarService implements MyService<CarEntityTO> {

    static final String ADDITION_URL = "/cars";
    @Autowired
    UncRestTemplate restTemplate;

    /*
    Добавлние машин
     */
    @Override
    public void save(HttpServletRequest req, HttpServletResponse res, CarEntityTO car) {
        restTemplate.postForObject(req, res, ADDITION_URL, car, CarEntityTO.class);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse res, CarEntityTO car) {
        restTemplate.patchForObject(req, res, ADDITION_URL, car, CarEntityTO.class);
    }

    /*
    Если существует
     */
    @Override
    public boolean isExist(CarEntityTO car) {
        return false;
    }

    @Override
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, CarEntityTO car) {
    }

    public void delete(HttpServletRequest req, HttpServletResponse res, long id) {
        ResponseEntity<String> response = restTemplate.exchange(req, res, ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
    }

    @Override
    public List<CarEntityTO> findAll(HttpServletRequest req, HttpServletResponse res) {

        ResponseEntity<CarEntityTO[]> response = restTemplate.exchange(req, res,
                ADDITION_URL, HttpMethod.GET, CarEntityTO[].class);
        return (response == null) ? null : Arrays.asList(response.getBody());
    }


    public CarEntityTO findById(HttpServletRequest req, HttpServletResponse res, long id) {

        ResponseEntity<CarEntityTO> response = restTemplate.exchange(req, res,
                ADDITION_URL + "/" + id, HttpMethod.GET, CarEntityTO.class);
        return (response == null) ? null : response.getBody();
    }
//TODO проверь, что передаются параметры req  res
    public List<CarEntityTO> findAllAvailableCars(HttpServletRequest req, HttpServletResponse res) {
        ResponseEntity<CarEntityTO[]> response = restTemplate.exchange(req, res,
                ADDITION_URL + "/" + "available", HttpMethod.GET, CarEntityTO[].class);
        return Arrays.asList(response.getBody());
    }
}
