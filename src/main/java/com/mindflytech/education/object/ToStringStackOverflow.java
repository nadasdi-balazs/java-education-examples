package com.mindflytech.education.object;

import lombok.ToString;

@ToString(callSuper = true)
public class ToStringStackOverflow extends SuperClassCircularReference {
    private String fieldOne;
    private double fieldTwo;
    private int fieldThree;

    ToStringStackOverflow() {
        addChild(this);
    }

    public String getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
    }

    public double getFieldTwo() {
        return fieldTwo;
    }

    public void setFieldTwo(double fieldTwo) {
        this.fieldTwo = fieldTwo;
    }

    public int getFieldThree() {
        return fieldThree;
    }

    public void setFieldThree(int fieldThree) {
        this.fieldThree = fieldThree;
    }

    public static void main(String[] args) {
        ToStringStackOverflow overflow = new ToStringStackOverflow();
        System.out.println("-- stack overflow comes from here");
        System.out.println("-- ToStringStackOverflow instance is: " + overflow.toString());
    }
}
