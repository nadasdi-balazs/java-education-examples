package com.mindflytech.education.oop.innerclass;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.mindflytech.education.util.CitiesOfCountries;

public class Lambdas {
    private record Mappable(String dataOne, String dataTwo){}


    private List<Mappable> mappables = new ArrayList<>() {{
        add(new Mappable("Japan", "Tokyo"));
        add(new Mappable("Germany", "Berlin"));
        add(new Mappable("UK", "London"));
        add(new Mappable("Belgium", "Brussels"));
        add(new Mappable("France", "Paris"));
    }};

    private List<CitiesOfCountries> countriesWithCities = new ArrayList<>() {{
        add(new CitiesOfCountries("Japan", Lists.newArrayList("Tokyo", "Osaka", "Kyoto")));
        add(new CitiesOfCountries("Germany", Lists.newArrayList("Berlin", "Munchen", "Hamburg")));
        add(new CitiesOfCountries("UK", Lists.newArrayList("London", "Glasgow", "Belfast")));
        add(new CitiesOfCountries("Belgium", Lists.newArrayList("Brussels", "Liege", "Antwerpen")));
        add(new CitiesOfCountries("France", Lists.newArrayList("Paris", "Toulouse", "Marseille")));
    }};

    private List<String> data = new ArrayList<>(){{
        add("data-one");
        add("data-two");
        add("data-third");
        add("data-four");
        add("data-five");
        add("data-six");
        add("data-seven");
        add("data-eight");
        add("odd-one-out");
    }};

    public static void main(String[] args) {
        Lambdas lambdas = new Lambdas();
        lambdas.runExample();
    }

    private void runExample() {
        List<String> foundOdd = data.parallelStream()
                .filter(data -> data.contains("odd"))
                .collect(Collectors.toList());
        System.out.println("-- filtered list for 'odd': [" + listToString(foundOdd) + "]");

        List<String> foundOddVerbose = data.parallelStream()
                .filter(data -> {
                    System.out.println("-- data: '" + data + "' ");
                    boolean isOdd = data.contains("odd");
                    System.out.print(isOdd ? " is odd\n" : " is not odd\n");
                    return isOdd;
                })
                .collect(Collectors.toList());
        listToString(foundOddVerbose);

        List<String> foundOddAnonymous = data.parallelStream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        System.out.print("-- anonymous inner class implementation, got: '" + s + "'");
                        boolean isOdd = s.contains("odd");
                        System.out.print(isOdd ? " is odd\n" : " is not odd\n");
                        return isOdd;
                    }
                })
                .collect(Collectors.toList());
        String odds = listToString(foundOddAnonymous);
        System.out.println("-- odds: " + odds);

        Function<Mappable, String> dataTwoMapper = mappable -> mappable.dataTwo();
        List<String> capitalCities = mappables.stream()
                .map(dataTwoMapper)
                .collect(Collectors.toList());
        String capitalString = listToString(capitalCities);
        System.out.println("-- capital cities of mappables: " + capitalString);

        Function<String, String> capitalizer = capital -> capital.toUpperCase();
        Function<Mappable, String> capitalizeString = dataTwoMapper.andThen(capitalizer);
        List<String> capitals = mappables.stream()
                .map(capitalizeString)
                .collect(Collectors.toList());
        String capitalizedString = listToString(capitals);
        System.out.println("-- capitalized capital cities of mappables: " + capitalizedString);

        List<Integer> lengths = mappables.stream()
                .map(((Function<Mappable, String>) mappable -> mappable.dataTwo())
                        .andThen(string -> string.length()))
                .collect(Collectors.toList());
        String lengthList = listToString(lengths);
        System.out.println("-- lengths of capitals: " + lengthList);

        boolean allCapitalsLengthIsFive = mappables.stream()
                .mapToInt(mappable -> {
                    String dataTwo = mappable.dataTwo();
                    int dataTwoLength = dataTwo.length();
                    return dataTwoLength;
                })
                .allMatch(size -> size == 5);
        System.out.println("-- are all capital cities' names have the length of 5? " + allCapitalsLengthIsFive);

//        BiConsumer<Mappable, Consumer<List<String>>> mapper;
        List<String> firstCities = countriesWithCities.stream()
                .<String>mapMulti((citiesOfCountries, consumer) -> {
                    List<String> cities = citiesOfCountries.cities();
                    consumer.accept(cities.get(0));
                })
                .collect(Collectors.toList());
        String firstCitiesAsString = listToString(firstCities);
        System.out.println("-- first cities of countriesWithCities: " + firstCitiesAsString);

        List<String> collectCities = countriesWithCities.stream()
                .<List<String>>mapMulti((citiesOfCountries, consumer) -> {
                    List<String> cities = citiesOfCountries.cities();
                    consumer.accept(cities);
                })
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        String cities = listToString(collectCities);
        System.out.println("-- cities: " + cities);


        List<String> collectCitiesWithFlatMapOnly = countriesWithCities.stream()
                .map(citiesOfCountries -> citiesOfCountries.cities())
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        String citiesWithFlatMap = listToString(collectCitiesWithFlatMapOnly);
        System.out.println("-- cities: " + citiesWithFlatMap);
    }

    private static String listToString(List<?> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }
}

class Calculator {
    private abstract class CantUseAbstractClassWithLambdas {
        public abstract void doStuff(String argument);
    }

    interface IntegerMath {
        int operation(int a, int b);
    }

    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }

    public static void main(String... args) {
        Calculator myApp = new Calculator();
        IntegerMath addition = (a, b) -> a + b;
        IntegerMath subtraction = (a, b) -> a - b;
        System.out.println("40 + 2 = " +
                myApp.operateBinary(40, 2, addition));
        System.out.println("20 - 10 = " +
                myApp.operateBinary(20, 10, subtraction));

        //compile error
        //Target type of a lambda conversion must be an interface
//        CantUseAbstractClassWithLambdas lambda = (String arg) -> System.out.println(arg);
    }
}

class TargetTypeExample {
    interface OwnPredicate<T> {
        boolean test(T t);
    }

    private void ownPredicateExpecter(OwnPredicate<String> ownPredicate, String test) {
        System.out.println("-- ownPredicateExpecter method called with: '" + ownPredicate
                + "', will test: '" + test + "' on it");
        boolean result = ownPredicate.test(test);
        System.out.println("-- the result is: " + result);
    }

    public static void main(String[] args) {
        Predicate<String> predicate = argument -> {
            System.out.println("-- predicate lambda called with argument: '" + argument + "'");
            boolean result = argument.length() > 5;
            System.out.println("-- this is greater than 5: " + result + ", will return with this");
            return result;
        };
        TargetTypeExample example = new TargetTypeExample();
        //compile error
        //Required type:        //OwnPredicate        //<String>
        //Provided:        //Predicate        //<String>
//        example.ownPredicateExpecter(predicate);

        //FAILS RUNTIME:
        //Exception in thread "main" java.lang.ClassCastException: class com.mindflytech.education.oop.innerclass.TargetTypeExample$$Lambda$60/0x0000000801005000 cannot be cast to class com.mindflytech.education.oop.innerclass.TargetTypeExample$OwnPredicate (com.mindflytech.education.oop.innerclass.TargetTypeExample$$Lambda$60/0x0000000801005000 and com.mindflytech.education.oop.innerclass.TargetTypeExample$OwnPredicate are in module com.mindflytech.education of loader 'app')
        //	at com.mindflytech.education/com.mindflytech.education.oop.innerclass.TargetTypeExample.main(Lambdas.java:204)
//        example.ownPredicateExpecter((OwnPredicate<String>)predicate, "some-random-bullshit");


        example.ownPredicateExpecter(argument -> predicate.test(argument), "some-random-bullshit");
    }
}

interface DefaultMethodRunnable extends Runnable {
    default void defaultMethod() {
        System.out.println("-- default method runs");
    }

    static void staticMethod() {
        System.out.println("-- static method runs");
    }

    private void privateMethod() {
        System.out.println("-- private method runs");
    }
}

interface MyPrinter{
    void print(String s);
}

class DefaultRunner {
    public static void main(String[] args) {
        DefaultRunner runner = new DefaultRunner();
        runner.run();
        runner.whatYouCantDoInALambda();
        runner.printerUser("Print this!");
    }

    private void run() {
        DefaultMethodRunnable runnable = () -> System.out.println("-- lambda runs");
        System.out.println("-- will call the methods of DefaultMethodRunnable created with lambda expression");
        runnable.run();
        runnable.defaultMethod();
        //compile error!
        //Static method may be invoked on containing interface class only
//        runnable.staticMethod();
        DefaultMethodRunnable.staticMethod();

        DefaultMethodRunnable runnable2 = () -> {
            //compile error
            //Cannot resolve method 'defaultMethod' in 'DefaultRunner'
//            defaultMethod();
        };
    }

    private void whatYouCantDoInALambda() {
        DefaultMethodRunnable cantDo = () -> {
            //compile error
            //Modifier 'static' not allowed here
//            static int stuff = 27;
            //compile error
            //';' expected
//            void method() {}
            //compile error
            //Identifier or type expected
//            static {
//                System.out.println("static");
//            }
            System.out.println("-- lambda runs");
            class InnerClass {
                void printer() {
                    System.out.println("-- I can declare and run local classes in a lambda expression");
                }
            }
            InnerClass innerClass = new InnerClass();
            innerClass.printer();
            new Runnable(){
                @Override
                public void run() {
                    System.out.println("-- you can also declare and run an anonymous inner class in a lambda");
                }
            }.run();
            Runnable runnable = () -> System.out.println("-- you can declare and run a lambda inside a lambda");
            runnable.run();
            interface LocalInterface extends Runnable {
                default void print() {
                    System.out.println("-- a local interface can also be declared here");
                }
            }
            LocalInterface local = () -> System.out.println("-- local interface lambda runs");
            local.run();
            local.print();
            record Record(int number, String text) {
                static {
                    System.out.println("-- static initializer of record declared inside lambda runs");
                }
            }
            Record instance = new Record(23, "text");
            System.out.println("-- this.getClass() in lambda: " + this.getClass());
            System.out.println("-- getClass() in lambda: " + getClass());
        };
        System.out.println("-- getClass() of lambda from outside: " + cantDo.getClass());
        System.out.println("-- lambda.toString from outside: " + cantDo.toString());
        cantDo.defaultMethod();
        cantDo.run();
        Runnable returnWith = () -> {
            {
                System.out.println("-- this is a block within the lambda");
            }
            synchronized (this){
                System.out.println("-- this is a synchronized block within the lambda");
            }
            if(System.currentTimeMillis() % 2 == 0) {
                System.out.println("-- current time millis is even");
                return;
            }
            System.out.println("-- current time millis is odd");
        };
        returnWith.run();
    }

    private void printerUser(String printable) {
        MyPrinter myPrinter = System.out::println;
        MyPrinter formattedPrinter = text -> System.out.println(getClass().getCanonicalName() + ": " + text);
        myPrinter.print(printable);
        formattedPrinter.print(printable);
    }
}
