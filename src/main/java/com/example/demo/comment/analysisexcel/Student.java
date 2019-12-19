package com.example.demo.comment.analysisexcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 解析的实体类
 *
 * @author zhangh
 * @date 2019/10/21
 */
public class Student implements Cloneable {
    /**
     * id
     */
    private Integer id;
    /**
     * 学号
     */
    private String no;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 成绩
     */
    private float score;

    /**
     * 地址
     */
    private String address;

    /**
     * 日期
     */
    private Date date;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", score=" + score +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private List<Student> studentsList=new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
