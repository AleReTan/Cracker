package com.netcracker.demo.controllers;

import com.netcracker.demo.models.CarEntityTO;
import com.netcracker.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CarController {

    @Autowired
    CarService carService;

     /*
    Получение всех автомобилей, выводит их в виде таблицы на страницу Cars.ftl
     */
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getCars(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        model.addAttribute("cars", carService.findAll(httpServletRequest, httpServletResponse));
        return "/car-like/cars";
    }

    @RequestMapping(value = {"/cars/create"}, method = RequestMethod.POST)
    public String createCar(@ModelAttribute CarEntityTO car, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        carService.save(httpServletRequest, httpServletResponse, car);
        return "redirect:/cars";
    }

    @RequestMapping(value = "/cars/create", method = RequestMethod.GET)
    public String createUserPage() {
        return "/car-like/createCar";
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.PATCH)
    public String updateCars(@ModelAttribute CarEntityTO car, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        carService.update(httpServletRequest, httpServletResponse, car);
        return "redirect:/cars";
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public String deleteCars(@PathVariable("id") long id,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        carService.delete(httpServletRequest, httpServletResponse, id);
        return "redirect:/cars";
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public String getCars(@PathVariable("id") long id, Model model,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Model car = model.addAttribute("car", carService.findById( httpServletRequest,httpServletResponse, id));
        return "/car-like/car";
    }
}
