package com.revature.Maxwell_Moord_p0.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Maxwell_Moord_p0.exceptions.InvalidRequestException;
import com.revature.Maxwell_Moord_p0.exceptions.ResourcePersistanceException;
import com.revature.Maxwell_Moord_p0.models.Mod;
import com.revature.Maxwell_Moord_p0.services.ModServices;

import com.revature.Maxwell_Moord_p0.web.dto.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.Maxwell_Moord_p0.web.servlets.Authable.checkAuth;

public class ModServlet extends HttpServlet {

    private final ModServices modServices;
    private final ObjectMapper mapper;



    public ModServlet(ModServices modServices, ObjectMapper mapper) {

        this.modServices = modServices;
        this.mapper = mapper;



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Handling the query params in the endpoint /mods?id=x
        if (req.getParameter("creator_name") != null) {

            ArrayList<Mod> mod;

            try {
                mod = modServices.readByCreatorName(req.getParameter("creator_name")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
                System.out.println(mod);
            } catch (ResourcePersistanceException e) {
                //logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(mod);
            resp.getWriter().write(payload);
            System.out.println(mod);
            return;
        }

        List<Mod> mods = modServices.readAllMods();
        String payload = mapper.writeValueAsString(mods);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (checkAuth(req, resp)) {
            Mod modToUpdate = mapper.readValue(req.getInputStream(), Mod.class);
            Mod mods = new Mod();

            if (modToUpdate.getCreatorName().equals(LoginCreds.getUsername()) && modToUpdate.getId() == null) {
                //creating
                mods = modServices.createMod(modToUpdate);
                String payload = mapper.writeValueAsString(mods);
                resp.getWriter().write(payload);


            }else if(modToUpdate.getCreatorName().equals(LoginCreds.getUsername()) && modToUpdate.getId()!=null){
                //updating
                System.out.println("Updating");
                mods = modServices.updateMod(modToUpdate);
                String payload = mapper.writeValueAsString(mods);
                resp.getWriter().write(payload);
            }else if(!modToUpdate.getCreatorName().equals(LoginCreds.getUsername())) {
                throw new InvalidRequestException("The creator name should match your username, please try again");
            }


        }
    }
}






