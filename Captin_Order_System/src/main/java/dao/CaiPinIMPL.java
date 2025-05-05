package dao;

import pojo.CaiPin;

import java.sql.*;
import java.util.Scanner;

public class CaiPinIMPL implements CaiPinDao {
    Scanner scanner = new Scanner(System.in);
    @Override
    public void findAllCaiPin(Connection conn) {
        String selectUserSQL = "SELECT * FROM t_caipin";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectUserSQL)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("cid") + ", Name: " + rs.getString("cname")
                        + ", Price: " + rs.getDouble("Cprice") + ",Cdesc:" + rs.getString("Cdesc")
                       + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CaiPin findByID(Connection conn) {
        return null;
    }

    @Override
    public void deleteCaiPin(Connection conn,int deleteid) {

        String deleteSQL = "DELETE FROM t_caipin WHERE cid = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, deleteid);
            pstmt.executeUpdate();
            System.out.println("Deleted a caipin.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeCaiPinName(Connection conn, int updateId, String newpname) {
        String updateSQL = "UPDATE t_caipin SET cname = ? WHERE cid = ?";
        try (PreparedStatement pstmt1 = conn.prepareStatement(updateSQL)) {

            pstmt1.setString(1, newpname);
            pstmt1.setInt(2, updateId); //
            pstmt1.executeUpdate();
            System.out.println("Updated  cname.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeCaiPinPrice(Connection conn, int changeID, Double newPrice) {
        String updateSQL = "UPDATE t_caipin SET Cprice = ? WHERE cid = ?";
        try (PreparedStatement pstmt2 = conn.prepareStatement(updateSQL)) {

            pstmt2.setDouble(1, newPrice);
            pstmt2.setInt(2, changeID); //
            pstmt2.executeUpdate();
            System.out.println("Updated Cprice.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeCaiPinDesc(Connection conn, int changeID, String Desc) {
        String updateSQL = "UPDATE t_caipin SET Cdesc = ? WHERE pid = ?";
        try (PreparedStatement pstmt2 = conn.prepareStatement(updateSQL)) {

            pstmt2.setString(1, Desc);
            pstmt2.setInt(2, changeID); //
            pstmt2.executeUpdate();
            System.out.println("Updated  Cdesc.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void insertCaiPin(Connection conn,String canme,double cprice,String Desc ) {
        String insertSQL = "INSERT INTO t_caipin (cid, cname,Cprice,Cdesc) VALUES (default, ?,?,?)";

        try {
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, canme);
                pstmt.setDouble(2, cprice);
                pstmt.setString(3, Desc);

                pstmt.executeUpdate();


                System.out.println("Inserted a new caipin.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
