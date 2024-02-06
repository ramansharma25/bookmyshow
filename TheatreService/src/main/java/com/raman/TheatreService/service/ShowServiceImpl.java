package com.raman.TheatreService.service;

import com.raman.TheatreService.entity.Movie;
import com.raman.TheatreService.entity.ShowTime;
import com.raman.TheatreService.entity.Theatre;
import com.raman.TheatreService.exception.MovieServiceCustomException;
import com.raman.TheatreService.exception.ShowServiceCustomException;
import com.raman.TheatreService.exception.TheatreServiceCustomException;
import com.raman.TheatreService.model.ShowRequest;
import com.raman.TheatreService.model.ShowResponse;
import com.raman.TheatreService.repository.MovieRepository;
import com.raman.TheatreService.repository.ShowRepository;
import com.raman.TheatreService.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static org.springframework.beans.BeanUtils.*;

@Service
public class ShowServiceImpl implements ShowService
{
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public long addShow(ShowRequest showRequest)
    {
        Theatre theatre = theatreRepository.findById(showRequest.getTheatreId())
                .orElseThrow(() -> new TheatreServiceCustomException("Theatre with theatreId :: " +showRequest.getTheatreId()+ " not found while adding show", "THEATRE_NOT_FOUND"));

        Movie movie = movieRepository.findById(showRequest.getMovieId())
                .orElseThrow(() -> new MovieServiceCustomException("Movie with movieId :: " +showRequest.getMovieId()+ " not found", "MOVIE_NOT_FOUND"));
        ShowTime show = ShowTime
                .builder()
                .theatreId(showRequest.getTheatreId())
                .movieId(showRequest.getMovieId())
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .showStatus(showRequest.getShowStatus())
                .build();
        showRepository.save(show);

        return show.getShowId();
    }

    @Override
    public ShowResponse getShowById(long showId)
    {
        ShowTime show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowServiceCustomException("Show with showId :: " +showId+ " not found", "SHOW_NOT_FOUND"));
        ShowResponse showResponse = new ShowResponse();
        copyProperties(show, showResponse);

        return showResponse;
    }

    @Override
    public List<ShowResponse> getAllShows()
    {
        List<ShowTime> showList = showRepository.findAll();

        List<ShowResponse> showResponses = showList
                .stream()
                .map(show -> {
                    ShowResponse showResponse = new ShowResponse();
                    copyProperties(show, showResponse);
                    return showResponse;
                })
                .toList();

        return showResponses;
    }
}
