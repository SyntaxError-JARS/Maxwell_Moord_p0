package com.revature.Maxwell_Moord_p0.models;


public class Modpack {


    private String modpackName;
    private String username;
    private String modId;


    public Modpack(String modpackName, String username, String modId){
        super();
        this.modpackName = modpackName;
        this.username = username;
        this.modId = modId;
    }

    public Modpack(){

    }

    //Getters And Setters
    public String getModpackName(){return modpackName;}

    public void setModpackName(String modpackName){this.modpackName = modpackName;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getModId(){return modId;}

    public void setModId(String modId){this.modId = modId;}





    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "Modpack Info{" +
                "Modpack Name='" + modpackName + '\'' +
                ", Mod Id='" + modId + '\'' +
                ", Username='" + username + '\'' +
                '}';
    }

}
