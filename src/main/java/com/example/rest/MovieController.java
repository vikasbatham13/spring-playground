package com.example.rest;

import com.example.processor.MovieProcessor;
import com.example.rest.model.FinalResponse;
import com.example.rest.model.OMDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieProcessor processor;

    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FinalResponse> getMovieDetail(@RequestParam String q) throws IOException {
        OMDBResponse movieResponse = new OMDBResponse();
        return processor.getMovie(q);
    }
}
