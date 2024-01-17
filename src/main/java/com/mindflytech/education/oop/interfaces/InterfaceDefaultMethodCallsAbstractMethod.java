package com.mindflytech.education.oop.interfaces;

import java.time.LocalDateTime;

public interface InterfaceDefaultMethodCallsAbstractMethod {
    void run();

    default void logAndRun() {
        System.out.println("Thread started at: " + LocalDateTime.now());
        run();
        System.out.println("Thread stopped at: " + LocalDateTime.now());
    }
}

class Implementor implements InterfaceDefaultMethodCallsAbstractMethod, Runnable {
    @Override
    public void run() {
        System.out.println("-- I am doing my work here");
    }

    public static void main(String[] args) {
        Implementor implementor = new Implementor();
        implementor.logAndRun();
    }
}

class CanRun implements Runnable {
    @Override
    public void run() {
        System.out.println("-- Class CanRun implemented void run()");
    }
}

/**
 * This class demonstrates the following behavior:
 * <br>"Methods that are already overridden by other candidates are ignored."
 * <br> (Also "Inherited instance methods from classes can override abstract interface methods")
 * <br>So we don't need to implement run() from InterfaceDefault... interface, because it has already been
 * implemented by our superclass CanRun
 */
class OtherImplementor extends CanRun implements InterfaceDefaultMethodCallsAbstractMethod {
    public static void main(String[] args) {
        OtherImplementor implementor = new OtherImplementor();
        implementor.run();
        implementor.logAndRun();
    }
}
