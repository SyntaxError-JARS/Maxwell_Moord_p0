package com.revature.Maxwell_Moord_p0.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Maxwell_Moord_p0.daos.AccountDao;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;

        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("Hey you have the follow id and email " + req.getParameter("id") + " " + req.getParameter("email") );
            return;
        }

        // Handling the query params in the endpoint /accounts?id=x
        if(req.getParameter("id") != null){

            Account account;

            try {
                account = accountServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
                System.out.println(account);
            } catch (ResourcePersistanceException e){
                //logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(account);
            resp.getWriter().write(payload);
            System.out.println(account);
            return;
        }

        List<Account> accounts = accountServices.readAll();
        String payload = mapper.writeValueAsString(accounts);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
