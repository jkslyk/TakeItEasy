package com.example.takeiteasy.controllers;

import com.example.takeiteasy.model.RegistrationRequest;
import com.example.takeiteasy.model.User;
import com.example.takeiteasy.services.ConfirmationTokenService;
import com.example.takeiteasy.services.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping()
@AllArgsConstructor
@Slf4j

public class RegistrationController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

    @Autowired(required = false)
    private final RegistrationService registerationService;
    private ConfirmationTokenService confirmationTokenService;

    @GetMapping(value = "/registration", produces = "application/json")
    @ResponseBody
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("/registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration", consumes = {"*/*"})
    public String register(@RequestBody RegistrationRequest request, Errors errors,
                                 SessionStatus sessionStatus) {

        ModelAndView mv = new ModelAndView();
        if (errors.hasErrors()) {
            mv.setViewName("registration");
            mv.addObject("user", request);
            return "registration";
        }
        mv.setViewName("main");
        mv.addObject("title", "TakeItEasy");
        mv.addObject("user", request);
        registerationService.register(request);
        sessionStatus.setComplete();

        return "main";
    }

    @GetMapping("/registration/confirm")
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
