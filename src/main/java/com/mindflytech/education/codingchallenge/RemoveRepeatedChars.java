package com.mindflytech.education.codingchallenge;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveRepeatedChars {
    Set<Integer> existingElements = new HashSet<>();

    /**
     * This is the original solution by
     * @author Eva Szeplaki
     *
     * @param startString the input string that may contain duplicates
     * @return the result without duplicates
     */
    public String removeCharacters(String startString) {
        StringBuilder resultString = new StringBuilder();

        for (int i = 0; i < startString.length(); i++) {
            String character = startString.substring(i, i + 1);
            if (!resultString.toString().contains(character)) {
                resultString.append(character);
            }
        }
        return resultString.toString();
    }

    public String removeDuplicatesWithStream(String input) {
        existingElements.clear();
        String noDuplicate = input.chars()
                .filter(this::allowIfNotPresentInBuffer)
                .mapToObj(integer -> new String(new char[]{(char) integer}))
                .collect(Collectors.joining());
        existingElements.clear();
        return noDuplicate;
    }

    private boolean allowIfNotPresentInBuffer(int character) {
        if(!existingElements.contains(character)) {
            existingElements.add(character);
            return true;
        }
        return false;
    }
}
