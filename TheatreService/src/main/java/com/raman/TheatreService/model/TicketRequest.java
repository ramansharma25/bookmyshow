package com.raman.TheatreService.model;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequest
{
    private long theatreId;
    private long movieId;
    private long showId;
    private List<Long> seatId;
}
