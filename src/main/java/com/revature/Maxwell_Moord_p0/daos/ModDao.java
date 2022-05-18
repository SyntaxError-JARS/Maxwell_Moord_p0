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

                mod.setId(rs.getString("id"));
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

                mod.setId(rs.getString("id"));
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

    public Boolean verifyCreatorExists(String creatorName) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select creator_name from mod_data where creator_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, creatorName);

            ResultSet rs = ps.executeQuery();

            if(!rs.isBeforeFirst()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean verifyIdExists(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select id from mod_data where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            if(!rs.isBeforeFirst()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Mod updateMod(String modName, String creatorName, String id) {
        Mod mod = new Mod();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "update mod_data set mod_names = ? where creator_name = ? and id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, modName);
            ps.setString(2, creatorName);
            ps.setInt(3, Integer.parseInt(id));

            int rs = ps.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from mod_data where creator_name = ? and id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, creatorName);
            ps.setInt(2, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                mod.setId(rs.getString("id"));
                mod.setModName(rs.getString("mod_names"));
                mod.setCreatorName(rs.getString("creator_name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return (mod);
    }

}
