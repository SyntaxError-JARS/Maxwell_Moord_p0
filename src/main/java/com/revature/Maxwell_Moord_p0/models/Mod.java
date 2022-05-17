package com.revature.Maxwell_Moord_p0.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Mod {


    private String modName;
    private String creatorName;


    public Mod(String modName, String creatorName){
        super();
        this.modName = modName;
        this.creatorName = creatorName;

    }

    public Mod(){

    }

    //Getters And Setters
    public String getModName(){return modName;}

    public void setModName(String modName){this.modName = modName;}

    public String getCreatorName(){return creatorName;}

    public void setCreatorName(String creatorName){this.creatorName = creatorName;}





    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "Mod Info{" +
                "Mod Name='" + modName + '\'' +
                ", Creator Name='" + creatorName + '\'' +
                '}';
    }

}
