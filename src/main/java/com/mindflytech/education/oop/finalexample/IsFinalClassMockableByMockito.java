package com.mindflytech.education.oop.finalexample;

public final class IsFinalClassMockableByMockito {
    private String one;
    private int two;

    public IsFinalClassMockableByMockito(String one, int two) {
        this.one = one;
        this.two = two;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    @Override
    public String toString() {
        return "IsFinalClassMockableByMockito{" +
                "one='" + one + '\'' +
                ", two=" + two +
                '}';
    }
}
