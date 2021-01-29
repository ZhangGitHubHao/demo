package com.example.study.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyDemo {
    public static void main(String[] args) {
        TianGou tianGou = new TianGou();
        ClassLoader classLoader = tianGou.getClass().getClassLoader();
        Class[] classes = tianGou.getClass().getInterfaces();
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        };

    }
}

interface People{
    void sayHi();
    void getAnswer(String answer);
}
class TianGou implements People{
    public String name;

    public void sayHi(){
        System.out.println("在吗?");
    }

    public void getAnswer(String answer){
        System.out.println(answer);
    }
}
