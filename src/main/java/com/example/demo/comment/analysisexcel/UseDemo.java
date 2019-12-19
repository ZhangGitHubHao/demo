package com.example.demo.comment.analysisexcel;

import java.util.List;

/**
 * 使用案例
 *
 * @author zhangh
 * @date 2019/10/21
 */
public class UseDemo {
    public static void main(String[] args) {
        Student student=new Student();
        String filePath="C:\\Users\\860118060\\Desktop\\students.xlsx";
        try {
            ReadExcelUtil readExcelUtil=new ReadExcelUtil(student,filePath);
            List<Object> list=readExcelUtil.getObjectsList();
            for (Object object:list){
                Student test=(Student) object;
                System.out.println(test.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
