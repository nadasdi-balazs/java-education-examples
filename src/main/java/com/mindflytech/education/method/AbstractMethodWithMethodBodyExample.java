package com.mindflytech.education.method;

public abstract class AbstractMethodWithMethodBodyExample {
    //compiler error
    //Abstract methods cannot have a body
//    abstract void doSomething() {
//        System.out.println("-- statement here");
//    }

    //compiler error
    //Illegal combination of modifiers: 'abstract' and 'private'
//    private abstract void method();

    //compiler error
    //Illegal combination of modifiers: 'abstract' and 'static'
//    static abstract void method();
}
