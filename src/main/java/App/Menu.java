package App;

import DataProcessing.CsvHandle;
import DataProcessing.ValidateInput;
import Models.StudentEnrolmentList;
import Utils.DataAvailableCheck;
import java.util.Scanner;

public class Menu {
    public static String filePath;

    public Menu(String filePath) {
        this.filePath= filePath;
    }

    public static String fileToString() {
        // Csv file exported path
        return filePath + "CsvData/data-exported.csv";
    }

    final protected static StudentEnrolmentList enrollManager = new StudentEnrolmentList();
    final protected static ValidateInput inputValidate = new ValidateInput();
    final protected static DataAvailableCheck dataCheck = new DataAvailableCheck();

    public static void menu() {
        int[] twoChoices = {1, 2};
        int[] threeChoices = {1, 2, 3};
        int optionPrint = 0;
        String sid = "";
        String cid = "";
        String sem = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to populate data from our default csv file or your file?");
        int csvOpt = inputValidate.validateIntInput(twoChoices, "Enter 1 for 'default csv', 2 for 'your file': ");
        switch (csvOpt) {
            case 1:
            case 2:
                System.out.println();
        }

        System.out.println("Would you like to: ");
        System.out.println("1. Add and Update Enrolment. \n2. Print the Enrolment Data.");
        int action = inputValidate.validateIntInput(twoChoices, "1. Add and Update Enrolment. \n2. Print the " +
                "Enrolment Data.");

        /* Add and Update Enrolment. */
        if (action == 1) {
            int opt = inputValidate.validateIntInput(twoChoices, "Enter 1 for New Enrollment or 2 for Update " +
                    "Enrollment: ");
            switch (opt) {
                case 1 -> System.out.println("***** Add new enrolment *****");
                case 2 -> System.out.println("***** Update enrolment *****");
            }
            System.out.print("Enter student ID or Name: ");
            sid = input.nextLine();
            sid = dataCheck.studentAvail(sid); //check if the student is already in the DB
            System.out.print("Enter course ID or Name: ");
            cid = input.nextLine();
            cid = dataCheck.courseAvail(cid); //check if the course's in the DB
            System.out.print("Enter semester: ");
            sem = input.nextLine(); //checke if the semester's in the Db
            sem = dataCheck.semAvail(sem);
            // 1: New Enrollment , 2(else): Update Enrollment
            switch (opt) {
                case 1 -> enrollManager.addEnrolment(sid, cid, sem);
                case 2 -> {
                    //list all courses of a student in a semester
                    //saveOption = 0: only printing
                    enrollManager.printCoursesOfStudentSem(sid, sem, 0);
                    int option = inputValidate.validateIntInput(twoChoices, "Enter 1 for Add Courses " +
                            "\n Enter 2 for Delete Courses of a Student in a Semester \nYour Choice: ");
                    enrollManager.updateEnrolment(sid, cid, sem, option);
                }
            }
            /* Print the Enrolment Data. */
        } else {
            int yesNo = inputValidate.validateIntInput(twoChoices, "Would you like to see: \n 1: All courses for 1 " +
                    "student in 1" +
                    " " +
                    "semester? \n 2. All students of 1 course in 1 semester? \n 3. All courses offered in 1 semester?" +
                    " \n " + "1 is Yes, 2 is No. \nYour choice: ");
            if (yesNo == 1) {
                optionPrint = inputValidate.validateIntInput(threeChoices, "Would you like to see: \n 1: All courses " +
                        "for the " +
                        "student in " +
                        "1 " +
                        "semester? \n 2. All students of 1 course in 1 semester? \n 3. All courses offered in 1 " +
                        "semester? \n " +
                        "Enter the option number: ");
                /* all courses for 1 student in 1 semester */
                /* saveOption = 0: only printing */
                if (optionPrint == 1) {
                    System.out.print("Enter student ID or Name: ");
                    sid = input.nextLine();
                    System.out.print("Enter semester: ");
                    sem = input.nextLine();
                    enrollManager.printCoursesOfStudentSem(sid, sem, 0);
                    /* all students of 1 course in 1 semester */
                } else if (optionPrint == 2) {
                    System.out.print("Enter course ID or Name: ");
                    cid = input.nextLine();
                    System.out.print("Enter semester: ");
                    sem = input.nextLine();
                    enrollManager.printStudentsOfCourseSem(cid, sem, 0);
                    /* all courses offered in 1 semester */
                } else {
                    System.out.print("Enter semester: ");
                    sem = input.nextLine();
                    enrollManager.printCoursesOfSem(sem, 0);
                }
            } else {
                endProgram(twoChoices);
            }
        }

        System.out.print("Would you like to save the above data in a CSV report?");
        int choice = inputValidate.validateIntInput(twoChoices, "1 is Yes, 2 is No. \nYour choice: ");
        if (choice == 1) {
            System.out.println("Where would you like to save CSV File?");
            System.out.print("Paste the directory path here: ");
            filePath = input.nextLine();
            filePath = fileToString();
            switch (optionPrint) {
                /* saveOption = 1: save to csv file*/
                case 1 -> enrollManager.printCoursesOfStudentSem(sid, sem, 1);
                case 2 -> enrollManager.printStudentsOfCourseSem(cid, sem, 1);
                case 3 -> enrollManager.printCoursesOfSem(sem, 1);
            }
            System.out.println("Data has been saved to the designated CSV File.");
        } else {
            endProgram(twoChoices);
        }
    }

    public static void endProgram(int[] twoChoices) {
        System.out.print("***************************************");
        System.out.println("Thanks for using the app.");
        int runChoice = inputValidate.validateIntInput(twoChoices, "Would you like to continue running the " +
                "app or exit?  \nEnter 1 for rerun, 2 for exit: ");
        switch (runChoice) {
            case 1 -> Menu.menu();
            case 2 -> System.out.println("----------------- End ----------------");
        }
    }
}
