package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestDemo {
    public static void main(String[] args) {
        // AbstractList ， AbstractSequentialList ， ArrayList ， AttributeList ， CopyOnWriteArrayList ， LinkedList ， RoleList ， RoleUnresolvedList ， Stack ， Vector
        List<String> list = new Vector<>();
        list.add("公众号");
        list.add("why技术");
        System.out.println("before remove:"+list);
        for (String item: list) {
            if (item.equals("why技术")){
                list.remove(item);
            }
        }
        System.out.println("after remove:"+list);
    }
}
