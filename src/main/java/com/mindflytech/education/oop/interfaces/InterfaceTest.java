package com.mindflytech.education.oop.interfaces;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.Constants;

import java.io.FileNotFoundException;
import java.io.IOException;

@Log4j2
public class InterfaceTest {
    public interface A {
        String data = "data";

        void method() throws FileNotFoundException;

        default void  second() {
            System.out.println("-- interface A, second(), default implementation");
            generateData();
        }

        private String generateData() {
            return "" + System.currentTimeMillis();
        }
    }

    public interface B {
//        String method();
        void method() throws FileNotFoundException;

        default void  second() {
            System.out.println("-- interface B, second(), default implementation");
        }
    }

    static class Problem implements A, B {
        @Override
        public void method() throws FileNotFoundException {
            //Compile error
            //Cannot assign a value to final variable 'data'
//            data = "something else";
            System.out.println("bla bla");
            second();
        }

        @Override
        public void second() {
            System.out.println("-- second's implementation in Problem class");
            B.super.second();
            A.super.second();
        }

//        @Override
//        public String method() {
//            System.out.println("bla bla");
//        }
    }

    static class ProblemChild extends Problem {
        @Override
//        public void method() throws Constants.ConstantException {
        public void method() throws FileNotFoundException {

        }
    }

    static class UserClass {
        private Problem problem = new Problem();
        public void doStuff() {
            try {
                problem.method();
            } catch (FileNotFoundException e) {
                log.error("-- Exception caught: " + e);
            }
        }

        public static void main(String args[]) {
            UserClass clazz = new UserClass();
            clazz.doStuff();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        A implementation = new A() {
            @Override
            public void method() throws FileNotFoundException {
                System.out.println("-- method called in anonymous inner class");
            }
        };
        implementation.method();
        Object asObject = implementation;
    }
}

interface NonPublic extends Runnable, CharSequence {
    //compile error
    //Modifier 'private' not allowed here
//    private static final String CONSTANT = "I am consant";

    //Compile error
    //not allowed in interface
//    {}

    //Compile error
    //not allowed in interface
//    static {}
}
