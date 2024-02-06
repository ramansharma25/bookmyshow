package com.raman.TheatreService.model;

import lombok.Data;

@Data
public class SeatRequest
{
    private long screenId;
    private String seatNumber;
    private boolean isBooked;
    private int price;
}
