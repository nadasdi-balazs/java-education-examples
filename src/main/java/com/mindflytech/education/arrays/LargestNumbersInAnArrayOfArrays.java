package com.mindflytech.education.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LargestNumbersInAnArrayOfArrays {
    public static void main(String[] args) {
        LargestNumbersInAnArrayOfArrays largestNumbersInAnArrayOfArrays = new LargestNumbersInAnArrayOfArrays();

        //        findLargestNums([[4, 2, 7, 1], [20, 70, 40, 90], [1, 2, 0]]) ➞ [7, 90, 2]
        int[][] sampleArray1 = {{4, 2, 7, 1}, {20, 70, 40, 90}, {1, 2, 0}};
        int[] largestNumbers1 = largestNumbersInAnArrayOfArrays.findLargestNums(sampleArray1);
        System.out.println("The largest numbers (1.): " + Arrays.toString(largestNumbers1));

//        findLargestNums([[-34, -54, -74], [-32, -2, -65], [-54, 7, -43]]) ➞ [-34, -2, 7]
        int[][] sampleArray2 = {{-34, -54, -74}, {-32, -2, -65}, {-54, 7, -43}};
        int[] largestNumbers2 = largestNumbersInAnArrayOfArrays.findLargestNums(sampleArray2);
        System.out.println("The largest numbers (2.): " + Arrays.toString(largestNumbers2));

//        findLargestNums([[0.4321, 0.7634, 0.652], [1.324, 9.32, 2.5423, 6.4314], [9, 3, 6, 3]]) ➞ [0.7634, 9.32, 9]
        double[][] sampleArray3 = {{0.4321, 0.7634, 0.652}, {1.324, 9.32, 2.5423, 6.4314}, {9, 3, 6, 3}};
        double[] largestNumbers3 = largestNumbersInAnArrayOfArrays.findLargestNums(sampleArray3);
        System.out.println("The largest numbers (3.): " + Arrays.toString(largestNumbers3));

    }

//    private Number[] findLargestNumsOfNumber(Number[][] array) {
//        Number[] result = new Number[array.length];
//        for (int i = 0; i < array.length; i++) {
//            Number[] innerArray = array[i];
//            if (innerArray == null || innerArray.length == 0) {
//                throw new IllegalArgumentException("null or empty array received");
//            }
//            Number largest = getLargestElementInArray(innerArray);
////            System.out.println("The largest number in the array:" + largest);
//            result[i] = largest;
//        }
//        return result;
//    }

    private int[] findLargestNums(int[][] array) {
        int length = array.length;
        double[][] doubles = new double[length][];
        for (int i = 0; i < length; i++) {
            int[] intArr = array[i];
            double[] doubleArr = Arrays.stream(intArr).asDoubleStream().toArray();
            doubles[i] = doubleArr;
        }
        double[] largestAsDouble = findLargestNums(doubles);
//        List<Double> collected = Arrays.stream(largestAsDouble).map(doubleValue -> (int) doubleValue).boxed().collect(Collectors.toList());
        List<Double> collected = Arrays.stream(largestAsDouble).boxed().collect(Collectors.toList());
        List<Integer> integerList = collected.stream().map(doubleValue -> doubleValue.intValue()).collect(Collectors.toList());
        int[] largestInts = integerList.stream().mapToInt(Integer::intValue).toArray();
        return largestInts;
    }

    private double[] findLargestNums(double[][] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            double[] innerArray = array[i];
            if (innerArray == null || innerArray.length == 0) {
                throw new IllegalArgumentException("null or empty array received");
            }
            double largest = getLargestElementInArray(innerArray);
//            System.out.println("The largest number in the array:" + largest);
            result[i] = largest;
        }
        return result;
    }

    private static double getLargestElementInArray(double[] innerArray) {
        double largest = innerArray[0];
        for (double current : innerArray) {
            largest = Math.max(current, largest);
        }
        return largest;
    }

    private static Number getLargestElementInArray(Number[] innerArray) {
        Number largest = innerArray[0];
        for (Number current : innerArray) {
            largest = Math.max(current.doubleValue(), largest.doubleValue());
        }
        return largest;
    }

}
