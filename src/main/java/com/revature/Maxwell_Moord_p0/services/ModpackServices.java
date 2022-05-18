package com.revature.Maxwell_Moord_p0.services;

import com.revature.Maxwell_Moord_p0.daos.ModDao;
import com.revature.Maxwell_Moord_p0.daos.ModpackDao;
import com.revature.Maxwell_Moord_p0.exceptions.InvalidRequestException;
import com.revature.Maxwell_Moord_p0.models.Modpack;

public class ModpackServices {
    ModpackDao modpackDao = new ModpackDao();

    public Modpack createModpack(Modpack modpackToCreate){
        String modpackName = modpackToCreate.getModpackName();
        String username = modpackToCreate.getUsername();
        String modId = modpackToCreate.getModId();

        Modpack modpackToCheck = modpackDao.findModpack(modpackName,username,modId);
        System.out.println(modpackToCheck.getModpackName());
        System.out.println(modpackToCheck.getUsername());
        System.out.println(modpackToCheck.getModId());


        if(modpackName.equals(modpackToCheck.getModpackName()) && username.equals(modpackToCheck.getUsername()) && modId.equals(modpackToCheck.getModId())){
            throw new InvalidRequestException("That mod is already in the modpack specified");
        }

        if(modId == null){throw new InvalidRequestException("modId cannot be null");
        }else {
            return modpackDao.createModpack(modpackName, username, modId);
        }
    }

    public boolean verifyUsername(String username){return modpackDao.verifyUsernameExists(username);}
   // public boolean verifyModId(String modId){return modpackDao.verifyModIdExists(modId);}
    public boolean verifyModpackName(String modpackName){return modpackDao.verifyModpackNameExists(modpackName);}



}
