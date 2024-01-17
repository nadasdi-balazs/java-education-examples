package com.mindflytech.education.oop.records;

import java.util.Objects;

public class RectangleAsJavaClass {
    private final double length;
    private final double width;

    public RectangleAsJavaClass(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double length() {
        return length;
    }

    public double width() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectangleAsJavaClass that = (RectangleAsJavaClass) o;
        return Double.compare(that.length, length) == 0 && Double.compare(that.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width);
    }

    @Override
    public String toString() {
        return "RectangleAsJavaClass{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }
}

