package com.netcracker.demo.service;


import com.netcracker.demo.models.OrderTO;
import com.netcracker.demo.utility.UncRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Service("orderService")
public class OrderService implements MyService<OrderTO> {

    static final String ADDITION_URL = "/orders";
    @Autowired
    UncRestTemplate restTemplate;

    @Override
    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, OrderTO object) {
        try {
            restTemplate.postForObject(ADDITION_URL + "/" + "createOrder", object, OrderTO.class);
        } catch (HttpStatusCodeException e) {
            AuthService.sendRedirectIfError(e, httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, OrderTO object) {
        try {
            restTemplate.patchForObject(ADDITION_URL, object, OrderTO.class);
        } catch (HttpStatusCodeException e) {
            AuthService.sendRedirectIfError(e, httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public boolean isExist(OrderTO object) {
        return false;
    }

    @Override
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, OrderTO object) {
        //TODO: переделать без объекта, юзать метод ниже(он сделан с uncTemplate'ом
        //restTemplate.delete(ADDITION_URL + "/" + object.getId(), object, OrderTO.class);
    }

    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, long id) {
        try {

            ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
        } catch (HttpStatusCodeException e) {
            AuthService.sendRedirectIfError(e, httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public List<OrderTO> findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            ResponseEntity<OrderTO[]> response = restTemplate.exchange(
                    ADDITION_URL, HttpMethod.GET, OrderTO[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpStatusCodeException e) {
            AuthService.sendRedirectIfError(e, httpServletRequest, httpServletResponse);
            return null;
        }
    }

    public OrderTO findById(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, long id) {

        try {
            ResponseEntity<OrderTO> response = restTemplate.exchange(
                    ADDITION_URL + "/" + id, HttpMethod.GET, OrderTO.class);

            return response.getBody();
        } catch (HttpStatusCodeException e) {
            AuthService.sendRedirectIfError(e, httpServletRequest, httpServletResponse);
            return null;
        }
    }
}
