package com.netcracker.demo.controllers;


import com.netcracker.demo.models.OrderEntityTO;
import com.netcracker.demo.service.DriverService;
import com.netcracker.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Controller
@ComponentScan
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("drivers", driverService.findAll());
        return "/order-like/orders";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute OrderEntityTO order) {
        order.setOrderStartTime(LocalDateTime.now().toString());
        order.setOrderEndTime("Не закончен");
        System.out.println(order);
        orderService.save(order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.GET)
    public String createOrderPage(Model model) {
        model.addAttribute("drivers", driverService.findAllAvailableDrivers());//свободные водилы, прочекать че делать когда нет свободных
        return "/order-like/createOrder";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PATCH)
    public String updateOrder(@ModelAttribute OrderEntityTO order) {
        orderService.update(order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable("id") long id) {
        orderService.delete(id);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public String getOrder(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("drivers", driverService.findAll());
        return "/order-like/order";
    }
}
