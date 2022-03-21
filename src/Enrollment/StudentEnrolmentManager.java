package Enrollment;

public interface StudentEnrolmentManager {
    public void addStudent(String sid, String name, String date, String cid, String cname, int credit_num,
                        String semester);

    public void deleteStudent();

    public void addCourse();

    public void deleteCourse();

    public void updateEnrollOneSem(Student s, String semester);

        public void getOne();

    public void getAll();

}
