package com.mindflytech.superexample;


public class SuperExample extends SuperSuper {
    public SuperExample() {
        super("default parameter");
        System.out.println("SuperExample no-arg constructor called");
    }

    public SuperExample(String parameter) {
        this();
        //compile error
        //Call to 'super()' must be first statement in constructor body
//        super();
        System.out.println("SuperExample constructor called with parameter: '" + parameter + "'");
    }

    public void demonstrate() {
        System.out.println("-- super's visibleInSubclass field referenced without super: '" + visibleInSubclass + "'");
        System.out.println("-- super's visibleInSubclass field referenced with super: '" + super.visibleInSubclass + "'");
        overrideable();
        super.overrideable();
    }

//    public SuperSuper returnSuper() {
//        //compile error
//        //'.' expected
//        return super;
//    }

    @Override
    public void overrideable() {
        System.out.println("-- SuperExample.overrideable called");
    }

    public static void main(String[] args) {
        SuperExample superExample = new SuperExample();
        superExample.demonstrate();
    }
}
