package com.example.demo.service;


import com.example.demo.exception.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WordCounterServiceTest {

    @Mock
    private Translator englishTranslator;
    private WordCounter wordCounter;


    @BeforeEach
    void setUp() {
        wordCounter
                = new WordCounter(englishTranslator);
    }

    @Test
    void getWordCount() {
        List<String> words = List.of( "Hello","hello","HELLO","bye");
        Mockito.when(englishTranslator.translate("Hello")).thenReturn("hello");
        Mockito.when(englishTranslator.translate("hello")).thenReturn("hello");
        Mockito.when(englishTranslator.translate("HELLO")).thenReturn("hello");
        Mockito.when(englishTranslator.translate("bye")).thenReturn("bye");
        wordCounter.addWords(words);
        Assertions.assertEquals(3, wordCounter.getCount("hello"));
    }

    @Test
    void addNonAlphabeticWord() {
        List<String> words = List.of( "97sdf");
        Assertions.assertThrows(InvalidInputException.class,()-> wordCounter.addWords(words));
    }

    @Test
    void getWordCountForNonExistentWord() {
        List<String> words = List.of( "hey");
        Mockito.when(englishTranslator.translate("hey")).thenReturn("hey");
        Mockito.when(englishTranslator.translate("bye")).thenReturn("bye");
        wordCounter.addWords(words);
        Assertions.assertEquals(0, wordCounter.getCount("bye"));
    }
}
