package com.netcracker.demo.controllers;

import com.netcracker.demo.models.CarEntityTO;
import com.netcracker.demo.models.DriverEntityTO;
import com.netcracker.demo.service.CarService;
import com.netcracker.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
public class DriverController {

    @Autowired
    DriverService driverService;
    @Autowired
    CarService carService;

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public String getDrivers(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        model.addAttribute("drivers", driverService.findAll(httpServletRequest, httpServletResponse));
        model.addAttribute("cars", carService.findAll(httpServletRequest, httpServletResponse));
        return "/driver-like/drivers";
    }

    @RequestMapping(value = "/drivers/create", method = RequestMethod.POST)
    public String createDriver(@ModelAttribute DriverEntityTO driver, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        driverService.save(httpServletRequest, httpServletResponse, driver);
        return "redirect:/drivers";
    }

    @RequestMapping(value = {"/drivers/create"}, method = RequestMethod.GET)
    public String createDriverPage() {
        return "/driver-like/createDriver";
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.PATCH)
    public String updateDriver(@ModelAttribute DriverEntityTO driver, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        driverService.update(httpServletRequest, httpServletResponse, driver);
        return "redirect:/drivers";
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.DELETE)
    public String deleteDriver(@PathVariable("id") long id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        driverService.delete(httpServletRequest, httpServletResponse, id);
        return "redirect:/drivers";
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    public String getDriver(@PathVariable("id") long id, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        model.addAttribute("driver", driverService.findById(httpServletRequest, httpServletResponse, id));
        model.addAttribute("cars", carService.findAll(httpServletRequest, httpServletResponse));
        return "/driver-like/driver";
    }
}
