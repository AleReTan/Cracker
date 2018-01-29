package com.netcracker.demo.service;

import com.netcracker.demo.models.Order;

import java.util.List;

public interface Service <T> {
    void save(T object);
    void update(T object);
    boolean isExist(T object);
    void delete(T object);
    List<T> findAll();
}
