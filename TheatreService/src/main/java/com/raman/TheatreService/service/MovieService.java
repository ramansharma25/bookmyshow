package com.raman.TheatreService.service;

import com.raman.TheatreService.model.MovieRequest;
import com.raman.TheatreService.model.MovieResponse;

import java.util.List;

public interface MovieService
{

    long addMovie(MovieRequest movieRequest);

    MovieResponse getMovieById(long movieId);

    List<MovieResponse> getAllMovies();
}
