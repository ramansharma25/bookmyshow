package com.raman.TheatreService.service;

import com.raman.TheatreService.model.SeatRequest;
import com.raman.TheatreService.model.SeatResponse;

import java.util.List;

public interface SeatService
{

    long addSeat(SeatRequest seatRequest);

    SeatResponse getSeatById(long seatId);

    List<SeatResponse> getAllSeats();
}
