package App;
import java.util.Scanner;
import DataProcessing.ValidateInput;
import Models.StudentEnrolmentList;
import Utils.DataAvailableCheck;

public class StartProgram {

    final protected static StudentEnrolmentList enrollManager = new StudentEnrolmentList();
    final protected static ValidateInput inputValidate = new ValidateInput();
    final protected static DataAvailableCheck dataCheck = new DataAvailableCheck();


    public static void main(String[] args) {
        int[] twoChoices = {1, 2};
        int[] threeChoices = {1, 2, 3};
        Scanner input = new Scanner(System.in);

        System.out.println("Would you like to: ");
        System.out.println("1. Add and Update Enrolment. \n2. Print the Enrolment Data.");
        int action = inputValidate.validateIntInput(twoChoices, "1. Add and Update Enrolment. \n2. Print the Enrolment Data.");

        /* Add and Update Enrolment. */
        if (action == 1) {
            int opt = inputValidate.validateIntInput(twoChoices, "Enter 1 for New Enrollment or 2 for Update " +
                    "Enrollment: ");
            if (opt == 1) {
                System.out.println("***** Add new enrolment *****");
            } else {
                System.out.println("***** Update enrolment *****");
            }
            System.out.print("Enter student ID or Name: ");
            String sid = input.nextLine();
            sid = dataCheck.studentAvail(sid); //check if the student is already in the DB
            System.out.print("Enter course ID or Name: ");
            String cid = input.nextLine();
            cid = dataCheck.courseAvail(cid); //check if the course's in the DB
            System.out.print("Enter semester: ");
            String sem = input.nextLine();
            // 1: New Enrollment , 2(else): Update Enrollment
            if (opt == 1) {
                enrollManager.addEnrolment(sid, cid, sem);
            } else {
                //list all courses of a student in a semester
                enrollManager.printCoursesOfStudentSem(sid, sem);
                int option = inputValidate.validateIntInput(twoChoices, "Enter 1 for Add Courses " +
                        "\n Enter 2 for Delete Courses of a Student in a Semester \nYour Choice: ");
                enrollManager.updateEnrolment(sid, cid, sem, option);
            }

        /* Print the Enrolment Data. */
        } else {
            int yesNo = inputValidate.validateIntInput(twoChoices, "Would you like to see: \n 1: All courses for 1 student in 1" +
                    " " +
                    "semester? \n 2. All students of 1 course in 1 semester? \n 3. All courses offered in 1 semester? \n " + "1 is Yes, 2 is No. \nYour choice: ");
            if (yesNo == 1) {
                int option = inputValidate.validateIntInput(threeChoices, "Would you like to see: \n 1: All courses " +
                        "for the " +
                        "student in " +
                        "1 " +
                        "semester? \n 2. All students of 1 course in 1 semester? \n 3. All courses offered in 1 semester? \n " +
                        "Enter the option number: ");
                /* all courses for 1 student in 1 semester */
                if (option == 1) {
                    System.out.print("Enter student ID or Name: ");
                    String sid = input.nextLine();
                    System.out.print("Enter semester: ");
                    String sem = input.nextLine();
                    enrollManager.printCoursesOfStudentSem(sid, sem);
                /* all students of 1 course in 1 semester */
                } else if (option == 2) {
                    System.out.print("Enter course ID or Name: ");
                    String cid = input.nextLine();
                    System.out.print("Enter semester: ");
                    String sem = input.nextLine();
                    enrollManager.printStudentsOfCourseSem(cid, sem);
                /* all courses offered in 1 semester */
                } else {
                    System.out.print("Enter semester: ");
                    String sem = input.nextLine();
                    enrollManager.printCoursesOfSem(sem);
                }
            }
        }

        System.out.print("Would you like to save in a Csv report?");
        int choice = inputValidate.validateIntInput(twoChoices, "1 is Yes, 2 is No. \nYour choice: ");
        if (choice == 1) {

        }


//        String csvfile = "C:\\Users\\Admin\\IdeaProjects\\further-prog\\asm1-further-prog\\default.csv";
//        CsvHandle.readCSV(csvfile);

    }


}
