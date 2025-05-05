package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {




    //存放conn对象
    private static List<Connection> list = new LinkedList<>();

    static {

        try {
            Properties prop = new Properties();

            InputStream is = Main.class.getClassLoader().getResourceAsStream("jdbc.properties");
            prop.load(is);
            String url=prop.getProperty("url");
            String name = prop.getProperty("user");
            String pwd = prop.getProperty("pwd");
            String driver = prop.getProperty("driver");
            Class.forName(driver);
            for (int i = 0; i < 10; i++) {
                Connection conn = DriverManager.getConnection(url,name,pwd);

                list.add(conn);}

            /*Class.forName("com.mysql.cj.jdbc.Driver");

            for (int i = 0; i < 10; i++) {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false",
                        "root",
                        "123456");

                list.add(conn);
            }*/

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    //获得conn对象  类的字节码对象
    public synchronized static Connection getConnection() {

        //代表池子中没有conn
        if (list.size() == 0) {
            try {
                ConnectionPool.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Connection conn = list.remove(0);
        return conn;

    }

    //把Conn对象放回池子
    public synchronized static void close(Connection connection) {

        list.add(connection);
        //随机唤醒一个线程
        ConnectionPool.class.notify();

    }

}
