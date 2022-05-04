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
public class Airport {
    @SequenceGenerator(
            name = "airport_sequence",
            sequenceName = "airport_sequence",
            allocationSize = 1

    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "airport_sequence"
    )
    private int airportId;
    private String airportCode;
    private String airportName;
    private String city;
    private String state;
    private String country;
    @OneToMany(mappedBy = "departureAirport")
    List<Flight> flights = new ArrayList<Flight>();

    public Airport() {
    }

    public Airport(String airportCode, String airportName, String city, String state, String country) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
