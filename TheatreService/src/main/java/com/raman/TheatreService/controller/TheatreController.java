package com.raman.TheatreService.controller;

import com.raman.TheatreService.model.BrowseTheatreResponse;
import com.raman.TheatreService.model.TheatreRequest;
import com.raman.TheatreService.model.TheatreResponse;
import com.raman.TheatreService.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController
{
    @Autowired
    private TheatreService theatreService;

    @PostMapping
    public ResponseEntity<Long> addTheatre(@RequestBody TheatreRequest theatreRequest)
    {
        long theatreId = theatreService.addTheatre(theatreRequest);
        return new ResponseEntity<>(theatreId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheatreResponse> getTheatreById(@PathVariable("id") long theatreId)
    {
        TheatreResponse theatreResponse = theatreService.getTheatreById(theatreId);
        return new ResponseEntity<>(theatreResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TheatreResponse>> getAllTheatres()
    {
        List<TheatreResponse> theatreResponse = theatreService.getAllTheatres();
        return new ResponseEntity<>(theatreResponse, HttpStatus.OK);
    }

    @GetMapping("/browseTheatres")
    public ResponseEntity<List<BrowseTheatreResponse>> browseTheatres(@RequestParam("movieId") long movieId, @RequestParam("showDate") String showDate)
    {
        List<BrowseTheatreResponse> theatreResponses = theatreService.browseTheatres(movieId, showDate);
        return new ResponseEntity<>(theatreResponses, HttpStatus.OK);
    }

    @GetMapping("/browseTheatresByName")
    public ResponseEntity<List<BrowseTheatreResponse>> browseTheatresByMovieName(@RequestParam("movieName") String movieName, @RequestParam("showDate") String showDate)
    {
        List<BrowseTheatreResponse> theatreResponses = theatreService.browseTheatresByMovieName(movieName, showDate);
        return new ResponseEntity<>(theatreResponses, HttpStatus.OK);
    }
}
