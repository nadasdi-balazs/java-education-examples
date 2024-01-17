package com.mindflytech.education.oop.constructor;

public class MethodCalledFromConstructorBetterBeFinal {
    public static void main(String[] args) {
        //this will throw an exception
        SubClassOverridesParentMethodCalledFromParentConstructor subClass2 = new SubClassOverridesParentMethodCalledFromParentConstructor("!@'parameter");
        System.out.println("-- subClass2 instantiated: " + subClass2);
        SubClassOverridesParentMethodCalledFromParentConstructor subClass = new SubClassOverridesParentMethodCalledFromParentConstructor("parameter");
        System.out.println("-- this will not be seen, constructor throws exception");
        //this will not throw an exception
        SuperClassWithNonPrivateMethodCallInConstuctor superClass = new SuperClassWithNonPrivateMethodCallInConstuctor("parameter");
    }
}
