package com.mindflytech.education.synchronizedexample;

public class SynchronizedExample {
    private int mutableState = 0;
    public static void main(String[] args) {
        SynchronizedExample example = new SynchronizedExample();
        example.runExample();
    }

    private void runExample() {
        //TODO: Dani kerdese: threadek gyartasa nagy szamban, megnezzuk, az elozo isAlive()-e,
        //ha alive, letrehozunk egy ujat. Megnezni, mennyit bir el a JVM

        Thread.ofVirtual()
                .name("Thread-1")
                .start(this::nonSynchronizedMethod)
                .run();
//                .start((name) -> {
//
//                });
        Thread.ofVirtual()
                .name("Thread-2")
                .start(this::nonSynchronizedMethod)
                .run();
    }

    private void nonSynchronizedMethod() {
        System.out.println("-- non synchronized method " + " mutableState: " + mutableState);
        for (int i = 0; i < 1000; i++) {
            mutableState++;
            System.out.println("-- mutableState: " + mutableState);
        }
        System.out.println("-- non synchronized method exits, mutableState: " + mutableState);
    }
}
