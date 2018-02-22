package com.netcracker.demo.service;


import com.netcracker.demo.models.OrderTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service("orderService")
public class OrderService implements MyService<OrderTO>{

    RestTemplate restTemplate = new RestTemplate();
    static final String URL = "http://localhost:8082/orders";

    @Override
    public void save(OrderTO object) {
        restTemplate.postForObject(URL + "/" + "createOrder", object, OrderTO.class);
    }

    @Override
    public void update(OrderTO object) {
        restTemplate.patchForObject(URL, object, OrderTO.class);
    }

    @Override
    public boolean isExist(OrderTO object) {
        return false;
    }

    @Override
    public void delete(OrderTO object) {
        restTemplate.delete(URL + "/" + object.getId(), object, OrderTO.class);
    }

    @Override
    public List<OrderTO> findAll() {
        ResponseEntity<OrderTO[]> response = restTemplate.getForEntity(
                URL, OrderTO[].class);
        return Arrays.asList(response.getBody());
    }

    public OrderTO findById(long id) {
        ResponseEntity<OrderTO> response = restTemplate.getForEntity(URL + "/" + id, OrderTO.class);
        return response.getBody();
    }
}
