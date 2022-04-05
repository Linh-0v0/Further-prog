package Interface;

import Models.StudentEnrolment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {
    /* Add new enrolment */
    void addEnrolment(String sidOrName, String cidOrName, String semester);

    /* Update enrolment of a student */
    void updateEnrolment(String idOrName, String cidOrName, String semester, int option);

    /* Delete enrolment of a student */
    void deleteEnrolment(String sidOrName, String cidOrName, String semester);

    /* Get enrolment of a student */
    ArrayList<StudentEnrolment> getOne(String sidOrName, String semester);

    /* Get all enrollment */
    ArrayList<StudentEnrolment> getAll();


}
