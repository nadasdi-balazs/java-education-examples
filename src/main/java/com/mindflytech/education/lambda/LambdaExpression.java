package com.mindflytech.education.lambda;

import com.mindflytech.util.Utils;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class LambdaExpression {
    @FunctionalInterface
    private interface Functional {
        void unaryVoidFunction(String parameter);
    }

    private interface NonVoidFunctional {
        String unaryStringFunction(String parameter);
    }

    @ToString
    private static class ConstructorReferenceTest {
        private final String status;

        public ConstructorReferenceTest(String input) {
            log.info("-- ConstructorReferenceTest constructor called");
            this.status = input;
            log.info("-- ConstructorReferenceTest constructor exited");
        }

        public String getStatus() {
            return status;
        }
    }

    private String prefixWithDateTime(String message) {
        LocalDateTime now = LocalDateTime.now();
        return now.toString() + " " + message;
    }

    public void runLambda() {
        NonVoidFunctional nonVoid = parameter -> "prefix::" + parameter.toUpperCase() + "::postfix";
        System.out.println("--called nonvoid: " + nonVoid.unaryStringFunction("something"));
        NonVoidFunctional nonVoidBlock = parameter -> {
            return "prefix::" + parameter.toUpperCase() + "::postfix";
        };
        System.out.println("--called nonvoid with block: " + nonVoidBlock.unaryStringFunction("something"));

        NonVoidFunctional usingMethodReference = this::prefixWithDateTime;
        System.out.println("--called usingMethodReference: " + usingMethodReference.unaryStringFunction("something"));

        //simplest usage of a lambda function
        Functional functional = parameter -> System.out.println("parameter of function: '" + parameter + "'");
        functional.unaryVoidFunction("bloergh");

        //return is not needed, even with multiple lines, because there is no return value
        Functional functional2 = parameter -> {
            System.out.println("This lambda is quite chatty");
            if(Utils.fiftyPercentChance()) {
                return;
            }
            System.out.println("parameter of function: '" + parameter + "'");
            System.out.println("The chatty lambda now exits");
        };
        functional2.unaryVoidFunction("hey, chatty");

        log.info("-- will create constructor reference");
        Function<String, ConstructorReferenceTest> constructorReference = ConstructorReferenceTest::new;
        log.info("-- constructor reference created: '" + constructorReference + "'");
        ConstructorReferenceTest result = constructorReference.apply("-- argument -> status");
        log.info("-- created object: '" + result + "'");

        List<String> testString = new ArrayList<>() {{
            add("first");
            add("seCond");
            add("third");
        }};

        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String string) {
                return containsUpperCase(string);
            }
        };
        List<String> results = testString.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        Utils.printList(results);
        Predicate<String> predicateFromLambda = string -> containsUpperCase(string);
        List<String> resultsFromLambda = testString.stream()
                .filter(predicateFromLambda)
                .collect(Collectors.toList());
        Utils.printList(results);

        List<String> resultsWithoutPredicateVariable = testString.stream()
                .filter(string -> {
                    char[] charArray = string.toCharArray();
                    for (char c : charArray) {
                        if (Character.isUpperCase(c)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
        Utils.printList(results);

        Predicate<String> predicateLambda = element -> containsUpperCase(element);
        List<String> results2 = testString.stream().filter(predicateLambda).collect(Collectors.toList());
        Utils.printList(results2);

        Predicate<String> predicateLambda2 = element -> {
            char[] charArray = element.toCharArray();
            for (char c : charArray) {
                if (Character.isUpperCase(c)) {
                    return true;
                }
            }
            return false;
        };
        List<String> results3 = testString.stream().filter(predicateLambda2).collect(Collectors.toList());
        Utils.printList(results3);

        List<String> results4 = testString.stream().filter(element -> containsUpperCase(element)).collect(Collectors.toList());
        Utils.printList(results4);

        List<String> results5 = testString.stream().filter(this::containsUpperCase).collect(Collectors.toList());
        Utils.printList(results5);

        List<String> results6 = testString.stream()
                .filter(element -> {
            char[] charArray = element.toCharArray();
            for (char c : charArray) {
                if (Character.isUpperCase(c)) {
                    return true;
                }
            }
            return false;
        })
                .collect(Collectors.toList());
        Utils.printList(results6);
    }

    private boolean containsUpperCase(String string) {
        char[] charArray = string.toCharArray();
        for (char c : charArray) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LambdaExpression lambda = new LambdaExpression();
        lambda.runLambda();
    }
}
