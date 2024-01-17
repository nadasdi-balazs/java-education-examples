package com.mindflytech.education.object;

import com.mindflytech.education.codingchallenge.FibonacciVariations;
import com.mindflytech.superexample.VariousTypesOfSuper;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class GetClassCapabilities {
    public static void main(String[] args) {
        Class<VariousTypesOfSuper> variousTypesOfSuperClass = VariousTypesOfSuper.class;
        Consumer<String> classPrinter = classString -> System.out.println("-- class: " + classString);
        Consumer<String> fieldPrinter = classString -> System.out.println("-- field: " + classString);
        Arrays.stream(variousTypesOfSuperClass.getClasses())
                .map(Objects::toString)
                .forEach(classPrinter);
        Arrays.stream(variousTypesOfSuperClass.getFields())
                .map(Objects::toString)
                .forEach(fieldPrinter);

        Class<FibonacciVariations> fibonacciVariationsClass = FibonacciVariations.class;
        Consumer<String> methodPrinter = classString -> System.out.println("-- method: " + classString);
        Arrays.stream(fibonacciVariationsClass.getMethods())
                .map(Object::toString)
                .forEach(methodPrinter);
        Consumer<String> declaredMethodPrinter =
                classString -> System.out.println("-- declared method: " + classString);
        Arrays.stream(fibonacciVariationsClass.getDeclaredMethods())
                .map(Object::toString)
                .forEach(declaredMethodPrinter);
    }
}
