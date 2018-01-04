package com.netcracker.demo.service;


import com.netcracker.demo.models.Order;


import java.util.List;

public interface OrderService {

    Order findById(long id);

    Order findByLastName(String name);

    void saveOrder(Order order);

    void updateOrder(Order order);
    void deleteOrderById(long id);

    List<Order> findAllOrders();

    void deleteAllOrders();

    boolean isOrderExist(Order order);

}
