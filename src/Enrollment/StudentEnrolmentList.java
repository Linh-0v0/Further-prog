package Enrollment;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentEnrolmentList implements StudentEnrolmentManager {

    private ArrayList<StudentEnrolment> enrollList;

    public StudentEnrolmentList() {
        enrollList = new ArrayList<StudentEnrolment>();
    }


    @Override
    public void addStudent(String sid, String name, String date, String cid, String cname, int credit_num,
                           String semester) {
        Student student = new Student(sid, name, date);
        Course course = new Course(cid, cname, credit_num);
        StudentEnrolment newEnrolment = new StudentEnrolment(student, course, semester);

        if (enrollList.contains(newEnrolment)) {
            System.out.println("The student has been enrolled!");
        } else {
            //New Enrollment is added to the Database
            enrollList.add(newEnrolment);
        }

    }

//    public void deleteStudent(Student s) {
//        student.remove(s);
//    }
//
//    public void enrollOneSem(int sid, String semester, String cname) {
//
//    }

    @Override
    public void updateEnrollOneSem(String idOrName, String semester, int option) {
        ArrayList<StudentEnrolment> filteredList = enrollListFilter(idOrName, semester);
        //Get student's info from the list
        String sid = filteredList.get(0).getStudent().getId();
        String sname = filteredList.get(0).getStudent().getName();
        String birthdate = filteredList.get(0).getStudent().getBirthday();

        // 1: add new course , 2: delete course
        if (option == 1) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter course id or name: ");
            String cidOrName = input.nextLine();
            //check if the course's id/name is in the school's course list
            for (Course c : courseList) {
                if (c.contains(cidOrName)) {
                    //get Course's info
                    String cid = c.getId();
                    String cname = c.getName();
                    Integer credit_num = c.getCredit_num();

                    Student student = new Student(sid, sname, birthdate);
                    Course course = new Course(cid, cname, credit_num);
                    enrollList.add(new StudentEnrolment(student, course, semester));
                    break;
                }
            }
        } else {

        }
    }

    public void printStudentsCourse(String idOrName, String semester) {
        ArrayList<StudentEnrolment> filteredList = enrollListFilter(idOrName, semester);
        //List all the courses of A Student in a Semester
        for (StudentEnrolment elm : filteredList) {
            System.out.println(elm);
        }
    }

    public ArrayList<StudentEnrolment> enrollListFilter(String idOrname, String semester) {
        ArrayList<StudentEnrolment> filteredList = new ArrayList<>();
        //filter out a list according to student's name/id and semester.
        for (StudentEnrolment elm : enrollList) {
            if ((elm.getStudent().getId() == idOrname
                    || elm.getStudent().getName() == idOrname)
                    && elm.getSemester() == semester) {
                filteredList.add(elm);
            }
        }
        return filteredList;
    }

    public void displayStudent() {
        System.out.println(student);
    }

    public void displayCourse() {
        System.out.println(course);
    }
}
