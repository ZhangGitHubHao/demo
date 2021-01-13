package com.example.study.stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamDemo {
    public static void main(String[] args) {
        writeInFile();
        readInFile();
    }

    private static void readInFile() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("D:" + File.separator + "data.txt"));
            // 第3步、进行读操作
            String name = null; // 接收名称
            float price = 0.0f; // 接收价格
            int num = 0; // 接收数量
            char temp[] = null; // 接收商品名称
            int len = 0; // 保存读取数据的个数
            char c = 0; // '\u0000'
            while (true) {
                temp = new char[200]; // 开辟空间
                len = 0;
                while ((c = dis.readChar()) != '\t') { // 接收内容
                    temp[len] = c;
                    len++; // 读取长度加1
                }
                name = new String(temp, 0, len); // 将字符数组变为String
                price = dis.readFloat(); // 读取价格
                dis.readChar(); // 读取\t
                num = dis.readInt(); // 读取int
                dis.readChar(); // 读取\n
                System.out.printf("名称：%s；价格：%5.2f；数量：%d\n", name, price, num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeInFile() {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("D:" + File.separator + "data.txt"));
            String names[] = {"衬衣", "手套", "围巾"}; // 商品名称
            float prices[] = {98.3f, 30.3f, 50.5f}; // 商品价格
            int nums[] = {3, 2, 1}; // 商品数量
            // 循环输出
            for (int i = 0; i < names.length; i++) {
                dos.writeChars(names[i]);
                dos.writeChar('\t');
                dos.writeFloat(prices[i]);
                dos.writeChar('\t');
                dos.writeInt(nums[i]);
                dos.writeChar('\n');
            }
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
