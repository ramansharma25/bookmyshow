package com.raman.TheatreService.repository;

import com.raman.TheatreService.entity.Theatre;
import com.raman.TheatreService.model.BrowseTheatreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long>
{
    @Query(value = "select t.theatre_id as theatreId, t.theatre_name as theatreName, t.theatre_city as city, t.theatre_location as location, s.show_time as showTime from theatre t inner join show_time s on t.theatre_id = s.theatre_id where movie_id = :movieId and show_date = :showDate", nativeQuery = true)
    List<BrowseTheatreResponse> browseTheatres(@Param("movieId") Long movieId, @Param("showDate") String showDate);

    @Query(value = "SELECT t.theatre_id as theatreId, t.theatre_name as theatreName, t.theatre_city as city, t.theatre_location as location, s.show_time as showTime FROM THEATRE T, SHOW_TIME S, MOVIE M WHERE T.THEATRE_ID=S.THEATRE_ID AND S.MOVIE_ID = M.MOVIE_ID AND M.MOVIE_NAME=:movieName AND SHOW_DATE=:showDate", nativeQuery = true)
    List<BrowseTheatreResponse> browseTheatresByMovieName(String movieName, String showDate);
}
