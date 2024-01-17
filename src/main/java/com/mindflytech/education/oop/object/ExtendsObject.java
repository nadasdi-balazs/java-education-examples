package com.mindflytech.education.oop.object;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ExtendsObject extends Object{
    //Yep, it is legal to explicitly declare Object as superclass
    public ExtendsObject() {
        super();
        System.out.println("-- ExtendObject's methods: " +
                Arrays.asList(getClass().getMethods())
                        .stream()
                        .map(Method::toString)
                        .collect(Collectors.joining("\n")));
    }

    public static void main(String[] args) {
        new ExtendsObject();
    }
}
