package com.raman.TheatreService.service;

import com.raman.TheatreService.model.TicketRequest;
import com.raman.TheatreService.model.TicketResponse;

import java.util.List;

public interface TicketService
{

    long bookTicket(TicketRequest ticketRequest);

    TicketResponse getTicketById(long ticketId);
}
