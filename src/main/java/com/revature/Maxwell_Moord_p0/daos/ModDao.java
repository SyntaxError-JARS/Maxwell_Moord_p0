package com.revature.Maxwell_Moord_p0.daos;

import com.revature.Maxwell_Moord_p0.exceptions.ResourcePersistanceException;
import com.revature.Maxwell_Moord_p0.models.Mod;
import com.revature.Maxwell_Moord_p0.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class ModDao {
    
    public ArrayList<Mod> findMods(){

        ArrayList<Mod> mods = new ArrayList<>();


        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from mod_data";
            Statement s = conn.createStatement();

            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) {
                Mod mod = new Mod();

                mod.setModName(rs.getString("mod_names"));
                mod.setCreatorName(rs.getString("creator_name"));
                

                mods.add(mod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return (mods);
    }


    public ArrayList<Mod> findByCreatorName(String creatorName) {
        ArrayList<Mod> mods = new ArrayList<>();


        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from mod_data where creator_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, creatorName);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mod mod = new Mod();

                mod.setModName(rs.getString("mod_names"));
                mod.setCreatorName(rs.getString("creator_name"));


                mods.add(mod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return (mods);
    }
}
