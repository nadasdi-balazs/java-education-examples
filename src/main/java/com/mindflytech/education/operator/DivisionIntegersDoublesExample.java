package com.mindflytech.education.operator;

public class DivisionIntegersDoublesExample {
    public static void main(String[] args) {
        System.out.println("10 / 4: " + 10 / 4);
        System.out.println("10 / 4d: " + 10 / 4d);
        System.out.println("10d / 4: " + 10d / 4);
        System.out.println("10d / 4d: " + 10d / 4d);
        double thisIsAnIntegerStoredAsDouble = 10 / 4;
        System.out.println("10 / 4 stored as double: " + thisIsAnIntegerStoredAsDouble);
    }
}
