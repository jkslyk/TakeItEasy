package com.example.takeiteasy.controllers;

import com.example.takeiteasy.services.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping()
@AllArgsConstructor
@Slf4j
public class LoginController {

    @GetMapping(value = {"login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("get the login");
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @PostMapping("login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){
        Boolean userExist = LoginService.login(email,password);
        if(userExist){
            return "main";
        }
        else{
            modelMap.addAttribute("msg","Invalid credentials");
        }
        return "login";
    }
}
