package com.mindflytech.education.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

public class OtherStreamExamples {
    public static void main(String[] args) {
        OtherStreamExamples examples = new OtherStreamExamples();
        examples.flatMap();
    }

    private void flatMap() {
        List<List<String>> namesNested = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zuckerberg"));

//        List<String> namesFlatStream = namesNested.stream()
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());

        namesNested.stream()
                .flatMap(Collection::stream)
                .forEach(string -> System.out.println("-- result element is: '" + string + "'"));
    }
}
