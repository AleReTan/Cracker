package com.netcracker.demo.controllers;


import com.netcracker.demo.models.Person;
import com.netcracker.demo.models.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class PersonController {

    @Autowired
    PersonRepo personRepo;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(Model model) {

        return "index";
    }

    @RequestMapping(value = {"/persons"}, method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("persons", personRepo.findAll());
        return "personsList";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String createUserPage() {
        return "create";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String addUser(@ModelAttribute("person") Person person) throws Exception {
        personRepo.save(person);
        return "redirect:/persons";
    }

}
