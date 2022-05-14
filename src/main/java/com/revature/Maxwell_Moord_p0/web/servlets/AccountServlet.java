package com.revature.Maxwell_Moord_p0.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Maxwell_Moord_p0.daos.AccountDao;
import com.revature.Maxwell_Moord_p0.models.Account;
import com.revature.Maxwell_Moord_p0.services.AccountServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

// @WebServlet("/accounts")
public class AccountServlet extends HttpServlet {

    private final AccountServices accountServices;
    private final ObjectMapper mapper;

    public AccountServlet(AccountServices accountServices, ObjectMapper mapper) {
        this.accountServices = accountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Account> accounts = accountServices.readAll();

        String payload = mapper.writeValueAsString(accounts);

        resp.getWriter().write(payload);
    }
}
