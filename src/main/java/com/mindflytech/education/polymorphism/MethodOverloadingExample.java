package com.mindflytech.education.polymorphism;

public class MethodOverloadingExample {

    //Compile error:
    // 'generateString()' clashes with 'generateString()'; both methods have same erasure
    //'generateString()' is already defined in 'com.mindflytech.education.polymorphism.MethodOverloadingExample'
//    public CharSequence generateString() {
//        return "" + System.currentTimeMillis();
//    }

    //Compile error:
    //'generateString()' is already defined in 'com.mindflytech.education.polymorphism.MethodOverloadingExample
//    public String generateString() {
//        return "" + System.currentTimeMillis();
//    }

    //Compile error:
    //'generateString()' is already defined in 'com.mindflytech.education.polymorphism.MethodOverloadingExample'
//    public void generateString() {
//    }
}
