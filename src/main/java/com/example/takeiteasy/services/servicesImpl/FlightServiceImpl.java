package com.example.takeiteasy.services.servicesImpl;

import com.example.takeiteasy.model.Airport;
import com.example.takeiteasy.model.Flight;
import com.example.takeiteasy.repository.FlightRepository;
import com.example.takeiteasy.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }
    @Override
    public Page<Flight> getAllFlightsPaged(int pageNum) {
        return flightRepository.findAll(PageRequest.of(pageNum,5, Sort.by("departureAirport")));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }


    @Override
    @Transactional
    public Flight saveFlight(Flight flight) {
        System.out.println("WE GOT HERE:::::::: >>>>>> " + flight);
        return flightRepository.saveAndFlush(flight);
    }

    @Override
    public void deleteFlightById(long flightId) {
        flightRepository.deleteById(flightId);
    }

    @Override
    public List<Flight> getAllFlightsByAirportAndDepartureTime(Airport depAirport, Airport destAirport, LocalDate depDate) {
        return flightRepository.findAllByDepartureAirportEqualsAndDestinationAirportEqualsAndDepartureDateEquals(depAirport, destAirport, depDate);
    }
}
