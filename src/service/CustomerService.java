package service;

import exception.CustomerNotFoundException;
import model.entity.BankAccount;
import model.entity.Customer;
import model.entity.Transaction;
import model.enums.AccountType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();
    protected final List<BankAccount> accounts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    BankAccount account;
    boolean iscontinue;

    public CustomerService() {

    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void addCustomerWithAccount() {
        Customer customer = new Customer();
        int customerId = customer.getCustomerId();
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter address: ");
        String address = scanner.next();
        System.out.println("Enter phone: ");

        String phone = scanner.next();
        System.out.println("Enter email: ");

        String email = scanner.next();
        int accountNumber = random.nextInt(10000);

        String accountType = AccountType.CUSTOMER.name();
        int balance = 0;

        while (!iscontinue) {
            System.out.println("Enter password (5 character)");
            String password = scanner.next();
            if (password.length() >= 5) {
                iscontinue = true;
                BankAccount account = new BankAccount(customerId, name, address, phone, email, password, accountNumber, balance, accountType);
                Customer customer1 = new Customer(customerId, name, address, phone, email, password);
                customers.add(customer1);
                accounts.add(account);
                System.out.println("Customer add successfully with account " + account.toString());
            } else {
                System.err.println("password minimum length must be 5 character. Try again");
            }
        }

    }

    public void updateAccount() {
        BankAccount account1 = getMyAccount();
        System.out.println("Enter new name: ");
        String name = scanner.next();
        account1.setName(name);
        System.out.println("Enter address: ");
        String address = scanner.next();
        account1.setAddress(address);
        System.out.println("Enter phone: ");
        String phone = scanner.next();
        account1.setPhone(phone);
        System.out.println("Enter email: ");
        account1.setEmail(scanner.next());
        System.out.println("Update is success" + account1);

    }

    public void deleteCustomer() {
        try {
            System.out.println("Which customer do you want to delete?");
            int customerId = scanner.nextInt();

            if (accounts.stream().noneMatch(customer -> customer.getCustomerId() == customerId)) {
                throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
            }
            accounts.removeIf(customer -> customer.getCustomerId() == customerId);
            System.out.println("Delete is successful");
            System.out.println(accounts);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error: " + e.getMessage());
        }
    }

    public BankAccount getMyAccount() {
        int accountNumber = accounts.get(accounts.size() - 1).getAccountNumber();
        try {
            return accounts.stream()
                    .filter(user -> user.getAccountNumber() == accountNumber)
                    .findFirst().orElseThrow(() -> new CustomerNotFoundException("Account with number " + accountNumber + " not found."));
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error: " + e.getMessage());
        }

        return null;
    }

    public void displayAccountDetails() {
        BankAccount account = getMyAccount();
        String operations;
        if (account.getOperations() != null) {
            operations = account.getOperations().toString();
        } else {
            operations = "Operation does not exist";
        }

        String message = String.format("""
                        My dear user🤩
                        Account number-> %s
                        Your balance-> %s
                        History-> %s""",
                account.getAccountNumber(),
                account.getBalance(),
                operations);
        System.out.println(message);
    }

    public BankAccount searchAccountByAccountNumber() {
        System.out.println("Search Account for account number->");
        int accountNumber = scanner.nextInt();
        try {
            return accounts.stream()
                    .filter(user -> user.getAccountNumber() == accountNumber)
                    .findFirst().orElseThrow(() -> new CustomerNotFoundException("Account with number " + accountNumber + " not found."));
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error: " + e.getMessage());
        }
        return null;
    }

    public void searchCustomerByCustomerId() {
        System.out.println("Search Customer for customer id->");
        int id = scanner.nextInt();
        try {
            Customer customer = customers.stream()
                    .filter(user -> user.getCustomerId() == id)
                    .findFirst().orElseThrow(() -> new CustomerNotFoundException("Customer with number " + id + " not found."));
            System.out.println(customer.toString());

        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error: " + e.getMessage());
        }
    }

    public void showTransactionHistoryPerMonthly() {
        BankAccount account = getMyAccount();
        LocalDate oneMonthAgo = LocalDate.of(2024, 1, 11).minusMonths(1);

        List<Transaction> transactions = account.getOperations().stream()
                .filter(transaction -> transaction.getDate().isAfter(oneMonthAgo) || transaction.getDate().isEqual(oneMonthAgo))
                .toList();

        for (Transaction transaction :
                transactions) {
            System.out.println(transaction.toString());
        }
    }


}
