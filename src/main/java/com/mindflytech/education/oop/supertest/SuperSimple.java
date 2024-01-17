package com.mindflytech.education.oop.supertest;

public class SuperSimple {
    protected static final String KEY = "SUPER_KEY";

    public SuperSimple(String description) {
        System.out.println("-- SuperSimple constructor called with: '" + description + "'");
    }

    protected String generateDescription() {
        String generatedDescription = "SuperSimple.generateDescription description generated";
        return generatedDescription;
    }
}

class Simple extends SuperSimple {
    public Simple() {
        super("default description in Simple");
    }

    @Override
    public String  generateDescription() {
        String localDescription = "Simple.generateDescription description generated";
        String superDescription = super.generateDescription();
        String generatedDescription = localDescription + " : " + superDescription  + " : " + super.KEY;
        return generatedDescription;
    }

    public static void main(String[] args) {
        Simple simple = new Simple();
        String description = simple.generateDescription();
        System.out.println("-- generated description: '" + description + "'");
    }
}
