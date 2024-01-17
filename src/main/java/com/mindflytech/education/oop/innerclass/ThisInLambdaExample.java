package com.mindflytech.education.oop.innerclass;

public class ThisInLambdaExample {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.Inner inner = outer.new Inner();
        inner.lambdaHolder();
    }
}

class OuterClass {
    private String variable = "I-am-defined-in-outer-class";

    class Inner {
        private String variable = "I-am-defined-in-inner-class";

        void lambdaHolder() {
            String variable = "I-am-defined-in-inner's-method";

            Runnable runnable = () -> {
                //compile error
                //Variable 'variable' is already defined in the scope
//                int variable = 3;
                System.out.println("-- in lambda, variable: '" + variable + "'");
                System.out.println("-- in lambda, this.variable: '" + this.variable + "'");
                System.out.println("-- in lambda, OuterClass.this.variable: '" + OuterClass.this.variable + "'");
            };
            runnable.run();
        }
    }
}
