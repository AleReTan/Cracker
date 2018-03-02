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


    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @ResponseBody
    public ObjectNode getJson( ) {
        ObjectNode objectNode = jsonService.getJson();
        System.out.println(objectNode.toString());
        return objectNode;
    }
}
