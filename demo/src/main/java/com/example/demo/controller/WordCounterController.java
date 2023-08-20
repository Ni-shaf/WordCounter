package com.example.demo.controller;

import com.example.demo.service.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word_counter/words")
public class WordCounterController {

    private final WordCounter wordCounter;

    @Autowired
    public WordCounterController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping(value = "/add")
    public void getCount(@RequestBody List<String> words) {
        wordCounter.addWords(words);
    }

    @GetMapping(value = "/getCount")
    public int getCount(@RequestParam(value = "word") String word) {
        return wordCounter.getCount(word);
    }
}
