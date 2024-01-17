package com.mindflytech.education.oop.enums;

public enum EnumSingletonService implements Service {
    INSTANCE;

    public static EnumSingletonService getInstance() {
        return INSTANCE;
    }

    @Override
    public void executeOperation(String data, String otherData) {
        System.out.println("executing operation on data: '" + data
                + "' and otherData: '" + otherData + "'");
    }

    @Override
    public String calculateValue(String circumstance) {
        return circumstance + System.currentTimeMillis();
    }

    public static void main(String[] args) {
        EnumSingletonService instance = EnumSingletonService.INSTANCE;
        instance.calculateValue("circumstance");
        Service service = EnumSingletonService.getInstance();
        service.executeOperation("my left leg", "my right arm");
        String value = service.calculateValue("my head_");
        System.out.println("calculated value: '" + value + "'");
    }
}
