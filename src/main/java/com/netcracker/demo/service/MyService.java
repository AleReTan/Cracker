package com.netcracker.demo.service;


import java.util.List;

public interface MyService<T> {
    void save(T object);

    void update(T object);

    boolean isExist(T object);

    void delete(T object);

    List<T> findAll();
}