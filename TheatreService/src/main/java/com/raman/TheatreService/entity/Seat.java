package com.raman.TheatreService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEAT_ID")
    private long seatId;

    @Column(name = "SEAT_NUMBER")
    private String seatNumber;

    @Column(name = "isBooked")
    private boolean isBooked;

    @Column(name = "SEAT_PRICE")
    private int price;

    @ManyToOne
    @JoinColumn(name = "screenId")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;
}
