package com.example.takeiteasy.controllers;

import com.example.takeiteasy.model.Airport;
import com.example.takeiteasy.services.AirportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Controller
@RequestMapping
@AllArgsConstructor
@Slf4j
public class AirportController {

    @Autowired
    AirportService airportService;
    @GetMapping("/airport/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddAirportPage(Model model) {
        model.addAttribute("airport", new Airport());
        return "new_airports";
    }

    @PostMapping("/airport/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveAirport(@Valid @ModelAttribute("airport") Airport airport, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("airport", new Airport());
            return "new_airports";
        }
        airportService.saveAirport(airport);
        model.addAttribute("airports", airportService.getAllAirportsPaged(0));
        model.addAttribute("currentPage", 0);
        return "airports";
    }
    @GetMapping("/airport/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAirport(@PathParam("airportId") int airportId, Model model){
        airportService.deleteAirport(airportId);
        model.addAttribute("airports", airportService.getAllAirportsPaged(0));
        model.addAttribute("currentPage", 0);
        return "airports";
    }

    @GetMapping("/airports")
    public String showAirportsList(@RequestParam(defaultValue = "0") int pageNo, Model model) {
        model.addAttribute("airports", airportService.getAllAirportsPaged(pageNo));
        model.addAttribute("currentPage", pageNo);
        return "airports";
    }
}
