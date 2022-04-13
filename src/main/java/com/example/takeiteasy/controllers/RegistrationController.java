package com.example.takeiteasy.controllers;

import com.example.takeiteasy.model.RegistrationRequest;
import com.example.takeiteasy.services.ConfirmationTokenService;
import com.example.takeiteasy.services.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
@Slf4j

public class RegistrationController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

    @Autowired(required = false)
    private final RegistrationService registerationService;
    private ConfirmationTokenService confirmationTokenService;

    @PostMapping()
    public String register(@RequestBody RegistrationRequest request) {
        return registerationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registerationService.confirmToken(token);
    }

}
