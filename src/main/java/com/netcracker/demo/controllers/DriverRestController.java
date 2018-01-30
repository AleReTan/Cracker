package com.netcracker.demo.controllers;

import com.netcracker.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@ComponentScan
public class DriverRestController {

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public String getOrders(Model model/*,@RequestBody List<DriverEntityTO> drivers*/) {
        model.addAttribute("drivers", driverService.findAll());
        return "drivers";
    }

    @RequestMapping(value = "/drivers/createDriver", method = RequestMethod.POST)
    public void createOrder() {

    }

    @RequestMapping(value = "/drivers/updateDriver", method = RequestMethod.PATCH)
    public void updateOrder() {

    }

    @RequestMapping(value = "/drivers/deleteDriver", method = RequestMethod.DELETE)
    public void deleteOrder() {

    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    public String getOrder(@PathVariable("id") long id, Model model) {
        model.addAttribute("driver", driverService.findById(id));
        return "driver";

    }
}
