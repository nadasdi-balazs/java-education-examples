package com.baeldung.tutorial.nestmates;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class LambdaTest {
    static {
        System.out.println("-- initialization block runs");
        log.info("same from log4j2");
    }
    public static void main(String[] args) {
        log.info("-- main runs");
        LambdaTest test = new LambdaTest();
        test.doStuff();
    }

    private void doStuff() {
        List<String> someStrings = List.of("one", "Two", "three");
        List<String> upperCaseStrings = someStrings.stream()
                .map(this::manipulateWithString)
                .collect(Collectors.toList());
//        List<String> upperCaseStrings = someStrings.stream()
//                .map(new Function<String, String>)() {
//
//        })
//                .collect(Collectors.toList());
        log.info(upperCaseStrings);
    }

    private String manipulateWithString(String s) {
        log.info(s);
        return s.toUpperCase();
    }

    static interface UpperCaseMapper {
        String toUpperCase(String lowerCase);
    }
}
