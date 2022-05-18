package com.revature.Maxwell_Moord_p0.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Maxwell_Moord_p0.models.Modpack;
import com.revature.Maxwell_Moord_p0.services.ModpackServices;
import com.revature.Maxwell_Moord_p0.web.dto.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.revature.Maxwell_Moord_p0.web.servlets.Authable.checkAuth;

public class ModpackServlet extends HttpServlet {

    private final ModpackServices modpackServices;
    private final ObjectMapper mapper;



    public ModpackServlet(ModpackServices modpackServices, ObjectMapper mapper) {
        this.modpackServices = modpackServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (checkAuth(req, resp)) {

            Modpack modpackToCreate = mapper.readValue(req.getInputStream(), Modpack.class);
            Modpack modpack = new Modpack();

            if (modpackToCreate.getUsername().equals(LoginCreds.getUsername())) {
                //creating
                modpack = modpackServices.createModpack(modpackToCreate);
                String payload = mapper.writeValueAsString(modpack);
                resp.getWriter().write(payload);
            }


        }
    }


}
