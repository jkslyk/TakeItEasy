package com.example.takeiteasy.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Aircraft {

    @SequenceGenerator(
            name = "aircraft_sequence",
            sequenceName = "aircraft_sequence",
            allocationSize = 1

    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "aircraft_sequence"
    )
    private long aircraftId;
    private String manufacturer;
    private String model;
    private Integer numberOfSeats;


    @OneToMany(mappedBy = "aircraft")
    private List<Flight> flights = new ArrayList<>();
    public Aircraft() {
    }

    public Aircraft( String manufacturer, String model, Integer numberOfSeats) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
    }
}
