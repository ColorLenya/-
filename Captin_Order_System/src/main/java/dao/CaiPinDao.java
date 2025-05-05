package dao;

import pojo.CaiPin;

import java.sql.Connection;

public interface CaiPinDao {

    void findAllCaiPin(Connection conn);//用户,管理员均有的查询所有菜品

    /*管理员特有函数*/
    CaiPin findByID(Connection conn);
    void deleteCaiPin(Connection conn,int deleteid);
    //void updateCaiPin(Connection conn);

    void changeCaiPinName(Connection conn,int changeID,String newpname);
    void changeCaiPinPrice(Connection conn ,int changeID,Double newPrice);
    void changeCaiPinDesc(Connection conn ,int changeID,String Desc);
    void insertCaiPin(Connection conn,String canme,double cprice,String Desc  );
}
