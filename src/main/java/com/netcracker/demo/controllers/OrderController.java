package com.netcracker.demo.controllers;


import com.netcracker.demo.models.Order;
import com.netcracker.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@ComponentScan
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> listAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
   public String index(){

        return "index";
    }
    @RequestMapping(value = {"/addOrder"}, method = RequestMethod.GET)
    public String createUserPage() {
        return "create";
    }


    @RequestMapping(value = {"/addOrder"}, method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("orders") Order order) throws Exception {
       orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @RequestMapping(value = {"/addOrder"}, method = RequestMethod.PUT)
    public ResponseEntity<String> createOrder(@RequestBody Order order)
    {
        System.out.println(order);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
