package com.example.study.desencoding;

public enum Demo {
    OK(0,"正常"){
        public void printDescription(){
            System.out.println("一切OK");
        }
    },
    ERR(1,"错误"){
        public void printDescription(){
            System.out.println("苦海无边,回头是岸");
        }
    },
    WARN(2,"警告"){
        public void printDescription(){
            System.out.println("我可警告过你啦");
        }
    };
    private int code;
    private String description;

    Demo(int code, String description) {
        this.code = code;
        this.description = description;
    }

    Demo() {
    }

    public abstract void printDescription();
}
