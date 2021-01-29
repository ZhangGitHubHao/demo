package com.example.study.thread;

import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

public class PipedDemo {
    static class MyThreadOne implements Runnable {
        PipedInputStream pipedInputStream;

        public MyThreadOne(PipedInputStream pipedInputStream) {
            this.pipedInputStream = pipedInputStream;
        }

        @Override
        public void run() {
            try {
                int temp;
                while ((temp = pipedInputStream.read()) != -1) {
                    System.out.println((char) temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThreadTwo implements Runnable {
        PipedOutputStream pipedOutputStream;

        public MyThreadTwo(PipedOutputStream pipedOutputStream) {
            this.pipedOutputStream = pipedOutputStream;
        }

        @Override
        public void run() {
            try {
                pipedOutputStream.write("hello".getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream();
        Thread thread1 = new Thread(new MyThreadOne(pipedInputStream));
        Thread thread2 = new Thread(new MyThreadTwo(pipedOutputStream));
        try {
            pipedInputStream.connect(pipedOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        thread1.start();
        thread2.start();

    }
}
