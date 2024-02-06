package com.raman.TheatreService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theatre
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "THEATRE_ID")
    private long theatreId;

    @Column(name = "THEATRE_NAME")
    private String theatreName;

    @Column(name = "THEATRE_CITY")
    private String city;

    @Column(name = "THEATRE_LOCATION")
    private String location;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Screen> screen;
}