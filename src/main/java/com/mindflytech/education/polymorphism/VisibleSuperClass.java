package com.mindflytech.education.polymorphism;

import com.mindflytech.util.Utils;

public abstract class VisibleSuperClass {
    public abstract void functionality();

    public static VisibleSuperClass factory() {
        return Utils.fiftyPercentChance() ? new PrivateImplementationOne() : new PrivateImplementationTwo();
    }

    private static class PrivateImplementationOne extends  VisibleSuperClass {
        @Override
        public void functionality() {
            System.out.println("-- I am PrivateImplementationOne derived from VisibleSuperClass");
        }
    }

    private static class PrivateImplementationTwo extends  VisibleSuperClass {
        @Override
        public void functionality() {
            System.out.println("-- I am PrivateImplementationTwo derived from VisibleSuperClass");
        }
    }
}
