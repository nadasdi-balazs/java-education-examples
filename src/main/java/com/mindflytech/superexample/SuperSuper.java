package com.mindflytech.superexample;

public class SuperSuper extends Object {
    protected final String visibleInSubclass;

    public SuperSuper() {
        this("default-parameter");
        System.out.println("SuperSuper constructor called");
    }

    public SuperSuper(String parameter) {
        System.out.println("SuperSuper constructor called with parameter: '" + parameter + "'");
        this.visibleInSubclass = parameter;
    }

    public void overrideable() {
        System.out.println("-- SuperSuper.overrideable called");
    }

//    public SuperExample overrideableSuper() {
//        System.out.println("-- SuperSuper.overrideableSuper (returns SuperExample) called");
//        //compile error
//        //'.' expected
//        return super;
//    }
}
