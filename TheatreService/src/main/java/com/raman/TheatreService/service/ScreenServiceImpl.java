package com.raman.TheatreService.service;

import com.raman.TheatreService.entity.Screen;
import com.raman.TheatreService.entity.Seat;
import com.raman.TheatreService.entity.Theatre;
import com.raman.TheatreService.exception.ScreenServiceCustomException;
import com.raman.TheatreService.exception.TheatreServiceCustomException;
import com.raman.TheatreService.model.ScreenRequest;
import com.raman.TheatreService.model.ScreenResponse;
import com.raman.TheatreService.repository.ScreenRepository;
import com.raman.TheatreService.repository.SeatRepository;
import com.raman.TheatreService.repository.TheatreRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ScreenServiceImpl implements ScreenService
{
    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public long addScreen(ScreenRequest screenRequest)
    {
        log.info("Adding Screen ...");

        //Get Theatre Information while adding Screen
        Theatre theatre = theatreRepository.findById(screenRequest.getTheatreId())
                .orElseThrow(() -> new TheatreServiceCustomException("Theatre not found with theatre id :: " +screenRequest.getTheatreId()+ " while adding screen :: " +screenRequest.getScreenName(), "THEATRE_NOT_FOUND"));

        //Add Screen
        Screen screen = Screen
                .builder()
                .screenName(screenRequest.getScreenName())
                .noOfSeats(screenRequest.getNoOfSeats())
                .theatre(theatre)
                .build();
        screenRepository.save(screen);

        //Add Seats From the Request after adding Screen. For Solving Screen Number going as null in Seat Table
        for(Seat seat : screenRequest.getSeat())
        {
            seat.setScreen(screen);
            seatRepository.save(seat);
        }

        log.info("Screen name :: " +screenRequest.getScreenName()+ " added in theatre id :: " +screenRequest.getTheatreId());
        return screen.getScreenId();
    }

    @Override
    public ScreenResponse getScreenById(long screenId)
    {
        log.info("Get Screen for :: " +screenId);
        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new ScreenServiceCustomException("Screen with " +screenId+ " not found", "SCREEN_NOT_FOUND"));
        ScreenResponse screenResponse = new ScreenResponse();
        copyProperties(screen, screenResponse);
        screenResponse.setTheatreId(screen.getTheatre().getTheatreId());
        return screenResponse;
    }

    @Override
    public List<ScreenResponse> getAllScreens()
    {
        log.info("Get all screens ... ");
        List<Screen> screensList = screenRepository.findAll();
        List<ScreenResponse> screenResponses
                = screensList
                .stream()
                    .map(scr -> {
                        ScreenResponse screenResp = new ScreenResponse();
                        copyProperties(scr, screenResp);
                        screenResp.setTheatreId(scr.getTheatre().getTheatreId());
                        return screenResp;
                    })
                .toList();

        return screenResponses;
    }
}
