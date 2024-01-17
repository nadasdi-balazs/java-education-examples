package com.mindflytech.education.oop.classmember;

public class InterfaceDefaultMethodOverride {
    interface Behavior {
        default void behavior() {
            System.out.println("-- Behavior.behavior is called");
        }

        //compiler error
        //Modifier 'final' not allowed here
//        final default void method() {
//
//        }
    }

    static class Class implements Behavior {
        @Override
        public void behavior() {
            Behavior.super.behavior();
            System.out.println("-- Class.behavior is called");
        }
    }

    public static void main(String[] args) {
        Class clazz = new Class();
        clazz.behavior();
    }
}
