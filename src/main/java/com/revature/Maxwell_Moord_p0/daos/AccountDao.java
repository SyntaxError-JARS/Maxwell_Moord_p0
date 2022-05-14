package com.revature.Maxwell_Moord_p0.daos;

import com.revature.Maxwell_Moord_p0.models.Account;
import com.revature.Maxwell_Moord_p0.services.AccountServices;
import com.revature.Maxwell_Moord_p0.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class AccountDao {



    public static Account create(Account newUser) {

        System.out.println("Here is the newUser as it enters our DAO layer: "+ newUser); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into usr_data (id, email, username, password) values (default,?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newUser.getEmail());
            ps.setString(2, newUser.getUsername());
            ps.setString(3, newUser.getPassword());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }
        return newUser;
    }

    public ArrayList<Account> findUsers(){

        ArrayList<Account> accounts = new ArrayList<>();


        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from usr_data";
            Statement s = conn.createStatement();

            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) {
                Account account = new Account();

                account.setEmail(rs.getString("email"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));

                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return (accounts);
    }

    public Boolean pullUsernames(String username) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select username from usr_data where username = ?";
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


    //Just pass newUser and select where that email exists if it does
    public Boolean pullEmails(String email) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select email from usr_data where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

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



}
