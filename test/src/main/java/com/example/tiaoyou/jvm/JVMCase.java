package com.example.tiaoyou.jvm;

/**
 * @author WangYH
 * @date 2022/6/9
 */
public class JVMCase {
    // 常量，被final修饰了，同理我们之前string里面学到的也是，这个就是直接被加入到常量池里面
    public final static String MAN_SEX_TYPE = "man";
    // 静态变量
    public static String WOMAN_SEX_TYPE = "woman";

    public static void main(String[] args) {
        Student stu = new Student();
        stu.setName("nick");
        stu.setSexType(MAN_SEX_TYPE);
        stu.setAge(20);

        JVMCase jvmCase = new JVMCase();
        // 调用静态方法
        print(stu);
        //  调用非静态方法
        jvmCase.sayHello(stu);
    }

    // 常规静态方法
    public static void print(Student stu) {
        System.out.println(stu.getName() + stu.getSexType());
    }

    // 非静态方法
    public void sayHello(Student stu) {
        System.out.println(stu.getName() + "say:hello");
    }
}

class Student {
    String name;
    String sexType;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
