package com.mindflytech.education.oop.sealed;

public sealed class SampleSealed permits PermittedSealedClass,
        PermittedNonSealedClass, PermittedFinalClass {}

sealed class PermittedSealedClass extends SampleSealed permits AnotherFinalClass {}

final class AnotherFinalClass extends PermittedSealedClass {}

final class PermittedFinalClass extends SampleSealed {}

non-sealed class PermittedNonSealedClass extends SampleSealed {}

final class RunawayNonSealedOption extends PermittedNonSealedClass {}

class SecondRunaway extends PermittedNonSealedClass {}

