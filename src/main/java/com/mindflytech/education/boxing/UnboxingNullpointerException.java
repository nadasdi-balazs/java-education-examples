package com.mindflytech.education.boxing;

public class UnboxingNullpointerException {
    public static void main(String[] args) {
        Integer thisIsNull = null;
        int unboxingThrowsNpe = thisIsNull;
    }
}
