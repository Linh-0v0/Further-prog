package Enrollment;

import java.util.Date;
import java.util.HashSet;

public class Student {
    private int id;
    private String name;
    private Date birthday;
    private HashSet<Course> courseList;

    public Student(int id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        courseList = new HashSet<Course>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void addCourse(Course c) {
        courseList.add(c);
    }
}
