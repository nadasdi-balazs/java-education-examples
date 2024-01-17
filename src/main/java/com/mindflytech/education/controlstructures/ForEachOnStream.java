package com.mindflytech.education.controlstructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mindflytech.education.controlstructures.ControlStructureUtils.generateStringList;

public class ForEachOnStream {
    public static void main(String[] args) {
        ForEachOnStream forEach = new ForEachOnStream();
        forEach.streamExample();
    }

    private void streamExample() {
        List<String> toIterateOver = generateStringList();
        toIterateOver.forEach(element -> System.out.println("-- element: '" + element + "'"));
        toIterateOver.stream().map(String::toUpperCase).forEach(
                element -> System.out.println("-- element, this time on stream api: '" + element + "'"));
        toIterateOver.parallelStream().forEach(element -> System.out.println("-- element, using parallelStream(): '"
                + element + "'"));

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "alpha");
        map.put(2, "beta");
        map.put(3, "gamma");
        map.put(4, "delta");
        map.put(5, "epsilon");
        map.put(6, "zeta");
        map.forEach((key, value) -> System.out.println("-- key, value: [" + key + ", " + value + "]"));
    }
}
