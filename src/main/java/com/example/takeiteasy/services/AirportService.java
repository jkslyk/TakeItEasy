package com.example.takeiteasy.services;

import com.example.takeiteasy.model.Airport;
import com.example.takeiteasy.repository.AirportRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class AirportService {
    private AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    public Page<Airport> getAllAirportsPaged(int pageNum) {
        return airportRepository.findAll(PageRequest.of(pageNum,5, Sort.by("airportName")));
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Integer airportId) {
        return airportRepository.findById(airportId).orElse(null);
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public void deleteAirport(Integer airportId) {
        airportRepository.deleteById(airportId);
    }
}
