package com.mindflytech.education.oop.instantiation;

public class DifferenceBetweenReferenceAndReferredObject {
    static class Something {
        private final String name;

        public Something(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Something instance = new Something("I am an instance");
        Something otherSomething = instance;
        System.out.println("-- name of instance: " + instance.getName());
        System.out.println("-- name of otherSomething: " + otherSomething.getName());
        otherSomething = null;
        System.out.println("-- name of instance: " + instance.getName());
        instance = null;
    }
}
