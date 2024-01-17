package com.mindflytech.education.inheritence;

public interface DiamondInterfaceTwo {
    default void thisWillCauseAmbiguity() {
        System.out.println("-- DiamondInterfaceTwo.thisWillCauseAmbiguity");
    }
}
