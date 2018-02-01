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
    public String getDrivers(Model model) {
        model.addAttribute("drivers", driverService.findAll());
        //здесь карс для того чтобы сравнить driver.carId=car.id и вывести car.model car.number
        model.addAttribute("cars", carService.findAll());
        return "drivers";
    }

    @RequestMapping(value = "/drivers/{id}/create", method = RequestMethod.POST)
    public void createDriver(@ModelAttribute DriverEntityTO driver) {

    }

    @RequestMapping(value = "/drivers/{id}/update", method = RequestMethod.POST)
    public void updateDriver(@ModelAttribute("driver") DriverEntityTO driver) {
        System.out.println(driver.toString());
        driverService.update(driver);
    }


    @RequestMapping(value = "/drivers/{id}/delete", method = RequestMethod.POST)
    public void deleteDriver() {

    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    public String getDriver(@PathVariable("id") long id, Model model) {
        model.addAttribute("driver", driverService.findById(id));
        model.addAttribute("cars", carService.findAll());
        return "driver";

    }
}
