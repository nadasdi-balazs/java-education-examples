package com.mindflytech.education.oop;

public class ConstructorMisunderstood {
    public ConstructorMisunderstood() {
        System.out.println("-- this is the constructor");
    }

    public void ConstructorMisunderstood() {
        System.out.println("-- this is not a constructor");
    }

    public static void main(String[] args) {
        ConstructorMisunderstood misunderstood = new ConstructorMisunderstood();
        misunderstood.ConstructorMisunderstood();
    }
}
