package com.baeldung.tutorial.concurrency;

import lombok.extern.log4j.Log4j2;

import java.util.Random;

@Log4j2
public class ThreadSolution extends Thread{
    private final String name;

    private Random random = new Random();

    public ThreadSolution(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        String messageTemplate = "-- ThreadSolution " + name + ": ";
        log.info(messageTemplate + "entered thread");
        int randomSleepTime = random.nextInt(1, 1000);
        try {
            Thread.sleep(randomSleepTime);
        } catch (InterruptedException e) {
            log.error(messageTemplate + "interrupted during execution");
            throw new RuntimeException(e);
        }
        log.info(messageTemplate + "exited thread");
    }
}
