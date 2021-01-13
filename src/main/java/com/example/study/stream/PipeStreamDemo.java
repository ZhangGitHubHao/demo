package com.example.study.stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

public class PipeStreamDemo {
    public static void main(String[] args) {
        Work1 work1 = new Work1();
        Work2 work2 = new Work2();
        try {
            work2.getPipedOutputStream().connect(work1.getPipedInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(work1).start();
        new Thread(work2).start();
    }

}

class Work1 implements Runnable {
    PipedInputStream pipedInputStream = new PipedInputStream();

    @Override
    public void run() {
        System.out.println("thread1");
        try {
            while (true) {
                byte[] bytes = new byte[1024];
                int len = pipedInputStream.read(bytes);
                if (len != -1){
                    System.out.println("输出:"+new String(bytes));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pipedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public PipedInputStream getPipedInputStream() {
        return pipedInputStream;
    }
}

class Work2 implements Runnable {
    PipedOutputStream pipedOutputStream = new PipedOutputStream();

    @Override
    public void run() {
        System.out.println("thread2");
        try {
            int i = 0;
            while (true) {
                pipedOutputStream.write(("number"+i).getBytes(StandardCharsets.UTF_8));
                pipedOutputStream.flush();
                i++;
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                pipedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public PipedOutputStream getPipedOutputStream() {
        return pipedOutputStream;
    }
}

