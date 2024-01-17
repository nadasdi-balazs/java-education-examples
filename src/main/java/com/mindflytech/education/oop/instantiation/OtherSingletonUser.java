package com.mindflytech.education.oop.instantiation;

public class OtherSingletonUser {
    public void useSingleton() {
        SingletonPatternWithStaticInitializerBlock instance = SingletonPatternWithStaticInitializerBlock.getInstance();
        instance.doSomething("with this");
    }
}
