package Enrollment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {
    /* Add new enrolment */
    public void addEnrolment(String sid, String name, String date, String cid, String cname, int credit_num, String semester);

    /* Update enrolment of a student */
    public void updateEnrolment(String idOrName, String cidOrName, String semester, int option);

    /* Delete enrolment of a student */
    public void deleteEnrolment(String sidOrName);

    /* Get enrolment of a student */
    public ArrayList<StudentEnrolment> getOne(String sidOrName);

    /* Get all enrollment */
    public ArrayList<StudentEnrolment> getAll();


}
