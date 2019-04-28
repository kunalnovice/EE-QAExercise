package com.EqualExperts.QAExercise.CustomisedLibraries;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class StringFunction {

    public String getRandomAphabets(int length) {
        String name = RandomStringUtils.randomAlphabetic(length).toLowerCase();
        return name;
    }
}
