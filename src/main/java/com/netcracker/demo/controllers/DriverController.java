package com.netcracker.demo.controllers;

import com.netcracker.demo.models.CarEntityTO;
import com.netcracker.demo.models.DriverEntityTO;
import com.netcracker.demo.service.CarService;
import com.netcracker.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Arrays;

@Controller
public class DriverController {
//TODO: localhost:8080/drivers/ не подгружает скрипт, localhost:8080/drivers подгружает скрипт
    @Autowired
    DriverService driverService;
    @Autowired
    CarService carService;

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public String getDrivers(Model model) {
        model.addAttribute("drivers", driverService.findAll());
        model.addAttribute("cars", carService.findAll());
        return "/driver-like/drivers";
    }

    @RequestMapping(value = "/drivers/create", method = RequestMethod.POST)
    public String createDriver(@ModelAttribute DriverEntityTO driver) {
        driverService.save(driver);
        return "redirect:/drivers";
    }

    @RequestMapping(value = "/drivers/create", method = RequestMethod.GET)
    public String createDriverPage(Model model) {
        model.addAttribute("cars", carService.findAllAvailableCars());//свободные машины, прочекать че делать когда нет свободных
        return "/driver-like/createDriver";
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.PATCH)
    public String updateDriver(@ModelAttribute DriverEntityTO driver) {
        driverService.update(driver);
        return "redirect:/drivers";
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.DELETE)
    public String deleteDriver(@PathVariable("id") long id) {
        driverService.delete(id);
        return "redirect:/drivers";
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    public String getDriver(@PathVariable("id") long id, Model model) {
        model.addAttribute("driver", driverService.findById(id));
        model.addAttribute("cars", carService.findAllAvailableCars());
        return "/driver-like/driver";
    }


}
