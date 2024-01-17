package com.mindflytech.education.oop.method;

public class FinalMethods {
    //compiler warning: useless, but understandable and causes no harm
    //'private' method declared 'final'
    private final void doStuff() {
        System.out.println("does stuff");
    }

    final void doStuffAgain() {
        System.out.println("does stuff");
    }

    //compile error
    //Illegal combination of modifiers: 'abstract' and 'final'
//    abstract final void thisWontCompile() {
//        System.out.println("does stuff");
//    }

    public interface NoFinalMethodsInInterfaces {
        //compile error
        //Modifier 'final' not allowed here
//        final void doStuff();

        //compile error
        //Modifier 'final' not allowed here
//        default final void doStuff();

        //compile error
        //Modifier 'final' not allowed here
//        private final void doStuff() {
//            System.out.println("does stuff");
//        }
    }
}
