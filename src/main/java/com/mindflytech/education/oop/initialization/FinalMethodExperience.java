package com.mindflytech.education.oop.initialization;

public class FinalMethodExperience {
    public static final void staticFinalMethod() {
        System.out.println("-- FinalMethodExperience.staticFinalMethod invoked");
    }

    //compile error
    //Illegal combination of modifiers: 'abstract' and 'final'
//    public final abstract void thisMethodCantExist();
}

class SubclassFinalMethodExperience extends FinalMethodExperience {
    //compile error
    //'staticFinalMethod()' cannot override 'staticFinalMethod()' in 'com.mindflytech.education.oop.initialization.FinalMethodExperience'; overridden method is final
//    public static final void staticFinalMethod() {
//        System.out.println("-- FinalMethodExperience.staticFinalMethod invoked");
//    }
}

interface FinalMethodTest {
    //compile error
    //Modifier 'final' not allowed here
//    final void thisMethodCantExist();

    //compile error
    //Modifier 'final' not allowed here
//    private final void thisMethodCantExist() {  }
//    private static final void thisMethodCantExist() {  }
//    static final void thisMethodCantExist() {}
//    default final void thisMethodCantExist() {}
}

abstract class FinalMethodTestOnAbstractClasses {
    //Compile error
    //Illegal combination of modifiers: 'final' and 'abstract'
//    final abstract void thisMethodCantExist();
//    private abstract final void thisMethodCantExist2() {  }
//    private static final abstract void thisMethodCantExist3() {  }
//    static final abstract void thisMethodCantExist4() {}
}
