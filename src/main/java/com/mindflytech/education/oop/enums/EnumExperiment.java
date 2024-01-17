package com.mindflytech.education.oop.enums;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EnumExperiment {
    private String instanceVariable = "I-am-an-instance-variable";
    private static String staticVariable = "I-am-a-static-variable";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        //Compile error
        //Cannot resolve method 'printTrue' in 'TriState'
//        TriState.TRUE.printTrue();

        Method[] triStateMethods = TriState.class.getDeclaredMethods();
        System.out.println("-- TriState class methods: " + Arrays
                .stream(triStateMethods)
                .map(Method::toString)
                .collect(Collectors.joining(", ")));
        TriState enumVar = TriState.getRandom();
        System.out.println("-- random enum: '" + enumVar + "', lowerCase: '" + enumVar.asLowerCase() + "', certainty: '" + enumVar.getCertainty() + "'");
        enumVar.run();
        enumVar.screwUpCertainty();
        System.out.println("-- enum value after screwing up certainty: '" + enumVar + "'");
        for(TriState state : TriState.values()) {
            System.out.println("-- enum constant: '" + state + "'");
        }
        //switching on enums doesn't have to be exhaustive
        switch(enumVar) {
            case FALSE -> System.out.println("false!");
        }

        LogLevel[] values = LogLevel.values();
        for(LogLevel level : values) {
            System.out.println("-- loglevel constant: " + level);
        }

        EnumExperiment experiment =  new EnumExperiment();
        experiment.holderMethod();

        Class<TriState> triStateClass = TriState.class;
//        TriState newInstance = triStateClass.newInstance();
        Constructor<?>[] constructors = triStateClass.getDeclaredConstructors();
        Constructor<?> constructor = constructors[0];
        constructor.setAccessible(true);
        TriState instance = (TriState)constructor.newInstance("new enum constant", 3, 1.5d);
//        System.out.println("-- new enum instance: '" + instance + "'");
        System.out.println(Arrays.stream(TriState.MAYBE.getClass().getMethods()).map(Method::toString).collect(Collectors.joining("\n")));
    }

    private void holderMethod() {
        String effectivelyFinal = "I-am-an-effectively-final-string-variable";
        enum Boolean {
            TRUE,
            FALSE;

            public void accessVariables() {
                //Compiler error:
                //Non-static variable 'effectivelyFinal' cannot be referenced from a static context
//                System.out.println("-- Boolean.accessVariables() accesses: local variable: '"
//                        + effectivelyFinal + "'");

                //Compile error:
                //Non-static field 'instanceVariable' cannot be referenced from a static context
//                System.out.println("-- Boolean.accessVariables() accesses: instance variable: '"
//                        + instanceVariable + "'");

                System.out.println("-- Boolean.accessVariables() accesses: static variable: '"
                        + staticVariable + "'");

                staticHolderMethod();
                //Compile error
                //Non-static method 'holderMethod()' cannot be referenced from a static context
//                holderMethod();
            }
        }

        Boolean localTrue = Boolean.TRUE;
        System.out.println("-- I declared a local enum: " + localTrue + ", its class: " + localTrue.getClass());
        localTrue.accessVariables();
    }

    public static void staticHolderMethod()  {
        String effectivelyFinal = "I-am-an-effectively-final-string-variable";
        enum Boolean {
            TRUE,
            FALSE;

            public void accessVariables() {
                //Compiler error:
                //Non-static variable 'effectivelyFinal' cannot be referenced from a static context
//                System.out.println("-- Boolean.accessVariables() accesses: local variable: '"
//                        + effectivelyFinal + "'");

                //ompile error:
                //Non-static field 'instanceVariable' cannot be referenced from a static context
//                System.out.println("-- Boolean.accessVariables() accesses: instance variable: '"
//                        + instanceVariable + "'");

                System.out.println("-- Boolean.accessVariables() accesses: static variable: '"
                        + staticVariable + "'");
            }
        }

        Boolean localTrue = Boolean.TRUE;
        System.out.println("-- I declared a local enum: " + localTrue + ", its class: " + localTrue.getClass());
        localTrue.accessVariables();
    }
}
