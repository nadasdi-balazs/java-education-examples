package com.mindflytech.education.generics;

import java.util.LinkedList;
import java.util.List;

public class RawTypeClassCastException {
    public static void main(String[] args) {
        RawTypeClassCastException classCast = new RawTypeClassCastException();
        classCast.createException();
    }

    private void createException() {
        List rawTypeList = new LinkedList();
        rawTypeList.add(27);
        String iAmAString = "I-am-a-string";
        rawTypeList.add(iAmAString);
//        useRawList(rawTypeList);

        useGenericsInstead(rawTypeList);
        List<String> stringList = new LinkedList<>();
        stringList.add(String.valueOf(27));
        stringList.add(iAmAString);
        useGenericsInstead(rawTypeList);
    }

    private void useGenericsInstead(List<String> typedList) {
        for(String element : typedList) {
            System.out.println("String element: '" + element + "'");
        }
    }

    private void useRawList(List rawTypeList) {
        Object firstAsObject = rawTypeList.get(1);
        String firstAsString = (String)firstAsObject;
        System.out.println("-- I got the element at index 1, I know it's a string: '" + firstAsString + "'");
        Object elementZeroAsObject = rawTypeList.get(0);
        System.out.println("-- I think it's a string, I'm going to cast it to a string: " + elementZeroAsObject);
        String elementZero = (String)elementZeroAsObject;
        System.out.println("-- did it work? '" + elementZero + "'");
    }
}
