package com.example.takeiteasy.controllers;

import com.example.takeiteasy.model.Aircraft;
import com.example.takeiteasy.services.AircraftService;
import com.example.takeiteasy.services.AirportService;
import com.example.takeiteasy.services.FlightService;
import com.example.takeiteasy.services.PassengerService;
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
public class AircraftController {

    @Autowired
    AirportService airportService;
    @Autowired
    AircraftService aircraftService;
    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passengerService;

    @GetMapping("/aircraft/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddAircraftPage(Model model) {
        model.addAttribute("aircraft", new Aircraft());
        return "new_aircrafts";
    }

    @PostMapping("/aircraft/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveAircraft(@Valid @ModelAttribute("aircraft") Aircraft aircraft, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("aircraft", new Aircraft());
            return "new_aircrafts";
        }
        aircraftService.saveAircraft(aircraft);
        model.addAttribute("aircrafts", aircraftService.getAllAircraftsPaged(0));
        model.addAttribute("currentPage", 0);
        return "aircrafts";
    }

    @GetMapping("/aircraft/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAircraft(@PathParam("aircraftId") long aircraftId, Model model){
        aircraftService.deleteAircraftById(aircraftId);
        model.addAttribute("aircrafts", aircraftService.getAllAircraftsPaged(0));
        model.addAttribute("currentPage", 0);
        return "aircrafts";
    }

    @GetMapping("/aircrafts")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAircraftsList(@RequestParam(defaultValue = "0") int pageNo, Model model) {
        model.addAttribute("aircrafts", aircraftService.getAllAircraftsPaged(pageNo));
        model.addAttribute("currentPage", pageNo);
        return "aircrafts";
    }
}
