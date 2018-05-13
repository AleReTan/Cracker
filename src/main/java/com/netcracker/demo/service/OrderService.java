package com.netcracker.demo.service;


import com.netcracker.demo.models.OrderEntityTO;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Service("orderService")
public class OrderService implements DispatcherService<OrderEntityTO> {

    static final String ADDITION_URL = "/orders";
    @Autowired
    UncRestTemplate restTemplate;

    @Override
    public void save(HttpServletRequest req, HttpServletResponse res, OrderEntityTO object) {
        restTemplate.postForObject(req, res, ADDITION_URL, object, OrderEntityTO.class);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse res, OrderEntityTO object) {
        restTemplate.patchForObject(req, res, ADDITION_URL, object, OrderEntityTO.class);
    }

    public void pickClient(HttpServletRequest req, HttpServletResponse res, OrderEntityTO object) {
        restTemplate.patchForObject(req, res, ADDITION_URL + "/" + "pickclient", object, OrderEntityTO.class);
    }

    public void closeOrder(HttpServletRequest req, HttpServletResponse res, OrderEntityTO object) {
        restTemplate.patchForObject(req, res, ADDITION_URL + "/" + "closeorder", object, OrderEntityTO.class);
    }

    public void cancelOrder(HttpServletRequest req, HttpServletResponse res, OrderEntityTO object) {
        restTemplate.patchForObject(req, res, ADDITION_URL + "/" + "cancelorder", object, OrderEntityTO.class);
    }

    @Override
    public boolean isExist(OrderEntityTO object) {
        return false;
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse res, OrderEntityTO object) {
    }

    public void delete(HttpServletRequest req, HttpServletResponse res, long id) {
        ResponseEntity<String> response = restTemplate.exchange(req, res, ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
    }

    @Override
    public List<OrderEntityTO> findAll(HttpServletRequest req, HttpServletResponse res) {

        ResponseEntity<OrderEntityTO[]> response = restTemplate.exchange(req, res,
                ADDITION_URL, HttpMethod.GET, OrderEntityTO[].class);
        return (response == null) ? null : Arrays.asList(response.getBody());
    }

    public OrderEntityTO findById(HttpServletRequest req, HttpServletResponse res, long id) {

        ResponseEntity<OrderEntityTO> response = restTemplate.exchange(req, res,
                ADDITION_URL + "/" + id, HttpMethod.GET, OrderEntityTO.class);
        return (response == null) ? null : response.getBody();
    }
}
