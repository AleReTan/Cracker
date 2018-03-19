package com.netcracker.demo.service;


import com.netcracker.demo.models.OrderEntityTO;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service("orderService")
public class OrderService implements MyService<OrderEntityTO> {

    static final String ADDITION_URL = "/orders";
    @Autowired
    UncRestTemplate restTemplate;

    @Override
    public void save(OrderEntityTO object) {
        restTemplate.postForObject(ADDITION_URL, object, OrderEntityTO.class);
    }

    @Override
    public void update(OrderEntityTO object) {
        restTemplate.patchForObject(ADDITION_URL, object, OrderEntityTO.class);
    }

    @Override
    public boolean isExist(OrderEntityTO object) {
        return false;
    }

    @Override
    public void delete(OrderEntityTO object) {
    }

    public void delete(long id) {
        ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
    }

    @Override
    public List<OrderEntityTO> findAll() {
        ResponseEntity<OrderEntityTO[]> response = restTemplate.exchange(
                ADDITION_URL, HttpMethod.GET, OrderEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public OrderEntityTO findById(long id) {
        ResponseEntity<OrderEntityTO> response = restTemplate.exchange(
                ADDITION_URL + "/" + id, HttpMethod.GET, OrderEntityTO.class);
        return response.getBody();
    }
}
