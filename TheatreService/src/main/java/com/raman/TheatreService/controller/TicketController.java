package com.raman.TheatreService.controller;

import com.raman.TheatreService.model.TicketRequest;
import com.raman.TheatreService.model.TicketResponse;
import com.raman.TheatreService.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController
{
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Long> bookTicket(@RequestBody TicketRequest ticketRequest)
    {
        long ticketId = ticketService.bookTicket(ticketRequest);
        return new ResponseEntity<>(ticketId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable("id") long ticketId)
    {
        TicketResponse ticketResponse = ticketService.getTicketById(ticketId);
        return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
    }
}
