package com.revature.Maxwell_Moord_p0.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.Maxwell_Moord_p0.exceptions.AuthenticationException;
//import com.revature.Maxwell_Moord_p0.exceptions.InvalidRequestException;
import com.revature.Maxwell_Moord_p0.exceptions.AuthenticationException;
import com.revature.Maxwell_Moord_p0.exceptions.InvalidRequestException;
import com.revature.Maxwell_Moord_p0.models.Account;
import com.revature.Maxwell_Moord_p0.services.AccountServices;
import com.revature.Maxwell_Moord_p0.services.ModServices;
import com.revature.Maxwell_Moord_p0.web.dto.LoginCreds;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthServlet extends HttpServlet {

    private final AccountServices accountServices;

    private final ObjectMapper mapper;

    public AuthServlet(AccountServices accountServices, ObjectMapper mapper){
        this.accountServices = accountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {

            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            Account authAccount = accountServices.authenticateAccount(loginCreds.getUsername(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authAccount", authAccount);


            resp.getWriter().write("You have successfully logged in!");
        } catch (AuthenticationException | InvalidRequestException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e){
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }
    }

}
