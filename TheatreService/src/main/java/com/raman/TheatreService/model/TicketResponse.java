package com.raman.TheatreService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse
{
    private long ticketId;
    private long theatreId;
    private long movieId;
    private long showId;
    private List<Long> seatId;
}
