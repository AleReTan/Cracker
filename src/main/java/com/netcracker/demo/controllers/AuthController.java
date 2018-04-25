package com.netcracker.demo.controllers;

import com.netcracker.demo.models.AuthThreadLocalTO;
import com.netcracker.demo.models.UserEntityTO;
import com.netcracker.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String getAuth(@RequestBody UserEntityTO userEntityTO, HttpServletRequest request, HttpServletResponse response) {
        AuthThreadLocalTO.setAuth(authService.getToken(userEntityTO.getLogin(), userEntityTO.getPassword()));
        return authService.getRole(request, response);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void getLogout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return "redirect:/login";
    }

    @RequestMapping(value = "/errorMSG", method = RequestMethod.GET)
    public String getErrorPage(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String message = (httpServletRequest.getAttribute("HttpStatus") == null) ? Integer.toString(httpServletResponse.getStatus()) : httpServletRequest.getAttribute("HttpStatus").toString();
        model.addAttribute("errorMessage", message);
        return "/authentification/errorPage";
    }

}
