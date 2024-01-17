package com.mindflytech.education.codingchallenge;

import com.mindflytech.util.Utils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import static java.util.Map.Entry.comparingByValue;

/**
 * This is a solution for the following coding challenge exercise:
 * <p>Count how many times an element is repeated (CountOccurrence) `easy`</p>
 * <p>   Given an array, create a function that returns an object detailing how many times each
 * <p>   element was repeated. Sort the object by value in descending order.
 * <p><strong> Examples: </strong>
 * <p>  countRepetitions(["cat", "dog", "cat", "cow", "cow", "cow"]) ➞ {"cow"=3, "cat"=2, "dog"=1}
 * <p>   countRepetitions([1, 5, 5, 5, 12, 12, 0, 0, 0, 0, 0, 0]) ➞ {0=6, 5=3, 12=2, 1=1}
 * <p>   countRepetitions(["Infinity", "null", "Infinity", "null", "null"]) ➞ {"null"=3, "Infinity"=2}
 */
public class ElementCounterMapper {
    //TODO call it in a unit test
    public static void main(String[] args) {
        ElementCounterMapper mapper = new ElementCounterMapper();
        List<Object> animals = List.of("cat", "dog", "cat", "cow", "cow", "cow");
        Map<Object, Long> countedAndGroupped = mapper.countAndMapObjectOccurrences(animals);
        Utils.printMap(countedAndGroupped);
    }

    /**
     *
     * @param input the objects whose occurrences will be counted
     * @return show how many times an input object is appeared in the list
     */
    public Map<Object, Long> countAndMapObjectOccurrences(List<Object> input) {
        Comparator<Map.Entry<Object, Long>> reverseValueComparator = (element, other) -> (-1) * Map.Entry.<Object, Long>comparingByValue().compare(element, other);
        Map<Object, Long> collected = input.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(reverseValueComparator)
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (e1, e2) -> e1, LinkedHashMap::new));
        return collected;
    }
}
