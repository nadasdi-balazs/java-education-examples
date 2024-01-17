package com.mindflytech.education.oop.instantiation;

import java.lang.reflect.Constructor;

public class ConstructorVisibility {
    private final String something;
    private int number;
    private double value;

//    {
//        this.something = "default value if nothing provided";
//    }

    private ConstructorVisibility(String something, int number, double value) {
        this.something = something;
        this.number = number;
        this.value = value;
    }

    protected ConstructorVisibility(String something, int number) {
        this.something = something;
        this.number = number;
    }

    ConstructorVisibility(String something) {
        this.something = something;
    }

    public ConstructorVisibility() {
        this(null, 0, -1.0);
        System.out.println("-- default no-arg constructor");
    }
}
