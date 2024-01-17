package com.mindflytech.education.oop.refactoring;

public class SwitchCaseToFactoryRefactoring {
    public static void main(String[] args) {
        SwitchCaseToFactoryRefactoring refactoring = new SwitchCaseToFactoryRefactoring();
        String serverRoute = refactoring.calculateServerRoute("test");
        System.out.println("-- server route: '" + serverRoute + "' ");
        DefaultServerRouteFactory factory = new DefaultServerRouteFactory();
        String serverRouteFromFactory = factory.determineServerRoute();
        System.out.println("-- server route from factory: '" + serverRouteFromFactory + "'");
    }

    private String calculateServerRoute(String installation) {
        return switch(installation) {
            case "local" ->  "http://localhost:8081";
            case "test" -> "http://cloud-provider.net/company/project/test";
            default -> "http://localhost:8081";
        };
    }

    public interface ServerRouteFactory {
        String determineServerRoute();
    }

    public static class DefaultServerRouteFactory implements ServerRouteFactory {
        private static final String LOCAL_ROUTE = "http://localhost:8081";
        private static final String TEST_ROUTE = "http://cloud-provider.net/company/project/test";

        @Override
        public String determineServerRoute() {
            String environmentProperty = System.getProperty("env");
            if(environmentProperty == null || environmentProperty.isBlank()) {
                System.out.println("-- environment property is null or blank, will default to local");
                return LOCAL_ROUTE;
            }
            if("test".equals(environmentProperty)) {
                return TEST_ROUTE;
            }
            System.out.println("-- warning: environment could not be detected, will default to local. " +
                    "Env property value: '" + environmentProperty + "'");
            return LOCAL_ROUTE;
        }
    }
}
