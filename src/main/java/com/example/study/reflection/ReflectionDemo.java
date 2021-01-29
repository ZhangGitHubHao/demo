package com.example.study.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionDemo {
    public static void main(String[] args) {
//        String s = "1";
//        int[] i = {0,1};
//
//        System.out.println(s.getClass().getName());
//        System.out.println(int.class.getName());
//        System.out.println(i.getClass().getName());
//        demo2();
        demo3();
    }

    private static void demo1() {
        try {
//            User u = new User("zhangsan");
//            Method method = u.getClass().getMethod("sayHi");
            Class uclass = Class.forName("com.example.study.reflection.User");
            Constructor constructor = uclass.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);

            User u = (User) constructor.newInstance("zhangsan");

            Method method = u.getClass().getDeclaredMethod("sayHi");
            method.setAccessible(true);
            method.invoke(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demo2() {
        try {
            Class c = Class.forName("com.example.study.reflection.Student");
            Field field = c.getDeclaredField("age");
            System.out.format("Type: %s%n", field.getType());

            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {
                System.out.format("Type: %s%n", f.getType());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void demo3() {
        try {
            Class studentClass = Student.class;
            Constructor[] constructors = studentClass.getDeclaredConstructors();
            for (Constructor c : constructors) {
                c.setAccessible(true);
                Class[] classes = c.getParameterTypes();
                List<Object> params = new ArrayList<>();
                for (Class cla : classes){
                    if (cla.getName().equals("java.lang.String")){
                        params.add("zhangsan");
                    }
                    if (cla.getName().equals("int")){
                        params.add(1);
                    }
                    if (cla.getName().equals("[I")){
                        params.add(new int[]{0,1});
                    }
                }
                Student student = null;
                if (params.size()==0){
                    student = (Student) c.newInstance();
                }
                if (params.size()==1){
                    student = (Student) c.newInstance(params.get(0));
                }
                if (params.size()==2){
                    student = (Student) c.newInstance(params.get(0),params.get(1));
                }
                System.out.println(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class User {
    public String name;

    public User() {
    }

    private User(String name) {
        this.name = name;
    }

    private void sayHi() {
        System.out.println("hello:" + this.name);
    }
}

class Student extends User {
    public int age;
    public boolean[][] b;
    public int[] c;

    private Student(int age) {
        this.age = age;
    }

    private Student(int age, String name) {
        this.name = name;
        this.age = age;
    }

    private Student(int age, int[] ins) {
        this.c = ins;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", b=" + Arrays.toString(b) +
                ", c=" + Arrays.toString(c) +
                '}';
    }
}
