package com.revature.Maxwell_Moord_p0.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Maxwell_Moord_p0.daos.AccountDao;
import com.revature.Maxwell_Moord_p0.exceptions.InvalidRequestException;
import com.revature.Maxwell_Moord_p0.models.Account;
import com.revature.Maxwell_Moord_p0.services.AccountServices;
import static com.revature.Maxwell_Moord_p0.web.servlets.Authable.checkAuth;
import com.revature.Maxwell_Moord_p0.exceptions.ResourcePersistanceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/accounts")
public class AccountServlet extends HttpServlet {

    private final AccountServices accountServices;
    private final ObjectMapper mapper;

    public AccountServlet(AccountServices accountServices, ObjectMapper mapper) {
        this.accountServices = accountServices;
        this.mapper = mapper;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account newUser = mapper.readValue(req.getInputStream(), Account.class);
        try{
        accountServices.validateUserInput(newUser);
            }catch(InvalidRequestException e){
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
            }finally {
            resp.getWriter().write("This user has been created " + newUser);
        }
        }
    }

