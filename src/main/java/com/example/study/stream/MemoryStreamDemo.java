package com.example.study.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MemoryStreamDemo {

    public static void main(String[] args) {
        writerInMemory();
    }


    public static void writerInMemory(){
        System.out.println("write");
        ByteArrayInputStream bis = new ByteArrayInputStream("I am superman".getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int haveContent = 0;
        try {
            while (haveContent != -1) {
                byte[] bytes = new byte[1024];
                haveContent = bis.read(bytes);
                bos.write((new String(bytes).toUpperCase()).getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(bos.toString());
    }
}
