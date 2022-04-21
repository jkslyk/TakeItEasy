package com.example.takeiteasy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(ModelAndView model) {
        model.addObject("title", "TakeItEasy");
        return "main";
    }

}
