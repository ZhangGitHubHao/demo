package com.example.study.test;

import java.util.List;
import java.util.Vector;

public class TestDemo {
    public static void main(String[] args) {
        // AbstractList ， AbstractSequentialList ， ArrayList ， AttributeList ， CopyOnWriteArrayList ， LinkedList ， RoleList ， RoleUnresolvedList ， Stack ， Vector
        List<String> list = new Vector<>();
        list.add("公众号");
        list.add("why技术");
        System.out.println("before remove:"+list);
        for (String item: list) {
            if ("why技术".equals(item)){
                list.remove(item);
            }
        }
        System.out.println("after remove:"+list);
    }
}
