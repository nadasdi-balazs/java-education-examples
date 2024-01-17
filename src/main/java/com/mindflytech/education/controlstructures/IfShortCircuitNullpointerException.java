package com.mindflytech.education.controlstructures;


import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class IfShortCircuitNullpointerException {
    public static void main(String[] args) {
        IfShortCircuitNullpointerException shortCircuit = new IfShortCircuitNullpointerException();
        shortCircuit.evaluate();
    }

    private void evaluate() {
        List<String> containsElements = generateListWithElements();
        printListNullSafe(containsElements);
        printListNotNullSafe(containsElements);

        List<String> empty = new ArrayList<>();
        printListNullSafe(empty);
        printListNotNullSafe(empty);

        List<String> iAmNull = null;
        printListNullSafe(iAmNull);
        printListNotNullSafe(iAmNull);
    }

    private void printListNullSafe(List<String> strings) {
        if(strings == null || strings.size() == 0) {
            System.out.println("-- warning: can't print null or empty list");
            return;
        }
        for(String string : strings) {
            System.out.println("-- element: '" + string +  "'");
        }
    }

    private void printListNotNullSafe(List<String> strings) {
        if(strings.size() == 0 || strings == null) {
            System.out.println("-- warning: can't print null or empty list");
            return;
        }
        for(String string : strings) {
            System.out.println("-- element: '" + string +  "'");
        }
    }

    private static List<String> generateListWithElements() {
        List<String> containsElements = new ArrayList<>();
        containsElements.add("firstElement");
        containsElements.add("secondElement");
        containsElements.add("I am bored of adding elements");
        containsElements.add("duh");
        return containsElements;
    }
}
