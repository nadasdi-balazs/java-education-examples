package com.mindflytech.education.oop.method;

import java.util.Arrays;
import java.util.stream.Collectors;

// based on https://www.baeldung.com/java-method-signature-return-type
public class MethodOverloadingExample {
    //main also works with vararg - we usually not write it like this
    public static void main(String... args) {
        MethodOverloadingExample overloading = new MethodOverloadingExample();
        overloading.sum(Integer.valueOf(2), Integer.valueOf(3));
        overloading.sum(2, 3);
        overloading.sum(2, 0x1);
        overloading.sum(2L, 0x1);
        overloading.sum(2L, 3L);
        overloading.sum(2d, 3L);
        overloading.sum(2d, 3d);
        overloading.sum(2.0d, 3.0d);
        overloading.sum(2.0, 3.0);
        overloading.sum(Float.valueOf(2), Float.valueOf(3));
        overloading.sum(2f, 3f);
        overloading.sum(1, 2L, 3D, 4f, 0x101);
        overloading.sum(2, "John");
        overloading.sum(new Object(), new Object());
        overloading.sum(new Object(), new Object(), new Object());
        overloading.sum(new Object(), new Object[]{new Object()});
        overloading.sum(new Object(), new Object(), new Object[]{new Object()});
        overloading.sum(new Object());

    }

    private Number sum(Integer term1, Integer term2) {
        System.out.println("-- Adding integers: sum(Integer, Integer): " + term1 + ", " + term2);
        int result = term1 + term2;
        System.out.println("-- result is: '" + result + "'");
        return result;
    }

    private Number sum(Double term1, Double term2) {
        System.out.println("-- Adding doubles: sum(Double, Double): " + term1 + ", " + term2);
        double result = term1 + term2;
        System.out.println("-- result is: '" + result + "'");
        return result;
    }

    private Number sum(Number term1, Number... term2) {
        double result = term1.doubleValue();
        for(Number val : term2) {
            result += val.doubleValue();
        }
        return result;
    }

    private Number sum(Number term1, Number term2) {
        System.out.println("-- Adding numbers: sum(Number, Number): " + term1 + ", " + term2);
        double result = term1.doubleValue() + term2.doubleValue();
        System.out.println("-- result is: '" + result + "'");
        return result;
    }

    private Number sum(Object term1, Object term2) {
        System.out.println("-- Adding objects: sum(Object, Object): " + term1 + ", " + term2);
        int result = term1.hashCode() + term2.hashCode();
        System.out.println("-- result is: '" + result + "'");
        return result;
    }

    public Number sum(Object term1, Object... term2) {
        System.out.println("-- Adding variable arguments: sum(Object, Object...):" + term2.length + ": "
                + term1 + ", " + Arrays.stream(term2).map(Object::toString).collect(Collectors.joining(",")));
        int result = term1.hashCode();
        for (Object o : term2) {
            result += o.hashCode();
        }
        System.out.println("-- result is: '" + result + "'");
        return result;
    }

    //compile error
    //'sum(Object, Object...)' is already defined in 'com.mindflytech.education.oop.method.MethodOverloadingExample'
//    public Number sum(Object term1, Object[] term2) {
//        return null;
//    }
}
