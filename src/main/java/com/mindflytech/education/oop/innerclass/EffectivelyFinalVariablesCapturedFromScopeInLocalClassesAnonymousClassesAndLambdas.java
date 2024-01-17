package com.mindflytech.education.oop.innerclass;

public class EffectivelyFinalVariablesCapturedFromScopeInLocalClassesAnonymousClassesAndLambdas {
    public static void main(String[] args) {
        EffectivelyFinalVariablesCapturedFromScopeInLocalClassesAnonymousClassesAndLambdas example =
                new EffectivelyFinalVariablesCapturedFromScopeInLocalClassesAnonymousClassesAndLambdas();
        example.runExample();
    }

    private void runExample() {
        String effectivelyFinal = "this-value-will-not-be-overriden";
        final String finalString = "I-am-final!";
        String variableString = "this-value-will-change";

        class ScopeCapturer implements Runnable {
            @Override
            public void run() {
                System.out.println("ScopeCapturer.run captures certan variables from enclosing scope: '"
                    + effectivelyFinal + "', '" + finalString + "', '" /*+ variableString + "'"*/);
            }
        }
        ScopeCapturer capturer = new ScopeCapturer();
        capturer.run();

        Runnable anonymousCapturer = new Runnable() {
            @Override
            public void run() {
                System.out.println("anonymous Runnable run() captures certan variables from enclosing scope: '"
                        + effectivelyFinal + "', '" + finalString + "', '" /*+ variableString + "'"*/);
            }
        };
        anonymousCapturer.run();

        Runnable capturerLambda = () -> {
            System.out.println("Runnable lambda run() captures certan variables from enclosing scope: '"
                    + effectivelyFinal + "', '" + finalString + "', '" /* + variableString + "'" */);
        };
        capturerLambda.run();

        variableString = "value-changed";

        final int someValue;
        //compile error
        //Variable 'someValue' might not have been initialized
//        System.out.println("some value: "  + someValue);
        someValue = 27;
        System.out.println("some value: "  + someValue);
    }
}
