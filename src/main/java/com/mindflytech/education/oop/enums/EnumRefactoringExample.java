package com.mindflytech.education.oop.enums;

public class EnumRefactoringExample {
    enum InstallationBefore{
        LOCAL,
        TEST,
        CLOUD
    }

    enum InstallationAfter {
        LOCAL {
            @Override
            public String calculateServerRoute() {
                return "http://localhost:8081";
            }
        },
        TEST {
            @Override
            public String calculateServerRoute() {
                return "http://cloud-provider.net/company/project/test";
            }
        },
        CLOUD {
            @Override
            public String calculateServerRoute() {
                return "http://cloud-provider.net/company/project/prod";
            }
        };

        public abstract String calculateServerRoute();
    }

    public static void main(String[] args) {
        EnumRefactoringExample refactoring = new EnumRefactoringExample();

        System.out.println(refactoring.fetchServerRouteBefore(InstallationBefore.LOCAL));
        System.out.println(refactoring.fetchServerRouteBefore(InstallationBefore.TEST));
        System.out.println(refactoring.fetchServerRouteBefore(InstallationBefore.CLOUD));

        System.out.println(refactoring.fetchServerRouteAfter(InstallationAfter.LOCAL));
        System.out.println(refactoring.fetchServerRouteAfter(InstallationAfter.TEST));
        System.out.println(refactoring.fetchServerRouteAfter(InstallationAfter.CLOUD));
    }

    private String fetchServerRouteBefore(InstallationBefore installation) {
        return switch(installation) {
            case LOCAL ->  "http://localhost:8081";
            case TEST -> "http://cloud-provider.net/company/project/test";
            case CLOUD -> "http://cloud-provider.net/company/project/prod";
        };
    }

    private String fetchServerRouteAfter(InstallationAfter installationAfter) {
        return installationAfter.calculateServerRoute();
    }
}
