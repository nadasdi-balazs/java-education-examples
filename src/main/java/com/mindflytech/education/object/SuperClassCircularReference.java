package com.mindflytech.education.object;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public abstract class SuperClassCircularReference {
    /**
     * Don't do this ever in production code. Child classes have an inherent reference towards the parent,
     * parent classes should not know about their children (to losen coupling)
     */
    private List<ToStringStackOverflow> children = new ArrayList<>();

    SuperClassCircularReference(ToStringStackOverflow child) {
        children.add(child);
    }

    SuperClassCircularReference() {
        System.out.println("-- superclass constructor called, waiting for setting child");
    }

    public void addChild(ToStringStackOverflow child) {
        children.add(child);
    }
}
