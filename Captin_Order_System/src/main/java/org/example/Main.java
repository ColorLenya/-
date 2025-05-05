package org.example;

import dao.CaiPinIMPL;
import dao.OrderIMPL;
import dao.UserIMPL;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import pojo.CaiPin;
import pojo.Order;
import pojo.User;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static  final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {


        ConnectionPool conne = new ConnectionPool();
        Connection conn =conne.getConnection();

        Scanner scanner =new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name=scanner.next();
        System.out.println("请输入密码：");
        String pwd = scanner.next();


        UserIMPL userIMPL = new UserIMPL();
        CaiPinIMPL caiPinIMPL = new CaiPinIMPL();
        OrderIMPL orderIMPL = new OrderIMPL();


        User user=userIMPL.login(name,pwd,conn);
        if(user.getRole()==1){
            logger.info("用戶"+user.getName()+"進入系統");


            System.out.printf("欢迎用戶%s进入系统\n",user.getName());
            while (true) {
                System.out.println("请输入数字：");
                System.out.println("1.查詢所有菜品");
                System.out.println("2.點單");
                int urchoose = scanner.nextInt();
                switch (urchoose) {
                    case 1: {
                       caiPinIMPL.findAllCaiPin(conn);

                       break;

                    }
                    case 2: {
                        HashMap<CaiPin,Integer> caiPinSet = new HashMap<>();
                        //鍵值對 鍵：菜品對象，值：下單數量。
                        Order newOrder = new Order(caiPinSet);
                        boolean tag =true;

                        System.out.println("今日菜單如下：");
                           userIMPL.initzerOrder(conn,caiPinSet);
                            do {
                                System.out.println("請輸入菜品ID：");
                                int caipinId = scanner.nextInt();
                                for(CaiPin i : caiPinSet.keySet()){
                                    if(i.getCaiPinID()==caipinId){
                                        System.out.println("請輸入菜品數量：");
                                        int number= scanner.nextInt();
                                        int sum = caiPinSet.get(i)+number;
                                        caiPinSet.put(i,sum);
                                    }
                                }

                                System.out.println("還需要點餐麽？1：yes  2：no");
                                int x = scanner.nextInt();
                                if(x==2){
                                    tag=false;
                                }


                            }while (tag);


                        Order order = new Order(caiPinSet);


                        orderIMPL.commitOrder(conn,order,user);
                        return;

                    }
                }
            }

        }else if(user.getRole()==2){



            System.out.printf("欢迎管理員%s进入系统\n",user.getName());

            while (true) {

                System.out.println("请输入数字：");
                System.out.println("1.查詢所有菜品");
                System.out.println("2.添加菜品");
                System.out.println("3.修改菜品");
                System.out.println("4.刪除菜品");
                System.out.println("5.查看訂單記錄！");
                int urchoose = scanner.nextInt();
             q:   switch (urchoose) {

                    case 1: {
                        caiPinIMPL.findAllCaiPin(conn);
                        //這個不用該
                        break;
                    }
                    case 2: {
                        System.out.println("请输入菜品名称：");
                        String canme = scanner.next();
                        System.out.println("请输入價格（double）");
                        double cprice = scanner.nextInt();
                        System.out.println("请输入菜品介紹");
                        String Desc = scanner.next();
                        caiPinIMPL.insertCaiPin(conn,canme,cprice,Desc);
                        //修改完成
                        break;
                    }
                    case 3: {
                        while (true) {
                            System.out.println("请输入你想修改的id");
                            int updateId = scanner.nextInt();

                            System.out.println("请输入数字：");
                            System.out.println("1.改菜品名称");
                            System.out.println("2.改菜品價格");
                            System.out.println("3.改菜品介紹");
                            System.out.println("4.退出修改");
                             urchoose = scanner.nextInt();
                            switch (urchoose) {
                                case 1: {
                                    System.out.println("新修改的菜品名称:\n");
                                    String newpname = scanner.next();
                                    caiPinIMPL.changeCaiPinName(conn,updateId,newpname);
                                    break;
                                }
                                case 2: {
                                    System.out.println("新修改的菜品價格:\n");
                                    Double newCprice = scanner.nextDouble();
                                    caiPinIMPL.changeCaiPinPrice(conn,updateId,newCprice);
                                    break;
                                }

                                case 3: {
                                    System.out.println("新修改的菜品介紹:\n");
                                    String newCdesc = scanner.next();
                                    caiPinIMPL.changeCaiPinDesc(conn,updateId,newCdesc);
                                    break;
                                }
                                case 4: {
                                    break q;
                                }
                            }

                        }

                    }
                    case 4: {
                        System.out.println("请输入待删除（id）");
                        int deleteid = scanner.nextInt();
                        caiPinIMPL.deleteCaiPin(conn,deleteid);
                        break;
                    }
                    case 5: {
                        orderIMPL.findOrder(conn);
                        //不用改
                        break;
                    }
                    case 6:{


                            conne.close(conn);

                        return;
                    }
                }
            }


        }else{
            System.out.println("用户名或密码输入错误！");
        }
    }
}