import service.BankingOperationService;
import service.CustomerService;
import service.LoginService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        LoginService loginService = new LoginService(customerService);
//        BankingOperationService bankingOperationService=new BankingOperationService(customerService);


        customerService.addCustomerWithAccount();

        Scanner scanner = new Scanner(System.in);
        boolean iscontinue=loginService.login();


        while (iscontinue) {
            System.out.println("""
                    1.Account Menu
                    2.Bank Operation Menu""");
            switch (scanner.nextInt()) {
                case 1 -> {
                }
                case 2 -> new BankingOperationService(customerService);
                default -> System.out.println("Behave according to the options");
            }
            System.out.println("""
                    <-Account Menu->
                    1.Update Account
                    2.Delete Account
                    3.Get My Info
                    4.Show transaction history per monthly
                     (1-3)""");

            switch (scanner.nextInt()) {
                case 1 -> {
                    customerService.updateAccount();
                }
                case 2 -> customerService.deleteCustomer();
                case 3 -> customerService.displayAccountDetails();
                case 4 -> customerService.showTransactionHistoryPerMonthly();
                default -> System.out.println("Behave according to the options");
            }



        }

    }
}