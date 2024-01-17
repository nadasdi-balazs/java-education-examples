package com.mindflytech.education.oop.enums;

public class EnumClonableTrial {
    public enum TriState implements Cloneable{
        ONE,
        TWO,
        THREE;

        //compile error
        //'clone()' cannot override 'clone()' in 'java.lang.Enum'; overridden method is final
//        @Override
//        protected Object clone() throws CloneNotSupportedException {
//            return super.clone();
//        }

        public final TriState copy() {
            try {
                System.out.println("-- I will clone my enum: " + this);
                //this will throw a CloneNotSupportedException
                Object cloned = this.clone();
                System.out.println("-- cloned: " + cloned);
                TriState result = (TriState)cloned;
                return result;
            } catch (CloneNotSupportedException e) {
                System.err.println("-- CloneNotSupportedException occurred: " + e
                        + "\nwill rethrow it as runtime exception");
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        TriState one = TriState.ONE;
        TriState copyOfOne = one.copy();
        System.out.println("-- are the two the same? " + one + ", " + copyOfOne);
        System.out.println("-- are their hashcodes the same? " + one.hashCode() + ", " + copyOfOne.hashCode());
    }
}
