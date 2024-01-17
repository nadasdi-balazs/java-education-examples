package com.baeldung.tutorial.nestmates;

public class Holder {
    public static class KeyWordTest {

        private String name = "outer name";

        class ThisInnerClass {
            private String name = "inner name";

            boolean isInnerClass = true;

            public ThisInnerClass() {
                System.out.println("-- inner name: '" + name + "'");
                KeyWordTest thisKeyword = KeyWordTest.this;
                System.out.println("-- reference to outer class: " + thisKeyword);
                String outerString = KeyWordTest.this.name;
                System.out.println("-- outer name: '" + outerString + "'");
            }
        }

        public static void main(String[] args) {
            KeyWordTest keyWordTest = new KeyWordTest();
            KeyWordTest.ThisInnerClass inner = keyWordTest.new ThisInnerClass();
        }
    }
}
