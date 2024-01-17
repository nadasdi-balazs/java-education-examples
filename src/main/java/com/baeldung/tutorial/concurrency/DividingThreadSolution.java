package com.baeldung.tutorial.concurrency;

import lombok.extern.log4j.Log4j2;

import static com.baeldung.tutorial.concurrency.ConcurrencyUtil.doDivisionWork;

@Log4j2
public class DividingThreadSolution extends Thread {
    private final String name;

    public DividingThreadSolution(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        doDivisionWork(getClass(), name);
    }
}
