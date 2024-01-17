package com.mindflytech.education.collection;

import com.mindflytech.util.Utils;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.*;

@Log4j2
public class ComparableExample {
    public static final Comparator<BankAccount> SALDO_COMPARATOR = new Comparator<BankAccount>() {
        @Override
        public int compare(BankAccount accountOne, BankAccount accountTwo) {
            BigDecimal saldoOne = accountOne.getSaldo();
            BigDecimal saldoTwo = accountTwo.getSaldo();
            int compareResult = saldoOne.compareTo(saldoTwo);
            log.info("-- comparing accountOne: " + accountOne + " and accountTwo: "
                    + accountTwo + " by saldo, result is: " + compareResult);
            return compareResult;
        }
    };

    public static final Comparator<BankAccount> SALDO_COMPARATOR_LAMBDA = (accountOne, accountTwo) -> {
        BigDecimal saldoOne = accountOne.getSaldo();
        BigDecimal saldoTwo = accountTwo.getSaldo();
        return saldoOne.compareTo(saldoTwo);
    };

    @Data
    public static class BankAccount implements Comparable<BankAccount> {
        private final String iban;
        private final String owner;
        private final BigDecimal saldo;

        public static BankAccount withBankAccountDetails(String iban, String owner, long saldo) {
            BigDecimal saldoDecimal = BigDecimal.valueOf(saldo);
            return new BankAccount(iban, owner, saldoDecimal);
        }

        @Override
        public int compareTo(BankAccount other) {
            BigDecimal otherSaldo = other.getSaldo();
            return saldo.compareTo(otherSaldo);
        }
    }

    public static void main(String[] args) {
        BankAccount pista = BankAccount.withBankAccountDetails("00001111", "Pista", 771632);
        BankAccount jozsi = BankAccount.withBankAccountDetails("00002222", "Jozsi", 713871359124124L);
        BankAccount juli = BankAccount.withBankAccountDetails("11112222", "Juli", 500000004555L);
        List<BankAccount> customers = new ArrayList<>(){{
                add(pista);
                add(jozsi);
                add(juli);
        }};
        customers.sort(SALDO_COMPARATOR);
        log.info("-- customers sorted: " + Utils.listToString(customers));
        Collections.shuffle(customers);
        log.info("-- customers unsorted again: " + Utils.listToString(customers));
        customers.sort(SALDO_COMPARATOR_LAMBDA);
        log.info("-- customers sorted again, now with lambda: " + Utils.listToString(customers));
        Collections.shuffle(customers);
        log.info("-- customers unsorted again: " + Utils.listToString(customers));
        customers.sort(BankAccount::compareTo);
        log.info("-- customers sorted again, now with BankAccount::compareTo: " + Utils.listToString(customers));
    }
}
