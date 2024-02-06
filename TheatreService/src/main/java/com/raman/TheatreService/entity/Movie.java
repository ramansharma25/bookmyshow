package com.raman.TheatreService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_ID")
    private long movieId;

    @Column(name = "MOVIE_NAME")
    private String movieName;

    @Column(name = "MOVIE_LANGUAGE")
    private String movieLanguage;
}
