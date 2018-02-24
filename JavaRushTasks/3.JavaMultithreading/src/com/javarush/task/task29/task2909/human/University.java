package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student student = null;

        for (Student i : students) {
            if (i.getAverageGrade() == averageGrade) {
                student = i;
                break;
            }
        }

        return student;
    }

    public Student getStudentWithMaxAverageGrade() {
        if(students.size() == 0)
            return null;

        Student student = students.get(0);

        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).getAverageGrade() > student.getAverageGrade())
                student = students.get(i);
        }

        return student;
    }

    public Student getStudentWithMinAverageGrade(){
        if(students.size() == 0)
            return null;

        Student student = students.get(0);

        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).getAverageGrade() < student.getAverageGrade())
                student = students.get(i);
        }

        return student;
    }

    public void expel(Student student){
        students.remove(student);
    }
}