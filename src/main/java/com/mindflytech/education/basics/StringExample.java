package com.mindflytech.education.basics;

public class StringExample {
    private static final int OFFSET = 20;
    public static void main(String[] args) {
        String test = "this-is-a-test-String";
        char[] charArray = test.toCharArray();
        char initial = charArray[0];
        char uppercaseInitial = (char)(initial - OFFSET);
        charArray[0] = uppercaseInitial;
    }
}
