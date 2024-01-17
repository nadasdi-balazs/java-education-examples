package com.mindflytech.education.collection.comparable;

import lombok.Data;
import lombok.extern.log4j.Log4j2;


import java.math.BigDecimal;
import java.util.*;

/**
 * Code originally from @author Eva Szeplaki
 */
@Log4j2
public class Main {
    public static final Comparator<BankAccount> SALDO_COMPARATOR = new Comparator<BankAccount>() {
        @Override
        public int compare(BankAccount accountOne, BankAccount accountTwo) {
            BigDecimal saldoOne = accountOne.saldo;
            BigDecimal saldoTwo = accountTwo.saldo;
            int comparedResult = saldoOne.compareTo(saldoTwo);
            log.info("[loginfo]: compare accountOne: " + saldoOne + " to accountTwo: " + saldoTwo + " by saldo, result is: " + comparedResult);
            System.out.println("[sysout]: comparing accountOne: " + accountOne + " and accountTwo: " + accountTwo + " by saldo, result is: " + comparedResult);
            return comparedResult;
        }
    };

    public static final Comparator<BankAccount> SALDO_COMPARATOR_LAMBDA = (accountOne, accountTwo) -> {
        BigDecimal saldoOne = accountOne.saldo;
        BigDecimal saldoTwo = accountTwo.saldo;

        return saldoOne.compareTo(saldoTwo);
    };
    @Data
    public static class BankAccount implements Comparable<BankAccount> {
        private final String iban;
        private final String owner;
        private final BigDecimal saldo;

//        public BankAccount(String iban, String owner, BigDecimal saldoDecimal) {
//            this.iban = iban;
//            this.owner = owner;
//            this.saldo = saldoDecimal;
//        }

        public static BankAccount withBankAccountDetails(String iban, String owner, long saldo) {
            BigDecimal saldoDecimal = BigDecimal.valueOf(saldo);
            return new BankAccount(iban, owner, saldoDecimal);
        }

        @Override
        public int compareTo(BankAccount other) {
            BigDecimal otherSaldo = other.saldo;
            return saldo.compareTo(otherSaldo);
        }
    }
    public static void main(String[] args) {
        BankAccount pista = BankAccount.withBankAccountDetails("00001111", "Pista", 771632);
        BankAccount jozsi = BankAccount.withBankAccountDetails("00002222", "Jozsi", 97163244444L);
        BankAccount juli = BankAccount.withBankAccountDetails("11112222", "Juli", 8971632);
        List<BankAccount> customers = new ArrayList<>(){{
            add(pista);
            add(jozsi);
            add(juli);
        }};


        customers.sort(SALDO_COMPARATOR);
        System.out.println("Customers sorted: " + customers);
        Collections.shuffle(customers);
        System.out.println("Customers are unsorted: " + customers);
        customers.sort(SALDO_COMPARATOR_LAMBDA);
        System.out.println("Customers sorted, but with lambda: " + customers);
        Collections.shuffle(customers);
        System.out.println("Customers are unsorted: " + customers);
        customers.sort(BankAccount::compareTo);
        System.out.println(customers);
    }
}