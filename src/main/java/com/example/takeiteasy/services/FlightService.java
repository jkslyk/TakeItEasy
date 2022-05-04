package com.example.takeiteasy.services;

import com.example.takeiteasy.model.Airport;
import com.example.takeiteasy.model.Flight;
import com.example.takeiteasy.repository.FlightRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@NoArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }
    public Page<Flight> getAllFlightsPaged(int pageNum) {
        return flightRepository.findAll(PageRequest.of(pageNum,5, Sort.by("departureAirport")));
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlightById(long flightId) {
        flightRepository.deleteById(flightId);
    }

    public List<Flight> getAllFlightsByAirportAndDepartureTime(Airport depAirport, Airport destAirport, LocalDate depDate) {
        return flightRepository.findAllByDepartureAirportEqualsAndDestinationAirportEqualsAndDepartureDateEquals(depAirport, destAirport, depDate);
    }
}
