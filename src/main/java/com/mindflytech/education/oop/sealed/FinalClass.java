package com.mindflytech.education.oop.sealed;

public final class FinalClass extends SealedClass implements AnotherSealedInterface, SealedInterface {
    //compile error
    //'finalMethod()' cannot override 'finalMethod()' in 'com.baeldung.tutorial.sealed.SealedClass'; overridden method is final
//    @Override
//    public void finalMethod() {
//    }
}
