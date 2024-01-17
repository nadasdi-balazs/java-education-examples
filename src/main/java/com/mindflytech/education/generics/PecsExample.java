package com.mindflytech.education.generics;

import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

import static com.mindflytech.util.Utils.prefixedPrintList;

/**
 * Producer
 * Extends
 * Consumer
 * Super
 */
public class PecsExample {
    public static void main(String[] args) {
        PecsExample example = new PecsExample();
        example.demonstrate();
    }

    private void demonstrate() {
        List<Operator> operators = generateOperatorList();
        List<? extends User> operatorStoredAsExtendsUser = generateOperatorList();
        List<? super User> operatorsStoredAsSuperUser = generateOperatorListAsSuperUser();
        List<Customer> customers = generateCustomerList();
        List<User> operatorsStoredAsUser = generateOperatorListAsUser();
        List<? extends User> customersStoredAsExtendsUser = generateCustomerList();

        //compile error
        //Required type:        //List        //<User>
        //Provided:             //List        //<Operator>
//        sendEmailsWrong(operators);

        //compile error
        //Required type:        //List        //<User>
        //Provided:             //List        //<Customer>
//        sendEmailsWrong(customers);

        sendEmailsFixed(operators);
        sendEmailsFixed(operatorStoredAsExtendsUser);
        sendEmailsFixed(customers);
        sendEmailsFixed(customersStoredAsExtendsUser);

//        addUsersFromMarketingDepartmentFixed(operators);

        //compilation error
        //Required type:        //List        //<Operator>
        //Provided:             //List        //<capture of ? extends User>
//        addUsersFromMarketingDepartmentWrong(operatorStoredAsExtendsUser);

        //compilation error
        //Required type:        //List        //<Operator>
        //Provided:             //List        //<User>
//        addUsersFromMarketingDepartmentWrong(operatorsStoredAsSuperUser);

        addUsersFromMarketingDepartmentFixed(operators);

        //compilation error
        //Required type:        //List        //<? super Operator>
        //Provided:             //List        //<capture of ? extends User>
        //We want to assign a List<? extends User> type variable to a List<? super Operator> parameter
        //this *could* work if ? *would* be exactly Operator, but the compiler can't be sure, hence the error
        //As you see, it *happened* to be Operator in the first case, so we could say that the compiler
        //was wrong, but the same type can hold Customers, as in the second case
//        addUsersFromMarketingDepartmentFixed(operatorStoredAsExtendsUser);
//        addUsersFromMarketingDepartmentFixed(customersStoredAsExtendsUser);

        addUsersFromMarketingDepartmentFixed(operatorsStoredAsSuperUser);
        addUsersFromMarketingDepartmentFixed(operatorsStoredAsUser);

        prefixedPrintList("-- after all operations, List<Operator> operators: ", operators);
        prefixedPrintList("-- after all operations, List<? extends User> operatorStoredAsExtendsUser: ", operatorStoredAsExtendsUser);
        prefixedPrintList("-- after all operations, List<? super User> operatorsStoredAsSuperUser operators: ", operatorsStoredAsSuperUser);
        prefixedPrintList("-- after all operations, List<User> operatorsStoredAsUser operators: ", operatorsStoredAsUser);
        prefixedPrintList("-- after all operations, List<Customer> customers: ", customers);
        prefixedPrintList("-- after all operations, List<? extends User> customersStoredAsExtendsUser: ", customersStoredAsExtendsUser);

        addUsersAndSendEmails(operatorsStoredAsUser);
        prefixedPrintList("-- after addUsersAndSendEmails,List<User> operatorsStoredAsUser: ", operatorsStoredAsUser);
    }

    private void addUsersAndSendEmails(List<User> users) {
        users.add(new Operator("john doe"));
        for (User user : users) {
            System.out.println("sending email to: " + user);
        }
    }

    private List<Operator> generateOperatorList() {
        LinkedList<Operator> list = new LinkedList<>();
        list.add(new Operator("john"));
        list.add(new Operator("arys"));
        return list;
    }

    private List<? super User> generateOperatorListAsSuperUser() {
        LinkedList<? super User> list = new LinkedList<>();
        list.add(new Operator("john"));
        list.add(new Operator("arys"));
        return list;
    }

    private List<User> generateOperatorListAsUser() {
        LinkedList<User> list = new LinkedList<>();
        list.add(new Operator("john"));
        list.add(new Operator("arys"));
        return list;
    }

    private List<Customer> generateCustomerList() {
        LinkedList<Customer> list = new LinkedList<>();
        list.add(new Customer("sam"));
        list.add(new Customer("daniel"));
        return list;
    }

    public void sendEmailsWrong(List<User> users) {
        for (User user : users) {
            System.out.println("-- sending email to " + user);
        }
    }

    public void sendEmailsFixed(List<? extends User> users) {
        //at this point, users list is a producer, because we only use it to provide data to
        //our later processing
        for (User user : users) {
            System.out.println("-- sending email to " + user);
        }
    }

    private void addUsersFromMarketingDepartmentWrong(List<Operator> users) {
        users.add(new Operator("john doe"));
        users.add(new Operator("jane doe"));
    }

    private void addUsersFromMarketingDepartmentFixed(List<? super Operator> users) {
        users.add(new Operator("john doe"));
        users.add(new Operator("jane doe"));
    }
}

@Data
class User {
    private final String name;
}

@ToString(callSuper=true, includeFieldNames=true)
class Operator extends User {
    public Operator(String name) {
        super(name);
    }
}

@ToString(callSuper=true, includeFieldNames=true)
class Customer extends User {
    public Customer(String name) {
        super(name);
    }
}
