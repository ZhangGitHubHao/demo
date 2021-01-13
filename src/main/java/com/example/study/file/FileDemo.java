package com.example.study.file;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) {
        FileDemo fileDemo = new FileDemo();

//        String creatFile1 = "D:" + File.separator + "file1.txt";
//        String creatFile2 = "D:" + File.separator + "file2.txt";
//        fileDemo.createFile(creatFile1);
//        fileDemo.createFile(creatFile2);
//        fileDemo.deleteFile(creatFile1);

        String dir = "D:"+File.separator+"Demo"+File.separator+"littleDemo";
//        fileDemo.createDirs(dir);
//        fileDemo.createFile(dir+File.separator+"test.txt");
//        fileDemo.createFile(dir+File.separator+"test2.txt");
//        fileDemo.createFile(dir+File.separator+"test3.txt");

        fileDemo.showDirFileList(dir);

    }

    private void createFile(String pathAndFileName) {
        File file = new File(pathAndFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile(String pathAndFileName) {
        File file = new File(pathAndFileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private void createDir(String dirPath){
        File file = new File(dirPath);
        file.mkdir();
    }

    private void createDirs(String dirPath){
        File file = new File(dirPath);
        file.mkdirs();
    }

    private void showDirFileList(String dir){
        File file = new File(dir);
//        String[] files = file.list();
        File[] files = file.listFiles();

        for (File fileName:files) {
            System.out.println(fileName.getPath());
        }
    }
}
