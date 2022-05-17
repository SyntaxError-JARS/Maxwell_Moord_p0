package com.revature.Maxwell_Moord_p0.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Maxwell_Moord_p0.daos.AccountDao;
import com.revature.Maxwell_Moord_p0.services.AccountServices;
import com.revature.Maxwell_Moord_p0.services.ModServices;
import com.revature.Maxwell_Moord_p0.web.servlets.AuthServlet;
import com.revature.Maxwell_Moord_p0.web.servlets.AccountServlet;
import com.revature.Maxwell_Moord_p0.web.servlets.ModServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();
        AccountDao accountDao = new AccountDao();
        AccountServices accountServices = new AccountServices();
        ModServices modServices = new ModServices();

        AuthServlet authServlet = new AuthServlet(accountServices, mapper);
        AccountServlet accountServlet = new AccountServlet(accountServices, mapper);
        ModServlet modServlet = new ModServlet(modServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("AccountServlet", accountServlet).addMapping("/accounts/*");
        context.addServlet("ModServlet", modServlet).addMapping("/mods/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
