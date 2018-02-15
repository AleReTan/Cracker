package com.netcracker.demo.controllers;

import com.netcracker.demo.models.DriverEntityTO;
import com.netcracker.demo.models.UserEntityTO;
import com.netcracker.demo.service.MyService;
import com.netcracker.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class UserController {

    private  UserService userService;

    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String admin(){
        return "/admin";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "/user-like/users";
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
    public String getUserByLogin(@PathVariable("login") String login,Model model) {
        model.addAttribute("userData", userService.getUserByLogin(login));
        return "/user-like/user";
    }

    @RequestMapping(value = {"/users/createUser"}, method = RequestMethod.GET)
    public String createUserPage() {
        return "/user-like/createUser";
    }

    @RequestMapping(value = "/users/createUser", method = RequestMethod.POST)
    public String createUsers(@ModelAttribute UserEntityTO u) {
        userService.save(u);
        return  "redirect:/admin/users";
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.POST)
    public String deleteDriver(@PathVariable("login") String login) {
        userService.delete(login);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/users/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestBody UserEntityTO u) {
        userService.delete(u);
    }
}

