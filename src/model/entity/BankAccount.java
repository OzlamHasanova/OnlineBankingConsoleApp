package model.entity;

import java.util.List;

public class BankAccount extends Customer{
//    private int accountId;
    private int accountNumber;
    private double balance;
    private String accountType;
    private List<Transaction> operations;

    public BankAccount(int customerId, String name, String address, String phone, String email,String password, int accountNumber, double balance, String accountType) {
        super(customerId, name, address, phone, email,password);
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<Transaction> getOperations() {
        return operations;
    }

    public void setOperations(List<Transaction> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                super.toString()+
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
