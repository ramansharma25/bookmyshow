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
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticketId;

    private long theatreId;

    private long movieId;

    private long showId;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seat;
}
