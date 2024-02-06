package com.raman.TheatreService.controller;

import com.raman.TheatreService.model.ShowRequest;
import com.raman.TheatreService.model.ShowResponse;
import com.raman.TheatreService.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController
{
    @Autowired
    ShowService showService;

    @PostMapping
    public ResponseEntity<Long> addShow(@RequestBody ShowRequest showRequest)
    {
        long showId = showService.addShow(showRequest);
        return new ResponseEntity<>(showId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowResponse> getShowById(@PathVariable("id") long showId)
    {
        ShowResponse showResponse = showService.getShowById(showId);
        return new ResponseEntity<>(showResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ShowResponse>> getAllShows()
    {
        List<ShowResponse> showResponses = showService.getAllShows();
        return new ResponseEntity<>(showResponses, HttpStatus.OK);
    }
}
