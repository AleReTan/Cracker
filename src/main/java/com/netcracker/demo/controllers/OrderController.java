package com.netcracker.demo.controllers;


import com.netcracker.demo.models.OrderTO;
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

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String listAllOrders(Model model) {

       List<OrderTO> orders;
        // //= orderService.findAllOrders();
        model.addAttribute("orders", orderService.findAllOrders());
       /* if (orders.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<List<OrderTO>>(orders, HttpStatus.OK);
    }*/
       return "orders";
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){

        return "index";
    }


    @RequestMapping(value = {"/addOrder"}, method = RequestMethod.PUT)
    public OrderTO createOrder(@RequestBody OrderTO order)
    {
        System.out.println(order.toString());
        return order;
    }
    @RequestMapping(value = {"/addOrder"}, method = RequestMethod.GET)
    public String createUserPage() {
        return "create";
    }


    @RequestMapping(value = {"/addOrder"}, method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("orders") OrderTO order  ) throws Exception {

        order.setTimeOrder(LocalDate.now());
        orderService.saveOrder(order);
        System.out.println(orderService.findByLastName("Я").toString());
        return "redirect:/orders";
    }

    @RequestMapping( value = {"/orders/{id}"}, method = RequestMethod.GET)
    public String getById(@PathVariable("id") long id, Model model){

        model.addAttribute("order", orderService.findById(id));
        return "showUser";
    }
}
