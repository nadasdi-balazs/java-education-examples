package com.mindflytech.education.oop.sealed;

public sealed class SealedClass implements SealedInterface permits FinalClass {
    public final void finalMethod() {
        System.out.println("I am final");
    }
}
