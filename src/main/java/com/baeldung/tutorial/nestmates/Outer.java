package com.baeldung.tutorial.nestmates;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Method;

@Log4j2
public class Outer {
    public void outerPublic() {
        log.info("-- Outer.outerPublic called");
        Inner inner = new Inner();
    }

    private void outerPrivate() {
        log.info("-- Outer.outerPrivate called");
//        innerPrivate();
    }

    class Inner {
        private void innerPrivate() {
            log.info("-- Inner.innerPrivate called");
        }

        public void innerPublic() {
            log.info("-- Inner.innerPublic called");
            outerPrivate();
        }

        public void innerPublicReflection(Outer ob) throws Exception {
            log.info("-- Inner.innerPublicReflection called");
            Method method = ob.getClass().getDeclaredMethod("outerPrivate");
            method.invoke(ob);
        }
    }

    public static void main(String[] args) throws Exception {
        Outer outer = new Outer();
        outer.outerPublic();
        outer.outerPrivate();
//        Outer.Inner inner = new Outer.Inner();
//        Outer.Inner inner = new Outer.new Inner();
        Outer.Inner inner = outer.new Inner();
        inner.innerPrivate();
        inner.innerPublic();
        inner.innerPublicReflection(outer);
    }
}