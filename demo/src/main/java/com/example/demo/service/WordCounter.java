package com.example.demo.service;

import com.example.demo.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WordCounter {


    private final ConcurrentHashMap<String, Integer> wordToCountMap;

    private final Translator englishTranslator;

    @Autowired
    public WordCounter(Translator englishTranslator) {
        this.englishTranslator = englishTranslator;
        this.wordToCountMap = new ConcurrentHashMap<>();
    }

    public void addWords(List<String> words) {
        if (!words.isEmpty()) {
            words.stream().forEach(word -> {
                if (validateWord(word)) {
                    String englishTranslatedWord = translateToEnglish(word);
                    wordToCountMap.put(englishTranslatedWord, wordToCountMap.getOrDefault(englishTranslatedWord, 0) + 1);
                }
            });
        } else throw new InvalidInputException("Empty words");
    }

    public int getCount(String word) {
        String englishTranslatedWord = translateToEnglish(word);
        return wordToCountMap.getOrDefault(englishTranslatedWord, 0);
    }

    private boolean validateWord(String word) {
        if (word != null && word.matches("[a-zA-Z]+")) return true;
        else throw new InvalidInputException("Word cannot have non-alphabetic characters");
    }

    private String translateToEnglish(String word) {
        return englishTranslator.translate(word);
    }

}

