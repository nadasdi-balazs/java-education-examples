package com.mindflytech.education.datastructure;

import java.util.Arrays;

public class StringExample {
    //BAD PRACTICE: don't name classes starting with lowercase letters
    //Also don't try to replace java.lang.String
    private static class string implements CharSequence {

        @Override
        public int length() {
            return 0;
        }

        @Override
        public char charAt(int index) {
            return 0;
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return null;
        }

        @Override
        public String toString() {
            return null;
        }
    }

    public static void main(String[] args) {
        StringExample example = new StringExample();
        example.run();
    }

    private void run() {
        String something = "something";
        string otherSomething;

        String email1 = "@srrwjfw.com";
        displaySplitOnAtResultWithLimitZero(email1);
        String email2 = "1@srrwjfw.com";
        displaySplitOnAtResultWithLimitZero(email2);
        String email3 = "oew@srrwjfw.com";
        displaySplitOnAtResultWithLimitZero(email3);
        String email4 = "oew@s";
        displaySplitOnAtResultWithLimitZero(email4);
        String email5 = "oew@";
        displaySplitOnAtResultWithLimitZero(email5);
    }

    private void displaySplitOnAtResultWithLimitZero(String email) {
        String[] split = email.split("@");
        System.out.println("-- result of split with limit 0: '" + Arrays.toString(split) + "'");
    }
}
