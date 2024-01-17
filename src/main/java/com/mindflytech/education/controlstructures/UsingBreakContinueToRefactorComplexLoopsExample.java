package com.mindflytech.education.controlstructures;

import java.util.ArrayList;
import java.util.List;

public class UsingBreakContinueToRefactorComplexLoopsExample {

    /**
     * This method checks all elements in the input array. If the element contains a dash (-) character,
     * the part after the dash is replaced with its uppercase version
     * <br>If there is no dash, that's a minor error, we should display a warning log message
     * <br>Note that this is a made up business case that we will implement in several different ways
     * <br>This implementation is the simplest approach, but not the nicest code, it does not leverage
     * neither continue statement, nor streams
     * @param array the input array
     * @return the elements containing dash, with uppercase letters after the dash
     */
    public String[] withoutEarlyContinueInLoop(String[] array) {
        List<String> result = new ArrayList<>();
        for(String element : array) {
            System.out.println("-- input element: '" + element + "'");
            boolean containsDash = element.contains("-");
            if(containsDash) {
                String[] split = element.split("-", -1);
                int elementCount = split.length;
                if(elementCount > 1) {
                    String shouldBeUppercase = concatenateElementsFromIndex(split, 1);
                    String uppercase = shouldBeUppercase.toUpperCase();
                    //let's say we ignore the parts after the second dash
                    String resultElement = split[0] + "-" + uppercase;
                    result.add(resultElement);
                } else {
                    System.err.println("ERROR: input string: '" + element + "' should contain more " +
                            "than one element after split on dash character");
                }
            } else {
                System.err.println("ERROR: input string: '" + element + "' should contain exactly " +
                        "one dash character!");
            }
        }
        String[]  arrayResult= result.toArray(new String[0]);
        return arrayResult;
    }

    private String concatenateElementsFromIndex(String[] array, int index) {
        StringBuffer buffer = new StringBuffer();
        for(int i = index; i < array.length; i++) {
            buffer.append(array[i]);
//            buffer.append("-");
        }
        String result = buffer.toString();
        return result;
    }
}

class Tester {
    public static final String[] TEST = new String[]{"Timea", "Balazs", "Ba-lazs", "-Balazs", "Timea-",
            "-other-string-"};

    public static void main(String[] args) {
//        demonstrateStringSplitToUppercaseBehavior();
        UsingBreakContinueToRefactorComplexLoopsExample example = new UsingBreakContinueToRefactorComplexLoopsExample();
        String[] testResult = example.withoutEarlyContinueInLoop(TEST);
        for(String result : testResult) {
            System.out.println("-- result: '" + result + "'");
        }
    }

    private static void demonstrateStringSplitToUppercaseBehavior() {
        for(String string : TEST) {
            System.out.println("-- original string: '" + string + "'");
            System.out.println("-- uppercase string: '" + string.toUpperCase() + "'");
            String[] split = string.split("-");
            for(String part : split) {
                System.out.println("---- split part: '" + part + "'");
            }
        }
    }
}
