package com.baeldung.tutorial.concurrency;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class RunnableSolution implements Runnable{
    private final String name;

    public RunnableSolution(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        log.info("-- RunnableSolution.run " + name + " started to run");
        log.info("-- RunnableSolution.run " + name + " finished running");
    }
}
