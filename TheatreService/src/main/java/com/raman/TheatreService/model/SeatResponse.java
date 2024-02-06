package com.raman.TheatreService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponse
{
    private long seatId;
    private String seatNumber;
    private boolean isBooked;
    private int price;
    private long screenId;
}
