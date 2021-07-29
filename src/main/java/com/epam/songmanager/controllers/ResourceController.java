package com.epam.songmanager.controllers;

import com.epam.songmanager.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

//    @GetMapping("/resource")
//    public String  getAll(Model model) {
//
//        model.addAttribute("resources", resourceService.getAll());
//        return "resources";
//    }

//    @PostMapping("/resource")
//    public String  add() {
//        return "resources";
//    }
}
