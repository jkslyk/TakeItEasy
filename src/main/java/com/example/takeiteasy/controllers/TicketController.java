package com.example.takeiteasy.controllers;

import com.example.takeiteasy.model.Airport;
import com.example.takeiteasy.model.Flight;
import com.example.takeiteasy.model.Passenger;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
@Slf4j
public class TicketController {

    @Autowired
    AirportService airportService;
    @Autowired
    AircraftService aircraftService;
    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passengerService;

    @GetMapping("/flight/book")
    @PreAuthorize("hasRole('ADMIN')")
    public String showBookFlightPage(Model model) {
        model.addAttribute("airports", airportService.getAllAirports());
        return "book_flight";
    }

    @PostMapping("/flight/book")
    @PreAuthorize("hasRole('ADMIN')")
    public String searchFlightToBook(@RequestParam("departureAirport") int departureAirport,
                                     @RequestParam("destinationAirport") int destinationAirport,
                                     @RequestParam("departureTime") String departureTime,
                                     Model model) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deptTime = LocalDate.parse(departureTime, dtf);
        Airport depAirport = airportService.getAirportById(departureAirport);
        Airport destAirport = airportService.getAirportById(destinationAirport);

        if (departureAirport == destinationAirport) {
            model.addAttribute("AirportError", "Departure and destination airport cant be same!");
            model.addAttribute("airports", airportService.getAllAirports());
            return "book_flight";
        }
        List<Flight> flights = flightService.getAllFlightsByAirportAndDepartureTime(depAirport, destAirport, deptTime);
        if(flights.isEmpty()){
            model.addAttribute("notFound", "No Record Found!");
        }else{
            model.addAttribute("flights", flights);
        }
        model.addAttribute("airports", airportService.getAllAirports());
        return "book_flight";
    }

    @GetMapping("/flight/book/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String showCustomerInfoPage(@RequestParam long flightId, Model model) {
        model.addAttribute("flightId", flightId);
        model.addAttribute("passenger", new Passenger());
        return "passenger_info";
    }

    @PostMapping("/flight/book/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String bookFlight(@Valid @ModelAttribute("passenger") Passenger passenger, BindingResult bindingResult, @RequestParam("flightId") long flightId, Model model) {
        Flight flight = flightService.getFlightById(flightId);
        Passenger passenger1 = passenger;
        passenger1.setFlight(flight);
        passengerService.savePassenger(passenger1);
        model.addAttribute("passenger", passenger1);
        return "confirmation_page";
    }

    @GetMapping("/flight/book/verify")
    @PreAuthorize("hasRole('ADMIN')")
    public String showVerifyBookingPage() {
        return "verify_booking";
    }

    @PostMapping("/flight/book/verify")
    @PreAuthorize("hasRole('ADMIN')")
    public String showVerifyBookingPageResult(@RequestParam("flightId") long flightId,
                                              @RequestParam("passengerId") long passengerId,
                                              Model model) {

        Flight flight = flightService.getFlightById(flightId);
        if (flight != null) {
            model.addAttribute("flight", flight);
            List<Passenger> passengers = flight.getPassengers();
            Passenger passenger = null;
            for (Passenger p : passengers) {
                if (p.getPassengerId() == passengerId) {
                    passenger = passengerService.getPassengerById(passengerId);
                    model.addAttribute("passenger", passenger);
                }
            }
            if (passenger != null) {
                return "verify_booking";
            }else{
                model.addAttribute("notFound", "Not Found");
                return "verify_booking";
            }
        } else {
            model.addAttribute("notFound", "Not Found");
            return "verify_booking";
        }

    }

    @PostMapping("/flight/book/cancel")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancelTicket(@RequestParam("passengerId") long passengerId, Model model){
        passengerService.deletePassengerById(passengerId);
        model.addAttribute("flights", flightService.getAllFlightsPaged(0));
        model.addAttribute("currentPage", 0);
        return "flights";
    }
}
