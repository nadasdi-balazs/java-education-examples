package com.baeldung.tutorial.concurrency;

import lombok.extern.log4j.Log4j2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class ConcurrencyRunner {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        runForkJoinPoolExample();

//        runHundredVirtualThreads();
//        runHundredPlatformThreads();
//        runHundredCompletableFutureAsync();
//        runHundredCompletableSupplyAsync();
//        runOtherAsyncSolution();
//        runOtherAsyncSolution2();
//        runOtherAsyncSolution3();
//        runCombinedFutureSolutionFixed();

//        DividingThreadSolution thread1 = new DividingThreadSolution("Thread One");
//        ThreadSolution thread2 = new ThreadSolution("Thread Two");
//        ThreadSolution thread3 = new ThreadSolution("Thread Three");
//        thread1.run();
//        log.info("-- first thread started");
//        thread2.run();
//        log.info("-- second thread started");
//        thread3.run();
//        log.info("-- third thread started");

//        RunnableSolution runnable1 = new RunnableSolution("Thread One");
//        RunnableSolution runnable2 = new RunnableSolution("Thread Two");
//        RunnableSolution runnable3 = new RunnableSolution("Thread Three");
//
//        runnable1.run();
//        log.info("-- first thread started");
//        runnable2.run();
//        log.info("-- second thread started");
//        runnable3.run();
//        log.info("-- third thread started");
    }

    private static void runForkJoinPoolExample() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        List<ForkJoinTask<String>> results = new LinkedList<>();
        List<Callable<String>> callables = new LinkedList<>();

        Callable<String> callable1 = () -> {
            String name = "callable_1";
            genericCallableTaskContinuation(name);
            return name;
        };
        callables.add(callable1);
//        ForkJoinTask<String> task1 = forkJoinPool.submit(callable1);
//        log.info("-- task1: " + task1);
//        results.add(task1);

        Callable<String> callable2 = () -> {
            String name = "callable_2";
            genericCallableTaskContinuation(name);
            return name;
        };
        callables.add(callable2);
//        ForkJoinTask<String> task2 = forkJoinPool.submit(callable2);
//        log.info("-- task1: " + task2);
//        results.add(task2);

        Callable<String> callable3 = () -> {
            String name = "callable_3";
            genericCallableTaskContinuation(name);
            return name;
        };
        callables.add(callable3);
//        ForkJoinTask<String> task3 = forkJoinPool.submit(callable3);
//        log.info("-- task3: " + task3);
//        results.add(task3);

        Callable<String> callable4 = () -> {
            String name = "callable_4";
            genericCallableTaskContinuation(name);
            return name;
        };
        callables.add(callable4);
//        ForkJoinTask<String> task4 = forkJoinPool.submit(callable4);
//        log.info("-- task4: " + task1);
//        results.add(task4);

        Callable<String> callable5 = () -> {
            String name = "callable_5";
            genericCallableTaskContinuation(name);
            return name;
        };
        callables.add(callable5);
//        ForkJoinTask<String> task5 = forkJoinPool.submit(callable5);
//        log.info("-- task5: " + task5);
//        results.add(task5);

        List<ForkJoinTask<String>> tasks = callables.stream()
                .map(callable -> {
                    ForkJoinTask<String> task = forkJoinPool.submit(callable);
                    log.info("-- task submitted, its state: '" + task.state() + "'");
                    return task;
                })
                .collect(Collectors.toList());

        String joinedResult = tasks.stream()
                .map(task -> {
                    log.info("-- while mapping, entered lambda expression");
//                    String join = task.join();
                    String join = null;
                    try {
                        join = task.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("-- while mapping, task's result is: '" + join + "'");
                    return join;
                })
                .collect(Collectors.joining(";"));
        log.info("-- joined result of all tasks: '" + joinedResult + "'");
        forkJoinPool.shutdown();
    }

    private static void genericCallableTaskContinuation(String name) {
        log.info("-- " + name + " starts to run");
        ConcurrencyUtil.doDivisionWork(Callable.class, name);
        log.info("-- " + name + " finished running");
    }

    private static void runCombinedFutureSolutionFixed() throws InterruptedException {
        CompletableFuture<String>[] futures = new CompletableFuture[100];
        log.info("-- will create 100 futures");
        for (int i = 0; i < 100; i++) {
            log.info("---- before future created");
//            DividingCompletableFuture taskProvider = new DividingCompletableFuture("future_" + i);
//            CompletableFuture<String> future = CompletableFuture.supplyAsync(taskProvider::provideAsyncTaskString);
            final int index = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                log.info("-- DividingCompletableFuture.calculateAsync, within lambda, before task");
                String name = "future_" + index;
                ConcurrencyUtil.doDivisionWork(DividingCompletableFuture.class, name);
                log.info("-- DividingCompletableFuture.calculateAsync, within lambda, after task");
                return name;
            });
            futures[i] = future;
            log.info("---- after future created: '" + future + "'");
        }
        Thread.sleep(1000L);
//        CompletableFuture<Void> combined = CompletableFuture.allOf(futures);
//        Void unused = combined.get();
//        log.info("-- end result: '" + unused + "'");
        String combinedResult = Stream.of(futures)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(", "));
        log.info("-- combinedResult: '" + combinedResult + "'");
    }

    private static void runCombinedFutureSolution() throws ExecutionException, InterruptedException {
        List<CompletableFuture<Supplier<String>>> futures = new LinkedList<>();
        log.info("-- will create 100 futures");
        for (int i = 0; i < 100; i++) {
            log.info("---- before future created");
            DividingCompletableFuture taskProvider = new DividingCompletableFuture("future_" + i);
            CompletableFuture<Supplier<String>> future = CompletableFuture.supplyAsync(taskProvider::provideAsyncTask);
            futures.add(future);
            log.info("---- after future created: '" + future + "'");
        }
        CompletableFuture<Void> combined = CompletableFuture.allOf((CompletableFuture<?>) futures);
        Void unused = combined.get();
        log.info("-- end result: '" + unused + "'");
    }

    private static void runOtherAsyncSolution() throws InterruptedException, ExecutionException {
        log.info("-- in main thread, before creating completable futures");
        List<CompletableFuture<String>> results = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            String name = "future_" + i;
            DividingCompletableFuture futureRunner = new DividingCompletableFuture(name);
            CompletableFuture<String> result = futureRunner.runOtherAsync();
            results.add(result);
        }
        Thread.sleep(1000L);
        log.info("-- in main thread, we will read the results of the async callbacks");
//        for(CompletableFuture<String> result : results) {
//            String stringResult = result.get();
//            log.info("---- result of one callback: '" + stringResult + "'");
//        }
//
//        Thread.sleep(1000L);
//        log.info("-- in main thread, completable futures already exited, will also exit main");
    }

    private static void runOtherAsyncSolution3() throws InterruptedException, ExecutionException {
        log.info("-- in main thread, before creating completable futures");
//        List<CompletableFuture<String>> results = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            String name = "future_" + i;
            DividingCompletableFuture futureRunner = new DividingCompletableFuture(name);
//            CompletableFuture<String> result = futureRunner.runOtherAsync3();
            futureRunner.runOtherAsync3();
//            results.add(result);
        }
        Thread.sleep(1000L);
//        log.info("-- in main thread, we will read the results of the async callbacks");
//        for(CompletableFuture<String> result : results) {
//            String stringResult = result.get();
//            log.info("---- result of one callback: '" + stringResult + "'");
//        }
//
//        Thread.sleep(1000L);
        log.info("-- in main thread, completable futures already exited, will also exit main");
    }

    private static void runOtherAsyncSolution2() throws InterruptedException, ExecutionException {
        log.info("-- in main thread, before creating completable futures");
        List<CompletableFuture<String>> results = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            String name = "future_" + i;
            DividingCompletableFuture futureRunner = new DividingCompletableFuture(name);
            CompletableFuture<String> result = futureRunner.runOtherAsync2();
            results.add(result);
        }
        Thread.sleep(1000L);
        log.info("-- in main thread, we will read the results of the async callbacks");
        for (CompletableFuture<String> result : results) {
            String stringResult = result.get();
            log.info("---- result of one callback: '" + stringResult + "'");
        }

        Thread.sleep(1000L);
        log.info("-- in main thread, completable futures already exited, will also exit main");
    }

    private static void runHundredCompletableSupplyAsync() throws InterruptedException, ExecutionException {
        log.info("-- in main thread, before creating completable futures");
        List<CompletableFuture<String>> results = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            String name = "future_" + i;
            DividingCompletableFuture futureRunner = new DividingCompletableFuture(name);
            CompletableFuture<String> result = futureRunner.computeUsingSupplyAsync();
            results.add(result);
        }
        Thread.sleep(1000L);
        log.info("-- in main thread, we will read the results of the async callbacks");
        for (CompletableFuture<String> result : results) {
            String stringResult = result.get();
            log.info("---- result of one callback: '" + stringResult + "'");
        }

        Thread.sleep(1000L);
        log.info("-- in main thread, completable futures already exited, will also exit main");
    }

    private static void runHundredCompletableFutureAsync() throws InterruptedException, ExecutionException {
        log.info("-- in main thread, before creating completable futures");
        for (int i = 0; i < 100; i++) {
            String name = "future_" + i;
            DividingCompletableFuture futureRunner = new DividingCompletableFuture(name);
            futureRunner.run();
        }

        Thread.sleep(1000L);
    }

    private static void runHundredPlatformThreads() throws InterruptedException {
        log.info("-- in main thread, before creating platform threads");
        for (int i = 0; i < 100; i++) {
            String name = "platform " + i;
            DividingThreadSolution platform = new DividingThreadSolution(name);
            platform.run();
        }

        Thread.sleep(1000L);
        log.info("-- in main thread, platform threads already exited, will also exit main");
    }

    private static void runHundredVirtualThreads() throws InterruptedException {
        log.info("-- in main thread, before creating virtual threads");
        for (int i = 0; i < 100; i++) {
            String name = "virtual " + i;
            DividingVirtualThreadSolution virtual = new DividingVirtualThreadSolution(name);
            virtual.run();
        }

        Thread.sleep(1000L);
        log.info("-- in main thread, virtual threads already exited, will also exit main");
    }
}
