package com.netcracker.demo.controllers;

import com.netcracker.demo.models.DriverEntityTO;
import com.netcracker.demo.service.CarService;
import com.netcracker.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class DriverController {

    @Autowired
    DriverService driverService;
    @Autowired
    CarService carService;

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public String getDrivers(Model model) {
        System.out.println("Зашли");
        model.addAttribute("drivers", driverService.findAll());
        System.out.println("После получения водителей");
        //здесь карс для того чтобы сравнить driver.carId=car.id и вывести car.model car.number
        model.addAttribute("cars", carService.findAll());
        System.out.println("Выходим");
        return "/driver-like/drivers";
    }

    @RequestMapping(value = "/drivers/newDriver", method = RequestMethod.POST)
    public void createDriver(@ModelAttribute DriverEntityTO driver) {
        System.out.println(driver);
        driverService.save(driver);
    }

    @RequestMapping(value = {"/drivers/newDriver"}, method = RequestMethod.GET)
    public String createDriverPage() {
        return "/driver-like/createDriver";
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.PATCH)
    public String updateDriver(@ModelAttribute DriverEntityTO driver) {
        driverService.update(driver);
        return "redirect:/drivers";
    }


    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.DELETE)
    public void deleteDriver() {

    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    public String getDriver(@PathVariable("id") long id, Model model) {
        model.addAttribute("driver", driverService.findById(id));
        model.addAttribute("cars", carService.findAll());
        return "/driver-like/driver";

    }
}
