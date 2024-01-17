package com.mindflytech.education.oop.classmember;

import com.mindflytech.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitializerBlockExample {
    private static final int CONSTANT;

    private static long classCreationTimestamp;

    private final long instanceCreationTimestamp;
    private final String state;

    static {
        //compile error
        //Initializer must be able to complete normally
//        throw new RuntimeException();
//        checkedExceptionThrower();
//        exceptionThrower();
        classCreationTimestamp = System.currentTimeMillis();
        CONSTANT = classCreationTimestamp % 2 == 0 ? 28 : 27;
    }

    private static void exceptionThrower() {
        throw new RuntimeException("will this prevent JVM from anything?");
    }

    private static void checkedExceptionThrower() throws IOException {
        throw new IOException("will this prevent JVM from anything?");
    }

    {
        System.out.println("-- initializer block executes");
        this.instanceCreationTimestamp = System.currentTimeMillis();
    }

    public InitializerBlockExample() {
        this("default");
        System.out.println("-- default constructor executes");
    }

    public InitializerBlockExample(String state) {
        System.out.println("-- String arg constructor executes");
        this.state = state;
    }

    public static void main(String[] args) {
        InitializerBlockExample initializer = new InitializerBlockExample();

        List<Integer> integerList = new ArrayList<>(){{
            add(27);
            add(28);
            add(29);
        }};
        Utils.prefixedPrintList("-- initialized list: ", integerList);
    }
}
