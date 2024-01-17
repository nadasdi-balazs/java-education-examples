package com.mindflytech.education.controlstructures;

import java.util.ArrayList;
import java.util.List;

public class ControlStructureUtils {
    public static List<String> generateStringList() {
        List<String> toIterateOver = new ArrayList<>() {{
            add("alpha");
            add("beta");
            add("gamma");
            add("delta");
            add("epsilon");
            add("zeta");
        }};
        return toIterateOver;
    }

    public static boolean isMillisDividableByFour() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("-- currentTimeMillis: " + currentTimeMillis);
        boolean millisDividableByFour = currentTimeMillis % 4L == 0;
        return millisDividableByFour;
    }
}
