package com.mindflytech.education.inheritence;


public class NameClashExternalRunner {
    public static void main(String[] args) {
        NameClashExternalRunner runner = new NameClashExternalRunner();
        runner.run();
    }

    private void run() {
//        NameClashExample example = new NameClashExample();
        //Cannot resolve method 'staticNameClashOnInterfaces' in 'NameClashExample'
//        example.staticNameClashOnInterfaces();

        //Cannot resolve method 'staticNameClashOnInterfaces' in 'NameClashExample'
//        NameClashExample.staticNameClashOnInterfaces();
    }
}
