package Models;

import DataProcessing.CsvHandle;
import Interface.StudentEnrolmentManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class StudentEnrolmentList implements StudentEnrolmentManager {

    ArrayList<StudentEnrolment> enrollList = CsvHandle.readCSV();
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
        String credit_num = "";
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(sidOrName) || s.getName().equalsIgnoreCase(sidOrName)) {
                //Get studentInfo if the student is already in the database
                sid = s.getId();
                sname = s.getName();
                date = s.getBirthday();
                break;
            }
        }
        for (Course c : courseList) {
            if (c.getId().equalsIgnoreCase(cidOrName) || c.getName().equalsIgnoreCase(cidOrName)) {
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
            System.out.println("The student has already been enrolled!");
        } else {
            //New Enrollment is added to the Database
            enrollList.add(newEnrolment);
            String[] str = {student.getId(), student.getName(), student.getBirthday(), course.getId(),
                    course.getName(), course.getCredit_num(), semester};
            CsvHandle.addDeleteInCsv(str);
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
            if (s.getCourse().getId().equalsIgnoreCase(cidOrName) || s.getCourse().getName().equalsIgnoreCase(cidOrName)) {
                isEnrolled = true;
            }
        }
        // 1: add new course , 2: delete course
        if (option == 1) {
            if (isEnrolled == true) {
                System.out.println("The student is already enrolled in the course and this semester.");
            } else {
                addEnrolment(idOrName, cidOrName, semester);
            }
        } else {
            if (isEnrolled == false) {
                System.out.println("The student does not enroll in the course and this semester.");
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
        String credit_num = "";
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(sidOrName) || s.getName().equalsIgnoreCase(sidOrName)) {
                //Get studentInfo if the student is already in the database
                sid = s.getId();
                sname = s.getName();
                date = s.getBirthday();
                break;
            }
        }
        for (Course c : courseList) {
            if (c.getId().equalsIgnoreCase(cidOrName) || c.getName().equalsIgnoreCase(cidOrName)) {
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
        for (StudentEnrolment s : enrollList) {
            //somehow this does not work :((
            if (enrollList.contains(newEnrolment)) {
                enrollList.remove(s);
                String[] str = {student.getId(), student.getName(), student.getBirthday(), course.getId(),
                        course.getName(), course.getCredit_num(), semester};
                CsvHandle.addDeleteInCsv(str);
                System.out.println("The student has now left the course.");

                break;
            }
        }
    }

    @Override
    /* Get one enrolment */
    public ArrayList<StudentEnrolment> getOne(String sidOrName, String cidOrName, String semester) {
        ArrayList<StudentEnrolment> aStudentEnrollList = new ArrayList<>();
        for (StudentEnrolment s : enrollList) {
            if ((s.getStudent().getId().equalsIgnoreCase(sidOrName) || s.getStudent().getName().equalsIgnoreCase(sidOrName))
                    && (s.getCourse().getId().equalsIgnoreCase(cidOrName) || s.getCourse().getName().equalsIgnoreCase(cidOrName))
                    && s.getSemester().equals(semester)) {
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
            if ((s.getStudent().getId().equalsIgnoreCase(sidOrname)
                    || s.getStudent().getName().equalsIgnoreCase(sidOrname))
                    && s.getSemester().equals(semester)) {
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
            if ((s.getCourse().getId().equalsIgnoreCase(cidOrname) || s.getCourse().getName().equalsIgnoreCase(cidOrname))
                    && s.getSemester().equals(semester)) {
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
            if (s.getSemester().equals(semester)) {
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

    /* Filter out all semesters from Enrolment list */
    public HashSet<String> allSems() {
        HashSet<String> semList = new HashSet<>();
        for (StudentEnrolment s : enrollList) {
            semList.add(s.getSemester());
        }
        return semList;
    }

    /* Print ALL COURSES for 1 STUDENT in 1 SEMESTER */
    public void printCoursesOfStudentSem(String sidOrName, String semester, int saveOption) {
        String sid = "";
        String sname = "";
        ArrayList<StudentEnrolment> filteredList = studentSemFilter(sidOrName, semester);
        //Get student's info from the list
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(sidOrName) || s.getName().equalsIgnoreCase(sidOrName)) {
                sid = s.getId();
                sname = s.getName();
            }
        }
        // saveOption = 1:only printing
        if (saveOption == 1) {
            System.out.printf("* %s (%s)- Enrolled Courses:\n", sname, sid);
            if (filteredList.isEmpty()) {
                System.out.println("List is empty");
            }
            //List all the courses of A Student in a Semester
            for (StudentEnrolment s : filteredList) {
                System.out.println(s.getCourse().toString() + semester);
            }
            //saveOption = 2:Save to Csv file
        } else {
            ArrayList<String[]> data = new ArrayList<>();
            for (StudentEnrolment s : filteredList) {
                String[] metadata = {s.getCourse().getId(), s.getCourse().getName(), s.getCourse().getCredit_num()};
                data.add(metadata);
            }
            CsvHandle.writeToCsv(data);
        }
    }

    /* Print ALL STUDENTS of 1 COURSES in 1 SEMESTER */
    public void printStudentsOfCourseSem(String cidOrName, String semester, int saveOption) {
        ArrayList<StudentEnrolment> filteredList = courseSemFilter(cidOrName, semester);
        //Get course's info from the list
        String cid = filteredList.get(0).getCourse().getId();
        String cname = filteredList.get(0).getCourse().getName();

        // saveOption = 1:only printing
        if (saveOption == 1) {
            System.out.printf("* %s (%s) - Enrolled Students:\n", cid, cname);
            if (filteredList.isEmpty()) {
                System.out.println("List is empty");
            }
            //List all the students of A Course in a Semester
            for (StudentEnrolment s : filteredList) {
                System.out.println(s.getStudent().toString() + semester);
            }
            //saveOption = 2:Save to Csv file
        } else {
            ArrayList<String[]> data = new ArrayList<>();
            for (StudentEnrolment s : filteredList) {
                String[] metadata = {s.getStudent().getId(), s.getStudent().getName(),
                        s.getStudent().getBirthday()};
                data.add(metadata);
            }
            CsvHandle.writeToCsv(data);
        }
    }

    /* Print ALL COURSES in 1 SEMESTER */
    public void printCoursesOfSem(String semester, int saveOption) {
        ArrayList<StudentEnrolment> filteredList = semFilter(semester);
        // saveOption = 1:only printing
        if (saveOption == 1) {
            System.out.printf("* %s's Courses:\n", semester);
            if (filteredList.isEmpty()) {
                System.out.println("List is empty");
            }
            //List all the Courses of the Semester
            for (StudentEnrolment s : filteredList) {
                System.out.println(s.getCourse().toString() + semester);
            }
            //saveOption = 2:Save to Csv file
        } else {
            ArrayList<String[]> data = new ArrayList<>();
            for (StudentEnrolment s : filteredList) {
                String[] metadata = {s.getCourse().getId(), s.getCourse().getName(), s.getCourse().getCredit_num()};
                data.add(metadata);
            }
            CsvHandle.writeToCsv(data);
        }
    }
}
