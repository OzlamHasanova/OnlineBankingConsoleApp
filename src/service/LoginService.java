package service;

import model.entity.BankAccount;
import model.enums.AccountType;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LoginService {
    private final CustomerService customerService;
    Scanner scanner=new Scanner(System.in);

    public LoginService(CustomerService customerService) {
        this.customerService = customerService;
    }
    public boolean login(){
        System.out.println("<-Login->");
        boolean isSuccess=false;
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter password");
        String password=scanner.next();
        for (BankAccount account :
                customerService.accounts) {
            if(Objects.equals(account.getName(), name) && Objects.equals(account.getPassword(), password)){
                if(Objects.equals(account.getName(), "ozlem") && Objects.equals(account.getPassword(), "11122")){
                    account.setAccountType(AccountType.ADMIN.name());
                    System.out.println("Login is success...Welcome Admin");
                    isSuccess=true;
                    break;
                }
                System.out.println("Login is success...Welcome");
                isSuccess=true;
                break;
            }
        }
        if (isSuccess==false){
            System.out.println("Password or name is false");

        }
        return isSuccess;


    }
}
