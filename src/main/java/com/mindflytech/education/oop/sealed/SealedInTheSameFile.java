package com.mindflytech.education.oop.sealed;

public sealed class SealedInTheSameFile {
}

sealed class SubClassInTheSameFileOne extends  SealedInTheSameFile {
}

final class SubSubClassInTheSameFileOne extends SubClassInTheSameFileOne {
}

non-sealed class SubClassInTheSameFileTwo extends SealedInTheSameFile {
}
