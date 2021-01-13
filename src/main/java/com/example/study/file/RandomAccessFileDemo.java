package com.example.study.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    public static void main(String[] args) {
        RandomAccessFileDemo rafDemo = new RandomAccessFileDemo();
        rafDemo.writeUser();
    }
    private void writeUser(){
        String dir = "D:"+File.separator+"Demo"+File.separator+"littleDemo";
        File file = new File(dir+File.separator+"test2.txt");
        try {
            RandomAccessFile raf = new RandomAccessFile(file,"rw");
            raf.writeChars("zhangsan");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
