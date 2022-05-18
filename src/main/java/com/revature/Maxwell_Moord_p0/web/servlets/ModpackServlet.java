package com.revature.Maxwell_Moord_p0.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Maxwell_Moord_p0.services.ModpackServices;

public class ModpackServlet {

    private final ModpackServices modpackServices;
    private final ObjectMapper mapper;



    public ModpackServlet(ModpackServices modpackServices, ObjectMapper mapper) {

        this.modpackServices = modpackServices;
        this.mapper = mapper;



    }

}
