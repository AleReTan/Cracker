package com.netcracker.demo.controllers;

import com.netcracker.demo.models.DriverEntityTO;
import com.netcracker.demo.service.CarService;
import com.netcracker.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class DriverRestController {

    @Autowired
    DriverService driverService;
    @Autowired
    CarService carService;

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public String getOrders(Model model) {
        model.addAttribute("drivers", driverService.findAll());
        //здесь карс для того чтобы сравнить driver.carId=car.id и вывести car.model car.number
        model.addAttribute("cars", carService.findAll());
        return "drivers";
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.POST)
    public void createOrder() {

    }
//хз пока че да как
    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.PATCH)
    public void updateOrder(@ModelAttribute DriverEntityTO driver) {
        driverService.update(driver);
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.DELETE)
    public void deleteOrder() {

    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    public String getOrder(@PathVariable("id") long id, Model model) {
        model.addAttribute("driver", driverService.findById(id));
        model.addAttribute("cars", carService.findAll());
        return "driver";

    }
}
