package com.mindflytech.education.oop.records;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

public class RecordPossibilities {
    record Rectangle(double length, double width) {}

    @Slf4j
    record RectangleWithCanonicalConstructorScrewedUp(double length, double width) {
        public RectangleWithCanonicalConstructorScrewedUp(double length, double width) {
            log.info("-- own canonical constructor called");
            if(length <= 0 || width <= 0) {
                throw new IllegalArgumentException("Rectangle's length or width must be positive! length: "
                        + length + ", width: " + width);
            }
            //Beware, this mistake can be made if you don't look at what code assist suggests
            this.length = length;
            this.width = width;
        }
    }

    record RectangleWithCanonicalConstructor(double length, double width) {
        //warning: Canonical constructor can be converted to compact form
        public RectangleWithCanonicalConstructor(double length, double width) {
            System.out.println("-- own canonical constructor called");
            //Beware, this mistake can be made if you don't look at what code assist suggests
            this.length = length;
            this.width = width;
        }

        public double calculateArea() {
            return width * length;
        }
    }

    record RectangleWithCompactConstructorMadeFromCanonical(double length, double width) {
        public RectangleWithCompactConstructorMadeFromCanonical {
            System.out.println("-- own compact constructor called");
            System.out.println("-- can access length as length: '" + length + "'");
            if(length <= 0 || width <= 0) {
                throw new IllegalArgumentException("Can't instantiate Rectangle with negative length or width, "
                        + length + ", " + width);
            }
            //Beware, this mistake can be made if you don't look at what code assist suggests
        }

        /**
         * This non-canonical, not compact constructor is used to create a square
         * @param length
         */
        public RectangleWithCompactConstructorMadeFromCanonical(double length) {
            this(length, length);
            System.out.println("-- custom constructor called with length: " + length
                    + ", will call compact constructor with width = -1 added");
        }

        public void ownMethod() {
            System.out.println("-- I am an instance method");
        }

        public double length() {
            System.out.println("-- I have overriden the default accessor method");
            return length;
        }

        //compile error
        //'RectangleWithCompactConstructorMadeFromCanonical(double, double)' is already defined
        // in 'com.mindflytech.education.oop.records.RecordPossibilities.RectangleWithCompactConstructorMadeFromCanonical'
//        public RectangleWithCompactConstructorMadeFromCanonical(double length, double width) {
//            System.out.println("-- this will collide with compact constructor");
//        }
    }

//    record RectangleWontCompile(double length, double width) {
//
//        // Field declarations must be static:
//        //compile error: Instance field is not allowed in record
//        BiFunction<Double, Double, Double> diagonal;
//
//        // Instance initializers are not allowed in records:
//        {
//            diagonal = (x, y) -> Math.sqrt(x*x + y*y);
//        }
//    }

    record RectangleWithStatic(double length, double width) {
        private static int counter;

        public static void eraseCounter() {
            counter = 0;
            System.out.println("-- counter erased, new value: " + counter);
        }

        public static int counter() {
            System.out.println("-- counter value: " + counter);
            return counter;
        }

        static {
            counter = 10;
            System.out.println("-- this is a static initializer, initializes counter to: " + counter);
        }

        public RectangleWithStatic {
            counter++;
            System.out.println("-- compact constructor called, will increment count to: " + counter);
        }
    }

    //compile error
    //Cannot inherit from final 'com.mindflytech.education.oop.records.RecordPossibilities.RectangleWithStatic'
//    record Extended extends RectangleWithStatic { }

//    static class BaseClass {}

    //compile error
    //No extends clause allowed for record
//    record Extended extends RecordPossibilities.BaseClass { }

    record RectangleWithInstanceMethods(double length, double width) {
        public double getLength() {
            System.out.println("-- same as length(), but my own implementation");
//            return length();
            return length;
        }

        public double getWidth() {
            System.out.println("-- same as width(), but my own implementation");
            return width;
        }
    }

    record RectangleWithNestedMembers(double length, double width) {
        public static class StaticNestedClass{}

        public class NestedClass{}

        public interface NestedInterface{
            record NestedRecordWithinNestedInterface(String data) {}
        }

        //compile warning:
        //Modifier 'static' is redundant for inner interfaces
        public static interface StaticNestedInterface{}

        public record NestedRecord(String text) {}

        //compile warning:
        //Modifier 'static' is redundant for inner records
        public static record StaticNestedRecord(String text) {}

        public enum NestedWithinRecord{
            STATE_ONE,
            STATE_TWO
        }

        private static String thisIsStatic = "Now this one is accessible from textAccessor";

        public void localRecordHolder() {
            String localText = "this is the local text that the local record won't be able to access";
            record LocalRecord(String text){
                public void textAccessor() {
                    //compile error
                    //Non-static variable 'localText' cannot be referenced from a static context
//                    System.out.println("-- this is local text: " + localText);
                    System.out.println("-- from LocalRecord.textAccessor, I can only access the enclosing scope's" +
                            " static variables such as thisIsStatic: '" + thisIsStatic + "'");
                }
            };
            LocalRecord local = new LocalRecord("this is text");
            System.out.println("-- local record instance in localRecordHolder: " + local);
            local.textAccessor();

            new Runnable() {
                @Override
                public void run() {
                    System.out.println("-- within anonymous Runnable, I can see local record: " + local);
                }
            }.run();
        }
    }

    //compile warning:
    //Modifier 'static' is redundant for inner records
    static record NestedWithinClass(String data){}

    public enum SimpleNestedEnum{
        STATE_ONE(new NestedRecordWithinEnum("state one")),
        STATE_TWO(new NestedRecordWithinEnum("state two"));

        public record NestedRecordWithinEnum(String text){}

        SimpleNestedEnum(NestedRecordWithinEnum dataHolder) {
            this.dataHolder = dataHolder;
        }

        private final NestedRecordWithinEnum dataHolder;

        public NestedRecordWithinEnum getDataHolder() {
            return this.dataHolder;
        }

        @Override
        public String toString() {
            return "SimpleNestedEnum{" +
                    "name=" + this.name() +
                    ";dataHolder=" + dataHolder +
                    '}';
        }
    }

    public static class Coordinate {
        private double X;
        private double Y;

        public Coordinate(double x, double y) {
            this.X = x;
            this.Y = y;
        }

        public static Coordinate fromCoordinates(double X, double Y) {
            return new Coordinate(X, Y);
        }

        public double getX() {
            return X;
        }

        public double getY() {
            return Y;
        }

        public void setX(double x) {
            X = x;
        }

        public void setY(double y) {
            Y = y;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "X=" + X +
                    ", Y=" + Y +
                    '}';
        }
    }

    record RunnableRecord(String text) implements Runnable {
        @Override
        public void run() {
            System.out.println("-- record runs, text is: " + text);
        }
    }

    record Triangle<C extends Coordinate> (C top, C left, C right) { }

    record RectangleOverridenDefaults(double length, double width) {
        //Compile error
        //Incorrect component accessor return type. Expected: 'double', found: 'int'
//        public int length() {
//            return 7;
//        }

    }

    record  GenericRecord<T>(T field) {
    }

    public static void main(String[] args) {
        GenericRecord<String> stringGenericRecord = new GenericRecord<>("String-field");

        RectangleWithCanonicalConstructorScrewedUp erroneous = new RectangleWithCanonicalConstructorScrewedUp(27, 42);
        System.out.println("-- this will have 0 length: " + erroneous);

        RectangleWithCompactConstructorMadeFromCanonical rectangle = new RectangleWithCompactConstructorMadeFromCanonical(23.0);
        System.out.println("-- rectangle made with custom constructor: " + rectangle);
        new RectangleWithCompactConstructorMadeFromCanonical(2.0, 3.0);
        rectangle.ownMethod();

        RectangleWithStatic  withStatic = new RectangleWithStatic(3, 5);
        System.out.println("-- rectangle class with static things: " + withStatic + ", static counter: " + RectangleWithStatic.counter());

        RectangleWithInstanceMethods withInstanceMethods = new RectangleWithInstanceMethods(42, 43);
        System.out.println("-- this one has instance methods: " + withInstanceMethods);
        withInstanceMethods.getWidth();
        withInstanceMethods.getLength();

        System.out.println("-- within enum, state one: " + SimpleNestedEnum.STATE_ONE);
        System.out.println("-- within enum, state two: " + SimpleNestedEnum.STATE_TWO);

        RectangleWithNestedMembers nested = new RectangleWithNestedMembers(2, 3);
        System.out.println("-- instance of a record that has a lot of embedded local members: " + nested);
        nested.localRecordHolder();

        Coordinate aCoord = Coordinate.fromCoordinates(6, 3);
        Coordinate bCoord = Coordinate.fromCoordinates(7, 5);
        Coordinate cCoord = Coordinate.fromCoordinates(8, 11);
        Triangle<Coordinate> triangle = new Triangle<>(aCoord, bCoord, cCoord);
        System.out.println("-- generic triangle made of coordinates: " + triangle);

        RunnableRecord runnable = new RunnableRecord("this record is runnable");
        runnable.run();
    }
}
