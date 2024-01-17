package com.mindflytech.education.polymorphism;

public class PolymorphismSubclassNotVisible {
    public static void main(String[] args) {
        PolymorphismSubclassNotVisible visible = new PolymorphismSubclassNotVisible();
        visible.useVisibleSuperClass();
    }

    private void useVisibleSuperClass() {
        VisibleSuperClass createdInFactory = VisibleSuperClass.factory();
        createdInFactory.functionality();
    }
}


