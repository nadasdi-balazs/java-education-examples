package com.mindflytech.education.inheritence;

public class NameClashExample extends NameClashExampleSuper implements Doable, AnotherDoable, DiamondInterfaceOne, DiamondInterfaceTwo {
    public static void main(String[] args) {
        NameClashExample clash = new NameClashExample();
        clash.clash();

        clash.anotherFunctionality();

        NameClashExampleSuper asSuper = new NameClashExample();
        asSuper.anotherFunctionality();

        Doable asInterface = new NameClashExample();
        asInterface.anotherFunctionality();

    }

    private void clash() {
        System.out.println(doesNotClash);
        method();
        Doable.super.method();
        super.method();
        AnotherDoable.super.method();

        //compile error: Reference to 'data' is ambiguous, both 'NameClashExampleSuper.data' and 'Doable.data' match
//        System.out.println("-- data in NameClashExample: '" + data + "'");
        System.out.println("-- super.data in NameClashExample: '" + super.data + "'");
        System.out.println("-- Doable.data in NameClashExample: '" + Doable.data + "'");
        System.out.println("-- AnotherDoable.data in NameClashExample: '" + AnotherDoable.data + "'");

        System.out.println("-- calling functionality: ");
        functionality();
        Doable.functionality();
        AnotherDoable.functionality();

        yetAnotherFunctionality();
        Doable.super.yetAnotherFunctionality();
        super.thirdFunctionality();

        defaultNameClashOnInterfaces();
    }

    @Override
    public void anotherFunctionality() {
        System.out.println("-- overriding something called anotherFunctionality");
    }

    @Override
    public void thirdFunctionality() {
        System.out.println("-- in NameClashExample.thirdFunctionality, call super methods");
        super.thirdFunctionality();
        //Abstract method 'thirdFunctionality()' cannot be accessed directly
//        Doable.super.thirdFunctionality();
    }

    @Override
    public void yetAnotherFunctionality() {
        System.out.println("-- yetAnotherFunctionality called NameClashExample");
    }

    @Override
    public void defaultNameClashOnInterfaces() {
        System.out.println("-- NameClashExample.defaultNameClashOnInterfaces actually crashes, I must implement it");
        AnotherDoable.super.defaultNameClashOnInterfaces();
        Doable.super.defaultNameClashOnInterfaces();
    }

    //com.mindflytech.education.inheritence.NameClashExample inherits unrelated defaults
    // for thisWillCauseAmbiguity() from types com.mindflytech.education.inheritence.DiamondInterfaceOne and
    // com.mindflytech.education.inheritence.DiamondInterfaceTwo
    @Override
    public void thisWillCauseAmbiguity() {
        System.out.println("-- diamond problem with two default methods, must implement it");
        DiamondInterfaceOne.super.thisWillCauseAmbiguity();
        DiamondInterfaceTwo.super.thisWillCauseAmbiguity();
    }

//    @Override
//    public final void dontTouchThis() {
//        System.out.println("-- this is final dontTouchThis in NameClashExampleSuper");
//    }

    //Static method 'instanceMethodClashesWithSubclassStaticMethod()' in
    // 'com.mindflytech.education.inheritence.NameClashExample' cannot override instance method
    // 'instanceMethodClashesWithSubclassStaticMethod()' in
    // 'com.mindflytech.education.inheritence.NameClashExampleSuper'
//    public static void instanceMethodClashesWithSubclassStaticMethod() {
//        System.out.println("-- NameClashExampleSuper.instanceMethodClashesWithSubclassStaticMethod");
//    }

    //Instance method 'staticMethodClashesWithSubclassInstanceMethod()' in
    // 'com.mindflytech.education.inheritence.NameClashExample' cannot override static method
    // 'staticMethodClashesWithSubclassInstanceMethod()' in
    // 'com.mindflytech.education.inheritence.NameClashExampleSuper'
//    public void staticMethodClashesWithSubclassInstanceMethod() {
//        System.out.println("-- NameClashExampleSuper.staticMethodClashesWithSubclassInstanceMethod");
//    }
}
