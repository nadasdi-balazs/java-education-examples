package com.mindflytech.education.oop.interfaces;

public interface InterfaceFinalDefaultMethod {
    //compiler warning
    //Modifier 'final' is redundant for interface fields
    //Modifier 'static' is redundant for interface fields
    static final String CONSTANT = "CONSTANT";

    //compile error
    //Modifier 'final' not allowed here
    /*final*/ default void finalDefaultMethod() {
        System.out.println("-- I can't be overriden in an implementation");
    }

    //compile error
    //Modifier 'final' not allowed here
//    final void cantExist();

    //compile error
    //Modifier 'final' not allowed here
//    static final void canIExist() {
//        System.out.println("not");
//    }
}
