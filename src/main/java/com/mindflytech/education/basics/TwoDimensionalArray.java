package com.mindflytech.education.basics;

public class TwoDimensionalArray {
    public static void main(String[] args) {
        int[] oneDimArray = new int[] {1, 2, 3, 4, 5};
        int[][] twoDimArray = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int[] array : twoDimArray) {
            for(int element : array) {
                System.out.println("-- element: " + element);
            }
        }

        int[][] anotherTwoDimArray = new int[3][3];
        anotherTwoDimArray[0][0] = 1;
        anotherTwoDimArray[0][1] = 1;
        anotherTwoDimArray[0][2] = 1;
        anotherTwoDimArray[1][0] = 1;
        anotherTwoDimArray[1][1] = 1;
        anotherTwoDimArray[1][2] = 1;
        anotherTwoDimArray[2][0] = 1;
        anotherTwoDimArray[2][1] = 1;
        anotherTwoDimArray[2][3] = 1;
    }
}
