package com.revature.Maxwell_Moord_p0.services;

import com.revature.Maxwell_Moord_p0.daos.ModDao;
import com.revature.Maxwell_Moord_p0.exceptions.InvalidRequestException;
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


    public Mod updateMod(Mod modToUpdate){
        String modName = modToUpdate.getModName();
        String creatorName = modToUpdate.getCreatorName();
        String id = modToUpdate.getId();
        if(!verifyCreatorName(creatorName)){throw new InvalidRequestException("This creator doesn't exist");}
        else if(!verifyId(id)){throw new InvalidRequestException("This id doesn't exist");}
        else if(verifyModName(modName)){
            throw new InvalidRequestException("This mod already exists " + modDao.findByModName(modName));
        }
        else{return modDao.updateMod(modName,creatorName,id);}

    }

    public boolean verifyCreatorName(String creatorName){return modDao.verifyCreatorExists(creatorName);}
    public boolean verifyId(String id){return modDao.verifyIdExists(id);}
    public boolean verifyModName(String modName){return modDao.verifyModNameExists(modName);}

    public Mod createMod(Mod modToUpdate){
        String modName = modToUpdate.getModName();
        String creatorName = modToUpdate.getCreatorName();
        if(verifyModName(modName)){
            throw new InvalidRequestException("This mod already exists " + modDao.findByModName(modName));
        }
        return modDao.createMod(modName,creatorName);
    }

}
