package com.mindflytech.education.basics;

import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        Map<Character, Integer> numberOfCharacterOccurrences = new HashMap<>();
        char input = 'i';
        Integer count = numberOfCharacterOccurrences.get(input);
        if(count != null || count != 0) {
            numberOfCharacterOccurrences.put(input, count + 1);
        } else {
            numberOfCharacterOccurrences.put(input, 1);
        }
    }
}
