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

    public AccountServices(AccountDao accountDao){this.accountDao = accountDao;}

    public AccountServices() {

    }


    //TODO:Exceptions to yell at the user
    public boolean validateUserInput(Account newUser) throws InvalidRequestException {
        System.out.println("Validating User: " + newUser);
        if(newUser == null) return false;
        if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")){
            throw new InvalidRequestException("Email cannot be blank");}
        if(newUser.getUsername() == null || newUser.getUsername().trim().equals("")){
            throw new InvalidRequestException("Username cannot be blank");}
        if(newUser.getPassword() == null || newUser.getPassword().trim().equals("")){
            throw new InvalidRequestException("Password cannot be blank");}
        System.out.println("The User Has been Validated");
        if (verifyNewEmail(newUser.getEmail()) == true){
            throw new InvalidRequestException("That email has already been taken");
        } else if (verifyNewUsername(newUser.getUsername()) == true) {
            throw new InvalidRequestException("That username has already been taken");
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

    public Account createNewUser(Account newUser){
        System.out.println("New user being created " + newUser);

        return AccountDao.create(newUser);
    }


    //Tells the authServlet that everything is good
    public Account authenticateAccount(String username, String password){

        if(password == null || password.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        Account authenticatedAccount = accountDao.authenticateAccount(username, password);

        if (authenticatedAccount == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return account;
    }

    public String deleteAccount(Account accountToDelete){
        String email = accountToDelete.getEmail();
        String username = accountToDelete.getUsername();
        String password = accountToDelete.getPassword();
        String deleteStatement = accountDao.deleteAccount(username);
        if(accountDao.checkForModpacks(username)){
            deleteStatement = (deleteStatement + accountDao.deleteModpacks(username));
        }

        return deleteStatement ;
    }



}




