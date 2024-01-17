package com.mindflytech.education.oop.sealed;

public sealed interface SealedInterface permits
        AnotherSealedInterface, NonSealedInterface,
        SealedClass, NonSealedClass, FinalClass,
        InTheSameFileClass.StaticInnerClass,
        SealedEnum, SealedRecord,
        InTheSameFileClass {
}

final class InTheSameFileClass implements SealedInterface {
    private sealed class PrivateSealedClass permits SingleSubClass {}
    final class SingleSubClass extends PrivateSealedClass {}
    static final class StaticInnerClass implements SealedInterface {}
}



