package com.mindflytech.education.oop.enums;

enum LogLevel {
    TRACE(1),
    DEBUG(2) {
        @Override
        public void introduceLevel() {
            System.out.println("Debug logs are often used during bugfix when the developer is not sure" +
                    " what caused the problem. They are recommended to be removed when the bug is fixed");
        }
    },
    INFO(3),
    WARN(4),
    ERROR(5);

    private final int level;

    LogLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void introduceLevel() {
        System.out.println("Here you can introduce what each log level is used for");
    }

    public static void main(String[] args) {
        //compile error
        //Enum types cannot be instantiated
//        new LogLevel(6);
        LogLevel trace = LogLevel.TRACE;
        int traceLevel = trace.getLevel();
        System.out.println("-- trace leve: " + traceLevel);
        trace.introduceLevel();
        LogLevel.DEBUG.introduceLevel();
    }
}
