package com.netcracker.demo.service;


import com.netcracker.demo.models.Order;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Order> orders;

    static{
        orders = populateDummyOrders();
    }

    public List<Order> findAllOrders() {
        return orders;
    }

    public Order findById(long id) {
        for(Order order : orders){
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

    public Order findByLastName(String name) {
        for(Order order : orders){
            if(order.getLastName().equalsIgnoreCase(name)){
                return order;
            }
        }
        return null;
    }

    public void saveOrder(Order order) {
        final String uri = "http://localhost:8082/ot/c";
        order.setId(counter.incrementAndGet());
        orders.add(order);
        RestTemplate restTemplate = new RestTemplate();
        Order result = restTemplate.postForObject( uri, order, Order.class);
    }

    public void updateOrder(Order order) {
        int index = orders.indexOf(order);
       orders.set(index, order);
    }

    public void deleteOrderById(long id) {

        for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext(); ) {
            Order order = iterator.next();
            if (order.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isOrderExist(Order order) {
        return findByLastName(order.getLastName())!=null;
    }

    public void deleteAllOrders(){
        orders.clear();
    }

    private static List<Order> populateDummyOrders(){
        List<Order> orders =new ArrayList<Order>();
        orders.add(new Order(counter.incrementAndGet(),"Sam","Valeev", "Perevertkina, 35", "89909090923", "Mercedes", "blue", "A567AA136", "getsdd@gmail.com"));
        return orders;
    }

}
