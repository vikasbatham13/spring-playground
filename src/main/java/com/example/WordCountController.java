package com.example;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/words")
public class WordCountController {
    private final WordCounter wordCounter;

    public WordCountController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping(value = "/count", consumes = MediaType.TEXT_PLAIN_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> numberCount(@RequestBody String wordString) {
        return wordCounter.count(wordString);
    }
}
