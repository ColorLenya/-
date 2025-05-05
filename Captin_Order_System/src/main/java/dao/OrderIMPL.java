package dao;

import pojo.CaiPin;
import pojo.Order;
import pojo.User;

import java.sql.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

public class OrderIMPL implements OrderDao{
    @Override
    public void findOrder(Connection conn) {
        String selectUserSQL = "SELECT t_order.oid,t_order.uid,t_order.cid,t_order.number,t_order.sumprice,t_order.time,\n" +
                "t_user.id,t_user.name,\n" +
                "t_caipin.cid,t_caipin.cname\n" +
                "from t_order ,t_user,t_caipin\n" +
                "\n" +
                "where (t_order.uid=t_user.id)and(t_order.cid=t_caipin.cid);";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectUserSQL)) {
            while (rs.next()) {
                System.out.println("OID: " + rs.getInt("oid") + ", UID: " + rs.getString("uid")
                        + ", CID: " + rs.getDouble("cid") + ",Number:" + rs.getString("number")
                        + ",Sumprice:" + rs.getString("sumprice")+ ",Time:" + rs.getString("time")
                        + ",username:" + rs.getString("name")+",caipinName:" + rs.getString("cname")+"\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commitOrder(Connection conn, Order order, User user) {
        LocalTime time = null;

        String str = user.getName();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int uid=user.getPid();
        try {

            pstmt = conn.prepareStatement(
                    "INSERT INTO t_order (oid,uid,cid,number,sumprice,time) VALUES (default,?,?,?,?,?)");
            //獲取Order中詳細信息

            for(CaiPin i  :order.getMap().keySet()){
                if(order.getMap().get(i)>0){
                    int number =order.getMap().get(i);
                    int cid = i.getCaiPinID();
                    pstmt.setInt(1,uid );
                    pstmt.setInt(2,cid );
                    pstmt.setInt(3,number );
                    pstmt.setDouble(4,(double) number*i.getPrice());
                    pstmt.setTime(5, java.sql.Time.valueOf(time.now()));
                    pstmt.addBatch();
                }

            }

            int [] ints = pstmt.executeBatch();
            System.out.println(Arrays.toString(ints));

            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
