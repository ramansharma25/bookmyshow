package com.raman.TheatreService.service;

import com.raman.TheatreService.model.ShowRequest;
import com.raman.TheatreService.model.ShowResponse;

import java.util.List;

public interface ShowService
{

    long addShow(ShowRequest showRequest);

    ShowResponse getShowById(long showId);

    List<ShowResponse> getAllShows();
}
