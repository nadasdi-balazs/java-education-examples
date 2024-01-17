package com.mindflytech.education.inheritence;

public abstract class NameClashExampleSuper {
    protected String data = "String data from NameClashExampleSuper";
    protected String doesNotClash = "this one from NameClashExampleSuper does not clash";

    public void method() {
        System.out.println("-- method called from NameClashExampleSuper");
    }

    public final void dontTouchThis() {
        System.out.println("-- this is final dontTouchThis in NameClashExampleSuper");
    }

    static void functionality() {
        System.out.println("-- functionality in NameClashExampleSuper");
    }

    abstract void anotherFunctionality();

    protected void thirdFunctionality() {
        System.out.println("-- thirdFunctionality called in NameClashSuperExample");
    }

    protected abstract void yetAnotherFunctionality();

    public void instanceMethodClashesWithSubclassStaticMethod() {
        System.out.println("-- NameClashExampleSuper.instanceMethodClashesWithSubclassStaticMethod");
    }

    public static void staticMethodClashesWithSubclassInstanceMethod() {
        System.out.println("-- NameClashExampleSuper.staticMethodClashesWithSubclassInstanceMethod");
    }
}
