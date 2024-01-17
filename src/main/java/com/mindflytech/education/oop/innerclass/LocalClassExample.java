package com.mindflytech.education.oop.innerclass;

public class LocalClassExample {
    private static String staticMember = "static-member";
    private String member = "member";

    interface HelloThere {
        String interfaceConstant = "interface-constant";

//        void screwUpLambda();

        void greet();

        default void accessMembers() {
            System.out.println("-- I can access IF constants and static members: '"
                    + staticMember + "', '" + interfaceConstant + "'");
        }
    }

    public void greetInEnglishSaneImplementation() {
        HelloThere helloThere = new HelloThere() {
            @Override
            public void greet() {
                System.out.println("Hello there!");
            }
        };
        helloThere.greet();
    }

    public void greetInEnglishSaneImplementationWithLambda() {
        HelloThere helloThere = () -> System.out.println("Hello there!");
        helloThere.greet();
    }

    public void greetInEnglish() {
        String name = "Balazs";
        interface HelloThere {
            String interfaceConstant = "interface-constant";

            void greet();

            default void accessMembers() {
                System.out.println("-- I can access IF constants and static members: '"
                        + staticMember + "', '" + interfaceConstant + "'"
                        + " cannot access non-static member: " /*+ member*/);
            }
        }
        class EnglishHelloThere implements HelloThere {
            private String notFinal = "I am not final";

            static {
                System.out.println("-- local class static initializer block, calls static method: '" + staticCallable() + "', ");
            }

            private static String staticCallable() {
                return "I exist and I am called";
            }

            public void greet() {
                System.out.println("Hello " + name + ", member: '" + member + "'");
            }
        }
        HelloThere myGreeting = new EnglishHelloThere();
        myGreeting.greet();
        myGreeting.accessMembers();
    }

    public void sayGoodbyeInEnglish() {
        class EnglishGoodbye {
            public static void sayGoodbye() {
                System.out.println("Bye bye");
            }
        }
        EnglishGoodbye.sayGoodbye();
    }

    public static void main(String[] args) {
        LocalClassExample localClassExample = new LocalClassExample();
        localClassExample.greetInEnglish();
        localClassExample.sayGoodbyeInEnglish();
        localClassExample.callWeirdChainSideEffectCallables();
    }

    private void callWeirdChainSideEffectCallables() {
        class WeirdChainSideEffectCallables {
            public WeirdChainSideEffectCallables weirdCallableSideEffectPrinterMethodOne() {
                System.out.println("-- this method is instantly called in the expression where we created the" +
                        " anonymous class, prints this message as a side-effect and returns itself");
                return this;
            }

            //This will not work with anonymous classes, but works with local classes
            public WeirdChainSideEffectCallables weirdCallableSideEffectPrinterMethodTwo() {
                System.out.println("-- thus we can chain the method calls");
                return this;
            }

            public WeirdChainSideEffectCallables weirdCallableSideEffectPrinterMethodThree() {
                System.out.println("-- so it looks like a weird abuse of builder pattern");
                return this;
            }
        }
        WeirdChainSideEffectCallables callEveryMethod = new WeirdChainSideEffectCallables()
                .weirdCallableSideEffectPrinterMethodOne()
                .weirdCallableSideEffectPrinterMethodTwo()
                .weirdCallableSideEffectPrinterMethodThree();
    }

    //Compile error: can't make them visible for each other *and* called by their (non-existent)
    //canonical names
//    private void SealedHolder() {
//        sealed class SealedSwitchPatternMatchingExample permits com.mindflytech.education.oop.innerclass.LocalClassExample.One, Two {
//
//        }
//    }
//    final class One extends SealedSwitchPatternMatchingExample {}
//    final class Two extends SealedSwitchPatternMatchingExample {}
}
