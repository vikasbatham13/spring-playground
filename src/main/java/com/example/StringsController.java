package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/words")
public class StringsController {

    @Autowired
    WordCounter wordCounter;

    @PostMapping(value = "/count", consumes = MediaType.TEXT_PLAIN_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> numberCount(@RequestBody String wordString) {

        return this.wordCounter.count(wordString);
    }
}
