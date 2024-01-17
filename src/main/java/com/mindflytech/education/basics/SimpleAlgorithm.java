package com.mindflytech.education.basics;

import com.mindflytech.util.Utils;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.OptionalInt;

@Log4j2
public class SimpleAlgorithm {
    private int someField = 28;

    public SimpleAlgorithm() {
        System.out.println("this is the default constructor");
    }

    public static void main(String[] args) {
        SimpleAlgorithm algorithm = new SimpleAlgorithm();
        int[] sampleOne = new int[] {1, 2, 3, 4, 5};
        int[] sampleTwo = new int[]{};
        algorithm.printLargestElementInArrayStreams(sampleTwo);
        algorithm.unreachableStatement();
    }

    private long unreachableStatement() {
        System.out.println("-- someField: " + someField);
        long startAt = System.currentTimeMillis();
        boolean fiftyPercentChance = Utils.percentChance(50);
        if(fiftyPercentChance) {
            System.out.println("-- chance variable is true, this statement is reachable");
            long elapsed = calculateElapsedTime(startAt);
            return elapsed;
            //compile error
            //unreachable statement
//            System.out.println("-- I am unreachable");
        } else {
            System.out.println("-- chance variable is false, this statement is reachable");
            long elapsed = calculateElapsedTime(startAt);
            return elapsed;
        }
        //compile error
        //unreachable statement
//        return 2L;
    }

    private static long calculateElapsedTime(long startAt) {
        long finishAt = System.currentTimeMillis();
        long elapsed = finishAt - startAt;
        return elapsed;
    }

    private void printLargestElementInArrayStreams(int[] array) {
        if (array == null || array.length == 0) {
            log.info("Null or empty array received: " + Arrays.toString(array));
            return;
        }
        OptionalInt max = Arrays.stream(array).max();
        int largest = max.getAsInt();
        System.out.println("-- largest element as optionalInt: " + max);
        System.out.println("The largest number in the array:" + largest);
    }

    private void printLargestElementInArrayForEach(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int largest = array[0];
        for (int current : array) {
            largest = Math.max(current, largest);
        }
        System.out.println("The largest number in the array:" + largest);
    }


    private void printLargestElementInArrayMathMax(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int largest = array[0];
        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            largest = Math.max(current, largest);
        }
        System.out.println("The largest number in the array:" + largest);
    }

    private void printLargestElementInArrayOriginalTimea(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int largest = array[0];
        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            if (current > largest) {
                largest = current;
            }
        }
        System.out.println("The largest number in the array:" + largest);
    }
}
