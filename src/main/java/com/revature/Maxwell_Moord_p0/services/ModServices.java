package com.revature.Maxwell_Moord_p0.services;

import com.revature.Maxwell_Moord_p0.daos.ModDao;
import com.revature.Maxwell_Moord_p0.exceptions.ResourcePersistanceException;
import com.revature.Maxwell_Moord_p0.models.Mod;

import java.util.ArrayList;

public class ModServices {

    private ModDao modDao = new ModDao();
    private Mod mod = new Mod();


    public ArrayList<Mod> readAllMods(){
        ArrayList <Mod> mods = modDao.findMods();

        return mods;
    }


    public ArrayList<Mod> readByCreatorName(String creatorName) throws ResourcePersistanceException {
        System.out.println(creatorName);
        ArrayList<Mod> mod = modDao.findByCreatorName(creatorName);
        return mod;
    }
    
}
