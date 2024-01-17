package com.mindflytech.education.generics;

public class StringSuperClass implements CharSequence {
    private final String string;

    public static StringSuperClass of(String string) {
        return new StringSuperClass(string);
    }

    public StringSuperClass(String string) {
        this.string = string;
    }

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public char charAt(int index) {
        return string.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return string.subSequence(start, end);
    }

    @Override
    public String toString() {
        return string;
    }
}
