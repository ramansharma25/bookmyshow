package com.raman.TheatreService.service;

import com.raman.TheatreService.entity.Screen;
import com.raman.TheatreService.entity.Seat;
import com.raman.TheatreService.entity.Theatre;
import com.raman.TheatreService.exception.TheatreServiceCustomException;
import com.raman.TheatreService.model.BrowseTheatreResponse;
import com.raman.TheatreService.model.TheatreRequest;
import com.raman.TheatreService.model.TheatreResponse;
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
public class TheatreServiceImpl implements TheatreService
{
    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public long addTheatre(TheatreRequest theatreRequest)
    {
        log.info("Adding Theatre ... ");
        //Add Theatre
        Theatre theatre = Theatre.builder()
                .theatreName(theatreRequest.getTheatreName())
                .city(theatreRequest.getCity())
                .location(theatreRequest.getLocation())
                .build();
        theatreRepository.save(theatre);

        //Add Screens from the request after adding theatre. For solving theatreId null issue in Screen Table
        for(Screen screen : theatreRequest.getScreen())
        {
            screen.setTheatre(theatre);
            screenRepository.save(screen);

            //Add Seats From the request after adding screen. For solving screenId null issue in Seat Table
            for(Seat seat : screen.getSeat())
            {
                seat.setScreen(screen);
                seatRepository.save(seat);
            }
        }

        log.info("Theatre" +theatre.getTheatreName()+ " created");
        return theatre.getTheatreId();
    }

    @Override
    public TheatreResponse getTheatreById(long theatreId)
    {
        log.info("Get the theatre for " +theatreId);
        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new TheatreServiceCustomException("Theatre with " +theatreId+ " not found", "THEATRE_NOT_FOUND"));
        TheatreResponse theatreResponse = new TheatreResponse();
        //copyProperties(theatre, theatreResponse);
        theatreResponse.setTheatreId(theatre.getTheatreId());
        theatreResponse.setTheatreName(theatre.getTheatreName());
        theatreResponse.setCity(theatre.getCity());
        theatreResponse.setLocation(theatre.getLocation());

        return theatreResponse;
    }

    @Override
    public List<TheatreResponse> getAllTheatres()
    {
        log.info("Get All Theatres ...");
        List<Theatre> theatresList = theatreRepository.findAll();

        List<TheatreResponse> theatreResponses
                = theatresList
                .stream()
                        .map(theatreList -> {
                            TheatreResponse theatreResp = new TheatreResponse();
                            copyProperties(theatreList, theatreResp);
                            return theatreResp;
                        })
                            .toList();
        return theatreResponses;
    }

    @Override
    public List<BrowseTheatreResponse> browseTheatres(long movieId, String showDate)
    {

        List<BrowseTheatreResponse> theatresList = theatreRepository.browseTheatres(movieId, showDate);

     /*   List<TheatreResponse> theatreResponses
                = theatresList
                .stream()
                .map(theatreList -> {
                    TheatreResponse theatreResp = new TheatreResponse();
                    copyProperties(theatreList, theatreResp);
                    return theatreResp;
                })
                .toList();*/
        return theatresList;
    }

    @Override
    public List<BrowseTheatreResponse> browseTheatresByMovieName(String movieName, String showDate)
    {
        List<BrowseTheatreResponse> theatresList = theatreRepository.browseTheatresByMovieName(movieName, showDate);
        return theatresList;
    }
}
