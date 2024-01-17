package com.mindflytech.education.inheritence;

public interface AnotherDoable {
    String data = "String data AnotherDoable";

    default void method() {
        System.out.println("-- method called from AnotherDoable");
    }

    void anotherFunctionality();

    static void functionality() {
        System.out.println("-- functionality in AnotherDoable");
    }

    default void defaultNameClashOnInterfaces() {
        System.out.println("-- AnotherDoable.defaultNameClashOnInterfaces");
    }

    static void staticNameClashOnInterfaces() {
        System.out.println("-- AnotherDoable.staticNameClashOnInterfaces");
    }
}
