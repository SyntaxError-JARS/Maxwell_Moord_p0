package com.revature.Maxwell_Moord_p0.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Mod {


    private String modName;
    private String creatorName;
    private String id;


    public Mod(String modName, String creatorName, String id){
        super();
        this.modName = modName;
        this.creatorName = creatorName;
        this.id = id;
    }

    public Mod(){

    }

    //Getters And Setters
    public String getModName(){return modName;}

    public void setModName(String modName){this.modName = modName;}

    public String getCreatorName(){return creatorName;}

    public void setCreatorName(String creatorName){this.creatorName = creatorName;}

    public String getId(){return id;}

    public void setId(String id){this.id = id;}





    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "Mod Info{" +
                "Mod ID='" + id + '\'' +
                ", Mod Name='" + modName + '\'' +
                ", Creator Name='" + creatorName + '\'' +
                '}';
    }

}
