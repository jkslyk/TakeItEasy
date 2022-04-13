package com.example.takeiteasy.services;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmialValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return true;
    }
}
