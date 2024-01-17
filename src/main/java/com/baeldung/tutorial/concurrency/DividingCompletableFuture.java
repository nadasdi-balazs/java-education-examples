package com.baeldung.tutorial.concurrency;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.*;
import java.util.function.Supplier;

@Log4j2
public class DividingCompletableFuture {
    private final String name;

    public DividingCompletableFuture(String name) {
        this.name = name;
    }

    public void run() throws ExecutionException, InterruptedException {
        log.info("-- DividingCompletableFuture.run called");
        Future<String> future = calculateAsync();
        log.info("-- DividingCompletableFuture.run, future created: " + future.toString());
        String result = future.get();
        log.info("-- DividingCompletableFuture.run, result: '" + future + "', result is: '" + result + "'");
    }

    public CompletableFuture<String> computeUsingSupplyAsync() {
        CompletableFuture<String> callbackResult = CompletableFuture.supplyAsync(() -> {
            log.info("-- DividingCompletableFuture.computeUsingSupplyAsync, within lambda, before task");
            ConcurrencyUtil.doDivisionWork(getClass(), name);
            log.info("-- DividingCompletableFuture.computeUsingSupplyAsync, within lambda, after task");
            return "ize";
        });
        return callbackResult;
    }

    public Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newVirtualThreadPerTaskExecutor().submit(() -> {
//        Executors.newCachedThreadPool().submit(() -> {
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, before task");
            ConcurrencyUtil.doDivisionWork(getClass(), name);
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, after task");
            completableFuture.complete(name);
            log.info("-- DividingCompletableFuture.calculateAsync, future completed");
            return "ize: '" + name + "'";
        });

        return completableFuture;
    }

    public CompletableFuture runOtherAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, before task");
            ConcurrencyUtil.doDivisionWork(getClass(), name);
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, after task");
//            completableFuture.complete(name);
            log.info("-- DividingCompletableFuture.calculateAsync, future completed");
        });
        return completableFuture;
    }

    public CompletableFuture runOtherAsync2() {
        Runnable task = () -> {
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, before task");
            ConcurrencyUtil.doDivisionWork(DividingCompletableFuture.class, name);
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, after task");
            log.info("-- DividingCompletableFuture.calculateAsync, future completed");
        };
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(task);
        return completableFuture;
    }

    public void runOtherAsync3() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        Runnable task = () -> {
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, before task");
            ConcurrencyUtil.doDivisionWork(DividingCompletableFuture.class, name);
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, after task");
            log.info("-- DividingCompletableFuture.calculateAsync, future completed");
        };
        forkJoinPool.execute(task);
    }


    public Supplier<String> provideAsyncTask() {
        Supplier<String> task = () -> {
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, before task");
            ConcurrencyUtil.doDivisionWork(DividingCompletableFuture.class, name);
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, after task");
            return name + "_completed";
        };
        return task;
    }

    public String provideAsyncTaskString() {
        Runnable task = () -> {
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, before task");
            ConcurrencyUtil.doDivisionWork(DividingCompletableFuture.class, name);
            log.info("-- DividingCompletableFuture.calculateAsync, within lambda, after task");
        };
        return name + "_completed";
    }
}
