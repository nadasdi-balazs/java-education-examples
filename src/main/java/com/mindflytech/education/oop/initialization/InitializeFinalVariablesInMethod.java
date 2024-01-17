package com.mindflytech.education.oop.initialization;

import java.util.Random;

public class InitializeFinalVariablesInMethod {
    private final String stringField;
    private final int intField;
    private int nonFinalInt = 42;
    
    public InitializeFinalVariablesInMethod() {
        this.intField = initializeIntFieldRandomly();
        this.stringField = initializeStringFieldWithTimestamp();
    }

    private int initializeIntFieldRandomly() {
        Random random = new Random();
        final int value;
        value = random.nextInt(100);
        return value;
    }

    //compiler warning
    //'private' method declared 'final'
    private final String initializeStringFieldWithTimestamp() {
        String stringField = "machine_generated_" + System.currentTimeMillis();
        return stringField;
    }

    private void lambdaExpressionFinalVariableCaptureTest() {
        String thisIsEffectivelyFinal = "this is effectively final";
        if(System.currentTimeMillis() % 2 == 0) {
            nonFinalInt = 27;
            //if this is assigned here, there is a compile error in the lambda expression:
            //Variable 'thisIsEffectivelyFinal' is accessed from within inner class, needs to be final or effectively final
            //or if lambda
            //Variable used in lambda expression should be final or effectively final
//            thisIsEffectivelyFinal = "this is not final";
        }
        Runnable runner = () -> System.out.println("-- Runnable runs, capturing final stringField = '" + stringField
                + "', effectively final variable: '" + thisIsEffectivelyFinal
                + "' and a non-final field: " + InitializeFinalVariablesInMethod.this.nonFinalInt);
        runner.run();
    }

    public static void main(String[] args) {
        InitializeFinalVariablesInMethod finalInit = new InitializeFinalVariablesInMethod();
        finalInit.lambdaExpressionFinalVariableCaptureTest();
    }

//    public InitializeFinalVariablesInMethod() {
//        initializeIntFieldRandomly();
//        initializeStringFieldWithTimestamp();
//    }

//    private void initializeIntFieldRandomly() {
//        Random random = new Random();
//        //compile error:
//        //here:
//        //Cannot assign a value to final variable 'intField'
//        //at the field declaration:
//        //Variable 'intField' might not have been initialized
//        this.intField = random.nextInt(100);
//    }
//
//    private void initializeStringFieldWithTimestamp() {
//        this.stringField = "machine_generated_" + System.currentTimeMillis();
//    }
}
