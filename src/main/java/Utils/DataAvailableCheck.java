package Utils;

import Models.Course;
import Models.Student;
import Models.StudentEnrolmentList;

import java.util.HashSet;
import java.util.Scanner;


public class DataAvailableCheck extends StudentEnrolmentList {
    /* Check if the student is in the Database*/
    public String studentAvail(String sidOrName) {
        String sid = "";
        String sname = "";
        String date = "";
        boolean enrolled = false;
        HashSet<Student> studentList = super.allStudents();
        Scanner input = new Scanner(System.in);
        do {
            for (Student s : studentList) {
                if (s.getId().equalsIgnoreCase(sidOrName) || s.getName().equalsIgnoreCase(sidOrName)) {
                    //Get studentInfo if the student is already in the database
                    sid = s.getId();
                    sname = s.getName();
                    System.out.printf("Student Found: %s - %s \n", sid, sname);
                    enrolled = true;
                    break;
                }
            }
            //loop to ask for input if course is not in the database
            if (enrolled == false) {
                System.out.println("Student is not in the database. Please enter again.");
                System.out.print("Enter student ID or Name: ");
                sid = input.nextLine();
            }
        } while (enrolled == false);
        return sid;

    }

    /* Check if the course is in the Database */
    public String courseAvail(String cidOrName) {
        String cid = "";
        boolean courseInDb = false;
        HashSet<Course> courseList = super.allCourses();
        Scanner input = new Scanner(System.in);
        do {
            for (Course c : courseList) {
                if (c.getId().equalsIgnoreCase(cidOrName) || c.getName().equalsIgnoreCase(cidOrName)) {
                    //If the course in the database
                    cid = c.getId();
                    courseInDb = true;
                    break;
                }
            }
            //loop to ask for input if course is not in the database
            if (courseInDb == false) {
                System.out.println("Course is not in the database. Please enter again.");
                System.out.print("Enter course ID or Name: ");
                cid = input.nextLine();
            }
        } while (courseInDb == false);
        return cid;
    }

    /* Check if the course is in the Database */
    public String semAvail(String semester) {
        String sem = "";
        boolean semInDb = false;
        HashSet<String> semList = super.allSems();
        Scanner input = new Scanner(System.in);
        do {
            for (String s : semList) {
                if (s.equalsIgnoreCase(semester)) {
                    //If the semester in the database
                    sem = semester;
                    semInDb = true;
                    break;
                }
            }
            //loop to ask for input if course is not in the database
            if (semInDb == false) {
                System.out.println("Semester is not in the database. Please enter again.");
                System.out.print("Enter semester: ");
                sem = input.nextLine();
            }
        } while (semInDb == false);
        return sem;
    }
}
