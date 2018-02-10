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

    /*
    Получение всех автомобилей, выводит их в виде таблицы на страницу Cars.ftl
     */
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars";
    }


    @RequestMapping(value = {"/cars/addCar"}, method = RequestMethod.GET)
    public String createUserPage() {
        return "addCar";
    }

    @RequestMapping(value = {"/cars/addCar"}, method = RequestMethod.POST)
    public String addCar(@ModelAttribute CarEntityTO car  ) throws Exception {
        carService.save(car);
        return "redirect:/cars";
    }
    /**
     * Меняем поле какое-то и обновленнеая машина записывается на back
     */
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.PATCH)
    public String updateCars(@ModelAttribute CarEntityTO car) {
        carService.update(car);
        return "redirect:/cars";
    }

    /**
     * Удаление
     */
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public String deleteCars(@PathVariable("id") long id) {
        carService.delete(id);
        return "redirect:/cars";
    }

    /**
     * Получение машины
     */
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public String getCars(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carService.findById(id));
        return "car";
    }
}
