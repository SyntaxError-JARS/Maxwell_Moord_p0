package com.revature.Maxwell_Moord_p0.services;


import com.revature.Maxwell_Moord_p0.daos.AccountDao;
import com.revature.Maxwell_Moord_p0.models.Account;



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
        accountDao.pullUsernames(newUser);
        AccountDao.create(newUser);
        return true;
    }

    public boolean verifyNewUsername(String username, Account newUser){
        //System.out.println(username);
        //6System.out.println(newUser.getUsername());

        if(username.equals(newUser.getUsername())){
            System.out.println("This Username has already been taken, please try again");
            return false;
        }

        return true;
    }

    public boolean verifyNewEmail(String username, Account newUser){
        //System.out.println(username);
        //6System.out.println(newUser.getUsername());

        if(username.equals(newUser.getUsername())){
            System.out.println("This Username has already been taken, please try again");
            return false;
        }

        return true;
    }

    public Account readAll(){
        return account;
    }



}
