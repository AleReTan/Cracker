package com.netcracker.demo.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface MyService<T> {
    void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, T object);

    void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,T object);

    boolean isExist(T object);

    void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,T object);

    List<T> findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}