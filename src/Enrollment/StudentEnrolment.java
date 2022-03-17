package Enrollment;

import java.util.HashSet;

public class StudentEnrolment implements StudentEnrolmentManager{
    private HashSet<Student> studentList;
    private HashSet<Course> courseList;
    private String semester;

    public StudentEnrolment(String semester) {
        studentList = new HashSet<Student>();
        courseList = new HashSet<Course>();
        this.semester = semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void addStudent(Student s) {
        studentList.add(s);
    }

    public void deleteStudent(Student s) {
        studentList.remove(s);
    }

    public void addCourse(Course c) {
        courseList.add(c);
    }

    public void deleteCourse(Course c) {
        courseList.remove(c);
    }

    public void enrollForOneSem(int sid, String semester, String cname) {

    }

    public void displayStudent() {
        System.out.println(studentList);
    }

    public void displayCourse() {
        System.out.println(courseList);
    }
}
