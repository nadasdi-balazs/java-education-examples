package com.mindflytech.education.oop.sealed;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SealedSwitchPatternMatchingExample {
    public static final List<Class<?>> ALLOWED_CLASSES = List.of(Runaway.class, FinalClass.class, SecondLevelSealedClass.class,
            ThirdLevelFinalClass.class, RunawayChildOne.class, RunawayChildTwo.class, RunawayChildThree.class);

    public static abstract sealed class SealedClass {
    }

    public static non-sealed class Runaway extends SealedClass {
        public Runaway() {
            System.out.println("-- Runaway constructor called");
        }
    }

    public static final class FinalClass extends SealedClass {
    }

    public static sealed class SecondLevelSealedClass extends SealedClass {
    }

    public static final class ThirdLevelFinalClass extends  SecondLevelSealedClass {
    }

    public static final class RunawayChildOne extends Runaway {
    }

    public static final class RunawayChildTwo extends Runaway {
    }

    public static final class RunawayChildThree extends Runaway {
    }

    public static SealedClass sealedClassFactory() {
        double random = Math.random();
        if(random < 0.25) {
            return new Runaway();
        }
        if(random < 0.5) {
            return new FinalClass();
        }
        if(random < 0.75) {
            return new SecondLevelSealedClass();
        }
        return new ThirdLevelFinalClass();
    }

    static SealedClass testableRunawayAwareSealedClassFactory(int index) {
        Class<?> sealedSubclass = ALLOWED_CLASSES.get(index);
        System.out.println("-- selected subclass: " + sealedSubclass);
        Constructor<?>[] constructors = sealedSubclass.getConstructors();
        Constructor<?> defaultConstructor = constructors[0];
        System.out.println("-- using constructor: " + defaultConstructor);
        Object instance;
        try {
            instance = defaultConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("exception thrown during constructor call", e);
        }
        System.out.println("-- successfully instantiated SealedClass' subclass: " + instance);
        return (SealedClass)instance;
    }

    public static SealedClass runawayAwareSealedClassFactory() {
        int numberOfOptions = ALLOWED_CLASSES.size();
        double originalRandom = Math.random();
        double randomBetweenZeroAndNumberOfOptions = originalRandom * numberOfOptions;
//        double fractionalIndex = randomBetweenZeroAndNumberOfOptions / numberOfOptions;
        int index = (int)Math.floor(randomBetweenZeroAndNumberOfOptions);
        return testableRunawayAwareSealedClassFactory(index);
    }



    public static void main(String[] args) {
        SealedClass sealedClass = sealedClassFactory();
        matchAndLogSealedSubclasses(sealedClass);
        SealedClass anotherSealedClass = runawayAwareSealedClassFactory();
        matchAndLogSealedSubclasses(anotherSealedClass);
    }

    private static void matchAndLogSealedSubclasses(SealedClass sealedClass) {
        switch (sealedClass) {
            case null -> System.out.println("Null: should not happen");
            case Runaway r -> System.out.println("It is Runaway type: " + r + ", exact class is: " + r.getClass());
            case FinalClass f -> System.out.println("It is FinalClass type: " + f + ", exact class is: " + f.getClass());
            case ThirdLevelFinalClass tl -> System.out.println("It is ThirdLevelFinalClass type: " + tl + ", exact class is: " + tl.getClass());
            case SecondLevelSealedClass sl -> System.out.println("It is SecondLevelSealedClass type: " + sl + ", exact class is: " + sl.getClass());
            //compile error
            //Label is dominated by a preceding case label 'SecondLevelSealedClass sl'
//            case ThirdLevelFinalClass tl -> System.out.println("It is ThirdLevelFinalClass type: " + tl + ", exact class is: " + tl.getClass());
            default -> System.out.println("Default case: should not happened");
        }
        ;
    }
}
