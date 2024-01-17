package com.mindflytech.education.oop.innerclass;

public class InnerClassAndSubclassFinalMethod {
    private final String doSomething() {
        return "I am coming from the superclass";
    }

    class SubAndInnerClass extends InnerClassAndSubclassFinalMethod {
        //compiler error
        //Method does not override method from its superclass
//        @Override
        private final String doSomething() {
            return "I am coming from the superclass";
        }
    }
}
