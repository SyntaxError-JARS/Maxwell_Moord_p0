package com.revature.Maxwell_Moord_p0.services;


import com.revature.Maxwell_Moord_p0.daos.AccountDao;
import com.revature.Maxwell_Moord_p0.exceptions.AuthenticationException;
import com.revature.Maxwell_Moord_p0.exceptions.InvalidRequestException;
import com.revature.Maxwell_Moord_p0.exceptions.ResourcePersistanceException;
import com.revature.Maxwell_Moord_p0.models.Account;


import java.util.ArrayList;
import java.util.Scanner;


public class AccountServices {

    private AccountDao accountDao = new AccountDao();
    private Account account = new Account();



    public boolean validateUserInput(Account newUser) {
        System.out.println("Validating User: " + newUser);
        if(newUser == null) return false;
        if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
        if(newUser.getUsername() == null || newUser.getUsername().trim().equals("")) return false;
        if(newUser.getPassword() == null || newUser.getPassword().trim().equals("")) return false;
        System.out.println("The User Has been Validated");
        if (verifyNewEmail(newUser.getEmail()) == true){
            System.out.println("This email has already been taken, please try again");
            return false;
        } else if (verifyNewUsername(newUser.getUsername()) == true) {
            System.out.println("This username has already been taken, please try again");
            return false;
        }else{
            createNewUser(newUser);
            return true;
        }
    }

    public boolean verifyNewUsername(String username){
        return accountDao.pullUsernames(username);
    }

    public boolean verifyNewEmail(String email){
        return accountDao.pullEmails(email);
    }

    public void createNewUser(Account newUser){
        System.out.println("New user being created");
        AccountDao.create(newUser);
    }

    //Outputs all the accounts
    public ArrayList<Account> readAll(){
        ArrayList <Account> accounts = accountDao.findUsers();

        return accounts;
    }


    public Account readById(String id) throws ResourcePersistanceException {
        System.out.println(id);
        Account account = accountDao.findById(id);
        return account;
    }

    //Tells the authServlet that everything is good
    public Account authenticateAccount(String email, String password){

        if(password == null || password.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        Account authenticatedAccount = accountDao.authenticateAccount(email, password);

        if (authenticatedAccount == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return account;
    }

}




