package com.example.takeiteasy.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping()
@AllArgsConstructor
@Slf4j
public class LoginController {

    @GetMapping(value = {"login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
//    @PostMapping("login")
//    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){
//        Boolean userExist = LoginService.login(email,password);
//        if(userExist){
//            return "main";
//        }
//        else{
//            modelMap.addAttribute("msg","Invalid credentials");
//        }
//        return "login";
//    }
}
