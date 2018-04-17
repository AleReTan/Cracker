package com.netcracker.demo.controllers;


import com.netcracker.demo.models.DriverEntityTO;
import com.netcracker.demo.models.OrderEntityTO;
import com.netcracker.demo.service.DriverService;
import com.netcracker.demo.service.OrderService;
import com.netcracker.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(HttpServletRequest req, HttpServletResponse res, Model model) {
        model.addAttribute("orders", orderService.findAll(req, res));
        model.addAttribute("drivers", driverService.findAll(req, res));
        model.addAttribute("roles", roleService.getRole(req, res));
        return "/order-like/orders";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.POST)
    public ResponseEntity createOrder(@ModelAttribute OrderEntityTO order, HttpServletRequest req, HttpServletResponse res) {
        System.out.println(order);
        try {
            orderService.save(req, res, order);
        } catch (IllegalArgumentException e) {
            e.getMessage();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.GET)
    public String createOrderPage(Model model, HttpServletRequest req, HttpServletResponse res) {
        model.addAttribute("drivers", driverService.findAllAvailableDrivers(req, res));//свободные водилы, прочекать че делать когда нет свободных
        model.addAttribute("roles", roleService.getRole(req, res));
        return "/order-like/createOrder";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PATCH)
    public String updateOrder(@ModelAttribute OrderEntityTO order, HttpServletRequest req, HttpServletResponse res) {
        orderService.update(req, res, order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/{id}/pickclient", method = RequestMethod.PATCH)
    public String pickClient(@ModelAttribute OrderEntityTO order, HttpServletRequest req, HttpServletResponse res) {
        orderService.pickClient(req, res, order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/{id}/closeorder", method = RequestMethod.PATCH)
    public String closeOrder(@ModelAttribute OrderEntityTO order, HttpServletRequest req, HttpServletResponse res) {
        orderService.closeOrder(req, res, order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/{id}/cancelorder", method = RequestMethod.PATCH)
    public String cancelOrder(@ModelAttribute OrderEntityTO order, HttpServletRequest req, HttpServletResponse res) {
        orderService.cancelOrder(req, res, order);
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
        model.addAttribute("roles", roleService.getRole(req, res));
        System.out.println(driverService.findById(req, res, orderEntityTO.getDriverId()));
        model.addAttribute("selectedDriver", driverService.findById(req, res, orderEntityTO.getDriverId()));
        return "/order-like/order";
    }
}
