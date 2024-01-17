package com.mindflytech.education.inheritence;

public interface Doable {
    String data = "String data Doable";

    default void method() {
        System.out.println("-- method called from Doable");
    }

    void anotherFunctionality();

    void thirdFunctionality();

    void dontTouchThis();

    default void yetAnotherFunctionality() {
        System.out.println("-- yetAnotherFunctionality in Doable");
    }

    static void functionality() {
        System.out.println("-- functionality in Doable");
    }

    default void defaultNameClashOnInterfaces() {
        System.out.println("-- Doable.defaultNameClashOnInterfaces");
    }

    static void staticNameClashOnInterfaces() {
        System.out.println("-- Doable.staticNameClashOnInterfaces");
    }
}
