package com.mindflytech.education.oop.instantiation;

public class SingletonUser {
    public static void main(String[] args) {
        SingletonPatternWithStaticInitializerBlock instance = SingletonPatternWithStaticInitializerBlock.getInstance();
        instance.printSomething();
        OtherSingletonUser otherUser = new OtherSingletonUser();
        otherUser.useSingleton();
    }
}
