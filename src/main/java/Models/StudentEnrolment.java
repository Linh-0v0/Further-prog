package Models;

public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }


    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public String[] toStringArr() {
        return new String[] {student.getId(), student.getName(), student.getBirthday(), course.getId(),
                course.getName(), course.getCredit_num(), semester};
    }
}
