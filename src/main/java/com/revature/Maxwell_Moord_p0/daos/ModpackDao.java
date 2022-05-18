package com.revature.Maxwell_Moord_p0.daos;


import com.revature.Maxwell_Moord_p0.models.Modpack;
import com.revature.Maxwell_Moord_p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModpackDao {

    public Boolean verifyModpackNameExists(String modpackName) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select modpack_name from modpack_data where modpack_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, modpackName);

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


    public Boolean verifyUsernameExists(String username) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select username from modpack_data where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

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


    public Modpack findModpack(String modpackName, String username, String modId) {

        Modpack modpack = new Modpack();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from modpack_data where modpack_name = ? and username = ? and mod_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, modpackName);
            ps.setString(2, username);
            ps.setInt(3, Integer.parseInt(modId));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                modpack.setModId(rs.getString("mod_id"));
                modpack.setModpackName(rs.getString("modpack_name"));
                modpack.setUsername(rs.getString("username"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return (modpack);
    }

    public Modpack createModpack(String modpackName, String username, String modId) {
        Modpack modpack = new Modpack();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "insert into modpack_data values (default, ?, ?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, modpackName);
            ps.setString(2, username);
            ps.setInt(3, Integer.parseInt(modId));


            int rs = ps.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from modpack_data where username = ? and modpack_name = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, modpackName);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                modpack.setModId(rs.getString("mod_id"));
                modpack.setModpackName(rs.getString("modpack_name"));
                modpack.setUsername(rs.getString("username"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return (modpack);
    }




}
