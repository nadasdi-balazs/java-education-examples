package com.mindflytech.education.controlstructures;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DoWhileContinueExample {
    public static void main(String[] args) {
        DoWhileContinueExample example = new DoWhileContinueExample();
        example.runExample();
        example.onlyPrintOddNumber();
        example.onlyPrintOddNumberWithContinue();
    }

    private void onlyPrintOddNumberWithContinue() {
        List<Integer> evens = new ArrayList<>();
        for(int number = 0; number < 10; number++) {
            if(number % 2 != 0) {
                continue;
            }
            System.out.println("--number is even: number" + number);
            //let's say we have 5 more things to do with an even number
            evens.add(number);
            sendToOtherService(number);
        }
    }

    private void onlyPrintOddNumber() {
        List<Integer> evens = new ArrayList<>();
        for(int number = 0; number < 10; number++) {
            if(number % 2 == 0) {
                System.out.println("--number is even: number" + number);
                //let's say we have 5 more things to do with an even number
                evens.add(number);
                sendToOtherService(number);
            }
        }
    }

    private void sendToOtherService(int number) {
        System.out.println("-- I am calling an external with API with number: " + number);
    }

    private void runExample() {
        int check = 0;
        do {
            System.out.println("-- first statement in do's code block, check value" + check);
            check++;
            if(check > 5) {
                System.out.println("-- continue called because check > 5: " + check);
                continue;
            }
            System.out.println("-- last statement, check value: " + check);
        } while(checkVariable(check));
        System.out.println("-- check value after do-while");
    }

    private boolean checkVariable(int check) {
        System.out.println("-- checkVariable called from while(boolean) with value: " + check);
        return check > 5 ? false : true;
    }
}
