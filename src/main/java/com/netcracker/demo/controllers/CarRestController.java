package com.netcracker.demo.controllers;

import com.netcracker.demo.models.CarEntityTO;
import com.netcracker.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarRestController {
    @Autowired
    CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public void getCars() {

    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.POST)
    public void createOrder() {

    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.PATCH)
    public void updateOrder(@ModelAttribute CarEntityTO car) {
        carService.update(car);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public void deleteOrder() {

    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public void getOrder(@PathVariable("id") long id) {

    }
}
