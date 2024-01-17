package com.mindflytech.education.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpperBoundWildcard {
    public static void main(String[] args) {
        UpperBoundWildcard wildcard = new UpperBoundWildcard();
        wildcard.demonstrate();
        wildcard.demonstrateOnSumOfNumbers();
    }

    public static void process(List<? extends StringSuperClass> list) {
        for (StringSuperClass elem : list) {
            System.out.println("-- element: " + elem + ", its type: " + elem.getClass());
        }
    }

    public static void processWithoutWildcard(List<StringSuperClass> list) {
        for (StringSuperClass elem : list) {
            System.out.println("-- element: " + elem + ", its type: " + elem.getClass());
        }
    }

    public static double sumOfList(List<? extends Number> list) {
        double s = 0.0;
        for (Number n : list)
            s += n.doubleValue();
        return s;
    }

//    public static <T> double sumOfListWithT(List<T extends Number> list) {
//        double s = 0.0;
//        for (Number n : list)
//            s += n.doubleValue();
//        return s;
//    }

    public static double sumOfListWithoutWildcard(List<Number> list) {
        double s = 0.0;
        for (Number n : list)
            s += n.doubleValue();
        return s;
    }

    private void demonstrate() {
        List<StringSuperClass> superClassList = new ArrayList<>();
        StringSuperClass one = StringSuperClass.of("one");
        superClassList.add(one);
        StringSuperClass two = StringSuperClass.of("two");
        superClassList.add(two);
        StringSuperClass three = StringSuperClass.of("three");
        superClassList.add(three);
        StringSuperClass four = StringSuperClass.of("four");
        superClassList.add(four);
        StringSubClass fourSub = StringSubClass.of("four-subclass");
        superClassList.add(fourSub);
        process(superClassList);
        processWithoutWildcard(superClassList);

        List<StringSubClass> subClassList = new ArrayList<>();
        subClassList.add(fourSub);
        process(subClassList);
        //compile error
        //Required type:        //List        //<StringSuperClass>
        //Provided:             //List        //<StringSubClass>
//        processWithoutWildcard(subClassList);

        List<String> list = new ArrayList<>();
        list.add("something");
        unknownExtendsString(list);
    }

    public void unknownExtendsString(List<? extends String> stringalikes) {
        for (String string : stringalikes) {
            System.out.println("-- stringalike: '" + string + "'");
        }
    }

    private void demonstrateOnSumOfNumbers() {
        List<Integer> li = Arrays.asList(1, 2, 3);
        System.out.println("-- sum of integers, using sumOfList: = " + sumOfList(li));
        //compile error
        //Required type:        //List        //<Number>
        //Provided:             //List        //<Integer>
//        System.out.println("-- sum of integers, using sumOfList: = " + sumOfListWithoutWildcard(li));

        List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
        System.out.println("-- sum of doubles, using sumOfList: = " + sumOfList(ld));
        //compile error
        //Required type:        //List        //<Number>
        //Provided:             //List        //<Double>
//        System.out.println("-- sum of doubles, using sumOfList: = " + sumOfListWithoutWildcard(ld));
    }
}
