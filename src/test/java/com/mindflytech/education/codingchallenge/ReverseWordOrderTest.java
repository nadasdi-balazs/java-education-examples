package com.mindflytech.education.codingchallenge;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith()
public class ReverseWordOrderTest {
    private ReverseOddWords reverse;

    @Before
    public void init() {
        reverse = new ReverseOddWords();
    }

    @Test
    public void whenGivenToEvasSolution_Bananas_ShouldReturn_sananaB() {
        String input = "Bananas";
        String reversed = reverse.reverseOddString(input);
        assertThat(reversed, is("sananaB"));
    }

    @Test
    public void whenGivenToBalazsSolution_Bananas_ShouldReturn_sananaB() {
        String input = "Bananas";
        String reversed = reverse.reverseOddStringBalazs(input);
        assertThat(reversed, is("sananaB"));
    }

    @Test
    public void test45() {
        String startString = "Bananas";
        String resultString = "sananaB";
        assertEquals(reverse.reverseOddString(startString), resultString);
    }

    @Test
    public void test46() {
        String startString = "One two three four";
        String resultString = "enO owt eerht four";
        assertEquals(reverse.reverseOddString(startString), resultString);
    }

    @Test
    public void test47() {
        String startString = "Make sure uoy only esrever sdrow of ddo length";
        String resultString = "Make sure you only reverse words of odd length";
        assertEquals(reverse.reverseOddString(startString), resultString);
    }
}
