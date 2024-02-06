package com.raman.TheatreService.service;

import com.raman.TheatreService.entity.Movie;
import com.raman.TheatreService.exception.MovieServiceCustomException;
import com.raman.TheatreService.model.MovieRequest;
import com.raman.TheatreService.model.MovieResponse;
import com.raman.TheatreService.repository.MovieRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class MovieServiceImpl implements MovieService
{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public long addMovie(MovieRequest movieRequest)
    {
        log.info("Adding Movie :: " +movieRequest.getMovieName());
        Movie movie = Movie
                .builder()
                .movieName(movieRequest.getMovieName())
                .movieLanguage(movieRequest.getMovieLanguage())
                .build();
        movieRepository.save(movie);
        log.info("Movie " +movie.getMovieName()+ " added");
        return movie.getMovieId();
    }

    @Override
    public MovieResponse getMovieById(long movieId)
    {
        log.info("Getting movie for movieId :: " +movieId);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieServiceCustomException("Movie with movieId :: " +movieId+ " not found", "MOVIE_NOT_FOUND"));
        MovieResponse movieResponse = new MovieResponse();
        copyProperties(movie, movieResponse);

        return movieResponse;
    }

    @Override
    public List<MovieResponse> getAllMovies()
    {
        log.info("Getting all movies ... ");
        List<Movie> movieList = movieRepository.findAll();
        List<MovieResponse> movieResponses
                = movieList
                .stream()
                .map(movie -> {
                    MovieResponse movieResponse = new MovieResponse();
                    copyProperties(movie, movieResponse);
                    return movieResponse;
                })
                .toList();
        return movieResponses;
    }

}
