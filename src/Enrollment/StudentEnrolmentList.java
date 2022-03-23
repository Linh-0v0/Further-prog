package Enrollment;

import java.util.ArrayList;

public class StudentEnrolmentList implements StudentEnrolmentManager {

    private ArrayList<StudentEnrolment> enrollList;

    public StudentEnrolmentList() {
        enrollList = new ArrayList<StudentEnrolment>();
    }

    @Override
    /* Add new enrolment */
    public void addEnrolment(String sid, String name, String date, String cid, String cname, int credit_num, String semester) {
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

    @Override
    /* Update enrolment of a student */
    public void updateEnrolment(String idOrName, String cidOrName, String semester, int option) {
        ArrayList<StudentEnrolment> filteredList = enrollListFilter(idOrName, semester);
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
            if (isEnrolled == false) {
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

    public void printStudentsEnrolment(String sidOrName, String semester) {
        ArrayList<StudentEnrolment> filteredList = enrollListFilter(sidOrName, semester);
        //List all the courses of A Student in a Semester
        for (StudentEnrolment s : filteredList) {
            System.out.println(s);
        }
    }

    public ArrayList<StudentEnrolment> enrollListFilter(String sidOrname, String semester) {
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
}