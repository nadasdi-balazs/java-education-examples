package com.mindflytech.education.basics;

public class CharExample {
    public static void main(String[] args) {
        char c = 'c';
        char p = 'p';
        char divided = (char)(p / c);
        System.out.println("-- c / p = '" + divided + "'");

        char thisWontFit = (char)8237502375L;
        System.out.println("-- this *actually* fits, but probably forced to shorten: '" + thisWontFit + "'");
        short whatIsItInShort = (short)8_237_502_375L;
        System.out.println("-- 8237502375L casted to short: " + whatIsItInShort);
    }
}
