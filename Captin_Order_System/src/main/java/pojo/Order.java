package pojo;

import java.util.HashMap;
import java.util.Set;

public class Order {
    public HashMap<CaiPin, Integer> getMap() {
        return map;
    }

    //将菜品set集合封装成Order对象，在UserIMPL中实现创建订单记录，并向订单记录表中添加信息
    private HashMap<CaiPin,Integer> map;

    public Order(HashMap<CaiPin,Integer> map) {
        this.map = map;
    }

}
