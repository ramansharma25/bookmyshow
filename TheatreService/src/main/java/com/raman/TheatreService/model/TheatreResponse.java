package com.raman.TheatreService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreResponse
{
    private long theatreId;
    private String theatreName;
    private String city;
    private String location;
}
