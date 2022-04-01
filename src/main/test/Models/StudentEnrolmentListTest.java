package Models;

import DataProcessing.CsvHandle;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class StudentEnrolmentListTest {

    ArrayList<StudentEnrolment> enrollList = CsvHandle.readCSV();

    @BeforeEach
    void setUp() {
        Student student = new Student("S101312", "Alex Mike", "10/13/1998");
        Course course = new Course("BUS2232", "Business Law", "3");
        StudentEnrolment newEnroll = new StudentEnrolment(student, course, "2020C");
        enrollList.add(newEnroll);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSize() {
        Assert.assertEquals(16, enrollList.size());
    }

    @Test
    void addEnrolment() {
        Student student = new Student("S654321", "vy", "11/9/2002");
        Course course = new Course("BUS1234", "Business", "4");
        StudentEnrolment newEnroll = new StudentEnrolment(student, course, "2021A");
        enrollList.add(newEnroll);
        Assert.assertEquals("Add Enrolment",17, enrollList.size());
    }

    @Test
    void deleteEnrolment() {
        Student student = new Student("S101312", "Alex Mike", "10/13/1998");
        Course course = new Course("BUS2232", "Business Law", "3");
        StudentEnrolment newEnroll = new StudentEnrolment(student, course, "2020C");
        for (StudentEnrolment s : enrollList) {
            if ((s.getStudent().getId() == student.getId()) && (s.getCourse().getId() == course.getId()) && (s.getSemester() == newEnroll.getSemester())) {
                enrollList.remove(s);
                break;
            }
        }
        Assert.assertEquals("Delete Enrolment", 15, enrollList.size());
    }

    @Test
    void getOne() {
        Student student = new Student("S101312", "Alex Mike", "10/13/1998");
        Course course = new Course("BUS2232", "Business Law", "3");
        String[] newEnroll = {student.getId(), student.getName(), student.getBirthday(), course.getId(),
                course.getName(), course.getCredit_num(), "2020C"};
        String[] returnEnroll = null;
        for (StudentEnrolment s : enrollList) {
            if ((s.getStudent().getId() == student.getId()) && (s.getCourse().getId() == course.getId()) && (s.getSemester() == "2020C")) {
                returnEnroll = new String[]{s.getStudent().getId(), s.getStudent().getName(), s.getStudent().getBirthday(),
                        s.getCourse().getId(), s.getCourse().getName(), s.getCourse().getCredit_num(), s.getSemester()};
                break;
            }
        }
        Assert.assertEquals("Get One Enrolment", newEnroll, returnEnroll);
    }

    @Test
    void getAll() {
        ArrayList<StudentEnrolment> returnList = new ArrayList<>();
        for (StudentEnrolment s : enrollList) {
            returnList.add(s);
        }
        Assert.assertEquals("Get All Enrolment", returnList, enrollList);
    }
}