package com.mindflytech.education.oop.innerclass;

public class InnerClassExperiment {
    private static String outerClassStaticField = "outer-class-static-field";

    static {
        System.out.println("-- outer class' static initializer block runs");
    }

    private String outerClassField = "outer-class-field";

    private void outerClassMethod() {
        System.out.println("-- outer class method runs");
    }

    public class InnerClass {
        static {
            System.out.println("-- InnerClass' static initializer block runs");
        }

        private static String innerClassStatic = "inner-class-static-string";

        public static class StaticNestedClassInInnerClass {}

        public class InnerInnerClass {}

        private String innerClassField = "inner-class-member-string";

        public void run() {
            outerClassMethod();
            InnerClassExperiment.this.outerClassMethod();
            int thisIsNotFinal = 27;
            int thisIsFinal = 27;
            class LocalClass implements Runnable {
                static {
                    System.out.println("-- The documentation on Oracle's website says that local classes can't have" +
                            " static members, but apparently this is not the case");
                }
                @Override
                public void run() {
                    System.out.println("-- local class' run method runs, it can access inner local final members, " +
                            "inner class members (and static stuff) and outer class members: '"
                            + thisIsFinal + "', '" + innerClassField + "', '" + innerClassStatic + "', '"
                            + outerClassField + "', '" + outerClassStaticField);
                }
            }
            LocalClass local = new LocalClass();
            thisIsNotFinal = 28;
            local.run();
            outerClassMethod();
        }
    }
}

class InnerRunner {
    public static void main(String[] args) {
        InnerClassExperiment outer = new InnerClassExperiment();
        InnerClassExperiment.InnerClass inner = outer.new InnerClass();
        inner.run();
    }
}
