package com.raman.TheatreService.service;

import com.raman.TheatreService.model.ScreenRequest;
import com.raman.TheatreService.model.ScreenResponse;

import java.util.List;

public interface ScreenService
{

    long addScreen(ScreenRequest screenRequest);

    ScreenResponse getScreenById(long screenId);

    List<ScreenResponse> getAllScreens();
}
