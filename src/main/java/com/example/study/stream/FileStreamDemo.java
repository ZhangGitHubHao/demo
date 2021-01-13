package com.example.study.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class FileStreamDemo {
    public static void main(String[] args) {
//        writeToFile();
//        readToFile();
//        copyFile();
        copyFile2();
    }

    public static void readToFile() {
        File file = new File("D:" + File.separator + "user.txt");
        if (!file.exists()) {
            return;
        }
        try {
            StringBuilder content = new StringBuilder();
            int temp = 0;
            InputStream inputStream = new FileInputStream(file);
            while (temp != -1) {
                byte[] con = new byte[10];
                temp = inputStream.read(con);
                content.append(new String(con));
            }
            System.out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readToFile2() {
        File file = new File("D:" + File.separator + "user.txt");
        if (!file.exists()) {
            return;
        }
        try {
            String content;
            int temp;
            InputStream inputStream = new FileInputStream(file);
            byte[] con = new byte[(int) file.length()];
            temp = inputStream.read(con);
            content = new String(con);
            System.out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile() {
        File file = new File("D:" + File.separator + "user.txt");
        if (file.exists()) {
            file.delete();
        }
        try (OutputStream outputStream = new FileOutputStream(file)) {
            file.createNewFile();
            String content = "my name is 张三";
            byte[] contentByte = content.getBytes(StandardCharsets.UTF_8);
            outputStream.write(contentByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile() {
        File file = new File("D:" + File.separator + "user.txt");
        if (!file.exists()) {
            return;
        }
        File fileCopy = new File("D:" + File.separator + "user_copy.txt");
        if (fileCopy.exists()) {
            fileCopy.delete();
        }

        InputStream read = null;
        OutputStream write = null;
        try {
            read = new FileInputStream(file);
            write = new FileOutputStream(fileCopy);

            int haveContent = 0;
            while (haveContent != -1) {
                byte[] temp = new byte[1024];
                haveContent = read.read(temp);
                write.write(temp);
            }
            write.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (write != null) {
                try {
                    write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void copyFile2() {
        File file = new File("D:" + File.separator + "user.txt");
        if (!file.exists()) {
            return;
        }
        File fileCopy = new File("D:" + File.separator + "user_copy2.txt");
        if (fileCopy.exists()) {
            fileCopy.delete();
        }

        try (FileReader reader = new FileReader(file); FileWriter writer = new FileWriter(fileCopy)) {
            int len = 0;
            while (len != -1) {
                char[] content = new char[1];
                len = reader.read(content);
                writer.write(content);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
