package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class CP {

    //存放conn对象
    private static List<Connection> list=new LinkedList<>();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            for (int i = 0; i < 10; i++) {
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test2?serverTimezone=UTC",
                        "root",
                        "123456");
                list.add(conn);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //获得conn对象  类的字节码对象
    public  synchronized   static   Connection  getConnection(){

        //代表池子中没有conn
        if(list.size()==0){
            try {
                CP.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Connection conn = list.remove(0);
        return conn;

    }

    //把Conn对象放回池子
    public  synchronized   static   void  close(Connection connection){

        list.add(connection);
        //随机唤醒一个线程
        CP.class.notify();

    }

}



