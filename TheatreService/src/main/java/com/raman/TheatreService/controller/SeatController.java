package com.raman.TheatreService.controller;

import com.raman.TheatreService.model.SeatRequest;
import com.raman.TheatreService.model.SeatResponse;
import com.raman.TheatreService.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController
{
    @Autowired
    private SeatService seatService;
    @PostMapping
    public ResponseEntity<Long> addSeat(@RequestBody SeatRequest seatRequest)
    {
        long seatId = seatService.addSeat(seatRequest);
        return new ResponseEntity<>(seatId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> getSeatById(@PathVariable("id") long seatId)
    {
        SeatResponse seatResponse = seatService.getSeatById(seatId);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SeatResponse>> getAllSeats()
    {
        List<SeatResponse> seatResponses = seatService.getAllSeats();
        return new ResponseEntity<>(seatResponses, HttpStatus.OK);
    }
}
