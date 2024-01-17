package com.mindflytech.education.lambda;

import static com.mindflytech.education.lambda.LambdaReturnsWithSubclassOfReturnTypeDefinedInFuncionalInterface.DATE_TIME_STRING_PRINTER;

public interface Functional {
    SuperType abstractMethod();

    default void printer() {
        DATE_TIME_STRING_PRINTER.run();
    }
}
