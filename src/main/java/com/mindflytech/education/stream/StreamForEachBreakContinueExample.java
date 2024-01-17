package com.mindflytech.education.stream;

import java.util.ArrayList;
import java.util.List;

public class StreamForEachBreakContinueExample {
    private final List<String> test = new ArrayList<>() {{
        add("first-value");
        add("second-value");
        add("third-value");
        add("4th-value");
        add("5th-value");
        add("6th-value");
    }};

    public static void main(String[] args) {
        StreamForEachBreakContinueExample example = new StreamForEachBreakContinueExample();
        example.run();
    }

    private void run() {
        test.stream().map(String::toUpperCase).forEach(string -> {
            if(string.contains("4")) {
                //compile error
                //break outside of switch or loop
//                break;
                //compile error
                //Continue outside of loop
//                continue;
            }
        });
    }
}
