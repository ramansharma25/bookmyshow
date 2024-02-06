package com.raman.TheatreService;

import com.raman.TheatreService.entity.Movie;
import com.raman.TheatreService.repository.MovieRepository;
import com.raman.TheatreService.repository.TheatreRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class TheatreServiceApplicationTests
{
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	TheatreRepository theatreRepository;

	@Test
	void findByMovieNameTest()
	{
		String movieName = "Fighter";

		Movie movie = movieRepository.findByMovieName(movieName);
		log.info("Getting movie with movieName :: " +movieName);

		log.info("Movie Id :: " +movie.getMovieId());
		log.info("Movie Name :: " +movie.getMovieName());
		log.info("Movie Language :: " +movie.getMovieLanguage());
	}

	@Test
	void findByMovieLanguageTest()
	{
		String movieLanguage = "Hindi";

		List<Movie> movieList = movieRepository.findByMovieLanguage(movieLanguage);
		log.info("Getting movies with language :: " +movieLanguage);
		movieList.forEach(movie -> {
			log.info("Movie Id :: " +movie.getMovieId());
			log.info("Movie Name :: " +movie.getMovieName());
			log.info("Movie Language :: " +movie.getMovieLanguage());
		});
	}
}
