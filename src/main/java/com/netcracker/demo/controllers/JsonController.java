package com.netcracker.demo.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netcracker.demo.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonController {
    @Autowired
    JsonService jsonService;

    @RequestMapping(value = "/allDriversJson", method = RequestMethod.GET)
    @ResponseBody
    public ObjectNode getAllDriversJson() {
        ObjectNode objectNode = jsonService.getAllDriversJson();
        return objectNode;
    }

    @RequestMapping(value = "/availableDriversJson", method = RequestMethod.GET)
    @ResponseBody
    public ObjectNode getAvailableDriversJson() {
        ObjectNode objectNode = jsonService.getAvailableDriversJson();
        return objectNode;
    }
}
