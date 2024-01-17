package com.mindflytech.education.controlstructures;

import com.mindflytech.util.Utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForExample {
    public static final Iterator<String> HORRIBLE_ITERATOR = new Iterator<>() {
        @Override
        public boolean hasNext() {
            boolean result = Utils.percentChance(95);
            if (result) {
                System.out.println("-- continue iteration");
            } else {
                System.out.println("-- STOP iteration");
            }
            return result;
        }

        @Override
        public String next() {
            return "current string is a timestamp: " + LocalDateTime.now();
        }
    };
    public static final Iterable<String> HORRIBLE_ITERABLE = () -> HORRIBLE_ITERATOR;

    public static void main(String[] args) {
        ForExample forExample = new ForExample();
        forExample.run();
//        forExample.forEach();

//        for(;;) {
//            System.out.println("STOP ME");
//        }

        for(boolean b = true; b; b = !b) {
            System.out.println("-- boolean b is: " + b);
        }

        LocalDateTime later = LocalDateTime
                .now()
                .plus(20, ChronoUnit.SECONDS);
        for(System.out.println("-- initialization"); LocalDateTime.now().isBefore(later); System.out.println("-- increment")) {
            System.out.println("-- I am running at: " + LocalDateTime.now());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                System.out.println("-- interrupted sleep: " + e);
            }
        }

        for(String text : HORRIBLE_ITERABLE) {
            System.out.println("-- iteration, text is: '" + text + "'");
        }
    }

    private static Iterable<String> generateHorribleIterable() {
        return () -> HORRIBLE_ITERATOR;
    }

    private void forEach() {
        Iterable<String> iterable = new IterableExample("this", "is", "going", "to", "be", "iterated", "over") ;
        for(String element : iterable) {
            System.out.println("-- own iterable's element: '" + element + "'");
        }
    }

    private void run() {
        FakeIterable fake = new FakeIterable();
        for(String element : fake) {
            System.out.println("-- element: " + element);
        }

        for(int i = 0; i < 100; ++i) {
            System.out.println("-- preIncrement i is: " + i);
        }
        for(int i =0; i < 100; i++) {
            System.out.println("-- i is: '" + i + "'");
        }
        for(long i =0; i < 100; i++) {
            System.out.println("-- i is: '" + i + "'");
        }
        for(char i =0; i < 100; i++) {
            System.out.println("-- i is: '" + i + "'");
        }
        for(short i =0; i < 100; i++) {
            System.out.println("-- i is: '" + i + "'");
        }
        for(float i =0; i < 100; i++) {
            System.out.println("-- i is: '" + i + "'");
        }
        for(double i =0; i < 100; i++) {
            System.out.println("-- i is: '" + i + "'");
        }
        //Please don't do that
        for(boolean i = true; i; i = !i) {
            System.out.println("-- i is: '" + i + "'");
        }
//        for(String i = "" + 0; i < 100; i++) {
//            System.out.println("-- i is: '" + i + "'");
//        }
    }

    private class FakeIterable implements Iterable<String> {
        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                String soleElement = "I-am-the-sole-element-of-this-fake";
                boolean usable = true;

                @Override
                public boolean hasNext() {
                    System.out.println("-- will return usable: " + usable);
                    return usable;
                }

                @Override
                public String next() {
                    usable = false;
                    System.out.println("-- usable set to false, will return sole element: '" + soleElement + "'");
                    return soleElement;
                }
            };
        }
    }

    private class IterableExample implements Iterable<String> {
        private final String[] arguments;
        private int pointer = 0;

        public IterableExample(String... arguments) {
            this.arguments = arguments;
        }

        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                @Override
                public boolean hasNext() {
                    System.out.println("---- hasNext called, pointer: " + pointer + ", arguments: "
                            + arrayToString(arguments));
                    return pointer < arguments.length;
                }

                @Override
                public String next() {
                    System.out.println("---- next called, pointer: '" + pointer + "', elements: '"
                            + arrayToString(arguments) + "'");
                    String element = arguments[pointer];
                    pointer++;
                    return element;
                }

                private String arrayToString(String[] array) {
                    return "["
                            + Stream.of(array).map(String::toString).collect(Collectors.joining(", "))
                            + "]";
                }
            };
        }
    }
}
