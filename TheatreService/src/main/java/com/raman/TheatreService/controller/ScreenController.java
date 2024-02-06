package com.raman.TheatreService.controller;

import com.raman.TheatreService.model.ScreenRequest;
import com.raman.TheatreService.model.ScreenResponse;
import com.raman.TheatreService.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screen")
public class ScreenController
{
    @Autowired
    ScreenService screenService;

    @PostMapping
    public ResponseEntity<Long> addScreen(@RequestBody ScreenRequest screenRequest)
    {
        long screenId = screenService.addScreen(screenRequest);
        return new ResponseEntity<>(screenId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenResponse> getScreenById(@PathVariable("id") long screenId)
    {
        ScreenResponse screenResponse = screenService.getScreenById(screenId);
        return new ResponseEntity<>(screenResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScreenResponse>> getAllScreens()
    {
        List<ScreenResponse> screenResponse = screenService.getAllScreens();
        return new ResponseEntity<>(screenResponse, HttpStatus.OK);
    }
}
