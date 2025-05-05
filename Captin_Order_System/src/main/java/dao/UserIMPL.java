package dao;


import pojo.CaiPin;
import pojo.Order;
import pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class UserIMPL implements UserDao {
    @Override
    public User login(String name, String password, Connection conn) {
        User loginUser = new User();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t_user");
            while (rs.next()) {
                if (rs.getString("Name").equals(name)) {
                    if (rs.getString("Password").equals(password)) {
                        loginUser.setPid(rs.getInt(1));
                        loginUser.setName(name);
                        loginUser.setPwd(password);
                        loginUser.setRole(rs.getInt(4));

                        System.out.println("登录成功！");
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return loginUser;
        }


    }



    @Override
    public HashMap<CaiPin,Integer>  initzerOrder(Connection conn,HashMap<CaiPin,Integer> caiPinSet) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t_caipin");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("cid") + ", Name: " + rs.getString("cname")
                        + ", Cprice: " + rs.getDouble("Cprice") + ",Cdesc:" + rs.getString("Cdesc")
                        + "\n");
                CaiPin newCaiPin = new CaiPin(rs.getInt("cid"),
                        rs.getString("cname"),rs.getDouble("Cprice"));
                caiPinSet.put(newCaiPin,0);
            }
            return caiPinSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }








}
