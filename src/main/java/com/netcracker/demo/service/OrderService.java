package com.netcracker.demo.service;


import com.netcracker.demo.utility.UncRestTemplate;
import com.netcracker.demo.models.OrderTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service("orderService")
public class OrderService implements MyService<OrderTO> {

    static final String ADDITION_URL = "/orders";
    @Autowired
    UncRestTemplate restTemplate;

    @Override
    public void save(OrderTO object) {
        restTemplate.postForObject(ADDITION_URL + "/" + "createOrder", object, OrderTO.class);
    }

    @Override
    public void update(OrderTO object) {
        restTemplate.patchForObject(ADDITION_URL, object, OrderTO.class);
    }

    @Override
    public boolean isExist(OrderTO object) {
        return false;
    }

    @Override
    public void delete(OrderTO object) {
        //TODO: переделать без объекта, юзать метод ниже(он сделан с uncTemplate'ом
        //restTemplate.delete(ADDITION_URL + "/" + object.getId(), object, OrderTO.class);
    }

    public void delete(long id) {
        ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
    }

    @Override
    public List<OrderTO> findAll() {
        ResponseEntity<OrderTO[]> response = restTemplate.exchange(
                ADDITION_URL, HttpMethod.GET, OrderTO[].class);
        return Arrays.asList(response.getBody());
    }

    public OrderTO findById(long id) {
        ResponseEntity<OrderTO> response = restTemplate.exchange(
                ADDITION_URL + "/" + id, HttpMethod.GET, OrderTO.class);
        return response.getBody();
    }
}
