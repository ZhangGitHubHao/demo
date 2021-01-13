package com.example.study.jsonOperation;





import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonLibDemo {
    public static void main(String[] args) {
        createJsonData();
        mapToJson();
    }

    public static void createJsonData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhangsan");
        jsonObject.put("sex", "男");
        jsonObject.put("age", new Integer(22));
        System.out.println(jsonObject.toString());
    }

    public static void mapToJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",15);
        JSONObject json = JSONObject.fromObject(map);
        System.out.println(json.toString());
    }


    private class User{
        public String name;
        public String sex;
        public int age;

    }
}
