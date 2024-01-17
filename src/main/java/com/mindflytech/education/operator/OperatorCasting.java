package com.mindflytech.education.operator;

public class OperatorCasting {
    public static void main(String[] args) {
        Integer integer = 27;
        //compile error
        //Inconvertible types; cannot cast 'java.lang.Integer' to 'java.lang.Double'
//        Double asDouble = (Double)integer;
        Number asNumber = (Number)integer;
        System.out.println("-- type of integer: " + integer.getClass() + ", type of  asNumber: " + asNumber.getClass());
    }
}
