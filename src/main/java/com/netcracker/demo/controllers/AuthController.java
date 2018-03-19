package com.netcracker.demo.controllers;

import com.netcracker.demo.models.AuthThreadLocalTO;
import com.netcracker.demo.models.UserEntityTO;
import com.netcracker.demo.service.AuthService;
import com.netcracker.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getAuthPage() {
        return "/authentification/loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String getAuth(@RequestBody UserEntityTO userEntityTO,HttpServletRequest request, HttpServletResponse response) {
        AuthThreadLocalTO.setAuth(authService.getToken(userEntityTO.getLogin(),userEntityTO.getPassword()));
        return authService.getRole(request,response);
    }

    @RequestMapping(value = "/errorMSG", method = RequestMethod.GET)
    public String getErrorPage(Model model,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String message =  (httpServletRequest.getAttribute("HttpStatus") == null)? Integer.toString(httpServletResponse.getStatus()): httpServletRequest.getAttribute("HttpStatus").toString();
        System.out.println(message);
        model.addAttribute("errorMessage",message);
        return "/authentification/errorPage";
    }

}