package com.mindflytech.education.oop.innerclass;

public class StaticNestedClassExperiment {
    private static String outerStatic;

    static {
        String testOuter = "outer-static-content";
        System.out.println("-- outerStatic will be: '" + testOuter + "'");
        System.out.println("-- from outer static block, is StaticNestedClass.testStaticMember already visible: '" + StaticNestedClass.instanceCount + "'");
        outerStatic = testOuter;

        StaticNestedClass adHocInstance = new StaticNestedClass("this instance will soon move out of scope");
        System.out.println("-- ad hoc isntance created in constructor: " + adHocInstance);
        adHocInstance.runnable.run();
        StaticNestedClass.staticRunnable.run();
    }

    private final String content;
    private StaticNestedClass nestedInstance = new StaticNestedClass("created in outer's constructor");

    public StaticNestedClassExperiment(String content) {
        System.out.println("-- StaticNestedClassExperiment constructor called");
        this.content = content;
    }

    public void test() {
        System.out.println("-- I can access my own class members and StaticNestedClass' static members from here: '" + content + "', '"
                + outerStatic + "', '" + nestedInstance + "', '" + StaticNestedClass.staticRunnable + "', '" + StaticNestedClass.instanceCount);
        StaticNestedClass.staticRunnable.run();
        nestedInstance.runnable.run();
        StaticNestedClass.InnerInStaticNested inner = new StaticNestedClass("static inner object to create an inner one").new InnerInStaticNested();
    }

    public String getContent() {
        return content;
    }

    public static abstract class StaticNestedAbstractClass {}

    public static class StaticNestedClass {
        private static int instanceCount = 0;

        static {
            System.out.println("-- static nested class' static block loaded, testStaticMember: " + instanceCount);
            System.out.println("-- from this block, is outer class' static member already visible: '" + outerStatic + "'");
            StaticNestedClassExperiment exp = new StaticNestedClassExperiment("experiment");
            System.out.println("-- I can instantiate the outer class from here: " + exp);
        }

        private static Runnable staticRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("-- anonymous static runnable in StaticNestedClassExperiment runs, instanceCount: " + instanceCount);
            }
        };

        public static Runnable runnableInstance() {
            return staticRunnable;
        }

        private Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("-- anonymous runnable in StaticNestedClassExperiment runs, content: '" + content + "', instanceCount: " + instanceCount);
            }
        };

        private final String content;

        public StaticNestedClass(String content) {
            instanceCount++;
            System.out.println("-- StaticNestedClass constructor called, instanceCount is: " + instanceCount);
            this.content = content;
        }

        public void test() {
            System.out.println(" I can access my class and the outer class' static members from StaticNestedClass: '"
                    + content + "', '" + instanceCount + "', '" + StaticNestedClassExperiment.outerStatic + "', '");
            runnable.run();
            String variable = "local variable";
            final String finalVariable = "final local variable";
            class Local implements Runnable {

                @Override
                public void run() {
                    System.out.println("-- Local class within StaticNestedClass, sees final variables from defining scope, StaticNestedClass content and outer class' static content: '"
                            + finalVariable + "', '" + instanceCount + "', '" + content + "', '" + outerStatic + "', '");
                }
            }
            variable = "reassigned local variable";
            Local local = new Local();
            local.run();
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "StaticNestedClass{" +
                    "content='" + content + '\'' +
                    "; testStaticMember='" + instanceCount + '\'' +
                    '}';
        }

        public class InnerInStaticNested {
            public InnerInStaticNested() {
                System.out.println("-- InnerInStaticNested created");
            }
        };
        public static class StaticInStaticNested {};
    }
}

class Runner {
    public static void main(String[] args) {
        StaticNestedClassExperiment outer = new StaticNestedClassExperiment("outer");
        outer.test();
        StaticNestedClassExperiment.StaticNestedClass inner = new StaticNestedClassExperiment.StaticNestedClass("inner");
        inner.test();
    }
}
