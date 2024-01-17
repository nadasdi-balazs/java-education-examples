package com.mindflytech.education.oop.method;

public abstract class MethodExample {
    private static class Lock {
        private static Lock instance;

        private Lock() {
        }

        static Lock getInstance() {
            if(instance == null) {
                instance = new Lock();
            }
            return instance;
        }
    }

    {
        synchronized (Lock.getInstance()) {
            System.out.println("-- I am within a synchronized block of an initializer block");
        }
    }

    private void one() {
        System.out.println("-- method one called");
    }

    protected void two() {
        System.out.println("-- method two called");
    }

    public void three() {
        System.out.println("-- method three called");
    }

    void four() {
        System.out.println("-- method four called");
    }

    public abstract void five();

    private final void six() {
        System.out.println("-- method six called");
    }

    final void seven() {
        System.out.println("-- method seven called");
    }

    static void eight() {
        System.out.println("-- method eight called");
    }

    final static void nine() {
        System.out.println("-- method nine called");
    }

    protected static void ten() {
        System.out.println("-- method ten called");
    }

    private static void eleven() {
        System.out.println("-- method eleven called");
    }

    private synchronized void twelve() {
        System.out.println("-- method twelve called");
        synchronized (this) {
            System.out.println("-- within synchronized block");
        }
    }

    static synchronized void thirteen() {
        System.out.println("-- method thirteen called");
    }
    /* equivalent to:
    private void twelve() {
        synchronized(this) {
            System.out.println("-- method twelve called");
            synchronized (this) {
                System.out.println("-- within synchronized block");
            }
         }
    }
    */
}

class ConcreteExample extends MethodExample {
    @Override
    public void five() {
        System.out.println("-- method five called");
        super.four();

        super.nine();
        MethodExample.nine();
        nine();
    }

    //compile error
    //'nine()' cannot override 'nine()' in 'com.mindflytech.education.oop.method.MethodExample'; overridden method is final
//    static void nine() {
//        System.out.println("-- method nine called");
//    }

    //compile error
    //'seven()' cannot override 'seven()' in 'com.mindflytech.education.oop.method.MethodExample'; overridden method is final
//    @Override
//    public void seven() {
//
//    }
}
