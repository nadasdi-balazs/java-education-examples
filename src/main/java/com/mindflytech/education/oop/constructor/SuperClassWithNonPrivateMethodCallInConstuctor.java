package com.mindflytech.education.oop.constructor;

import lombok.ToString;

@ToString
public class SuperClassWithNonPrivateMethodCallInConstuctor {
    private final String parameter;

    public SuperClassWithNonPrivateMethodCallInConstuctor(String parameter) {
        System.out.println("-- superclass constructor called with parameter: '" + parameter + "'");
        verifyString(parameter);
        this.parameter = parameter;
    }

    /*final*/ void verifyString(String parameter) {
        System.out.println("-- super.verifyString called with parameter: '" + parameter
                + "', will throw exception if there IS @ ! or ' in it");
        boolean containsAt = parameter.contains("@");
        boolean containsExclamation = parameter.contains("!");
        boolean containsApostrophe = parameter.contains("'");
        if(containsAt || containsExclamation || containsApostrophe) {
            String message = "Class cannot be created with a parameter containg @, ! or ': " + parameter;
            System.err.println(message);
            throw new IllegalArgumentException(message);
        }
    }
}
