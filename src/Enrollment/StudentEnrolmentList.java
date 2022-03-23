package Enrollment;

import java.util.ArrayList;
import java.util.HashSet;

public class StudentEnrolmentList implements StudentEnrolmentManager {

    ArrayList<StudentEnrolment> enrollList;
    HashSet<Course> courseList = allCourses();

    @Override
    /* Add new enrolment */
    public void addEnrolment(String sidOrName, String cidOrName, String semester) {
        String sid = "";
        String sname = "";
        String date = "";
        String cid = "";
        String cname = "";
        int credit_num = 0;
        for (StudentEnrolment s : enrollList) {
            if (s.getStudent().getId() == sidOrName || s.getStudent().getName() == sidOrName) {
                //Get studentInfo
                sid = s.getStudent().getId();
                sname = s.getStudent().getName();
                date = s.getStudent().getBirthday();
                break;
            }
        }
        for (Course c : courseList) {
            if (c.getId() == cidOrName || c.getName() == cidOrName) {
                //Get courseInfo
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
        ArrayList<StudentEnrolment> filteredList = studentSemFilter(idOrName, semester);
        //Get student's info from the list
        String sid = filteredList.get(0).getStudent().getId();
        String sname = filteredList.get(0).getStudent().getName();
        String birthdate = filteredList.get(0).getStudent().getBirthday();
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
                //check if the course's id/name is in the school's course list
                for (Course c : courseList) {
                    if (c.getId() == cidOrName || c.getName() == cidOrName) {
                        //get Course's info
                        String cid = c.getId();
                        String cname = c.getName();
                        Integer credit_num = c.getCredit_num();

                        Student student = new Student(sid, sname, birthdate);
                        Course course = new Course(cid, cname, credit_num);
                        enrollList.add(new StudentEnrolment(student, course, semester));
                        System.out.println("The student is now added to the course.");
                        break;
                    }
                }
            }
        } else {
            if (isEnrolled == false) {
                System.out.println("The student does not have this course.");
            } else {
                //check if the course's id/name is in the school's course list
                for (Course c : courseList) {
                    if ((c.getId() == cidOrName || c.getName() == cidOrName)) {
                        //get Course's info
                        String cid = c.getId();
                        String cname = c.getName();
                        Integer credit_num = c.getCredit_num();

                        //remove if the enrolment's course matches the input course
                        enrollList.removeIf(s -> s.getCourse().getId() == cid);
                        System.out.println("The student is now removed from the course.");
                        break;
                    }
                }
            }
        }
    }

    @Override
    /* Delete enrolment of a student */
    public void deleteEnrolment(String sidOrName) {
        for (StudentEnrolment s : enrollList) {
            if (s.getStudent().getId() == sidOrName || s.getStudent().getName() == sidOrName) {
                enrollList.remove(s);
            }
        }
    }

    @Override
    public ArrayList<StudentEnrolment> getOne(String sidOrName) {
        ArrayList<StudentEnrolment> aStudentEnrollList = new ArrayList<>();
        for (StudentEnrolment s : enrollList) {
            if (s.getStudent().getId() == sidOrName || s.getStudent().getName() == sidOrName) {
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
    public ArrayList<Student> allStudents() {
        ArrayList<Student> studentList = new ArrayList<>();
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
