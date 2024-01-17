package com.mindflytech.education.oop.object;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CloneExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneExample runner = new CloneExample();
        runner.runClones();
    }

    public void runClones() throws CloneNotSupportedException {
        DefaultCloneable defaultCloneable = new DefaultCloneable(3, "default cloneable string field", "default-cloneable-random");
        DefaultCloneable defaultCloned = defaultCloneable.copy();
        System.out.println("-- default cloneable, original instance: '" + defaultCloneable
                + "', cloned: '" + defaultCloned + "'");
        System.out.println("defaultCloneable.equals(defaultCloned): '" + (defaultCloneable.equals(defaultCloned)) + "'");

        OverridenCloneMethodCloneable overridenCloneable = new OverridenCloneMethodCloneable(3, "overriden cloneable string field", "overriden-cloneabl-random");
        OverridenCloneMethodCloneable overridenCloned = (OverridenCloneMethodCloneable)overridenCloneable.clone();
        System.out.println("-- default cloneable, original instance: '" + overridenCloneable
                + "', cloned: '" + overridenCloned + "'");
        System.out.println("defaultCloneable.equals(defaultCloned): '" + (overridenCloneable.equals(overridenCloned)) + "'");
        RandomObject.printAllInstances();
    }
}

@Data
class DefaultCloneable implements java.lang.Cloneable {
    private int intField;
    private String stringField;
    private RandomObject randomObject;
    private Object object;

    public DefaultCloneable(int intField, String stringField, String randomObjectString) {
        this.intField = intField;
        this.stringField = stringField;
        this.randomObject = new RandomObject(randomObjectString);
        this.object = new Object();
        System.out.println("-- DefaultCloneable.<init> called, created object: " + this);
    }

    public DefaultCloneable copy() throws CloneNotSupportedException {
        DefaultCloneable clone = (DefaultCloneable) this.clone();
        System.out.println("-- DefaultCloneable.copy called, created object: " + clone);
        return clone;
    }
}

@Data
class OverridenCloneMethodCloneable implements java.lang.Cloneable {
    private int intField;
    private String stringField;
    private RandomObject randomObject;
    private Object object;

    public OverridenCloneMethodCloneable(int intField, String stringField, String randomObjectString) {
        this.intField = intField;
        this.stringField = stringField;
        this.randomObject = new RandomObject(randomObjectString);
        this.object = new Object();
        System.out.println("-- OverridenCloneMethodCloneable.init called, created object: " + this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        System.out.println("-- OverridenCloneMethodCloneable.clone called, created object: " + clone);
        return clone;
    }
}

@Data
class RandomObject {
    private final String id;
    private static List<RandomObject> allInstances = new ArrayList<>();

    public static void printAllInstances() {
        String allInstances = RandomObject.allInstances.stream().map(RandomObject::toString).collect(Collectors.joining(","));
        System.out.println("-- all instances of RandomObject: " + allInstances);
    }

    public RandomObject(String id) {
        this.id = id;
        System.out.println("-- RandomObject instantiated with id: '" + id + "'");
        allInstances.add(this);
    }
}
