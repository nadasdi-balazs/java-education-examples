package com.mindflytech.education.codingchallenge;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RemoveRepeatedCharsTest {
    private RemoveRepeatedChars remove = new RemoveRepeatedChars();

    @Test
    void when_Inputaabbcdefcdfaa_Expect_abcdef() {
        String result = remove.removeDuplicatesWithStream("aabbcdefcdfaa");
        //self-validating - this is the right way according to FIRST principles
        String expectedResult = "abcdef";
        assertThat(result, is(expectedResult));

        //not self-validating - don't do that
//        if(!result.equals(expectedResult)) {
//            throw new AssertionError("Expected: '" + expectedResult + "' but got: '" + result + "'");
//        }
    }
}