package com.raman.TheatreService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowResponse
{
    private long showId;
    private long theatreId;
    private long movieId;
    private LocalDate showDate;
    private String showTime;
    private String showStatus;
}
