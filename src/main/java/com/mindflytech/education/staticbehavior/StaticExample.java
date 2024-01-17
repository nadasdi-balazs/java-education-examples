package com.mindflytech.education.staticbehavior;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Slf4j
public class StaticExample {
    static final String CONSTANT;

    private final String fieldFive;

    private int longInt = 1_000_000;

    private char character = 'c';

//    private int shouldBeDouble = 0.0;

    static String fieldTwo;
    static String fieldThree = "field-three-value";

    /**
     * This is the javadoc of fieldFour
     */
    static String fieldFour;

    String fieldOne;

    //This is a rarely recommended practice
    static {
        log.info("-- entered static initialization block, fieldThree: '" + fieldThree + "'");
        fieldTwo = "field-two-value";
        String anotherStuff = "local variable to static block";
        //FIELD_THREE cannot be referenced from here as it might not have been initialized
        log.info("-- exited static initialization block, fieldTwo, fieldThree, anotherStuff, CONSTANT: '" + fieldTwo + "', '" + fieldThree + "', '" + anotherStuff /*+ "', '"
                + FIELD_THREE + "'"*/);
        CONSTANT = "field-three-value";
        log.info("-- exited static initialization block, fieldTwo, fieldThree, anotherStuff, CONSTANT: '" + fieldTwo + "', '" + fieldThree + "', '" + anotherStuff + "', '"
                + CONSTANT + "'");
        int notInitialized ;
        notInitialized = 3;
        if(notInitialized > 3) {
            System.out.println("greater than three");
        }
        String same = "aaa";
        String anotherSame = "aaa";
        Integer thisIsAnObject = 3;

    }

    //this is almost never recommended
    {
        log.info("-- entered initialization block, fieldOne: '" + fieldOne + "'");
        this.fieldOne = "field-one-value";
        fieldFive = "field-five-value";
        log.info("-- exited initialization block, object state: '" + this + "'");
    }

    public StaticExample() {
        log.info("-- entering constructor runs, object state: '" + this + "', static fields: " + fieldTwo + "', '" + fieldThree + "', '" + CONSTANT + "'");
        fieldOne = "another field one value";
        fieldTwo = "static values can also be changed";
        log.info("-- exiting constructor runs, object state: '" + this + "', static fields: " + fieldTwo + "', '" + fieldThree + "', '" + CONSTANT + "'");
    }

    public void doSomething() {
        log.info("-- doSomething, object state: '" + this + "'");
        fieldTwo = "another value for field two";
        log.info("-- doSomething, object state after change: '" + this + "'");
        Object lock = new Object();
        synchronized (lock) {
            log.info("-- within synchronized");
            fieldOne = "new value from synchronized block";
            log.info("-- object state: " + this);
        }
    }
}
