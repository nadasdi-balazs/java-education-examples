package com.mindflytech.education.oop.instantiation;

public class ConstructorCircularCall extends UselessSuperclass{
    private String something;
    private int number;
    private double value;

    public static void main(String[] args) {
        ConstructorCircularCall circular = new ConstructorCircularCall();
    }

    //compile error
    //Recursive constructor invocation
    public ConstructorCircularCall() {
        this("default");
    }

    public ConstructorCircularCall(String stringParameter) {
//        this(stringParameter);
        this(stringParameter, -1);
        this.something = stringParameter;
    }

    ConstructorCircularCall(String stringParameter, int intParameter) {
//        this();
        this.something = stringParameter;
        this.number = intParameter;
    }
}

class UselessSuperclass {
    public UselessSuperclass() {
        System.out.println("I am important, I am being called");
    }
}
