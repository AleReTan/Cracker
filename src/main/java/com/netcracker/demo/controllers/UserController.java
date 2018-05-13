package com.netcracker.demo.controllers;

import com.netcracker.demo.models.UserEntityTO;
import com.netcracker.demo.service.RoleService;
import com.netcracker.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        model.addAttribute("allUsers", userService.findAll(httpServletRequest, httpServletResponse));
        model.addAttribute("roles", roleService.getRole(httpServletRequest, httpServletResponse));
        return "/user-like/users";
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
    public String getUserByLogin(@PathVariable("login") String login, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        model.addAttribute("userData", userService.getUserByLogin(httpServletRequest, httpServletResponse, login));
        model.addAttribute("roles", roleService.getRole(httpServletRequest,httpServletResponse));
        return "/user-like/user";
    }

    @RequestMapping(value = {"/users/createUser"}, method = RequestMethod.GET)
    public String createUserPage() {
        return "/user-like/createUser";
    }

    @RequestMapping(value = "/users/createUser", method = RequestMethod.POST)
    public String createUsers(@ModelAttribute UserEntityTO u, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        userService.save(httpServletRequest, httpServletResponse, u);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/drivers/{login}", method = RequestMethod.PATCH)
    public String updateDriver(@ModelAttribute UserEntityTO u, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        userService.update(httpServletRequest, httpServletResponse, u);
        return "redirect:/drivers";
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("login") String login, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        userService.delete(httpServletRequest, httpServletResponse, login);
        return "redirect:/admin/users";
    }
}