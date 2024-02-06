package com.raman.TheatreService.model;

import com.raman.TheatreService.entity.Seat;
import lombok.Data;

import java.util.List;

@Data
public class ScreenRequest
{
    private long theatreId;
    private String screenName;
    private int noOfSeats;
    private List<Seat> seat;
}
