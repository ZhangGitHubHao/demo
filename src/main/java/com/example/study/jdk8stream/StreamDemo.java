package com.example.study.jdk8stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("aaaaaa1");
        strings.add("aaaaaaaaaaaaaa2");
        strings.add("dddddd1");
        strings.add("dddd2");
        strings.add("bbbbbbbbbb1");
        strings.add("bbb2");
        strings.add("ccc2");
        strings.add("ccc1");
//        demo1(strings);
//        demo2(strings);
//        demo3(strings);
//        demo4();
//        demo5(strings);
        demo6(hugeList());
    }

    private static void demo1(List<String> param1) {
        param1.stream().sorted(StreamDemo::sort).forEach(System.out::println);
    }

    private static void demo2(List<String> param1) {
        param1.stream().filter(s -> s.length() > 4 && s.startsWith("a")).forEach(System.out::println);
    }

    private static void demo3(List<String> param1){
        param1.stream().map(User::new).forEach(System.out::println);
    }

    private static void demo4(){
        List<User> users = new ArrayList<>();
        User user1 = new User("zhangsan");
        User user2 = new User("lisi");
        users.add(user1);
        users.add(user2);
        users.stream().map(item -> new Student(item.name)).forEach(System.out::println);
    }
    private static void demo5(List<String> param1){
        param1.stream().reduce((s1, s2) -> s1+"hello"+s2).ifPresent(System.out::println);
    }
    private static void demo6(List<String> param1){
        long t0 = System.nanoTime();
        param1.stream().sorted().count();
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

        long t2 = System.nanoTime();
        param1.parallelStream().sorted().count();
        long t3 = System.nanoTime();
        long millis2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("parallel sort took: %d ms", millis2));

    }

    private static List<String> hugeList(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        return values;
    }
    private static int sort(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return s1.compareTo(s2);
        }
        return s1.trim().length() - s2.trim().length();
    }
    private static class User{
        String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    private static class Student{
        String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
