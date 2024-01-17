package com.baeldung.tutorial.concurrency;

import lombok.extern.log4j.Log4j2;

import java.util.Random;

@Log4j2
public class ConcurrencyUtil {
    private static Random random = new Random();
    public static String buildMessageTemplate(Class<?> clazz, String name) {
        return "-- " + clazz.getName() + ", name: " + name + " ";
    }

    public static long doDivisionWork(Class<?> clazz, String name) {
        int minWait = 1;
        int maxWait = 1000;
        String messageTemplate = buildMessageTemplate(clazz, name);
        int randomSleepTime = generateSleepTime(minWait, maxWait, messageTemplate);
        long result = Long.MAX_VALUE / Integer.MAX_VALUE;
        log.info(" -- division completed: " + result);
        wait(messageTemplate, randomSleepTime);
        log.info(messageTemplate + "exited thread");
        return result;
    }

    private static void wait(String messageTemplate, int randomSleepTime) {
        try {
            Thread.sleep(randomSleepTime);
        } catch (InterruptedException e) {
            log.error(messageTemplate + "interrupted during execution");
            throw new RuntimeException(e);
        }
    }

    private static int generateSleepTime(int minWait, int maxWait, String messageTemplate) {
        log.info(messageTemplate + "entered thread");
        int randomSleepTime = random.nextInt(minWait, maxWait);
        log.info(messageTemplate + "will wait: " + randomSleepTime + " milliseconds");
        return randomSleepTime;
    }

    public static String doStringOperation(Class<?> clazz, String name) {
        int minWait = 1;
        int maxWait = 10;
        String messageTemplate = buildMessageTemplate(clazz, name);
        int randomSleepTime = generateSleepTime(minWait, maxWait, messageTemplate);
        String original = "mvscmoscsooekceifmeij3wd9i29kw9938439kd93i39id393i0d3wokw";
        String result = "";
        for(char c : original.toCharArray()) {
            wait(messageTemplate, randomSleepTime);
            result += c;
        }
        return result;
    }
}
