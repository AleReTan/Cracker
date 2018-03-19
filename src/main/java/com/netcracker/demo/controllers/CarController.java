package com.netcracker.demo.controllers;

import com.netcracker.demo.models.CarEntityTO;
import com.netcracker.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {

    @Autowired
    CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "/car-like/cars";
    }

    @RequestMapping(value = {"/cars/create"}, method = RequestMethod.POST)
    public String createCar(@ModelAttribute CarEntityTO car) {
        carService.save(car);
        return "redirect:/cars";
    }

    @RequestMapping(value = "/cars/create", method = RequestMethod.GET)
    public String createUserPage() {
        return "/car-like/createCar";
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.PATCH)
    public String updateCars(@ModelAttribute CarEntityTO car) {
        carService.update(car);
        return "redirect:/cars";
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public String deleteCars(@PathVariable("id") long id) {
        carService.delete(id);
        return "redirect:/cars";
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public String getCars(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carService.findById(id));
        return "/car-like/car";
    }
}
