package Interface;

import Models.StudentEnrolment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {
    /* Add new enrolment */
    public void addEnrolment(String sidOrName, String cidOrName, String semester);

    /* Update enrolment of a student */
    public void updateEnrolment(String idOrName, String cidOrName, String semester, int option);

    /* Delete enrolment of a student */
    public void deleteEnrolment(String sidOrName, String cidOrName, String semester);

    /* Get enrolment of a student */
    public ArrayList<StudentEnrolment> getOne(String sidOrName, String cidOrName, String semester);

    /* Get all enrollment */
    public ArrayList<StudentEnrolment> getAll();


}
