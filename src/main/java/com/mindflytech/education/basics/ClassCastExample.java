package com.mindflytech.education.basics;

public class ClassCastExample {
    public static void main(String[] args) {
        char euro = '\u20AC';
        char minus = (char)(euro - 9000);
        System.out.println("-- \\20AC - 9000's result: " + minus);
    }
}
