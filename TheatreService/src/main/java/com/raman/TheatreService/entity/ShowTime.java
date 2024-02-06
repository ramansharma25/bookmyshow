package com.raman.TheatreService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowTime
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long showId;

    private long theatreId;

    private long movieId;

    private LocalDate showDate;

    private String showTime;

    private String showStatus;
}
