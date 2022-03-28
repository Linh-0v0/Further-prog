package Models;

public class Student {
    private String id;
    private String name;
    private String birthday;

    public Student(String id, String name, String birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id +
                "', name='" + name +
                "', birthday='" + birthday +
                "'}";
    }
}
