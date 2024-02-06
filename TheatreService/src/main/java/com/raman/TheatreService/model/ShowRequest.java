package com.raman.TheatreService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowRequest
{
    private long theatreId;
    private long movieId;
    private LocalDate showDate;
    private String showTime;
    private String showStatus;
}
