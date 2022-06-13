package com.example.tiaoyou.mode.prototype;

/**
 * @author WangYH
 * @date 2022/6/10
 */
class CloneStudent implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CloneStudent clone() {
        CloneStudent cloneStudent = null;
        try {
            cloneStudent = (CloneStudent) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneStudent;
    }
}

public class StudentCloneTest {
    public static void main(String[] args) {
        CloneStudent cloneStudent = new CloneStudent();
        cloneStudent.setName("王勇");

        CloneStudent cloneStudent2 = cloneStudent.clone();
        cloneStudent2.setName("王勇2");

        System.out.println(" 学生 1:" + cloneStudent.getName());
        System.out.println(" 学生 2:" + cloneStudent2.getName());
    }
}