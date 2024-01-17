package com.baeldung.tutorial.concurrency;

import jdk.incubator.concurrent.StructuredTaskScope;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;

@Slf4j
public class StructuredTaskScopeExample {
    record ResultHolder(Long division, String concatenation){}

    public static void main(String[] args) throws InterruptedException {
        StructuredTaskScopeExample example = new StructuredTaskScopeExample();
        ResultHolder result = example.run();
        log.info("-- everything completed normally, result is: '" + result + "'");
//        log.info("-- everything completed normally, result is:");
    }
    //TODO examine when can an InterruptedException be thrown
    //TODO: string concatenation can be broken down to further subclassing, just for the sake of
    // testing StructuredTaskScope's capabilities
    private ResultHolder run() throws InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            callDivision();
            Future<Long> divisionResult = scope.fork(() -> callDivision());
            Future<String> concatenatedString = scope.fork(() -> callStringConcatenation());

            scope.join();

            return new ResultHolder(divisionResult.resultNow(), concatenatedString.resultNow());
        }
//////        return null;
//        return new ResultHolder(-1L, "this-should-only-be-a-test");
    }
//
    private String callStringConcatenation() {
        log.info("-- string operation started");
        String result = ConcurrencyUtil.doStringOperation(getClass(), "StructuredTaskScopeExample-string-operation");
        log.info("-- string operation started");
        return result;
    }

    private long callDivision() {
        log.info("-- division operation starts");
        long divided = ConcurrencyUtil.doDivisionWork(getClass(), "StructuredTaskScopeExample-division");
        log.info("-- division operation finishes");
        return divided;
    }
}
