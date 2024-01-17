package com.mindflytech.education.controlstructures;

import java.util.ArrayList;
import java.util.List;

public class ContinueWithSwitchExpression {
    private final List<String> test = new ArrayList<>() {{
        add("first-value");
        add("second-value");
        add("third-value");
        add("4th-value");
        add("5th-value");
        add("6th-value");
    }};

    public static void main(String[] args) {
        ContinueWithSwitchExpression withSwitch = new ContinueWithSwitchExpression();
        withSwitch.run();
    }

    private void run() {
        for(String element : test) {
            String mapped = switch (element) {
                case "second-value" -> element.toUpperCase();
                case "third-value" -> {
                    System.out.println("-- element is 'third-value', will it allow continue? " );
                    //compile error, it does not allow to continue from switch expression
//                    return;
                    yield "this-didn't-work";
                    //compile error
                    //Continue outside of enclosing switch expression
//                    continue;
                    //Unreachable statement
//                    System.out.println("-- this won't run");
                }
                default -> "dummy";
            };
            System.out.println("-- from element: '" + element + "' we mapped: '" + mapped + "'");
        }
    }
}

