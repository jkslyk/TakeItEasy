package com.example.takeiteasy.controllers;

import com.example.takeiteasy.model.RegistrationRequest;
import com.example.takeiteasy.model.User;
import com.example.takeiteasy.services.ConfirmationTokenService;
import com.example.takeiteasy.services.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping()
@AllArgsConstructor
@Slf4j

public class RegistrationController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

    @Autowired
    private final RegistrationService registerationService;
    private ConfirmationTokenService confirmationTokenService;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @GetMapping(value = "registration", produces = "application/json")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("registration", new RegistrationRequest());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "registration")
    public ModelAndView register(@Valid RegistrationRequest registration) {

        System.out.println("check");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        mv.addObject("title", "TakeItEasy");
        mv.addObject("user", registration);
        registerationService.register(registration);

        return mv;
    }

    @GetMapping("registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registerationService.confirmToken(token);
    }

//    @GetMapping(value="/admin/home")
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(auth.getName());
//        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }




}
