package com.mindflytech.education.strictfpexample;

public strictfp class StrictfpExample {
    private interface Demonstration {
        //This is also an abstract method, strictfp is not allowed here
//        void strictfp doStuff();
    }

    private strictfp enum EnumDemonstration {
        A,
        B;
    }
}
