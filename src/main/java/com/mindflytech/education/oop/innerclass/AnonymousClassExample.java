package com.mindflytech.education.oop.innerclass;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class AnonymousClassExample {
    private String field = "member-field";
    private static String staticField = "static-field";
    private Runnable anonymRunnable = new Runnable() {
        static {
            System.out.println("-- even an anonymous class can have a static initializer");
        }

        @Override
        public void run() {
            System.out.println("-- anonymous runnable runs, accesses members and static members: '"
                    + field + "', '" + staticField + "'");
        }
    };
    private static Runnable staticAnonymRunnable = new Runnable() {
        static {
            System.out.println("-- another anonymous class with a static initializer");
        }

        @Override
        public void run() {
            System.out.println("-- static anonymous runnable runs, accesses static members: '" + staticField + "'");
        }
    };

    private static void staticWork() {
        new HasConstructorArg() {
            static {
                System.out.println("-- anonymous classes can have static initializer blocks");
            }

            @Override
            public void implementMe() {
                System.out.println("-- in anonymous class, implementMe method, content is: '" + content + "', also accessing enclosing class' static field: '" + staticField + "'");
            }
        }.implementMe();

        //Compiler error:
        //Target type of a lambda conversion must be an interface
//        HasConstructorArg withLambda = () -> {
//            System.out.println("-- in anonymous class, implementMe method, content does not exist, also accessing enclosing class' static field: '" + staticField + "'");
//        };
    }

    private void work() {
        String contentGiven = "ad-hoc-content";
        String contentRetrieved = new HasConstructorArg(contentGiven) {
            @Override
            public void implementMe() {
                System.out.println("-- implemented");
            }
        }.getContent();
        System.out.println("-- content given: '" + contentGiven + "', content retrieved: '" + contentRetrieved + "'");
        HasConstructorArg hasConstructorArg = new HasConstructorArg(contentGiven, "print this!") {
            @Override
            public void implementMe() {
                System.out.println("-- implementMe, anonym class' content: '" + content + "'");
            }
        };
        hasConstructorArg.implementMe();
        new HasConstructorArg() {
            @Override
            public void implementMe() {
                System.out.println("-- in anonymous class, implementMe method, content is: '" + content + "', also accessing enclosing class' instance field: '" + field + "'");
            }
        }.implementMe();
        System.out.println("-- also running other runnable anonymous classes");
        anonymRunnable.run();

        Object objectWithUnusableMethods = new Object() {
            public String useMe() {
                return "useMe method called (not callable this way)";
            }
        }.useMe();
        //Compile error
        //Cannot resolve method 'useMe' in 'Object'
//        objectWithUnusableMethods.useMe();
        class CallableUseMe {
            public String useMe() {
                return "useMe method called (callable this way)";
            }
        };
        CallableUseMe callMe = new CallableUseMe();
        System.out.println("-- local class' own (not inherited) method can be called, unlike anonymous class' own method: '"
                + callMe.useMe() + "'");

        //Valid, but has no effect
        new Object() {
            public String useMe() {
                return "useMe method called (not callable this way)";
            }
        };
        String used = new Object() {
            private static int counter;

            private int nonStaticCounter;

            {
                System.out.println("-- instance initializer initializes counters to 2");
                counter = 2;
                nonStaticCounter = 2;
            }

            public String useMe() {
                counter++;
                nonStaticCounter++;
                return "useMe method called (callable this way), counter: " + counter + ", nonStaticCounter: " + nonStaticCounter;
            }
        }.useMe();

        System.out.println("-- retrieved when instantly called, retrieved string: '" + used + "'");

        String staticUsed = new Object() {
            public static String staticUseMe() {
                return "useMe method called (not callable this way";
            }
        }.staticUseMe();
        System.out.println("-- retrieved when instantly called static method, retrieved string: '" + staticUsed + "'");

        Object notObject = new Object() {
            public Object weirdCallableSideEffectPrinterMethodOne() {
                System.out.println("-- this method is instantly called in the expression where we created the" +
                        " anonymous class, prints this message as a side-effect and returns itself");
                return this;
            }

            //THIS IS NOT TRUE  - we haven't defined a type, therefore we don't have access to this method
            //UNLESS THIS IS THE FIRST CALLED METHOD
            public Object weirdCallableSideEffectPrinterMethodTwo() {
                System.out.println("-- thus we can chain the method calls");
                return this;
            }
        }.weirdCallableSideEffectPrinterMethodOne();

        new Runnable() {
            static class StaticNestedClass {}
            class InnerClass {}
            record Record(String content) {}
            record Null() {}
            enum Enum {
                ONE,
                TWO;
            }

            @Override
            public void run() {
                Record record = new Record("content");
                record.content();
                System.out.println(record.toString());

                class Local {
                    private final String content;

                    Local(String content) {
                        this.content = content;
                    }

                    @Override
                    public String toString() {
                        return "Local{" +
                                "content='" + content + '\'' +
                                '}';
                    }
                }

                Local local = new Local("local-instance");
                System.out.println("-- in run, instantiated local class: " + local);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-- runnable in runnable");
                    }
                };
                Runnable lambdaRunnable = () -> System.out.println("-- runnable in lambda runnable");
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-- this will run here");
                    }
                }.run();
                System.out.println("-- in run, instantiated anonymous class: " + runnable);
                StaticNestedClass staticNestedClass = new StaticNestedClass();
                System.out.println("-- in run, instantiated staticNestedClass: " + staticNestedClass);
                InnerClass innerClass = new InnerClass();
                System.out.println("-- in run, instantiated innerClass: " + innerClass);
                Record randomRecord = new Record("record-content");
                System.out.println("-- in run, instantiated randomRecord: " + randomRecord);
                Enum randomEnum = Enum.ONE;
                System.out.println("-- in run, instantiated randomEnum: " + randomEnum);
            }
        }.run();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        new Object() {
            public void useMe() {

            }
        }.useMe();

        Object anonymousObject = new Object() {
            public void useMe() {
            }
        };
//        anonymousObject.useMe();

    }

    public static void main(String[] args) {
        AnonymousClassExample.staticWork();
        AnonymousClassExample anonym = new AnonymousClassExample();
        anonym.work();
        staticAnonymRunnable.run();
    }

    static abstract class HasConstructorArg {
        protected final String content;

        HasConstructorArg() {
            System.out.println("-- no-arg constructor called, content will be default");
            this.content = "default-content";
        }

        HasConstructorArg(String content) {
            this.content = content;
        }

        HasConstructorArg(String content, String printOnly) {
            System.out.println("-- called with two arguments, the print-only is: '" + printOnly + "'");
            this.content = content;
        }

        public abstract void implementMe();


        public String getContent() {
            return content;
        }
    }
}


