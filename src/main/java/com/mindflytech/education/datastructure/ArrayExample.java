package com.mindflytech.education.datastructure;

import java.util.Arrays;

public class ArrayExample {
    public static void main(String[] args) {
        ArrayExample example = new ArrayExample();
        example.showSlice();
    }

    private void showSlice() {
        int[] toBeSliced = new int[]{1, 2, 68, 37,  Integer.MIN_VALUE, 3, 35835, 332, -3, -255, Integer.MAX_VALUE};
        int[] part = Arrays.copyOfRange(toBeSliced, 0, 3);
        System.out.println(Arrays.toString(part));
    }
}
