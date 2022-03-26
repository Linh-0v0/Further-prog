package Models;

public class Course {
    private String id;
    private String name;
    private int credit_num;

    public Course(String id, String name, int credit_num) {
        this.id = id;
        this.name = name;
        this.credit_num = credit_num;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredit_num() {
        return credit_num;
    }

}
