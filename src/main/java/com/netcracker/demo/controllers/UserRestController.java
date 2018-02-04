package com.netcracker.demo.controllers;

import com.netcracker.demo.models.UserEntityTO;
import com.netcracker.demo.service.MyService;
import com.netcracker.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Для того, чтобы убедиться в работе тестового контроллера необходимо проверить наличие
* в схеме  eav таблицы Users и пользователей в ней с полями
* login: , password: без использования криптования, role:{USER, ADMIN}
* При переходе  на вкладку ("/rest/user/**") требуется роль ADMIN
* а на вкладку ("/rest/help/**") роль ("USER")
*/

@Controller
@ComponentScan
//@RequestMapping(value = "/admin")
public class UserRestController {

    private  UserService userService;

    @Autowired
    public UserRestController( UserService userService ) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(){

        return "admin";
    }

    //Отрабатываем этот контроллер
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "users";
    }


    @RequestMapping(value = "admin/users/{login}", method = RequestMethod.GET)
    public String getUserByLogin(@PathVariable("login") String login,Model model) {
        model.addAttribute("userData", userService.getUserByLogin(login));
        return "user";
    }

/*
    @RequestMapping(value = "/users/createUser", method = RequestMethod.POST)
    public void createUser(@RequestBody UserEntity u) {
        userService.;
    }

    @RequestMapping(value = "/users/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestBody UserEntity u) {
        userService.delete(u);
    }

    // Код, для фронт части для кодирования токена
    /*String originalInput = "login:pass";
    String token = "Base " + Base64.getEncoder().encodeToString(originalInput.getBytes());*/

   /* @RequestMapping(value = {"/addOrder"}, method = RequestMethod.PUT)
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
        return "redirect:/orders";
    }

*/


}

