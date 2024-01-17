package com.mindflytech.education.basics;

import com.mindflytech.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayBasics {
    private static final int[] SAMPLE_ONE = new int[] {1, 2, 3, 4, 5};
    private static final int[] SAMPLE_TWO = new int[] {1, Integer.MIN_VALUE, 3, Integer.MAX_VALUE, 5};
    private static final int[] SAMPLE_THREE = new int[] {1, -23, 3315123, -3434, 55};

    public static void main(String[] args) {
        ArrayBasics basics = new ArrayBasics();
        testTwoAlgorithmsWithOneArray(basics, SAMPLE_ONE);
        testTwoAlgorithmsWithOneArray(basics, SAMPLE_TWO);
        testTwoAlgorithmsWithOneArray(basics, SAMPLE_THREE);
    }

    private static void testTwoAlgorithmsWithOneArray(ArrayBasics basics, int[] sample) {
        Utils.prefixedPrintArray("-- input array: ", sample);
        int[] invertedArray = basics.createInvertedArray(sample);
        Utils.prefixedPrintArray("-- reverse ordered array: ", invertedArray);
        int[] invertedArrayOtherIteration = basics.createInvertedArrayOtherIteration(sample);
        Utils.prefixedPrintArray("-- reverse ordered array, other iteration: ", invertedArrayOtherIteration);
    }

    private int[] createInvertedArray(int[] array) {
        int length = array.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            int indexOfLastElement = length - 1;
            int back = array[indexOfLastElement - i];
            result[i] = back;
        }
        return result;
    }

    private int[] createInvertedArrayOtherIteration(int[] input) {
        int length = input.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            int current = input[i];
            int indexOfLastElement = length - 1;
            int ithFromLastElement = indexOfLastElement - i;
            result[ithFromLastElement] = current;
        }
        return result;
    }

    private int[] findLargestNums(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] innerArray : array) {
            if (innerArray == null || innerArray.length == 0) {
                throw new IllegalArgumentException("null or empty array received");
            }
            int largest = innerArray[0];
            for (int current : innerArray) {
                largest = Math.max(current, largest);
            }
//            System.out.println("The largest number in the array:" + largest);
            result.add(largest);
        }
        //TODO FIXME
        return null;
    }
}
