package com.mindflytech.education.oop.sealed;

import org.junit.Test;

import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static com.mindflytech.education.oop.sealed.SealedSwitchPatternMatchingExample.*;

public class SealedSwitchPatternMatchingExampleTest {
    @Test
    public void whenTestableRunawayAwareSealedClassFactoryCalledWith_0_ShouldReturn_Runaway() {
        SealedClass actualObject = testableRunawayAwareSealedClassFactory(0);
        Class<?> actualClass = actualObject.getClass();
        assertThat(actualClass, is(Runaway.class));
    }

    @Test
    public void whenTestableRunawayAwareSealedClassFactoryCalledWith_1_ShouldReturn_FinalClass() {
        SealedClass actualObject = testableRunawayAwareSealedClassFactory(1);
        Class<?> actualClass = actualObject.getClass();
        assertThat(actualClass, is(FinalClass.class));
    }
    @Test
    public void whenTestableRunawayAwareSealedClassFactoryCalledWith_2_ShouldReturn_SecondLevelSealedClass() {
        SealedClass actualObject = testableRunawayAwareSealedClassFactory(2);
        Class<?> actualClass = actualObject.getClass();
        assertThat(actualClass, is(SecondLevelSealedClass.class));
    }
    @Test
    public void whenTestableRunawayAwareSealedClassFactoryCalledWith_3_ShouldReturn_ThirdLevelFinalClass() {
        SealedClass actualObject = testableRunawayAwareSealedClassFactory(3);
        Class<?> actualClass = actualObject.getClass();
        assertThat(actualClass, is(ThirdLevelFinalClass.class));
    }
    @Test
    public void whenTestableRunawayAwareSealedClassFactoryCalledWith_4_ShouldReturn_RunawayChildOne() {
        SealedClass actualObject = testableRunawayAwareSealedClassFactory(4);
        Class<?> actualClass = actualObject.getClass();
        assertThat(actualClass, is(RunawayChildOne.class));
    }
    @Test
    public void whenTestableRunawayAwareSealedClassFactoryCalledWith_5_ShouldReturn_RunawayChildTwo() {
        SealedClass actualObject = testableRunawayAwareSealedClassFactory(5);
        Class<?> actualClass = actualObject.getClass();
        assertThat(actualClass, is(RunawayChildTwo.class));
    }
    @Test
    public void whenTestableRunawayAwareSealedClassFactoryCalledWith_6_ShouldReturn_RunawayChildThree() {
        SealedClass actualObject = testableRunawayAwareSealedClassFactory(6);
        Class<?> actualClass = actualObject.getClass();
        assertThat(actualClass, is(RunawayChildThree.class));
    }
}
