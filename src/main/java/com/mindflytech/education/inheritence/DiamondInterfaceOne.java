package com.mindflytech.education.inheritence;

public interface DiamondInterfaceOne extends DiamondSuperInterface {
    default void thisWillCauseAmbiguity() {
        System.out.println("-- DiamondInterfaceOne.thisWillCauseAmbiguity");
    }
}
