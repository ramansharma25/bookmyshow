package com.raman.TheatreService.service;

import com.raman.TheatreService.entity.Screen;
import com.raman.TheatreService.entity.Seat;
import com.raman.TheatreService.exception.ScreenServiceCustomException;
import com.raman.TheatreService.exception.SeatServiceCustomException;
import com.raman.TheatreService.model.SeatRequest;
import com.raman.TheatreService.model.SeatResponse;
import com.raman.TheatreService.repository.ScreenRepository;
import com.raman.TheatreService.repository.SeatRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class SeatServiceImpl implements SeatService
{
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public long addSeat(SeatRequest seatRequest)
    {
       log.info("Adding seat ... ");
       //Get Screen Information while adding Seat
        Screen screen = screenRepository.findById(seatRequest.getScreenId())
                .orElseThrow(() -> new ScreenServiceCustomException("Screen Not Found with screenId :: " +seatRequest.getScreenId()+ " while adding seat with seatNumber :: " +seatRequest.getSeatNumber(), "SCREEN_NOT_FOUND"));

        //Add Seat
        Seat seat = Seat
                .builder()
                .seatNumber(seatRequest.getSeatNumber())
                .isBooked(seatRequest.isBooked())
                .price(seatRequest.getPrice())
                .screen(screen)
                .build();
        seatRepository.save(seat);
        log.info("Seat added with seatNumber :: " +seat.getSeatNumber()+ " in Screen Id :: " +seatRequest.getScreenId());
        return seat.getSeatId();
    }

    @Override
    public SeatResponse getSeatById(long seatId)
    {
        log.info("Getting seat information for seat id :: " +seatId);
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new SeatServiceCustomException("Seat with seat id :: " +seatId+ " not found", "SEAT_NOT_FOUND"));

        SeatResponse seatResponse = new SeatResponse();
/*        seatResponse.setSeatId(seat.getSeatId());
        seatResponse.setSeatNumber(seat.getSeatNumber());
        seatResponse.setBooked(seat.isBooked());
        seatResponse.setPrice(seat.getPrice());
*/
        copyProperties(seat, seatResponse);
        seatResponse.setScreenId(seat.getScreen().getScreenId());
        return seatResponse;
    }

    @Override
    public List<SeatResponse> getAllSeats()
    {
        log.info("Get All Seats ...");
        List<Seat> seatList = seatRepository.findAll();
        List<SeatResponse> seatResponses
                = seatList
                .stream()
                .map(seat -> {
                    SeatResponse seatResponse = new SeatResponse();
                    copyProperties(seat, seatResponse);
                    seatResponse.setScreenId(seat.getScreen().getScreenId());
                    return seatResponse;
                })
                .toList();
        return seatResponses;
    }
}
