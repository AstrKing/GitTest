package com.example.demo.annotation;

public class Test {
    public static void main(String[] args) {
        Student student =new Student();
        Chef chef = new Chef() ;
        Teacher teacher = new Teacher();
        Person person = DIYAnnotationUtils.getPerson(student, chef, teacher);
        if (person != null){
            person.hobby();
        }
    }
}
