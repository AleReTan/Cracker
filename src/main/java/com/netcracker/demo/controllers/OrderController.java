package com.netcracker.demo.controllers;


import com.netcracker.demo.models.DriverEntityTO;
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
    public String getOrders(HttpServletRequest req, HttpServletResponse res, Model model) {
        model.addAttribute("orders", orderService.findAll(req, res));
        model.addAttribute("drivers", driverService.findAll(req, res));
        return "/order-like/orders";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute OrderEntityTO order, HttpServletRequest req, HttpServletResponse res) {
        orderService.save(req, res, order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.GET)
    public String createOrderPage(Model model, HttpServletRequest req, HttpServletResponse res) {
        model.addAttribute("drivers", driverService.findAllAvailableDrivers(req, res));//свободные водилы, прочекать че делать когда нет свободных
        return "/order-like/createOrder";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PATCH)
    public String updateOrder(@ModelAttribute OrderEntityTO order, HttpServletRequest req, HttpServletResponse res) {
        orderService.update(req, res, order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable("id") long id, HttpServletRequest req, HttpServletResponse res) {
        orderService.delete(req, res, id);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public String getOrder(@PathVariable("id") long id, Model model, HttpServletRequest req, HttpServletResponse res) {
        OrderEntityTO orderEntityTO = orderService.findById(req, res, id);
        model.addAttribute("order", orderEntityTO);
        model.addAttribute("drivers", driverService.findAllAvailableDrivers(req, res));
        //DriverEntityTO selectedDriver = driverService.findById(req, res, orderEntityTO.getDriverId());
        //if (selectedDriver != null)
        model.addAttribute("selectedDriver", driverService.findById(req, res, orderEntityTO.getDriverId()));
        return "/order-like/order";
    }
}
