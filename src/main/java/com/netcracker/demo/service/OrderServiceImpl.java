package com.netcracker.demo.service;


import com.netcracker.demo.models.OrderTO;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<OrderTO> orders;

    static {
        orders = populateDummyOrders();
    }

    public List<OrderTO> findAllOrders() {
        return orders;
    }

    public OrderTO findById(long id) {
        for (OrderTO order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public OrderTO findByLastName(String name) {
        for (OrderTO order : orders) {
            if (order.getLastName().equalsIgnoreCase(name)) {
                return order;
            }
        }
        return null;
    }

    public void saveOrder(OrderTO order) {
        final String uri = "http://localhost:12945/rest/order";
        order.setId(counter.incrementAndGet());
        orders.add(order);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject(uri, order, OrderTO.class);
    }

    public void updateOrder(OrderTO order) {
        int index = orders.indexOf(order);
        orders.set(index, order);
    }

    public void deleteOrderById(long id) {

        for (Iterator<OrderTO> iterator = orders.iterator(); iterator.hasNext(); ) {
            OrderTO order = iterator.next();
            if (order.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isOrderExist(OrderTO order) {
        return findByLastName(order.getLastName()) != null;
    }

    public void deleteAllOrders() {
        orders.clear();
    }

    private static List<OrderTO> populateDummyOrders() {
        List<OrderTO> orders = new ArrayList<OrderTO>();
        orders.add(new OrderTO(counter.incrementAndGet(), "Sam", "Valeev", "Perevertkina, 35", "89909090923", "Mercedes", "blue", "A567AA136", "Иванов", LocalDate.parse("2016-08-16"), LocalDate.parse("2016-08-17"), "Базовый", 2500.0));
        return orders;
    }

}
