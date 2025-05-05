package dao;

import pojo.Order;
import pojo.User;

import java.sql.Connection;

public interface OrderDao {
    /*管理员特有函数*/
    void findOrder(Connection conn);

    /*用户特有函数*/
    void commitOrder(Connection conn, Order order, User user);//根据Order向数据库订单表插入新东西
}
