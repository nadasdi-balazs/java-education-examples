package com.mindflytech.education.codingchallenge;

import java.util.Arrays;

public class ReverseOddWords {
    /**
     * Eva's implementation
     * @param words
     * @return
     */
    public String reverseOddString(String words) {
        String reversedOddStrings = "";
        String[] stringArray = words.split(" ");
        String[] reversedArray = new String[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            String currentWord = stringArray[i];
            if(currentWord.length() % 2 == 0) {
                reversedArray[i] = currentWord;
                continue;
            }
            String reversedSoloString = reverseString(currentWord);
            reversedArray[i] = reversedSoloString;
        }
        reversedOddStrings = String.join(" ", reversedArray);
        return reversedOddStrings;
    }

    private String reverseString(String currentWord) {
        String reversedSoloString = "";
        int indexOfLastChar = currentWord.length() - 1;
        for (int index = indexOfLastChar; index >= 0; index--) {
            reversedSoloString += currentWord.charAt(index);
        }
        return reversedSoloString;
    }

    /**
     * Balazs' implementation
     * @param input
     * @return
     */
    public String reverseOddStringBalazs(String input) {
        String[] words = input.split(" ");
        StringBuffer result = new StringBuffer();
        for(String word : words) {
            int length = word.length();
            boolean even = isEven(length);
            if(even) {
                result.append(word);
            } else {
                String reversed = reverse(word);
                result.append(reversed);
            }
        }
        return result.toString();
    }

    private String reverse(String word) {
        String result = "";
        for(char c : word.toCharArray()) {
            result = c + result;
        }
        return result;
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static void main(String[] args) {
        String doubleWhitespace = " I am  Balazs";
        String[] split = doubleWhitespace.split(" ");
        System.out.println("-- split array: " + Arrays.toString(split));
    }
}
