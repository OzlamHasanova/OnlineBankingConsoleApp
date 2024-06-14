package service;

import exception.InsufficientFundsException;
import model.entity.BankAccount;
import model.entity.Transaction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Scanner;

public class BankingOperationService {
    private final CustomerService customerService;
    Scanner scanner = new Scanner(System.in);
    BankAccount account;

    public BankingOperationService(CustomerService customerService) {
        this.customerService = customerService;
        account = customerService.getMyAccount();
        boolean iscontinue = true;
        while (iscontinue) {
            System.out.println("""
                    <-Transaction menu->
                    Which bank transaction do you want to do?
                    1.Deposit money
                    2.Withdraw money
                    3.Transfer money between accounts
                    4.My Balance
                    """);
            int transaction = scanner.nextInt();
            switch (transaction) {
                case 1 -> depositMoneyMyBankAccount();
                case 2 -> withdrawMoneyAndCheckMyBalance();
                case 3 -> transferMoneyBetweenAccounts();
                case 4 -> showBalanceAmount();
            }
            System.out.println("""
                    You want to do a new operation?(1 or 2)
                    1.Yes
                    2.No""");
            int number = scanner.nextInt();
            iscontinue = number == 1;
        }

    }

    private void showBalanceAmount() {
    }

    public void depositMoneyMyBankAccount() {
        System.out.println("Deposit amount: ");
        double amount = scanner.nextDouble();
        account.setBalance(amount);
        System.out.println("Transaction is success. Your wallet amount: " + account.getBalance());
        Transaction transaction = new Transaction("Deposit: " + amount, LocalDate.now());
        account.setOperations(Collections.singletonList(transaction));
    }

    public void withdrawMoneyAndCheckMyBalance() {
        System.out.println("Withdraw amount: ");
        double amount = scanner.nextDouble();
        double myBalance = account.getBalance();
        try {
            if (amount <= myBalance) {
                myBalance -= amount;
                System.out.println("Transaction is success. Your wallet amount: " + myBalance);
                Transaction transaction = new Transaction("Withdraw: " + amount, LocalDate.now());
                account.setOperations(Collections.singletonList(transaction));
            }
            throw new InsufficientFundsException("Insufficient Funds. No Money👻");
        } catch (InsufficientFundsException e) {
            System.err.println(e.getMessage());


        }

    }

    public void transferMoneyBetweenAccounts() {

        System.out.println("Enter the account number to which you will send money");
        int sendMoneyAccountNumber = scanner.nextInt();
        BankAccount sendMoneyAccount = customerService.searchAccountByAccountNumber();
        double sendMoneyAccountBalance = sendMoneyAccount.getBalance();
        double myBalance = account.getBalance();
        System.out.println("Enter amount(your balance+%s)" + myBalance);
        double amount = scanner.nextDouble();
        try {
            if (amount <= myBalance) {
                myBalance -= amount;
                sendMoneyAccountBalance += amount;
                System.out.println("Transaction is success. Your wallet amount: " + myBalance);
                Transaction transaction = new Transaction("Withdraw: " + amount, LocalDate.now());
                account.setOperations(Collections.singletonList(transaction));
            }
            throw new InsufficientFundsException("Insufficient Funds. No Money👻");
        } catch (InsufficientFundsException e) {
            System.err.println(e.getMessage());
        }


    }
}
