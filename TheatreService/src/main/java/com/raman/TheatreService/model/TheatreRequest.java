package com.raman.TheatreService.model;

import com.raman.TheatreService.entity.Screen;
import lombok.Data;

import java.util.List;

@Data
public class TheatreRequest
{
    private String theatreName;
    private String city;
    private String location;
    private List<Screen> screen;
}
