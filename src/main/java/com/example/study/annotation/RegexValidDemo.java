package com.example.study.annotation;

public class RegexValidDemo {
    static class User{
        String name;
        @RegexValid(policy = RegexValid.Policy.DATE)
        String date;
        @RegexValid(policy = RegexValid.Policy.MAIL)
        String mail;

        public User(String name, String date, String mail) {
            this.name = name;
            this.date = date;
            this.mail = mail;
        }

        @Override
        public String toString() {
            return "user{" +
                    "name='" + name + '\'' +
                    ", date='" + date + '\'' +
                    ", mail='" + mail + '\'' +
                    '}';
        }
    }

    static void printDate(@RegexValid(policy = RegexValid.Policy.DATE) String date) throws Exception {
        System.out.println(date);
    }

    public static void main(String[] args) throws Exception {
//        User user = new User("zhangsan","201912-12","123@163.com");
//        if (RegexValidUtil.check(user)){
//            System.out.println("校验通过");
//        }
        printDate("201912-12");
    }
    
    
}
