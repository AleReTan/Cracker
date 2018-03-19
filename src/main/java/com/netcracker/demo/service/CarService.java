package com.netcracker.demo.service;

import com.netcracker.demo.utility.UncRestTemplate;
import com.netcracker.demo.models.CarEntityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Service("carService")
public class CarService implements MyService<CarEntityTO> {

    static final String ADDITION_URL = "/cars";
    @Autowired
    UncRestTemplate restTemplate;

    /*
    Добавлние машин
     */
    @Override
    public void save(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,CarEntityTO car) {
       try {
           restTemplate.postForObject(ADDITION_URL, car, CarEntityTO.class);
       }catch (HttpStatusCodeException e){
           AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
           ;
       }
    }

    @Override
    public void update(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,CarEntityTO car) {
       try {
           restTemplate.patchForObject(ADDITION_URL, car, CarEntityTO.class);
       }catch (HttpStatusCodeException e){
           AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
       }
    }

    /*
    Если существует
     */
    @Override
    public boolean isExist(CarEntityTO car) {
        return false;
    }

    @Override
    public void delete(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,CarEntityTO car) {
        //restTemplate.delete(URL + "/" + car.getId(), car, CarEntityTO.class);
    }

    public void delete(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,long id) {
        //restTemplate.delete(URL + "/" + id); //не работает, хз почему
       try {
           ResponseEntity<String> response = restTemplate.exchange(ADDITION_URL + "/" + id, HttpMethod.DELETE, String.class);
       }catch (HttpStatusCodeException e){
           AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
       }
       }

    @Override
    public List<CarEntityTO> findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ResponseEntity<CarEntityTO[]> response = restTemplate.exchange(
                ADDITION_URL, HttpMethod.GET, CarEntityTO[].class);
        return Arrays.asList(response.getBody());
    }

    public List<CarEntityTO> findAllTest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try{
            httpServletResponse.setStatus(HttpStatus.OK.value());
            ResponseEntity<CarEntityTO[]> response = restTemplate.exchange(
                    ADDITION_URL, HttpMethod.GET, CarEntityTO[].class);
            return Arrays.asList(response.getBody());
        }
        catch (HttpStatusCodeException e){
            AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
            return null;
        }

    }

    public CarEntityTO findById(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,long id) {
        try{
        ResponseEntity<CarEntityTO> response = restTemplate.exchange(
                ADDITION_URL + "/" + id, HttpMethod.GET, CarEntityTO.class);
        return response.getBody();}
        catch (HttpStatusCodeException e){
                AuthService.sendRedirectIfError(e,httpServletRequest,httpServletResponse);
                return null;
            }
    }
}
