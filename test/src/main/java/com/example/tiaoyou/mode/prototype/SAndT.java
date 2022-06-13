package com.example.tiaoyou.mode.prototype;

/**
 * @author WangYH
 * @date 2022/6/10
 * <p>
 * 浅谈深拷贝和浅拷贝
 * 在调用super.clone方法之后，首先会检查当前对象所属的类是否支持clone，也就是看该类是否实现了Cloneable接口。
 * <p>
 * 如果支持，那么就先创建当前对象所属类的一个新对象，对该对象进行初始化，使得新对象的成员变量的值与当前对象的成员变量的值相同。
 * 那么问题来了，如果对于其它对象的引用以及list等类型的成员属性，则只能赋值这些对象的引用了。所以简单调用super.clone这种克隆对象
 * 的方式，就是一种浅拷贝，而不是深拷贝。
 *
 * 对于这种情况，我们要用深拷贝来解决，其实深拷贝就是基于浅拷贝来递归实现具体的每个对象
 *
 * 【使用场景】
 * 在一些重复创建对象的场景下，我们就可以使用原型模式来提高对象的创建性能
 * 例如：我们在循环体内创建对象时，我们可以使用clone的方式来实现
 *
 * 除此之外，原型模式在开源框架中的应用也非常广泛
 * 例如 Spring 中，@Service 默认都是单例的。用了私有全局变量，若不想影响下次注入或每次上下文获取 bean，
 * 就需要用到原型模式，我们可以通过以下注解来实现，@Scope(“prototype”)。
 */
class StudentTest implements Cloneable {
    private String name;
    private TeacherTest teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherTest getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherTest teacher) {
        this.teacher = teacher;
    }

    @Override
    protected Object clone() {
        StudentTest student = null;
        try {
            TeacherTest teacher = null;
            student = (StudentTest) super.clone();
            teacher = (TeacherTest) this.teacher.clone();
            student.setTeacher(teacher);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return student;
    }
}

class TeacherTest implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() {
        TeacherTest teacherTest = null;
        try {
            teacherTest = (TeacherTest) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return teacherTest;
    }
}

public class SAndT {
    public static void main(String[] args) {
        TeacherTest teacherTest = new TeacherTest();
        teacherTest.setName("王宇航");

        StudentTest studentTest = new StudentTest();
        studentTest.setName("学生1");
        studentTest.setTeacher(teacherTest);

        StudentTest studentTest2 = (StudentTest)studentTest.clone();
        studentTest2.setName("学生2");
        studentTest2.getTeacher().setName("王宇航2");

        System.out.println(" 学生 1:" + studentTest.getName()+studentTest.getTeacher().getName());
        System.out.println(" 学生 2:" + studentTest2.getName()+studentTest2.getTeacher().getName());

    }
}
