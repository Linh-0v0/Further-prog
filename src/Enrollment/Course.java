package Enrollment;

import java.util.HashSet;

public class Course {
    private int id;
    private String name;
    private double credit_num;
    private HashSet<Student> studentList;

    public Course(int id, String name, double credit_num) {
        this.id = id;
        this.name = name;
        this.credit_num = credit_num;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCredit_num() {
        return credit_num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit_num(double credit_num) {
        this.credit_num = credit_num;
    }

    public void addStudent(Student s) {
        studentList.add(s);
    }
}
