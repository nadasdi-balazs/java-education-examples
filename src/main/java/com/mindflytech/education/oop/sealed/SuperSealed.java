package com.mindflytech.education.oop.sealed;

import lombok.extern.log4j.Log4j2;

@Log4j2
public sealed class SuperSealed
    //permits clause can be omitted if the permitted classes are in the same source file
//    permits SuperSealed.SubClassOne, SuperSealed.SubClassTwo, SuperSealed.SubClassThree
    //but you cannot mix the two solutions
//    permits NonSealed
    permits NonSealed, SuperSealed.SubClassOne, SuperSealed.SubClassTwo, SuperSealed.SubClassThree
{
    public SuperSealed() {
        log.info("-- SuperSealed constructor called");
    }

    public static sealed class SubClassThree extends SuperSealed
        permits SubSubClassThree{

    }
    
    public static final class SubSubClassThree extends SubClassThree{

    }

    public static final class SubClassTwo extends SuperSealed {
        public SubClassTwo() {
            log.info("-- SubClassTwo constructor called");
        }
    }

    public static non-sealed class SubClassOne extends SuperSealed {
        public SubClassOne() {
            log.info("-- SubClassOne constructor called");
        }
    }

    //TODO: erdekes, itt a non-sealt nem fogadta el
    public static class SubSubClassOne extends SubClassOne {
        public SubSubClassOne() {
            log.info("-- SubSubClassOne constructor called");
        }
    }

    public static final class SubSubSubClassOne extends SubSubClassOne {
        public SubSubSubClassOne() {
            log.info("-- SubSubSubClassOne constructor called");
        }
    }

//    public static class SubClassThree extends SuperSealed

    public static void main(String[] args) {
        SubSubSubClassOne one = new SubSubSubClassOne();
    }
}
