package com.mindflytech.education.oop.interfaces;


public interface InterfacePrivateStaticDefaultMethodExample {
    static void cantAccesUser() {
        cantAcces();
    }

    private static String cantAcces() {
        String message = "you can't access me!";
        System.out.println(message);
        return message;
    }

    private static String alsoCantAcces() {
        String message = "you also can't access me!";
        System.out.println(message);
        return message;
    }

    private void wontBeCalled() {
        System.out.println("You will not see this");
    }

    default void behavior() {
        System.out.println("This is the behavior you inherited for free from the interface");
    }

    default void behaviorTwo() {
        System.out.println("This is the behaviorTwo you inherited for free from the interface");
    }
}

class InterfaceUser {
    static class InterfaceImplementor implements InterfacePrivateStaticDefaultMethodExample {
        public static void interfaceStaticMethodUser() {
            InterfacePrivateStaticDefaultMethodExample.cantAccesUser();
        }

        @Override
        public void behaviorTwo() {
            System.out.println("I hereby override the inherited default method behavior");
        }
    }

    public static void main(String[] args) {
        InterfacePrivateStaticDefaultMethodExample.cantAccesUser();
        InterfaceImplementor implementor = new InterfaceImplementor();
        System.out.println("-- implementor: " + implementor.toString());
        implementor.behavior();
        implementor.behaviorTwo();
        //compile error
        //Static method may be invoked on containing interface class only
//        InterfaceImplementor.cantAccesUser();
//        implementor.cantAccesUser();

        InterfacePrivateStaticDefaultMethodExample anonymous = new InterfacePrivateStaticDefaultMethodExample() {
        };
        anonymous.behavior();
    }
}
