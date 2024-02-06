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
public class Screen
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long screenId;

    @Column(name = "SCREEN_NAME")
    private String screenName;

    @Column(name = "NO_OF_SEATS")
    private int noOfSeats;

    @ManyToOne
    @JoinColumn(name = "theatreId")
    private Theatre theatre;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seat;
}