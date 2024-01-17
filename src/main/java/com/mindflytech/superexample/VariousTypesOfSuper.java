package com.mindflytech.superexample;

import com.mindflytech.education.oop.interfaces.InterfaceTest;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class VariousTypesOfSuper extends SuperSuper implements Runnable, Serializable, Comparable<VariousTypesOfSuper>, InterfaceTest.A, InterfaceTest.B {
    private final int rank;

    public VariousTypesOfSuper(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(VariousTypesOfSuper other) {
        int ourRank = this.rank;
        int otherRank = other.rank;
        return ourRank - otherRank;
    }

    @Override
    public void run() {
        System.out.println("-- I am running");
        System.out.println("-- superclass by default: " + super.getClass());
        System.out.println("-- Runnable.super.getClass(): " + Runnable.super.getClass());
        System.out.println("-- Serializable.super.getClass(): " + Serializable.super.getClass());
        System.out.println("-- Comparable.super.getClass(): " + Comparable.super.getClass());
    }

    @Override
    public void method() throws FileNotFoundException {
        System.out.println("-- method() called");
    }

    @Override
    public void second() {
        System.out.println("-- what is InterfaceTest.A.super.getClass()?: " + InterfaceTest.A.super.getClass());
        System.out.println("-- what is InterfaceTest.B.super.getClass()?: " + InterfaceTest.B.super.getClass());
        InterfaceTest.A.super.second();
        InterfaceTest.B.super.second();
    }

    public static void main(String[] args) {
        VariousTypesOfSuper types = new VariousTypesOfSuper(2);
        types.run();
        types.second();
    }
}
