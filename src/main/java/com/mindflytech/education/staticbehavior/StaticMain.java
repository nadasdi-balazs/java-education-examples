package com.mindflytech.education.staticbehavior;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StaticMain {
    static {
        //Please don't throw exceptions from static initalizers
//        neverDoThis();
        log.info("-- main static runs");
        StaticExample.fieldFour = "field four gets a value here";
        log.info("-- static variables in main's static block: : '" + StaticExample.fieldTwo + "', '" + StaticExample.fieldThree + "', '" + StaticExample.CONSTANT + "', '" + StaticExample.fieldFour + "'");
    }

    private static void neverDoThis() {
        throw new RuntimeException("");
    }

    /**
     * This is the entry point for StaticMain class
     * @param args
     */
    public static void main(String[] args) {
        log.info("-- main.main runs");
        StaticExample staticExample = new StaticExample();
        staticExample.doSomething();
        staticExample = null;
        log.info("-- staticExample: " + staticExample);
        log.info("-- static variables in main: : '" + StaticExample.fieldTwo + "', '" + StaticExample.fieldThree + "', '" + StaticExample.CONSTANT + "', '"+ StaticExample.fieldFour + "'");

        List<String> cities = new ArrayList() {{
            add("New York");
            add("Rio");
            add("Tokyo");
            log.info("-- during list initialization: " + this.stream().collect(Collectors.joining(",")));
        }

            @Override
            public int size() {
                log.info("-- I have overriden ArrayList.size()");
                return super.size();
            }
        };
        log.info("-- cities after initialization: " + cities.stream().collect(Collectors.joining(",")));
        log.info("-- cities size: " + cities.size());

        String ize = "ize";
        Object hoze = (Object)ize;
        Object otherStuff = new Object();
        String willThrowException = (String) otherStuff;

        String withText;
        int decide = 1;
        if(decide == 1) {
            withText = "one";
        } else {
            withText = "two";
        }
        String withTextTernary = decide == 1 ? "one" : "two";
    }
}
