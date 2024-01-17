package com.mindflytech.education.controlstructures;

import java.util.ArrayList;
import java.util.List;

import static com.mindflytech.education.controlstructures.ControlStructureUtils.generateStringList;
import static com.mindflytech.education.controlstructures.ControlStructureUtils.isMillisDividableByFour;

public class BreakContinueExample {
    public static void main(String[] args) {
        BreakContinueExample breakIt = new BreakContinueExample();
//        breakIt.executeLoop();
//        breakIt.breakWithLoopAndForLoop();
//        breakIt.continueWithLoopAndForLoop();
//        breakIt.breakFromInnerMethodCall();
//        breakIt.compareBreakAndReturn();
//        breakIt.breakWithLoopAndForLoop();
//        breakIt.useContinueInSwitchOnly('a');
        breakIt.continueDoWhileExample();
    }

    private void continueDoWhileExample() {
        int a = 0;
        int b = 0;
        do {
            boolean millisDividableByFour = isMillisDividableByFour();
            if(millisDividableByFour) {
                System.out.println("-- currentTimeMillis is dividable with 4, continue loop with no increment in a"
                        + "\n\tcurrent a: " + a + ", b: " + b);
                b++;
                continue;
            }
            a++;
            b++;
        } while (a < 10);
        System.out.println("-- a, b at the end of the loop: " + a + ", b: " + b);

        List<String> toIterateOver = generateStringList();
        toIterateOver.forEach(element -> {
            System.out.println("-- element is: " + element);
            if(isMillisDividableByFour()) {
                //continue outside of loop
//                continue;
            }
        });
    }


    private void useContinueInSwitchOnly(char character) {
        switch (character) {
//            case 'a':
//                break;
//            case 'A':
            case 'a', 'A':
                System.out.println("starts with a or A: '" + character + "'");
                break;
            case 'b', 'B':
                System.out.println("starts with b or B: '" + character + "'");
                //Continue outside of loop
//                continue;
            case 'c', 'C':
                System.out.println("starts with c or C, exiting processing: '" + character + "'");
                return;
            default:
                System.out.println("other character: '" + character + "'");
        }
    }

    private void compareBreakAndReturn() {
        String[] loop = new String[]{"Aa", "Bb", "444", "Cc", "11", "22", "33", "333"};
        for (String element : loop) {
            System.out.println("-- element: '" + element + "'");
            char firstChar = element.charAt(0);
            switch (firstChar) {
                case 'a', 'A':
                    System.out.println("starts with a or A: '" + firstChar + "'");
                    break;
                case 'b', 'B':
                    System.out.println("starts with b or B: '" + firstChar + "'");
                    continue;
                case 'c', 'C':
                    System.out.println("starts with c or C, exiting processing: '" + firstChar + "'");
                    return;
                default:
                    System.out.println("other character: '" + firstChar + "'");
            }
        }
    }

    private void executeLoop() {
        outer2:
        System.out.println("-- before loop");
        String[] loop = new String[]{"Aa", "Bb", "444", "Cc", "11", "22", "33", "333"};
        outer:
        outer3:
        for (String element : loop) {
            System.out.println("-- element: '" + element + "'");
            inner4:
            for (char c : element.toCharArray()) {
                System.out.println("-- char: '" + c + "'");
                if (Character.isAlphabetic(c)) {
                    inner:
                    inner2:
                    System.out.println("-- letter is alphabetic: '" + c + "', element: '" + element + "'");
                    break inner4;
//                    continue;
                }
//                if(System.nanoTime() % 2 == 0) {
                // undefinded label inner
//                    break inner;
//                }
                if (Character.isDigit(c) && element.length() > 2) {
                    System.out.println("-- char: '" + c + "' is digit and element's: '"
                            + element + "' length is greater than 2, break outer");
                    inner3:
                    if (System.nanoTime() % 2 == 0) {
                        //compile error
                        //undefined label outer2
                        //the reason is that there are statements between the label and the outer
                        //for loop
//                        break outer2;
                        break outer3;
//                        break;
                    }
                    break outer;
                }
            }
        }
        System.out.println("-- exited loop");
    }

    private void executeLoopClean() {
        outer2:
        System.out.println("-- before loop");
        String[] loop = new String[]{"Aa", "Bb", "444", "Cc", "11", "22", "33", "333"};
        outer:
        outer3:
        for (String element : loop) {
            System.out.println("-- element: '" + element + "'");
            inner:
            for (char c : element.toCharArray()) {
                System.out.println("-- char: '" + c + "'");
                if (Character.isAlphabetic(c)) {
                    inner2:
                    System.out.println("-- letter is alphabetic: '" + c + "', element: '" + element + "'");
                    break;
                }
                if (Character.isDigit(c) && element.length() > 2) {
                    System.out.println("-- char: '" + c + "' is digit and element's: '"
                            + element + "' length is greater than 2, break outer");
                    if (System.nanoTime() % 17 == 0) {
                        break inner;
                    }
                    if (System.nanoTime() % 2 == 0) {
                        break outer3;
                    }
                    break outer;
                }
            }
        }
        System.out.println("-- exited loop");
    }

    private void breakWithLoopAndForLoop() {
        String[] loop = new String[]{"Aa", "Bb", "444", "Cc", "11", "22", "33", "333"};
        outer:
        for (String element : loop) {
            System.out.println("-- element: '" + element + "'");
            char firstChar = element.charAt(0);
            switch (firstChar) {
                case 'a', 'A':
                    System.out.println("starts with a or A: '" + firstChar + "'");
                    break;
                case 'b', 'B':
                    System.out.println("starts with b or B: '" + firstChar + "'");
                    continue outer;
                case 'c', 'C':
                    System.out.println("starts with c or C, exiting processing: '" + firstChar + "'");
                    break outer;
                default:
                    System.out.println("other character: '" + firstChar + "'");
            }
        }
        System.out.println("-- exited loop");
    }

    private void continueWithLoopAndForLoop() {
        String[] loop = new String[]{"Aa", "Bb", "444", "Cc", "11", "22", "33", "333"};
        outer:
        for (String element : loop) {
            System.out.println("-- element: '" + element + "'");
            char firstChar = element.charAt(0);
            switch (firstChar) {
                case 'a', 'A':
                    System.out.println("starts with a or A: '" + firstChar + "'");
                    break;
                case 'b', 'B':
                    System.out.println("starts with b or B: '" + firstChar + "'");
                    break;
                case 'c', 'C':
                    System.out.println("starts with c or C, exiting processing: '" + firstChar + "'");
                    break outer;
                default:
                    System.out.println("other character: '" + firstChar + "'");
            }
        }
        System.out.println("-- exited loop");
    }

    private void breakFromInnerMethodCall() {
        String[] loop = new String[]{"Aa", "Bb", "444", "Cc", "11", "22", "33", "333"};
        outer:
        for (String element : loop) {
            System.out.println("-- element: '" + element + "'");
            char firstChar = element.charAt(0);
            //This is not working, labels don't have reference
//            characterOperation(firstChar);
        }
        System.out.println("-- exited loop");
    }

    //undefined label: outer, doesn't work
//    private void characterOperation(char firstChar) {
//        switch (firstChar) {
//            case 'a', 'A':
//                System.out.println("starts with a or A: '" + firstChar + "'");
//                break;
//            case 'b', 'B':
//                System.out.println("starts with b or B: '" + firstChar + "'");
//                break;
//            case 'c', 'C':
//                System.out.println("starts with c or C, exiting processing: '" + firstChar + "'");
//                break outer;;
//            default:
//                System.out.println("other character: '" + firstChar + "'");
//        }
//    }

}
