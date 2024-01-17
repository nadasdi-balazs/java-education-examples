package com.mindflytech.education.controlstructures;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ReturnExample {
    public ReturnExample() {
        System.out.println("-- this is a constructor");
        //return is unnecessary
        return;
    }

    //Cannot return a value from a method with void result type
//    public ReturnExample(String value) {
//        System.out.println("-- this is a constructor");
//        //return is unnecessary
//        return "something";
//    }

    private void canHaveStatementAfterReturnInIfTrueBlock() {
        if(true) {
            System.out.println("-- canHaveStatementAfterReturnInIfTrueBlock returns immediately");
            return;
        }
        System.out.println("-- this will never run, because if(true) is always true and we return " +
                "from the if block");
    }

    public static void main(String[] args) {
        ReturnExample example = new ReturnExample();
        example.elaborateReturns();
        example.returnFromSwitchStatement("this is a string");
        double result = example.returnDoubleExact();
        System.out.println("-- returned float(?) value: " + result);
        int finallyResult = example.returnVsFinally();
        System.out.println("-- so returnVsFinally actually returned: " + finallyResult);

        example.isReturnNecessaryInLambdas();
    }

    private void isReturnNecessaryInLambdas() {
        //expression lamdba, one line, not necessary
        BiFunction<Integer, Integer, Integer> adder = (Integer op1, Integer op2) -> op1 + op2;
        //statement lambda, has code block, if there is a return value, it is necessery
        BiFunction<Integer, Integer, Integer> adderStatement = (Integer op1, Integer op2) -> {
            return op1 + op2;
        };
        //statement lambda, no return value, not necessery
        Runnable run = () -> {
            int aladar = 42;
            int bela = 27;
            Integer cecil = adder.apply(aladar, bela);
            int geza = 33;
            adderStatement.apply(cecil, geza);
            System.out.println(" aladar, bela, cecil, geza: " + aladar + ", " + bela + ", " + cecil + ", " + geza);
        };
        run.run();
    }

    private void elaborateReturns() {
        Runnable runs = () -> {
            System.out.println("-- lambda expression");
            if(ControlStructureUtils.isMillisDividableByFour()) {
                System.out.println("-- will return from a lambda, declared explicitly");
                return;
            }
            System.out.println("-- further statements");
        };
        for(int i = 0; i < 20; i++) {
            runs.run();
        }

        Function<String, String> timestampify = (String input) -> {
            String timestamp = "" + System.currentTimeMillis();
            String result = input + "_" + timestamp;
            System.out.println("-- input is: '" + input + "', result is: '" + result + "'");
            return result;
        };
        List<String> list = ControlStructureUtils.generateStringList();
        for(String element : list) {
            String result = timestampify.apply(element);
            System.out.println("-- function  returned with: '" + result + "'");
        }
        Function<String, String> simpleTimestampify = (String input) -> input + "_" + System.currentTimeMillis();
        for(String element : list) {
            String result = simpleTimestampify.apply(element);
            System.out.println("-- function  returned with: '" + result + "'");
        }
    }

    private String returnFromSwitchStatement(Object object) {
        String result = switch (object) {
            case null -> "It is a null object";
            case Integer i -> "It is an integer: " + i;
            case String s -> "It is a string: " + s;
            //Expression, block or throw statement expected
//            case String s -> return ("It is a string: " + s);
            //Return outside of enclosing switch expression
//            case String s -> {
//                return "It is a string: " + s;
//            }
            case SwitchExample.Employee e -> "It is an Employee: " + e;
            default -> "It is none of the known data types";
        };
        return result;
    }

    //Normal widening rules apply
    private double returnDoubleExact() {
        float result = 10.0f;
        System.out.println("-- will return with double: " + result);
        return result;
    }

//    private float returnFloatExact() {
//        double result = 10.0d;
//        System.out.println("-- will return with double: " + result);
//        //Required type:
//        //float
//        //Provided:
//        //double
//        return result;
//    }

    private int returnVsFinally() {
        try {
            System.out.println("-- this normal return in try block wants to return 5...");
            return 5;
        } finally {
            System.out.println("-- but this finally block wants to return 10");
            return 10;
        }
    }
}
