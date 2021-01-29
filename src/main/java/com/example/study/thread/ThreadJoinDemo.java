package com.example.study.thread;

public class ThreadJoinDemo {
    static class MyThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName() + " 运行，i = " + i); // 取得当前线程的名字
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        Thread t = new Thread(thread,"线程A");
        t.start();
        for (int i = 0; i < 50; i++) {
            if (i>10){
                t.join();
            }
            System.out.println("Main 线程运行,i = "+ i);
        }
    }
}
