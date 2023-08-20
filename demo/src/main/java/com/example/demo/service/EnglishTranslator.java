package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
class EnglishTranslator implements Translator {

    @Override
    public String translate(String word) {
        //dummy translator: returns word in lowercase
        return word.toLowerCase();
    }
}