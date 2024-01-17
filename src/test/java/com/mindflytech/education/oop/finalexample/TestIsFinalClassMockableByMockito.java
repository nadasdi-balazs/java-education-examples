package com.mindflytech.education.oop.finalexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestIsFinalClassMockableByMockito {
    @Mock
    private IsFinalClassMockableByMockito mockable;

    @Test
    public void fakeTestInOrderToSeeIfWeCanMockFinalClasses() {
        when(mockable.getTwo()).thenReturn(2);

        System.out.println(mockable);
        int expectedValue = 2;
        mockable.setTwo(expectedValue);
        int resultValue = mockable.getTwo();
        assertThat(resultValue, is(expectedValue));
    }
}
