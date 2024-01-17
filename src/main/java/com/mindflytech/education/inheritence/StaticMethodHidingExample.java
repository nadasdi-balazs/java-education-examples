package com.mindflytech.education.inheritence;

public class StaticMethodHidingExample {
    public static class SuperClass {
        public static void staticMethod() {
            System.out.println("-- SuperClass.staticMethod called");
        }

        public void instanceMethod() {
            System.out.println("-- SuperClass.instanceMethod called");
        }
    }

    public static class SubClass extends SuperClass {
        public static void staticMethod() {
            System.out.println("-- SubClass.staticMethod called");
        }

        @Override
        public void instanceMethod() {
            System.out.println("-- SubClass.instanceMethod called");
            System.out.println("-- I will call my superclass' instance method now:");
            super.instanceMethod();
//            super.staticMethod();
        }
    }

    public static void main(String[] args) {
        SuperClass subClassReferencedAsSuperClass = new SubClass();
        //not a nice way to call a static method, not recommended (but shows hiding behavior)
//        SuperClass.staticMethod();
        subClassReferencedAsSuperClass.staticMethod();
        subClassReferencedAsSuperClass.instanceMethod();
    }
}
