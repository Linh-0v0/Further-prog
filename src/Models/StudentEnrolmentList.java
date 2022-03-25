package Models;

import Interface.StudentEnrolmentManager;
import Utils.*;

import java.util.ArrayList;
import java.util.HashSet;

public class StudentEnrolmentList implements StudentEnrolmentManager {

    ArrayList<StudentEnrolment> enrollList;
    HashSet<Student> studentList = allStudents();
    HashSet<Course> courseList = allCourses();

    @Override
    /* Add enrolment */
    public void addEnrolment(String sidOrName, String cidOrName, String semester) {
        String sid = "";
        String sname = "";
        String date = "";
        String cid = "";
        String cname = "";
        int credit_num = 0;
        for (Student s : studentList) {
            if (s.getId() == sidOrName || s.getName() == sidOrName) {
                //Get studentInfo if the student is already in the database
                sid = s.getId();
                sname = s.getName();
                date = s.getBirthday();
                break;
            }
        }
        for (Course c : courseList) {
            if (c.getId() == cidOrName || c.getName() == cidOrName) {
                //Get courseInfo if the course in the database
                cid = c.getId();
                cname = c.getName();
                credit_num = c.getCredit_num();
                break;
            }
        }
        Student student = new Student(sid, sname, date);
        Course course = new Course(cid, cname, credit_num);
        StudentEnrolment newEnrolment = new StudentEnrolment(student, course, semester);

        if (enrollList.contains(newEnrolment)) {
            System.out.println("The student has been enrolled!");
        } else {
            //New Enrollment is added to the Database
            enrollList.add(newEnrolment);
        }
    }

    @Override
    /* Update enrolment of a student */
    public void updateEnrolment(String idOrName, String cidOrName, String semester, int option) {
        //Get all the Course's Enrolments of The Student
        ArrayList<StudentEnrolment> filteredList = studentSemFilter(idOrName, semester);
        boolean isEnrolled = false;
        //check if the student is already added to the Course
        for (StudentEnrolment s : filteredList) {
            if (s.getCourse().getId() == cidOrName || s.getCourse().getName() == cidOrName) {
                isEnrolled = true;
            }
        }
        // 1: add new course , 2: delete course
        if (option == 1) {
            if (isEnrolled == true) {
                System.out.println("The student is already enrolled in the course.");
            } else {
                addEnrolment(idOrName, cidOrName, semester);
            }
        } else {
            if (isEnrolled == false) {
                System.out.println("The student does not enroll in the course.");
            } else {
                deleteEnrolment(idOrName, cidOrName, semester);
            }
        }
    }

    @Override
    /* Delete course of a student */
    public void deleteEnrolment(String sidOrName, String cidOrName, String semester) {
        String sid = "";
        String sname = "";
        String date = "";
        String cid = "";
        String cname = "";
        int credit_num = 0;
        for (Student s : studentList) {
            if (s.getId() == sidOrName || s.getName() == sidOrName) {
                //Get studentInfo if the student is already in the database
                sid = s.getId();
                sname = s.getName();
                date = s.getBirthday();
                break;
            }
        }
        for (Course c : courseList) {
            if (c.getId() == cidOrName || c.getName() == cidOrName) {
                //Get courseInfo if the course in the database
                cid = c.getId();
                cname = c.getName();
                credit_num = c.getCredit_num();
                break;
            }
        }
        Student student = new Student(sid, sname, date);
        Course course = new Course(cid, cname, credit_num);
        StudentEnrolment newEnrolment = new StudentEnrolment(student, course, semester);

        if (enrollList.contains(newEnrolment)) {
            for (StudentEnrolment s : enrollList) {
                if (s == newEnrolment) {
                    enrollList.remove(s);
                }
            }
            //Delete course
            System.out.println("The student has now left the course.");
        } else {
            System.out.println("The student does not enroll in the course.");
        }
    }

    @Override
    /* Get one enrolment */
    public ArrayList<StudentEnrolment> getOne(String sidOrName, String cidOrName, String semester) {
        ArrayList<StudentEnrolment> aStudentEnrollList = new ArrayList<>();
        for (StudentEnrolment s : enrollList) {
            if ((s.getStudent().getId() == sidOrName || s.getStudent().getName() == sidOrName)
                && (s.getCourse().getId() == cidOrName || s.getCourse().getName() == cidOrName)
                && s.getSemester() == semester) {
                aStudentEnrollList.add(s);
            }
        }
        return aStudentEnrollList;
    }

    /* Get all enrollment */
    @Override
    public ArrayList<StudentEnrolment> getAll() {
        return enrollList;
    }

    /*** ADDITIONAL METHODS ***/
    /* Filter out an enrollment list of the REQUIRED STUDENT and SEMESTER*/
    public ArrayList<StudentEnrolment> studentSemFilter(String sidOrname, String semester) {
        ArrayList<StudentEnrolment> filteredList = new ArrayList<>();
        //filter out a list according to student's name/id and semester.
        for (StudentEnrolment s : enrollList) {
            if ((s.getStudent().getId() == sidOrname
                    || s.getStudent().getName() == sidOrname)
                    && s.getSemester() == semester) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    /* Filter out an enrollment list of the REQUIRED COURSES and SEMESTER*/
    public ArrayList<StudentEnrolment> courseSemFilter(String cidOrname, String semester) {
        ArrayList<StudentEnrolment> filteredList = new ArrayList<>();
        //filter out a list according to student's name/id and semester.
        for (StudentEnrolment s : enrollList) {
            if ((s.getCourse().getId() == cidOrname || s.getCourse().getName() == cidOrname)
                    && s.getSemester() == semester) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    /* Filter out an enrollment list of the REQUIRED SEMESTER */
    public ArrayList<StudentEnrolment> semFilter(String semester) {
        ArrayList<StudentEnrolment> filteredList = new ArrayList<>();
        //filter out a list according to student's name/id and semester.
        for (StudentEnrolment s : enrollList) {
            if (s.getSemester() == semester) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    /* Filter out all COURSES from Enrolment list */
    public HashSet<Course> allCourses() {
        HashSet<Course> courseList = new HashSet<>();
        //add course from 'enrollList' to 'courseList'
        for (StudentEnrolment s : enrollList) {
            courseList.add(s.getCourse());
        }
        return courseList;
    }

    /* Filter out all STUDENTS from Enrolment list */
    public HashSet<Student> allStudents() {
        HashSet<Student> studentList = new HashSet<>();
        for (StudentEnrolment s : enrollList) {
            studentList.add(s.getStudent());
        }
        return studentList;
    }

    /* Print ALL COURSES for 1 STUDENT in 1 SEMESTER */
    public void printCoursesOfStudentSem(String sidOrName, String semester) {
        ArrayList<StudentEnrolment> filteredList = studentSemFilter(sidOrName, semester);
        //Get student's info from the list
        String sid = filteredList.get(0).getStudent().getId();
        String sname = filteredList.get(0).getStudent().getName();
        System.out.printf("%s (%s) - Enrolled Courses:", sname, sid);
        //List all the courses of A Student in a Semester
        for (StudentEnrolment s : filteredList) {
            System.out.println(s.getCourse());
        }
    }

    /* Print ALL STUDENTS of 1 COURSES in 1 SEMESTER */
    public void printStudentsOfCourseSem(String cidOrName, String semester) {
        ArrayList<StudentEnrolment> filteredList = courseSemFilter(cidOrName, semester);
        //Get course's info from the list
        String cid = filteredList.get(0).getCourse().getId();
        String cname = filteredList.get(0).getCourse().getName();
        System.out.printf("%s (%s) - Enrolled Students:", cid, cname);
        //List all the students of A Course in a Semester
        for (StudentEnrolment s : filteredList) {
            System.out.println(s.getStudent());
        }
    }

    /* Print ALL COURSES in 1 SEMESTER */
    public void printCoursesOfSem(String semester) {
        ArrayList<StudentEnrolment> filteredList = semFilter(semester);
        System.out.printf("%s 's Courses:", semester);
        //List all the Courses of the Semester
        for (StudentEnrolment s : filteredList) {
            System.out.println(s.getCourse());
        }
    }
}
