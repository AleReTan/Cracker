package com.netcracker.demo.controllers;


import com.netcracker.demo.models.OrderTO;
import com.netcracker.demo.service.DriverService;
import com.netcracker.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;


@Controller
@ComponentScan
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String listAllOrders(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        model.addAttribute("orders", orderService.findAll(httpServletRequest, httpServletResponse));
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

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PATCH)
    public String updateOrder(@ModelAttribute OrderTO order, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        orderService.update(httpServletRequest, httpServletResponse, order);
        return "redirect:/orders";
    }
    @RequestMapping(value = {"/createOrder"}, method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("orders") OrderTO order, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse  ) throws Exception {

        //order.setTimeOrder(LocalDate.now());
        orderService.save(httpServletRequest, httpServletResponse, order);
        System.out.println(order.toString());
        return "redirect:/orders";
    }

    @RequestMapping(value = {"/orders/{id}"}, method = RequestMethod.GET)
    public String getById(@PathVariable("id") long id, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        model.addAttribute("order", orderService.findById(httpServletRequest, httpServletResponse, id));
        model.addAttribute("drivers", driverService.findAll(httpServletRequest, httpServletResponse));
        return "showUser";
    }
}
