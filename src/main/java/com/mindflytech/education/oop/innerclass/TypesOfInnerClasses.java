package com.mindflytech.education.oop.innerclass;

import com.google.common.annotations.VisibleForTesting;

public class TypesOfInnerClasses {
    private static String staticFieldOfEnclosingClass = "I am a STATIC field of the enclosing class called TypesOfInnerClasses";
    private String fieldOfEnclosingClass = "I am a field of the enclosing class called TypesOfInnerClasses";

    static {
        System.out.println("-- external holder TypesOfInnerClasses loaded");
    }

    public TypesOfInnerClasses() {
        System.out.println("-- external holder TypesOfInnerClasses instantiated");
        StaticNestedClass.staticMethod();
    }

    public static class AnotherStaticInnerClass {
        static {
            System.out.println("-- static inner class AnotherStaticInnerClass loaded");
        }

        @Override
        public String toString() {
            return "TypesOfInnerClasses.AnotherStaticInnerClass{}";
        }
    }

    @VisibleForTesting
    static interface HolderInterface {
        String CONSTANT = "i-belong-to-holder-interface";

        class InnerClassInInterface{}

        //compiler warning
        //Modifier 'static' is redundant for inner classes of interfaces
        static class StaticClassInInterface {}

        static enum Enum {
            ONE,
            TWO
        }

        static record Record() {}

        default void holderMethod() {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("-- anonymous class within a default method of a nested interface runs: " + this);
                }
            };
            class Local {
                void print() {
                    System.out.println("-- I can access the interface's CONSTANT: '" + CONSTANT + "'");
                    System.out.println("-- even the anonym Runnable defined in the same method call: " + runnable);
                    runnable.run();
                }
            }
            Local local = new Local();
            local.print();
            runnable.run();
        }
    }

    //compiler warning
    //Modifier 'static' is redundant for inner interfaces
    static interface StaticInterface {}

    enum Enum {
        ONE,
        TWO;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    //compiler warning
    //Modifier 'static' is redundant for inner enums
    static enum StaticEnum {
        ONE,
        TWO
    }

    record Record() {
        @Override
        public String toString() {
            return "Record{}";
        }
    }

    //compiler warning
    //Modifier 'static' is redundant for inner records
    static record StaticRecord() {}

    static class StaticNestedClass {
        static class StaticNestedClassWithinStaticNestedClass {
            static class StaticNestedClassInStaticNestedClass {}
        }

        class InnerClassInStaticNestedClass {
            static class StaticNestedClassInInnerClass {}
        }

        interface InnerInterfaceInStaticInnerClass extends Runnable {
            //compiler warning
            //Modifier 'static' is redundant for inner classes of interfaces
            static class StaticNestedClassInInterface {}
        };

        enum Enum {
            ONE {
                static class StaticClassInEnumConstant {}
            },
            TWO;

            static class StaticClassInEnum {}
        }

        //compiler warning
        //Modifier 'static' is redundant for inner enums
        static enum StaticEnum {
            ONE,
            TWO
        }

        record Record() {
            static class StaticClassInRecord {}
        }

        //compiler warning
        //Modifier 'static' is redundant for inner records
        static record StaticRecord() {}

        static {
            System.out.println("-- StaticNestedClass loaded");
            class LocalClass implements Runnable {
                @Override
                public void run() {
                    System.out.println("-- I can't believe that this is happening, but apparently I can define local classes in static initializer blocks as well");
                }
            }
            LocalClass local = new LocalClass();
            local.run();
        }

        static String staticContent = "i-am-StaticNestedClass-static-variable";

        String memberContent = "i-am-StaticNestedClass-member-variable";

        public void instanceMethod() {
            final class LocalClass {
                {
                    System.out.println("-- local classes can have initializer blocks. here I can access the enclosing class instance: " + StaticNestedClass.this);
                }

                static void staticAccess() {
                    System.out.println("-- LocalClass.staticAccess called, accesses enclosing class' static members: '" + StaticNestedClass.staticContent + "'");
                    //compile error
                    //'com.mindflytech.education.oop.innerclass.TypesOfInnerClasses.StaticNestedClass.this' cannot be referenced from a static context
//                    StaticNestedClass.this.instanceMethod();
                }

                void access() {
                    System.out.println("-- LocalClass.access called, accesses enclosing class' members: '" + StaticNestedClass.this.memberContent + "'");
                }
            }
            LocalClass.staticAccess();
            LocalClass local = new LocalClass();
            local.access();

            abstract class AbstractLocalClass {
                abstract void doIt();
            }
            AbstractLocalClass iAmDoingIt = new AbstractLocalClass() {
                @Override
                void doIt() {
                    System.out.println("I am doing it");
                }
            };
            iAmDoingIt.doIt();
            interface LocalInterface {}
            enum Enum implements Runnable {
                ONE {
                    @Override
                    public void run() {
                        System.out.println("-- local enum constant ONE: " + this + " runs. Enum is static, so it can access the enclosing class' static context: "
                                + StaticNestedClass.staticContent);
                    }
                };
            }
            Enum.ONE.run();
            record Record() {}
        }

        public static void staticMethod() {
            System.out.println("-- StaticNestedClass.staticMethod called");
            class LocalClass {
                static class StaticClassInLocalClass {}

                class InnerClassInLocalClass {}

                interface InterfaceInLocalClass {}

                enum Enum {
                    ONE,
                    TWO;
                }

                record Record() {}

                static {
                    System.out.println("-- static initializer block in local class");
                }

                {
                    System.out.println("-- local classes can have initializer blocks");
                }

                private final int privateField = 42;
                protected final String protectedField = "I-am-protected-string";
                String defaultField = "I-am-default-string";
                public String publicField = "I-am-public-string";

                public static void staticMethodInLocalClassInStaticMethod() {
                    System.out.println("-- staticMethodInLocalClassInStaticMethod called");
                }

                public void methodInLocalClassInStaticMethod() {
                    System.out.println("-- smethodInLocalClassInStaticMethod called");
                }
            }
            System.out.println("-- LocalClass.StaticClassInLocalClass will be instantiated");
            LocalClass.StaticClassInLocalClass staticClassInLocalClass = new LocalClass.StaticClassInLocalClass();
            System.out.println("-- LocalClass.StaticClassInLocalClass has been instantiated: " + staticClassInLocalClass);
            System.out.println("-- LocalClass.staticMethodInLocalClassInStaticMethod() will be called");
            LocalClass.staticMethodInLocalClassInStaticMethod();
            System.out.println("-- LocalClass.staticMethodInLocalClassInStaticMethod() has been called, will instantiate");
            LocalClass local = new LocalClass();
            System.out.println("-- LocalClass instance: " + local);
            local.methodInLocalClassInStaticMethod();
            LocalClass.InnerClassInLocalClass innerClassInLocalClass = local.new InnerClassInLocalClass();
            LocalClass.InterfaceInLocalClass interfaceInLocalClass = new LocalClass.InterfaceInLocalClass() {};
            LocalClass.Enum one =  LocalClass.Enum.ONE;
            LocalClass.Record recordLocal = new LocalClass.Record();
        }

        public StaticNestedClass() {
            System.out.println("-- StaticNestedClass' constructor called");
        }
    }

    class InnerClass {
        {
            System.out.println("I am also a code block, but I always run during instantiation, before the constructor call");
        }
        static class StaticClassInInnerClass {
            class InnerClassInStaticClass {}
        }

        class InnerClassInInnerClass {}

        interface InterfaceInInnerClass {
            class InnerClassInInterface {}
        }

        enum Enum {
            ONE,
            TWO;
            class InnerClassInEnum {}
        }

        record Record() {
            class InnerClassInRecord {}
        }

        public void instantiate() {
            String fieldOfEnclosingClass1 = TypesOfInnerClasses.this.fieldOfEnclosingClass;
            System.out.println("-- member classes can access outer class' static content: '" + staticFieldOfEnclosingClass + "'");
            System.out.println("-- I can see the enclosing class' field from the inner class: '" + fieldOfEnclosingClass1 + "'");
            InterfaceInInnerClass.InnerClassInInterface innerClassInInterface = new InterfaceInInnerClass.InnerClassInInterface();
            TypesOfInnerClasses.this.method();
        }

        public static void accessStaticFieldOfOuterClass() {
            {
                System.out.println("this is also a code block");
            }
            System.out.println("-- member class' static method can access outer class' static content: '" + staticFieldOfEnclosingClass + "'");
            //compiler error
            //Non-static field 'fieldOfEnclosingClass' cannot be referenced from a static context
//            System.out.println("-- I can see the enclosing class' field from the inner class: '" + fieldOfEnclosingClass + "'");
        }
    }

    private void method() {
        System.out.println("TypesOfInnerClasses.method() called");
    }
}

class Instantiator {
    public static void main(String[] args) {
        System.out.println("-- instantiator's main started");
        TypesOfInnerClasses.HolderInterface holderInterface = new TypesOfInnerClasses.HolderInterface() {};
        holderInterface.holderMethod();

        TypesOfInnerClasses.StaticNestedClass.staticMethod();
        TypesOfInnerClasses.StaticNestedClass staticNestedClass = new TypesOfInnerClasses.StaticNestedClass();
        System.out.println("-- StaticNestedClass instantiated: " + staticNestedClass);
        staticNestedClass.instanceMethod();
        TypesOfInnerClasses.StaticNestedClass.StaticNestedClassWithinStaticNestedClass staticNestedClassWithin = new TypesOfInnerClasses.StaticNestedClass.StaticNestedClassWithinStaticNestedClass();
        TypesOfInnerClasses.StaticNestedClass holder = new TypesOfInnerClasses.StaticNestedClass();
        TypesOfInnerClasses.StaticNestedClass.InnerClassInStaticNestedClass innerClassInStaticNestedClass = holder.new InnerClassInStaticNestedClass();
        new TypesOfInnerClasses.StaticNestedClass.InnerInterfaceInStaticInnerClass() {
            static class StaticClassInAnonymousClass {}

            @Override
            public void run() {
                System.out.println("-- anonymous class defined as TypesOfInnerClasses.StaticNestedClass.InnerInterfaceInStaticInnerClass" +
                        " runs");
            }
        }.run();

        Outer.StaticNestedClass staticNestedClassInstance = new Outer.StaticNestedClass();

        instantiateStaticClasses();
        instantiateInnerClasses();
    }

    private static void instantiateInnerClasses() {
        TypesOfInnerClasses.InnerClass innerClass1 = new TypesOfInnerClasses().new InnerClass();
        TypesOfInnerClasses types = new TypesOfInnerClasses();
        TypesOfInnerClasses.InnerClass innerClass = types.new InnerClass();
        TypesOfInnerClasses.InnerClass anotherInnerClassInstance = types.new InnerClass();
        //compile error
        //Qualified new of static class
//        innerClass.new StaticClassInInnerClass();
        TypesOfInnerClasses.InnerClass.StaticClassInInnerClass staticClassInInnerClass = new TypesOfInnerClasses.InnerClass.StaticClassInInnerClass();
        TypesOfInnerClasses.InnerClass.InterfaceInInnerClass interfaceInInnerClass = new TypesOfInnerClasses.InnerClass.InterfaceInInnerClass() {};
        TypesOfInnerClasses.InnerClass.InterfaceInInnerClass.InnerClassInInterface innerClassInInterface = new TypesOfInnerClasses.InnerClass.InterfaceInInnerClass.InnerClassInInterface();
        TypesOfInnerClasses.InnerClass.Enum enumInstance = TypesOfInnerClasses.InnerClass.Enum.ONE;
        TypesOfInnerClasses.InnerClass.Enum.InnerClassInEnum innerClassInEnum = enumInstance. new InnerClassInEnum();
        TypesOfInnerClasses.InnerClass.Record recordInstance = new TypesOfInnerClasses.InnerClass.Record();
        TypesOfInnerClasses.InnerClass.Record.InnerClassInRecord innerClassInRecord = recordInstance.new InnerClassInRecord();
    }

    private static void instantiateStaticClasses() {
        TypesOfInnerClasses.StaticNestedClass.InnerClassInStaticNestedClass.StaticNestedClassInInnerClass something = new TypesOfInnerClasses.StaticNestedClass.InnerClassInStaticNestedClass.StaticNestedClassInInnerClass();
        TypesOfInnerClasses.StaticNestedClass.InnerInterfaceInStaticInnerClass.StaticNestedClassInInterface interface1 = new TypesOfInnerClasses.StaticNestedClass.InnerInterfaceInStaticInnerClass.StaticNestedClassInInterface();
        TypesOfInnerClasses.StaticNestedClass.Enum.StaticClassInEnum classInEnum = new TypesOfInnerClasses.StaticNestedClass.Enum.StaticClassInEnum();
        //It can't see the class defined only within the constant
//        TypesOfInnerClasses.StaticNestedClass.Enum.ONE.
        TypesOfInnerClasses.StaticNestedClass.Record.StaticClassInRecord staticClassInRecord = new TypesOfInnerClasses.StaticNestedClass.Record.StaticClassInRecord();
    }
}

class Outer {
    static class StaticNestedClass {

    }
}