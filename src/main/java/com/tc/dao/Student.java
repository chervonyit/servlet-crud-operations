package com.tc.dao;

public class Student {

    private String name;
    private int age;
    private int school_id;

    public Student(String name, int age, int school_id) {
        this.name = name;
        this.age = age;
        this.school_id = school_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSchoolId() {
        return school_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school_id +
                '}';
    }

    public void setSchoolId(int school_id) {
        this.school_id = school_id;
    }
}
