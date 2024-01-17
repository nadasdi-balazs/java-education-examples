package com.mindflytech.education.lambda;


import com.mindflytech.util.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaReturnsWithSubclassOfReturnTypeDefinedInFuncionalInterface {
//    class SuperType {}

//    class SubType extends SuperType {}

    class OtherType {}

    interface OwnRunnable extends Runnable {
        default void defaultMethod() {
            System.out.println("this demonstrates that these methods are callable from the reference side " +
                    "(but not from inside the lambda expression's body");
        }
    }



    public static final Runnable DATE_TIME_STRING_PRINTER = () -> System.out.println("-- current date time is: " + LocalDateTime.now());

    public static void main(String[] args) {
        LambdaReturnsWithSubclassOfReturnTypeDefinedInFuncionalInterface example = new LambdaReturnsWithSubclassOfReturnTypeDefinedInFuncionalInterface();
        example.runExample();
    }

    private void runExample() {
        Functional lambda = () -> new SubType();

        List<Horse> horses = horsesGenerator();
        List<Horse> pegasuses = horses.stream()
                .filter(horse -> {
                    if (horse instanceof Pegasus) {
                        System.out.println("-- I have found a pegasus!: " + horse);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        List<Horse> silentPegasuses = horses.stream()
                .filter(horse -> horse instanceof Pegasus)
                .collect(Collectors.toList());

//        List<EggLayer> egglayersConvertedFromHorses = horses.stream()
//                .<FireBreather>map(horse -> {
//                    if (horse instanceof Pegasus) {
//                        return new Dragon();
//                    }
//                    //Compile error
//                    //Bad return type in lambda expression: Chicken cannot be converted to FireBreather
//                    return new Chicken();
//                })
//                .collect(Collectors.toList());

        List<EggLayer> egglayersConvertedFromHorses = horses.stream()
                .<EggLayer>map(horse -> {
                    if (horse instanceof Pegasus) {
                        return new Dragon();
                    }
                    //Compile error
                    //Bad return type in lambda expression: Chicken cannot be converted to FireBreather
                    return new Chicken();
                })
                .collect(Collectors.toList());

        int name = 29;
        Runnable runnableLambda = () -> {
            //Compiler error
            //Variable 'name' is already defined in the scope
//            int name = 27;
            if(Utils.fiftyPercentChance()) {
                //this is here to demonstrate that if the lambda implements a void method, you can still
                //use return, but the return must be void (e.g. should not return any value)
                return;
            }
            System.out.println("-- current date and time: " + LocalDateTime.now());
        };

        OwnRunnable ownRunnable = () -> {
            System.out.println("-- own runnable runs in lambda body");
            //compiler error
            //Cannot resolve method 'defaultMethod' in 'LambdaReturnsWithSubclassOfReturnTypeDefinedInFuncionalInterface'
//            defaultMethod();
        };
        ownRunnable.defaultMethod();
        ownRunnable.run();

        /**
         * This is here to demonstrate that while lambda expressions do not introduce a new level of scope
         * unlike anonymous inner classes, that do it (name could be declared here, but could not be declared
         * in the previous lambda
         */
        Runnable anonymousInnerClassRunnable = new Runnable() {
            private int name = 26;

            @Override
            public void run() {
                System.out.println("-- name is: " + name);
            }
        };
        anonymousInnerClassRunnable.run();

        DATE_TIME_STRING_PRINTER.run();

        Functional other = () -> new SuperType();
        Functional usesDefault = () -> {
            //compile error
            //Cannot resolve method 'printer' in 'LambdaReturnsWithSubclassOfReturnTypeDefinedInFuncionalInterface'
//            printer();
            //compile error
            //Non-static method 'printer()' cannot be referenced from a static context
//            Functional.printer();
            other.printer();
            return new SubType();
        };
        usesDefault.printer();

        Functional implementedWithAnonymousInnerClass = new Functional() {
            @Override
            public SuperType abstractMethod() {
                System.out.println("-- I am an anonymous inner class, therefore I can use the " +
                        "default methods of the interface I implement as follows:");
                printer();
                return new SuperType();
            }
        };
        implementedWithAnonymousInnerClass.printer();
    }

    private List<Horse> horsesGenerator() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse());
        horses.add(new Horse());
        horses.add(new Pegasus());
        horses.add(new Horse());
        horses.add(new Pegasus());
        horses.add(new Horse());
        return horses;
    }


    class Horse {
        public String identifyMyself() {
            return "I am a horse.";
        }
    }

    interface Flyer {
        default public String identifyMyself() {
            return "I am able to fly.";
        }

        default public String identify() {
            return "I am able to fly.";
        }
    }

    interface Mythical {
        default public String identifyMyself() {
            return "I am a mythical creature.";
        }
    }

    class Pegasus extends Horse implements Flyer,Mythical {
        public void identifyAsSupers() {
        }
    }

    interface Animal2 {
        default public String identifyMyself() {
            return "I am an animal.";
        }
    }

    interface EggLayer extends Animal2 {
        default public String identifyMyself() {
            return "I am able to lay eggs.";
        }
    }

    interface FireBreather extends Animal2 {
//    default public String identifyMyself() {
//        return "I am able to lay eggs.";
//    }
    }

    class Dragon implements EggLayer, FireBreather {
    }

    class Chicken implements EggLayer {}
}
