package com.mindflytech.education.generics;

public class StringSubClass extends StringSuperClass {
    public static StringSubClass of(String string) {
        return new StringSubClass(string);
    }

    public StringSubClass(String string) {
        super(string);
    }
}
