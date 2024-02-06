package com.raman.TheatreService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse
{
    private long movieId;
    private String movieName;
    private String movieLanguage;
}
