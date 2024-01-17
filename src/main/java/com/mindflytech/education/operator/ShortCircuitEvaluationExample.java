package com.mindflytech.education.operator;

public class ShortCircuitEvaluationExample {
    public static void main(String[] args) {
        ShortCircuitEvaluationExample shortCircuit = new ShortCircuitEvaluationExample();
        shortCircuit.evaluate();
    }

    private void evaluate() {
        System.out.println("-- will start short circuit evaluation example");
        boolean willBeTrueIfFirstIsTrue = provideTrueValue() || provideRandomValue() || provideFalseValue();
        System.out.println("-- willBeTrueIfFirstIsTrue evaluated to: " + willBeTrueIfFirstIsTrue);
    }

    private boolean provideTrueValue() {
        System.out.println("-- provideTrueValue called, will return with true");
        return true;
    }

    private boolean provideRandomValue() {
        System.out.println("-- provideRandomValue called, will return with: ");
        boolean result = System.nanoTime() % 2 == 0 ? true : false;
        System.out.print(result);
        return result;
    }

    private boolean provideFalseValue() {
        System.out.println("-- provideFalseValue called, will return with false");
        return false;

    }
}
