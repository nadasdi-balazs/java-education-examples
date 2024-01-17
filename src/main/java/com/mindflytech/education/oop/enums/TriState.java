package com.mindflytech.education.oop.enums;

public enum TriState implements Runnable {
    TRUE(1) {
        public void printTrue() {
            System.out.println("TRUE");
        }

//        @Override
//        public void run() {
//            System.out.println("-- TriState.TRUE runs");
//        }

        @Override
        public String asLowerCase() {
            return "true";
        }
    },
    MAYBE(0.5) {
        @Override
        public void run() {
            System.out.println("-- TriState.MAYBE runs");
        }

        @Override
        public String asLowerCase() {
            return "maybe";
        }

        //as per https://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#jls-8.9
        //"Instance methods declared in these class bodies may be invoked outside the enclosing enum type only if they
        // override accessible methods in the enclosing enum type."
       //true: valid, but not accessible from outside
        public boolean isTrue() {
            return Math.random() > 0.5d;
        }

        public static final int MAYBE_OWN_CONSTANT = 23;
        public int maybeOwnVariable = 24;
    },
    FALSE(0) {
        @Override
        public void run() {
            System.out.println("-- TriState.FALSE runs");
        }

        @Override
        public String asLowerCase() {
            return "false";
        }
    };

    //This does not have to be final, but it is strongly recommended to be
    private /*final*/ double certainty;

    //Modifier 'public' not allowed here
    //@Contract(pure = true)
    //public private TriState(     double certainty )
    TriState(double certainty) {
        this.certainty = certainty;
    }

    public static TriState getRandom() {
        double random = Math.random();
        System.out.println("-- random value: " + random);
        if(random < (1.0 / 3.0)) {
            return TriState.FALSE;
        } else if (random < (2.0 / 3.0)) {
            return TriState.MAYBE;
        }
        return TriState.TRUE;
    }

//    We could also implement Runnable here:
    @Override
    public void run() {
        System.out.println("-- TriState base run() method");
    }

    @Override
    public String toString() {
        return "TriState{" +
                "certainty=" + certainty +
                ",name=" + this.name() +
                ",ordinal=" + this.ordinal() +
                '}';
    }

    public double getCertainty() {
        System.out.println("-- certainty is: " + certainty);
        return certainty;
    }

    public void screwUpCertainty() {
        double originalCertainty = this.certainty;
        System.out.println("-- screwing up certainty. original: " + originalCertainty);
        double newCertainty = originalCertainty + 0.5d;
        double limit = 1.0d;
        if(newCertainty > limit) {
            newCertainty = newCertainty - limit;
        }
        System.out.println("-- screwing up certainty. new: " + newCertainty);
        this.certainty = newCertainty;

        //compile error
        //Cannot inherit from enum 'com.mindflytech.education.oop.enums.TriState'
//        TriState fourthState = new TriState(1.5d) {
//            @Override
//            public String asLowerCase() {
//                return "more than sure";
//            }
//        };
        //compile error
        //Enum constant 'new TriState(1.5d)' must implement abstract method 'asLowerCase()' in 'TriState
//        TriState fourthState = new TriState(1.5d);
    }

    //compile error
    //'values()' is already defined in 'com.mindflytech.education.oop.enums.TriState'
//    public static TriState[] values() {
//        return new TriState[] {TriState.TRUE};
//    }

    public abstract String asLowerCase();

}

//compile error
//Classes cannot directly extend 'java.lang.Enum'
//Enum<MyOwnEnum>
//public class MyOwnEnum extends Enum {
//
//    /**
//     * Sole constructor.  Programmers cannot invoke this constructor.
//     * It is for use by code emitted by the compiler in response to
//     * enum class declarations.
//     *
//     * @param name    - The name of this enum constant, which is the identifier
//     *                used to declare it.
//     * @param ordinal - The ordinal of this enumeration constant (its position
//     *                in the enum declaration, where the initial constant is assigned
//     *                an ordinal of zero).
//     */
//    protected MyOwnEnum(String name, int ordinal) {
//        super(name, ordinal);
//    }
//}
