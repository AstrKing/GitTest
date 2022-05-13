package com.example.demo.annotation;

public enum DIYEnum {
    xiaoJie("小杰","打代码"),
    TEACHER("老师","教书"),
    CHEF("厨师","做饭");

    private String name;
    private String worker;

    DIYEnum(String name,String worker) {
        this.name = name;
        this.worker = worker;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
