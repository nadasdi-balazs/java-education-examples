package com.mindflytech.education.oop.classmember;

import java.util.LinkedList;
import java.util.List;

public class StaticKnowsAllInstances {
    private static List<StaticKnowsAllInstances> allInstances = new LinkedList<>();

    private String name;

    public StaticKnowsAllInstances() {
        allInstances.add(this);
    }

    public StaticKnowsAllInstances(String name) {
        this.name = name;
        allInstances.add(this);
    }

    public static List<StaticKnowsAllInstances> getAllInstances() {
        return allInstances;
    }

    @Override
    public String toString() {
        return "StaticKnowsAllInstances{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        StaticKnowsAllInstances instance1 = new StaticKnowsAllInstances();
        StaticKnowsAllInstances instance2 = new StaticKnowsAllInstances("I am the second one");
        StaticKnowsAllInstances instance3 = new StaticKnowsAllInstances("huluulu");
        StaticKnowsAllInstances instance4 = new StaticKnowsAllInstances("wat");
        List<StaticKnowsAllInstances> allInstances = StaticKnowsAllInstances.getAllInstances();
        for(StaticKnowsAllInstances instance : allInstances) {
            System.out.println("-- instance: " + instance);
        }
    }
}
