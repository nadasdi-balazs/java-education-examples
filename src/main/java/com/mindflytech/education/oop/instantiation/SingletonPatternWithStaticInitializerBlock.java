package com.mindflytech.education.oop.instantiation;

public class SingletonPatternWithStaticInitializerBlock {
    private static final SingletonPatternWithStaticInitializerBlock INSTANCE;

    static {
        System.out.println("-- entered static initializer block");
        INSTANCE = new SingletonPatternWithStaticInitializerBlock();
        System.out.println("-- exited static initializer block");
    }

    public static SingletonPatternWithStaticInitializerBlock getInstance() {
        return INSTANCE;
    }

    public SingletonPatternWithStaticInitializerBlock() {
        System.out.println("-- in constructor");
    }

    public void printSomething() {
        System.out.println("--something");
    }

    public void doSomething(String withThis) {
        System.out.println("-- I prefix the following string: '" + withThis + "'");
    }
}
