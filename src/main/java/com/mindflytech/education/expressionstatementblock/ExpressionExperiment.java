package com.mindflytech.education.expressionstatementblock;

public class ExpressionExperiment {
    public static void main(String[] args) {
        ExpressionExperiment experiment = new ExpressionExperiment();
        experiment.experiment();
    }

    private void experiment() {
        System.out.println("-- value of : (\"something\"  + 1 * 2 * 3): '" + ("something"  + 1 * 2 * 3) + "'");
        int i;
        System.out.println("-- i = 42: '" + (i = 42) + "'");
        int result = i = 42;
        System.out.println("-- i = 42: '" + result + "'");
    }
}
