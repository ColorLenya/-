package pojo;

import java.util.Set;

public class CaiPin {
    //用户根据菜单选择，菜品id和数量-->创建菜品对象，在UserIMPL实现将各种菜品整合为set集合，封装成order对象
    private int CaiPinID;
    private int number;
    private String CaiPinName;

    private Double price;

    public CaiPin(int caiPinID, int number) {
        CaiPinID = caiPinID;
        this.number = number;
    }//这是给用户点餐的


    public CaiPin(int caiPinID,  String caiPinName, Double price) {
        CaiPinID = caiPinID;

        CaiPinName = caiPinName;
        this.price = price;
    }//这是给管理员看的

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public int getCaiPinID() {
        return CaiPinID;
    }

    public void setCaiPinID(int caiPinID) {
        CaiPinID = caiPinID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCaiPinName() {
        return CaiPinName;
    }

    public void setCaiPinName(String caiPinName) {
        CaiPinName = caiPinName;
    }




}
