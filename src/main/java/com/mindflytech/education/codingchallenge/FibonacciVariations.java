package com.mindflytech.education.codingchallenge;

public class FibonacciVariations {
    public static void main(String[] args) {
        FibonacciVariations variations = new FibonacciVariations();
        int result = variations.naiveRecursion(10);
//        int result = variations.naiveRecursion(-2);
        System.out.println("-- result of naive recursion implementation: " + result);

        int loopResult = variations.arrayImplementation(10);
    }

    private int arrayImplementation(int index) {
        checkNonNegative(index);
        int[] fiboElement = new int[index];
        fiboElement[0] = 0;
        fiboElement[1] = 1;
        fiboElement[2] = 2;
        if(index < 3) {
            return fiboElement[index];
        }
        for(int i = 0; i < index; i++) {

        }
        return fiboElement[index - 1];
    }

    private int naiveRecursion(int index) {
        System.out.println("-- naiveRecursion called with " + index);
        checkNonNegative(index);
        if(index == 0) {
            System.out.println("-- naiveRecursion called with 0, will return 0");
            return 0;
        }
        if(index < 3) {
            System.out.println("-- naiveRecursion called with 1 or 2: " + index + ", will return 1");
            return 1;
        }
        int result = naiveRecursion(index - 1) + naiveRecursion(index - 2);
        System.out.println("-- naiveRecursion called with: " + index + ", result is: " + result);
        return result;
    }

    private static void checkNonNegative(int index) {
        if(index < 0) {
            throw new IllegalArgumentException("Fibonacci should not be called with negative numbers");
        }
    }
}
