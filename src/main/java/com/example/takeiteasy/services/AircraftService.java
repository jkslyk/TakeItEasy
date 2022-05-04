package com.example.takeiteasy.services;

import com.example.takeiteasy.model.Aircraft;
import com.example.takeiteasy.repository.AircraftRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    public Page<Aircraft> getAllAircraftsPaged(int pageNum) {
        return aircraftRepository.findAll(PageRequest.of(pageNum,5, Sort.by("model")));
    }

    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long aircraftId) {
        return aircraftRepository.findById(aircraftId).orElse(null);
    }

    public Aircraft saveAircraft(Aircraft aircraft) {
        if(aircraft==null) throw new IllegalArgumentException();
        return aircraftRepository.save(aircraft);
    }

    public void deleteAircraftById(Long aircraftId) {
        aircraftRepository.deleteById(aircraftId);
    }
}
