package com.raman.TheatreService.service;

import com.raman.TheatreService.model.BrowseTheatreResponse;
import com.raman.TheatreService.model.TheatreRequest;
import com.raman.TheatreService.model.TheatreResponse;

import java.util.List;

public interface TheatreService
{

    long addTheatre(TheatreRequest theatreRequest);

    TheatreResponse getTheatreById(long theatreId);

    List<TheatreResponse> getAllTheatres();

    List<BrowseTheatreResponse> browseTheatres(long movieId, String showDate);

    List<BrowseTheatreResponse> browseTheatresByMovieName(String movieName, String showDate);
}
