package com.mindflytech.education.instanceofexample;

public class InstanceofExample {
    private static class Apple {

    }

    private static class Pear {

    }

    private interface OwnRunnable {
        void run();
    }

    private static class RunnableApple extends Apple implements OwnRunnable {
        @Override
        public void run() {
            System.out.println("-- runnable apple runs");
        }
    }

    private static class RunnablePear extends Pear implements OwnRunnable {
        @Override
        public void run() {
            System.out.println("-- runnable pear runs");
        }
    }

    public static void main(String[] args) {
        InstanceofExample example = new InstanceofExample();
        example.runExample();
    }

    private void runExample() {
        Apple apple = new Apple();
        //Incompatible types, cannot cast - compile error
//        if(!(apple instanceof Pear)) {
//            System.out.println("-- apple is not a pear");
//        }

        Apple runnableApple = new RunnableApple();
        //incompatible types, error
//        if (!(runnableApple instanceof Pear)) {
//            System.out.println("-- runnable apple a pear");
//        }
        if(runnableApple instanceof OwnRunnable) {
            System.out.println("-- runnable apple is runnable");
        }
        if(runnableApple instanceof Apple) {
            System.out.println("-- runnable apple is an apple");
        }

        OwnRunnable onlyInterfaceReference;
        if(runnableApple instanceof OwnRunnable) {
            onlyInterfaceReference = (OwnRunnable)runnableApple;
        } else {
            Pear runnablePear = new RunnablePear();
            //TODO: should have done instanceof type check before casting
            onlyInterfaceReference = (OwnRunnable)runnablePear;
        }
        if(onlyInterfaceReference instanceof Pear) {
            System.out.println("-- runnable apple is a pear");
        } else {
            System.out.println("-- runnable apple is NOT a pear");
        }

    }
}
