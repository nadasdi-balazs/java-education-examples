package com.mindflytech.education.generics;

import java.util.ArrayList;
import java.util.List;

import static com.mindflytech.util.Utils.prefixedPrintList;

public class LowerBoundWildcard {
    //I don't think this makes much sense but let's see
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        LowerBoundWildcard wildcard = new LowerBoundWildcard();
        wildcard.demonstrate();
    }

    private void demonstrate() {
        //I don't think this makes much sense but let's see
        List<Number> numbers = new ArrayList<>();
        numbers.add(27);
        numbers.add(27L);
        numbers.add((Number)27);
        addNumbers(numbers);
        prefixedPrintList("-- called addNumbers(List<? super Integer> list) on a List<Number>: ", numbers);

        List<? super Integer> list = new ArrayList<>();
//        list.add(27);
        //compile error
        //Required type:        //capture of ? super Integer
        //Provided:             //Number
//        list.add((Number)Double.valueOf(27.0D));
        //compile error
        //Required type:        //capture of ? super Integer
        //Provided:             //Object
//        list.add(new Object());
        //compile error
        //Required type:        //capture of ? super Integer
        //Provided:             //Long
//        list.add(Long.valueOf(27L));
    }
}
