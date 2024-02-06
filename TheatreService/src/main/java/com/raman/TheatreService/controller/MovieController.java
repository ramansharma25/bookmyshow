package com.raman.TheatreService.controller;

import com.raman.TheatreService.model.MovieRequest;
import com.raman.TheatreService.model.MovieResponse;
import com.raman.TheatreService.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@Log4j2
public class MovieController
{
    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<Long> addMovie(@RequestBody MovieRequest movieRequest)
    {
        log.info("Adding movie ... ");
        long movieId = movieService.addMovie(movieRequest);
        return new ResponseEntity<>(movieId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable("id") long movieId)
    {
        MovieResponse movieResponse = movieService.getMovieById(movieId);
        return new ResponseEntity<>(movieResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies()
    {
        List<MovieResponse> movieResponseList = movieService.getAllMovies();
        return new ResponseEntity<>(movieResponseList, HttpStatus.OK);
    }
}
