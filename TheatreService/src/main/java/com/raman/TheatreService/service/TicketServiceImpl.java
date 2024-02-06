package com.raman.TheatreService.service;

import com.raman.TheatreService.entity.Seat;
import com.raman.TheatreService.entity.Ticket;
import com.raman.TheatreService.exception.SeatServiceCustomException;
import com.raman.TheatreService.exception.TicketServiceCustomException;
import com.raman.TheatreService.model.TicketRequest;
import com.raman.TheatreService.model.TicketResponse;
import com.raman.TheatreService.repository.SeatRepository;
import com.raman.TheatreService.repository.TicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.*;

@Service
public class TicketServiceImpl implements  TicketService
{
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public long bookTicket(TicketRequest ticketRequest)
    {
        //Book Ticket
        Ticket ticket = Ticket
                .builder()
                .theatreId(ticketRequest.getTheatreId())
                .movieId(ticketRequest.getMovieId())
                .showId(ticketRequest.getShowId())
                .build();
        ticketRepository.save(ticket);

        //Update Seat Information
        for(long seatId : ticketRequest.getSeatId())
        {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new SeatServiceCustomException("Seat with seatId :: " +seatId+ " not found", "SEAT_NOT_FOUND"));
            seat.setBooked(true);
            seat.setTicket(ticket);
            seatRepository.save(seat);
        }

        return ticket.getTicketId();
    }

    @Override
    public TicketResponse getTicketById(long ticketId)
    {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketServiceCustomException("Ticket with ticketId :: " +ticketId+ " not found", "TICKET_NOT_FOUND"));

        List<Long> seatIdList = ticket.getSeat()
                .stream()
                .map(Seat::getSeatId)
                .toList();

        TicketResponse ticketResponse = TicketResponse
                .builder()
                .ticketId(ticket.getTicketId())
                .movieId(ticket.getMovieId())
                .showId(ticket.getShowId())
                .theatreId(ticket.getTheatreId())
                .seatId(seatIdList)
                .build();

        return ticketResponse;
    }
}
