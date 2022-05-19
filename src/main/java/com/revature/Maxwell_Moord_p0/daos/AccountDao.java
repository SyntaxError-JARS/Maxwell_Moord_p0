package com.revature.Maxwell_Moord_p0.daos;

import com.revature.Maxwell_Moord_p0.exceptions.ResourcePersistanceException;
import com.revature.Maxwell_Moord_p0.models.Account;
import com.revature.Maxwell_Moord_p0.models.Mod;
import com.revature.Maxwell_Moord_p0.services.AccountServices;
import com.revature.Maxwell_Moord_p0.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class AccountDao {


    public static Account create(Account newUser) {

        System.out.println("Here is the newUser as it enters our DAO layer: " + newUser); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into usr_data (email, username, password) values (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newUser.getEmail());
            ps.setString(2, newUser.getUsername());
            ps.setString(3, newUser.getPassword());

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
        return newUser;
    }


    public Boolean pullUsernames(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select username from usr_data where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    //Just pass newUser and select where that email exists if it does
    public Boolean pullEmails(String email) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select email from usr_data where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public Account authenticateAccount(String username, String password) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from usr_data where username = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Account account = new Account();

            account.setEmail(rs.getString("email"));
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("password"));


            return account;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    public Boolean checkForModpacks(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from modpack_data where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String deleteModpacks(String username) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "delete from modpack_data where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);


            int rs = ps.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        String deletedModpacks;
        return deletedModpacks = ("All modpacks of user " + username + " have been deleted");
    }

    public String deleteAccount(String username) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "delete from usr_data where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);


            int rs = ps.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        String deletedAccount;
        return deletedAccount = ("Account of " + username + " has been deleted \n");
    }


}

