package dao;


import pojo.CaiPin;
import pojo.Order;
import pojo.User;
import java.sql.Connection;
import java.util.HashMap;

public interface UserDao {
    /*公共函数*/
    User login(String name,String password,Connection conn);//实现登录的,根据数据库返回的Role,判断返回User对象还是Admin对象


    /*用户特有函数*/
    //void user(User user);//user的登录界面,可以查看菜单 还可以选择自己喜欢的菜进行点菜（根据菜品编号）。
    HashMap<CaiPin,Integer>  initzerOrder(Connection conn,HashMap<CaiPin,Integer> caiPinSet);//根據今日菜品表内容，創建hashmap并且封裝成Order
    //Order chooseCaiPin(Connection conn, HashMap<CaiPin,Integer> caiPinSet);//根据数据库菜品表返回的information，创建菜品对象，进而创建订单对象


    /*管理员特有函数*/
   // void admin(Admin admin);//admin的登录界面,可以查询添加菜品、删除菜品、修改菜品、查菜品。查看点餐记录！

}
