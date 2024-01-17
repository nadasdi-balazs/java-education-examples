package com.mindflytech.util;

import com.google.common.collect.Lists;
import com.mindflytech.education.generics.StringSubClass;
import com.mindflytech.education.generics.StringSuperClass;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class Utils {
    //    private String something = "something";
    private Utils() {
        throw new UnsupportedOperationException();
    }

    public static String twoDimensionalArrayToString(int[][] twoDimArray) {
        StringBuffer resultBuffer = new StringBuffer("[");
        for (int[] array : twoDimArray) {
            String arrayAsString = Arrays.toString(array);
            resultBuffer.append(arrayAsString);
        }
        resultBuffer.append("]");
        String result = resultBuffer.toString();
        return result;
    }

    public static void printMap(Map<?, ?> map) {
        map.entrySet()
                .stream()
                .forEach(entry -> log.info("-- entry in map: [key:'" + entry.getKey() + "', value: '" + entry.getValue() + "']"));
    }

    public static String generateCurrentDateTime() {
        long epochMillis = System.currentTimeMillis();
        LocalDateTime dateTime = Instant.ofEpochMilli(epochMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        String dateTimeString = dateTime.toString();
        return dateTimeString;
    }

    public static String generateFileAbsolutePath(String directory) {
        return directory + File.separator + "tmp_" + System.currentTimeMillis() + ".txt";
    }

    public static String listToString(List<?> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    public static <T> String listToStringWithT(List<T> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    public static void printList(List<?> list) {
        prefixedPrintList("-- list:", list);
    }

    public static void prefixedPrintList(String prefix, List<?> list) {
        String listAsString = listToString(list);
        System.out.println(prefix + " [" + listAsString + "]");
    }

    public static void prefixedPrintArray(String prefix, int[] intArray) {
        String listAsString = Arrays.toString(intArray);
        System.out.println(prefix + " [" + listAsString + "]");
    }

    public static List<StringSuperClass> generateAsStringSuperClassList() {
        StringSuperClass one = StringSuperClass.of("one");
        StringSuperClass two = StringSuperClass.of("two");
        StringSuperClass three = StringSuperClass.of("three");
        StringSuperClass four = StringSuperClass.of("four");
        StringSubClass fourSub = StringSubClass.of("four-subclass");
        List<StringSuperClass> superSuperList = Lists.newArrayList(one, two, three, four);
        return superSuperList;
    }

    public static boolean fiftyPercentChance() {
        return percentChance(50.0);
    }

    public static boolean percentChance(double percent) {
        double random = Math.random();
        return random <= percent / 100.0;
    }

    public static String generateRandomString() {
        long countOfCharacters = (long)Math.random() * 100;
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < countOfCharacters; i++) {
            char current = (char)(Math.random() * 1000);
            buffer.append(current);
        }
        return buffer.toString();
    }
}
