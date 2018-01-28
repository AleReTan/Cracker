package com.netcracker.demo.service;


import com.netcracker.demo.models.OrderTO;


import java.util.List;

public interface OrderService {

    OrderTO findById(long id);

    OrderTO findByLastName(String name);

    void saveOrder(OrderTO order);

    void updateOrder(OrderTO order);
    void deleteOrderById(long id);

    List<OrderTO> findAllOrders();

    void deleteAllOrders();

    boolean isOrderExist(OrderTO order);

}
