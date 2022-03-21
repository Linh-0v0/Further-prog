package Enrollment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class StudentEnrolment implements StudentEnrolmentManager{
    private Student student;
    private Course course;
    private String semester;
    private ArrayList<ArrayList<Object>> enrollList;

    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


    @Override
    public void addStudent(String sid, String name, String date, String cid, String cname, int credit_num,
                           String semester) {
        Student student = new Student(sid, name, date);
        Course course = new Course(cid, cname, credit_num);
        setSemester(semester);

        ArrayList<Object> tempArr = new ArrayList<Object>();
        tempArr.addAll(Arrays.asList(student, course, semester));

        if (enrollList.contains(tempArr)) {
            System.out.println("The student has been enrolled!");
        } else {
            enrollList.add(new ArrayList<Object>(Arrays.asList(student, course, semester)));
        }

    }

//    public void deleteStudent(Student s) {
//        student.remove(s);
//    }
//
    public void addCourse(Course c) {
        course.add(c);
    }

    public void deleteCourse(Course c) {
        course.remove(c);
    }

//    public void enrollOneSem(int sid, String semester, String cname) {
//
//    }

    @Override
    public void updateEnrollOneSem(String sid, String sname, String semester){

        for (ArrayList<Object> elm: enrollList) {
            if ((elm.contains(sid) || elm.contains(sname)) && elm.contains(semester)) {
                System.out.println(elm);
            }
        }


    }

    public void displayStudent() {
        System.out.println(student);
    }

    public void displayCourse() {
        System.out.println(course);
    }
}
