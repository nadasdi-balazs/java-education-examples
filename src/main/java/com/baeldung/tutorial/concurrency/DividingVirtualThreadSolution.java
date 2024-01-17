package com.baeldung.tutorial.concurrency;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DividingVirtualThreadSolution {
    private final String name;

    public DividingVirtualThreadSolution(String name) {
        this.name = name;
    }

    public void run() {
        log.info("-- within run");
        Thread.ofVirtual()
                .name(name)
                .start(() -> {
                    log.info("-- entered thread");
                    ConcurrencyUtil.doDivisionWork(getClass(), name);
                    log.info("-- will exit thread");
                });
    }

    private void work() {
        log.info("-- entered thread");
        ConcurrencyUtil.doDivisionWork(getClass(), name);
        log.info("-- will exit thread");
    }
}
