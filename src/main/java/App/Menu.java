package App;

import DataProcessing.ValidateInput;
import Models.StudentEnrolment;
import Models.StudentEnrolmentList;
import Utils.DataAvailableCheck;
import Utils.RegexCheck;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static String filePathRead;
    public static String filePathExport;

    static {
        filePathRead = readCsvStrPath();
    }

    public static String readCsvStrPath() {
        //Default Csv path for populating data
        return "C:\\Users\\Admin\\IdeaProjects\\further-prog\\asm1-student-enrolment\\CsvData\\default.csv";
    }

    public static String exportedCsvStrPath() {
        // Csv exported path
        return filePathExport + "\\CsvData\\data-exported.csv";
    }

    final protected static StudentEnrolmentList enrollManager = new StudentEnrolmentList();
    final protected static DataAvailableCheck dataCheck = new DataAvailableCheck();
    final protected static ValidateInput inputValidate = new ValidateInput();


    public static void menu() {
        int[] twoChoices = {1, 2};
        int[] threeChoices = {1, 2, 3};
        int optionPrint = 0;
        String sid = "";
        String cid = "";
        String sem = "";
        Scanner input = new Scanner(System.in);
        System.out.println("\nWould you like to populate data from our default csv file or your file?");
        int csvOpt = inputValidate.validateIntInput(twoChoices, "Enter 1 for 'default csv', 2 for 'your file': ");
        switch (csvOpt) {
            case 1 -> {
                System.out.println("Now reading the default Csv File :)\n");
            }
            case 2 -> {
                System.out.print("Paste your Csv file path here (e.g: D:\\directoryname\\testing\\abc.txt: ");
                filePathRead = input.nextLine();
                //check if file exist
                File f = new File(filePathRead);
                while (!RegexCheck.csvCheck(filePathRead) || !f.exists()) {
                    if (!RegexCheck.csvCheck(filePathRead)) {
                        System.out.println("This is not a CSV file :(");
                    } else if (!f.exists()) {
                        System.out.println("File path does not exist!");
                    }
                    System.out.print("Paste your Csv file path: ");
                    filePathRead = input.nextLine();
                    f = new File(filePathRead);
                }
                System.out.println("Received your CSV file :)");
            }
        }

        int action = inputValidate.validateIntInput(twoChoices, "Would you like to: \n1. Add and Update Enrolment. " +
                "\n2. Print the " +
                "Enrolment Data.\nYour choice: ");

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
                    //saveOption = 1: only printing
                    enrollManager.printCoursesOfStudentSem(sid, sem, 1);
                    int option = inputValidate.validateIntInput(twoChoices, "Enter 1 for Add Courses " +
                            "\n Enter 2 for Delete Courses of a Student in a Semester \nYour Choice: ");
                    enrollManager.updateEnrolment(sid, cid, sem, option);
                }
            }
            /* Print the Enrolment Data. */
        } else {
            optionPrint = inputValidate.validateIntInput(threeChoices, "\nWould you like to see: \n 1: All courses " +
                    "for the student in 1 semester? \n 2. All students of 1 course in 1 semester? \n 3. All courses " +
                    "offered in 1 " +
                    "semester? \nEnter the option number: ");
            /* all courses for 1 student in 1 semester */
            /* saveOption = 1: only printing */
            switch (optionPrint) {
                case 1:
                    System.out.print("Enter student ID or Name: ");
                    sid = input.nextLine();
                    System.out.print("Enter semester: ");
                    sem = input.nextLine();
                    enrollManager.printCoursesOfStudentSem(sid, sem, 1);
                    /* all students of 1 course in 1 semester */
                case 2:
                    System.out.print("Enter course ID or Name: ");
                    cid = input.nextLine();
                    System.out.print("Enter semester: ");
                    sem = input.nextLine();
                    enrollManager.printStudentsOfCourseSem(cid, sem, 1);
                    /* all courses offered in 1 semester */
                case 3:
                    ArrayList<StudentEnrolment> enrollList = enrollManager.getAll();
                    if (enrollList.isEmpty()) {
                        System.out.println("enrollList in Menu is empty");
                    } else {
                        System.out.println("enrollList in Menu is populated");
                    }
                    System.out.print("Enter semester: ");
                    sem = input.nextLine();
                    enrollManager.printCoursesOfSem(sem, 1);
            }
        }

        System.out.print("\nWould you like to save the above data in a CSV report?");
        int choice = inputValidate.validateIntInput(twoChoices, "\n1 is Yes, 2 is No. \nYour choice: ");
        if (choice == 1) {
            System.out.println("Exporting...");
            filePathExport = input.nextLine();
            filePathExport = exportedCsvStrPath();
            switch (optionPrint) {
                /* saveOption = 2: save to csv file*/
                case 1 -> enrollManager.printCoursesOfStudentSem(sid, sem, 2);
                case 2 -> enrollManager.printStudentsOfCourseSem(cid, sem, 2);
                case 3 -> enrollManager.printCoursesOfSem(sem, 2);
            }
            System.out.printf("Data has been saved to %s", exportedCsvStrPath());
        } else {
            endProgram(twoChoices);
        }
    }

    public static void endProgram(int[] twoChoices) {
        System.out.println("***************************************");
        System.out.println("Thanks for using the app!");
        int runChoice = inputValidate.validateIntInput(twoChoices, "Would you like to continue running the " +
                "app or exit?  \nEnter 1 for rerun, 2 for exit: ");
        switch (runChoice) {
            case 1 -> menu();
            case 2 -> System.out.println("----------------- End ----------------");
        }
    }
}
