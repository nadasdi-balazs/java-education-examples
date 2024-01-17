package com.mindflytech.education.controlstructures;

import com.mindflytech.util.Utils;
import org.springframework.stereotype.Component;

class EmployeeUser {
    public static void main(String[] args) {
        SwitchExample.Employee employee = new SwitchExample.Employee(2352, "name", "department");
        System.out.println("-- employee can greet you: " + employee.greet());
    }
}

public class SwitchExample {
    private String instanceString = "I belong to the instance";
    private static String classString = "I belong to the class";

    @Component
    record Employee(int employeeNumber, String name, String department) {
        public String greet() {
            System.out.println(classString);
            return "Hello, I am employee: " + toString();
        }
    };

    public static void main(String[] args) {
        SwitchExample switchExample = new SwitchExample();
//
//        String weekday = switchExample.switchAsExpression("Thursday");
//        System.out.println("Thursday is a: " + weekday);
//
//        switchExample.arrowOperator("Thursday");
//
        Employee balazs = new Employee(27, "Balazs", "IT");
        String patternMatchingText = switchExample.patternMatching(balazs);
        System.out.println("-- pattern matching result: '" + patternMatchingText + "'");
        String resultStatement = switchExample.patternMatchingWithStatement(balazs);
        System.out.println("-- pattern matching works with switch statements: '"
                + resultStatement + "'");

        patternMatchingText = switchExample.patternMatching(null);
        System.out.println("-- pattern matching result: '" + patternMatchingText + "'");
        resultStatement = switchExample.patternMatchingWithStatement(null);
        System.out.println("-- pattern matching works with switch statements: '"
                + resultStatement + "'");
//
//        switchExample.patterMatchingReturnDowncastedObject(balazs);
//        String typeMatch = switchExample.patterMatchingReturnDowncastedObject(null);
//        System.out.println("-- type matched: '" + typeMatch + "'");
//
//        Triangle triangle = new Triangle(24d, 27d, 42d);
//        double perimeter = switchExample.getPerimeterFallThrough(triangle);
//        System.out.println("-- triangle perimeter is: " + perimeter);
//        double perimeterWithNoFallThrough = switchExample.getPerimeter(triangle);
//        System.out.println("-- triangle perimeter is: " + perimeterWithNoFallThrough);

//        switchExample.switchOnBoolean(true);

        switchExample.switchCornerCases();
        System.out.println("-- calling switchAsExpressionAgain with Thursday, result:");
        String thursday = "Thursday";
        String result = switchExample.switchAsExpressionAgain(thursday);
        System.out.println("-- : '" + result + "'");

        System.out.println("-- calling switchAsExpressionThree with Thursday, result:");
        String resultThree = switchExample.switchAsExpressionThree(thursday);
        System.out.println("-- : '" + resultThree + "'");

        switchExample.switchExpressionCornerCases();

        String weekday = switchExample.weekday(thursday);
        System.out.println("-- result of weekday method for input :'" + thursday + "': '" + weekday + "'");
        String saturday = "Saturday";
        String weekend = switchExample.weekday(saturday);
        System.out.println("-- result of weekday method for input :'" + saturday + "': '" + weekend + "'");
        String typoSaturday = "SAturday";
        String typosAturday = "SAturday";
        String matchedWrongCases = switchExample.weekdayCaseInsensitive(typoSaturday);
        System.out.println("-- did it match typoSaturday: '" + typoSaturday + "', result: '" + matchedWrongCases + "'");

        switchExample.switchAsExpression("Monday");
        switchExample.switchAsExpression("Saturday");
    }

    private void switchCornerCases() {
        int determine = 2;
        switch(determine) {
            //Statement must be pretended with a case label
//            System.out.println("I am here");
        }
        switch(determine) {

        }
        switch (determine) {
            case 2:
                System.out.println("-- it is 2, no other case labels provided");
        }
        switch (determine) {
            default:
                System.out.println("-- this only has a default branch");
        }
        switch (determine) {
            case 2:
                System.out.println("-- case 2 it is, but it will");
            default:
                System.out.println("-- overflow, because there was no break");
        }

        Integer wrapped = determine;
        switch (wrapped) {
            case 2 -> System.out.println("-- I wrapped the int to an Integer and it still works");
        }

        String determineString = "someString";
        switch (determineString) {
            case "thisWontMatch":
                System.out.println("-- you should not see this");
        }

        switch (determineString) {
            case "someString", "somestring":
                System.out.println("-- some string detected");
            case "thisWontMatch":
                System.out.println("-- now you should see this");
        }

        Single guessWhat = Single.ONE;
        switch (guessWhat) {

        }
        switch (guessWhat) {
            case ONE:
                System.out.println("-- guess what - guessWhat's value is: " + guessWhat);
        }

        Double guessTwo = Double.TWO;
        switch (guessTwo) {
            case ONE:
                System.out.println("-- value is ONE");
        }
        switch (guessTwo) {
            case ONE:
            case TWO:
                System.out.println("-- value is ONE or TWO");
        }
        switch (guessTwo) {
            case ONE:
            case TWO:
                System.out.println("-- value is ONE or TWO");
            case null: {
                System.out.println("-- variable is null");
            }
        }
        switch (guessTwo) {
            case TWO:
                System.out.println("-- value is TWO");
        }
        switch (guessTwo) {
            case ONE -> {
                System.out.println("-- also ONE, but with arrow notation");
                int something = 27;
                //compiler warning
                //'break' statement is unnecessary
//                break;
            }
            case TWO -> System.out.println("-- also TWO, but with arrow notation");
            default -> System.out.println("-- This should never run");
        }

        double wontSwitch = 2.7;
        //compile error
        //Selector type of 'double' is not supported
//        switch (wontSwitch) {
//            case 2.7:
//                System.out.println("-- it is 2.7");
//        }
        //compile error
        //Selector type of 'double' is not supported
//        String match = switch (wontSwitch) {
//            case 2.7 -> "match";
//            default -> "not match";
//        };

        boolean fiftyPercentChance = Utils.fiftyPercentChance();
        //compiler error
        //Selector type of 'boolean' is not supported
//        switch(fiftyPercentChance) {
//            case true -> System.out.println("-- it was true");
//        }

        //compiler error
        //'{' expected
//        switch (determine)
            //compiler error
        //Case statement outside switch
//        case 2:
//            System.out.println("-- it was two");
    }

    private String switchAsExpression(String day) {
        return switch (day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday":
                int twentySeven = 27;
                System.out.println("-- weekday branch, value of twentySeven: " + twentySeven);
//                return "Weekday";
                yield "Weekday";
            case "Saturday", "Sunday":
                //compiler error:
                //java: variable twentySeven might not have been initialized
//                twentySeven++;
//                System.out.println("-- weekend branch, value of twentySeven: " + twentySeven);
                yield "Weekend";
            default:
                yield "Unknown";
        };
    }

    private String switchAsExpressionAgain(String day) {
        return switch (day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Weekday";
            case "Saturday", "Sunday" -> "Weekend";
            default -> "Unknown";
        };
    }

    private String switchAsExpressionThree(String day) {
        return switch (day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> {
                String weekday = "Weekday";
                System.out.println("---- inside weekday block, needs a yield");
                yield weekday;
            }
            case "Saturday", "Sunday" -> {
                String weekend = "Weekend";
                System.out.println("---- inside weekend block, needs a yield");
                yield weekend;
                //return outside of enclosing switch expression
//                return weekend;
            }
            default -> "Unknown";
        };
    }

    private void switchExpressionCornerCases() {
        int determine = 3;
        int result = switch (determine) {
            case 1 -> 2;
            case 2 -> 3;
            case 3 -> 4;
            default -> 5;
        };
        System.out.println("-- result of first switch expression: " + result);

        Byte selector =  6;
        String resultText = switch (selector) {
            case 1 -> "Value is 1";
            default -> "Not 1";
        };
        System.out.println("-- result of second switch expression: " + resultText);

        String resultAgain = switch(resultText) {
            case "Not 1" -> "The previous result was not 1";
            default -> "The previous result might have been 1";
        };
        System.out.println("-- string switch expression returned value: '" + resultAgain + "'");

        Double enumSelector = Double.ONE;
        CustomValueHolder customValue = switch(enumSelector) {
            case ONE -> new CustomValueHolder("The first option as String: " + enumSelector);
            case TWO -> new CustomValueHolder("The second option as String: " + enumSelector);
        };


        enumSelector = Double.TWO;
        String supriseEnumTostring = switch(enumSelector) {
            case ONE -> "The first option as String: " + enumSelector;
            case TWO -> "The second option as String: " + enumSelector;
        };
        System.out.println("-- enum switch expression returned value: '" + supriseEnumTostring + "'");
        System.out.println("-- enum switch expression returned value: '" + customValue + "'");

        determine = 4;
        int resultYielded = switch (determine) {
            case 1 -> {
                int two = 2;
                yield two;
            }
            case 2 -> {
                System.out.println("---- with block, received two, will return three");
                yield 3;
            }
            case 3 -> {
                int four = 4;
                yield four;
            }
            default -> {
                doSomeExternalOperation(determine);
                yield 5;
            }
        };
        System.out.println("-- result yielded: " + resultYielded);
    }

    private void doSomeExternalOperation(int parameter) {
        System.out.println("-- dummy external operation with parameter: '" + parameter + "'");
    }

    private String weekday(String day) {
        String weekdayOrWeekend = switch (day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday":
//                System.out.println("-- empty case label, what does it yield? for input: '" + day + "'");
//                return "Weekday";
                yield  "Weekday";
//            case "Saturday", "Sunday":
//                String result = "Weekend";
                //compiler error
                //Different case kinds used in the switch
//            case "Sonntag" -> "Weekend, but in German";
            case "Saturday", "Sunday":
                System.out.println("-- Hurray, it is weekend on: '" + day + "'");
                yield "Weekend";
            default:
                throw new IllegalArgumentException("weekday method only accept day's names!");
                //compiler error
                //Different case kinds used in the switch
//            default -> throw new IllegalArgumentException("weekday method only accept day's names!");
        };
        return weekdayOrWeekend;
    }

    private String weekdayCaseInsensitive(String day) {
        String weekdayOrWeekend = switch (day.toLowerCase()) {
            case "monday", "tuesday", "wednesday", "thursday", "friday":
                yield  "Weekday";
            case "saturday", "sunday":
                System.out.println("-- Hurray, it is weekend on: '" + day + "'");
                yield "Weekend";
            default:
                throw new IllegalArgumentException("weekday method only accept day's names!");
        };
        return weekdayOrWeekend;
    }

    private String arrowOperator(String day) {
        return switch (day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> {
                System.out.println("I am here in weekday case");
                yield "Weekday";
//                break;
            }
            case "Saturday", "Sunday" -> "Weekend";
            default -> "Unknown";
        };
    }

    private String patternMatchingWithStatement(Object object) {
        String result;
        switch (object) {
            case null: result = "It is a null object";
                break;
            case Integer i:
                result = "It is an integer: " + i;
                break;
            case String s:
                result = "It is a string: " + s;
                break;
            case Employee e:
                result = "It is an Employee: " + e;
                break;
            default: result = "It is none of the known data types";
        }
        return result;
    }
    private String patternMatching(Object object) {
        return switch (object) {
            case null -> "It is a null object";
            case Integer i -> "It is an integer: " + i;
            case String s -> "It is a string: " + s;
            case Employee e -> "It is an Employee: " + e;
            default -> "It is none of the known data types";
        };
    }

    private String patterMatchingReturnDowncastedObject(Object object) {
        String result;
        result = switch (object) {
            case Integer i -> {
                System.out.println("It is an integer: " + i);
                doIntegerOperation(i);
                yield "" + i;
            }
            case String s -> "It is a string: " + s;
            case Employee e -> {
                System.out.println("-- employee matched: " + e);
                yield "It is an Employee: " + e;
            }
            case null -> {
                System.out.println("-- no nullpoint exception will be thrown");
                yield "this object was null";
            }
            default -> "It is none of the known data types";
        };
        return result;
    }

    private void doIntegerOperation(Integer i) {
        System.out.println("integer operation on i: " + i);
    }

    private double getPerimeterFallThrough(Shape shape) throws IllegalArgumentException {
        switch (shape) {
            case null: return -1.0;
            case Rectangle r: return 2 * r.length() + 2 * r.width();
            case Circle c:    return 2 * c.radius() * Math.PI;
            case Triangle t: {
                double perimeter = t.a + t.b + t.c;
                return perimeter;
            }
            //BEWARE: fall through traditional switch statement: since there is no return, yield, etc.. it falls through to the default case
            default:          throw new IllegalArgumentException("Unrecognized shape");
        }
    }

    private double getPerimeter(Shape shape) throws IllegalArgumentException {
        //FIXME this should have been declared as an abstract method in Shape class, thus the entire switch pattern matching
        //would be unnecessary. Don't use switch pattern matching instead of using abstract methods
        switch (shape) {
            case Rectangle r: return 2 * r.length() + 2 * r.width();
            case Circle c:    return 2 * c.radius() * Math.PI;
            case Triangle t: {
                double perimeter = t.a + t.b + t.c;
                return perimeter;
            }
            default: throw new IllegalArgumentException("Unrecognized shape - this will never occur");
        }
    }

//    private void switchOnBoolean(Boolean toBeSwitchedOn) {
//        switch (toBeSwitchedOn) {
//            //Constant expression required
//            case Boolean.TRUE:
//                System.out.println("true");
//                break;
//            case Boolean.FALSE:
//                System.out.println(false);
//                break;
//        }
//    }
//    private void switchOnBoolean(boolean toBeSwitchedOn) {
////        selector type of boolean is not supported
//        switch (toBeSwitchedOn) {
//            case true:
//                System.out.println("true");
//                break;
//            case false:
//                System.out.println(false);
//                break;
//        }
//    }
    
    private static abstract sealed class Shape permits Rectangle, Circle, Triangle {}
    
    private static final class Rectangle extends Shape {
        private final double length;
        private final double width;

        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }

        public double length() {
            return length;
        }

        public double width() {
            return width;
        }
    }

    private static final class Circle extends Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }


        public double radius() {
            return radius;
        }
    }

    private static final class Triangle extends Shape {
        private final double a;
        private final double b;
        private final double c;

        public Triangle(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public double getA() {
            return a;
        }

        public double getB() {
            return b;
        }

        public double getC() {
            return c;
        }
    }

    private enum Single {
        ONE {
            @Override
            public String toString() {
                return "One's custom toString";
            }
        };

        public abstract String toString();
    }

    private enum Double {
        ONE {
            @Override
            public String toString() {
                return "enum: Double, option: ONE";
            }
        },
        TWO {
            @Override
            public String toString() {
                return "enum: Double, option: TWO";
            }
        };

        public abstract String toString();
    }

    public record CustomValueHolder(String customValue){}
}

