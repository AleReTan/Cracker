package com.netcracker.demo.controllers;


import com.netcracker.demo.models.OrderEntityTO;
import com.netcracker.demo.service.DriverService;
import com.netcracker.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


@Controller
@ComponentScan
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        model.addAttribute("orders", orderService.findAll(httpServletRequest, httpServletResponse));
        return "/order-like/orders";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute OrderEntityTO order, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        order.setOrderStartTime(LocalDateTime.now().toString());
        order.setOrderEndTime("Не закончен");
        System.out.println(order);
        orderService.save(httpServletRequest, httpServletResponse, order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.GET)
    public String createOrderPage(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        model.addAttribute("drivers", driverService.findAll(httpServletRequest, httpServletResponse));//свободные водилы, прочекать че делать когда нет свободных
        return "/order-like/createOrder";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PATCH)
    public String updateOrder(@ModelAttribute OrderEntityTO order, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        orderService.update(httpServletRequest, httpServletResponse, order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable("id") long id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        orderService.delete(httpServletRequest, httpServletResponse, id);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public String getOrder(@PathVariable("id") long id, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        model.addAttribute("order", orderService.findById(httpServletRequest, httpServletResponse, id));
        model.addAttribute("drivers", driverService.findAll(httpServletRequest, httpServletResponse));
        return "/order-like/order";
    }
}
