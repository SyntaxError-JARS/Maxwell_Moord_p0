package com.revature.Maxwell_Moord_p0.services;

import com.revature.Maxwell_Moord_p0.daos.AccountDao;
import com.revature.Maxwell_Moord_p0.models.Account;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AccountServiceTestSuite {
    
    AccountServices sut;
    AccountDao mockAccountDao;
    
    @BeforeEach
    public void testPrep(){
        mockAccountDao = mock(AccountDao.class);
        sut = new AccountServices(mockAccountDao);
    }

    @Test
    public void test_validateInput_givenValidAccount_returnTrue(){

        // Arrange
        Account account = new Account("valid", "valid", "valid");

        // Act
        boolean actualResult = sut.validateUserInput(account);

        // Assert
        Assertions.assertTrue(actualResult);

    }

    @Test
    public void test_create_givenValidUser_returnsAccount(){
        // Arrange
        Account account = new Account("pie", "pie", "pie");
        // THe below code ensures that the services can continue execution and get expected results from the dao without any issues
        when(mockAccountDao.create(account)).thenReturn(account);

        // Act
        Account actualAccount = sut.createNewUser(account);

        // Assert
        Assertions.assertEquals("pie", actualAccount.getEmail());
        Assertions.assertEquals("pie", actualAccount.getUsername());
        Assertions.assertEquals("pie", actualAccount.getPassword());
        // Mockito is verifying that the creation method was execute only once!
        verify(mockAccountDao, times(1)).create(account);
    }

    
}
