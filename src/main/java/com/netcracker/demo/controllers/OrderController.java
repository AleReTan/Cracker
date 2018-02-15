package com.netcracker.demo.controllers;


import com.netcracker.demo.models.OrderTO;
import com.netcracker.demo.service.DriverService;
import com.netcracker.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@ComponentScan
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String listAllOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
       return "orders";
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){

        return "index";
    }


    @RequestMapping(value = {"/createOrder"}, method = RequestMethod.PUT)
    public OrderTO createOrder(@RequestBody OrderTO order)
    {
        System.out.println(order.toString());
        return order;
    }
    @RequestMapping(value = {"/createOrder"}, method = RequestMethod.GET)
    public String createUserPage() {
        return "/createOrder";
    }


    @RequestMapping(value = {"/createOrder"}, method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("orders") OrderTO order  ) throws Exception {

    //    order.setTimeOrder(LocalDate.now());
        orderService.save(order);
        return "redirect:/orders";
    }

    @RequestMapping(value = {"/orders/{id}"}, method = RequestMethod.GET)
    public String getById(@PathVariable("id") long id, Model model){
        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("drivers", driverService.findAll());
        return "showUser";
    }
}
