package App;

import Utils.ValidateInput;
import Models.StudentEnrolmentList;
import Utils.DataAvailableCheck;
import Utils.RegexCheck;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Menu {
    public static String filePathRead;
    public static String filePathExport;

    static {
        filePathRead = readCsvStrPath();
        filePathExport = exportedCsvStrPath();
    }

    public static String readCsvStrPath() {
        //Default Csv path for populating data
        String file = new File("src/main/resources/CsvData/default.csv").getAbsolutePath();
        return file;
    }

    public static String exportedCsvStrPath() {
        // Csv exported path
        String file = new File("src/main/resources/CsvData/data-exported.csv").getAbsolutePath();
        return file;
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
                System.out.print("Paste your Csv file path here (e.g: D:\\directoryname\\testing\\abc.csv: ");
                filePathRead = input.nextLine();
                File f = new File(filePathRead);
                //check if file exist
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
        switch (action) {
            case 1 -> {
                int opt = inputValidate.validateIntInput(twoChoices, "Enter 1 for New Enrollment or 2 for Update " +
                        "Enrollment: ");
                // 1: New Enrollment , 2(else): Update Enrollment
                switch (opt) {
                    case 1 -> {
                        System.out.println("***** Add new enrolment *****");
                        System.out.print("Enter student ID or Name: ");
                        sid = input.nextLine();
                        sid = dataCheck.studentAvail(sid); //check if the student is already in the DB
                        sid = sid.toUpperCase();
                        System.out.print("Enter course ID or Name: ");
                        cid = input.nextLine();
                        cid = dataCheck.courseAvail(cid); //check if the course's in the DB
                        cid = cid.toUpperCase();
                        System.out.print("Enter semester: ");
                        sem = input.nextLine(); //checke if the semester's in the Db
                        sem = dataCheck.semAvail(sem);
                        sem = sem.toUpperCase();
                        enrollManager.addEnrolment(sid, cid, sem);
                        endProgram(twoChoices);
                        break;
                    }
                    case 2 -> {
                        System.out.println("***** Update enrolment *****");
                        int option = inputValidate.validateIntInput(twoChoices, " Enter 1 for Add Courses " +
                                "\n Enter 2 for Delete Courses of a Student in a Semester \nYour Choice: ");
                        System.out.print("Enter student ID or Name: ");
                        sid = input.nextLine();
                        sid = dataCheck.studentAvail(sid); //check if the student is already in the DB
                        sid = sid.toUpperCase();
                        System.out.print("Enter course ID or Name: ");
                        cid = input.nextLine();
                        cid = dataCheck.courseAvail(cid); //check if the course's in the DB
                        cid = cid.toUpperCase();
                        System.out.print("Enter semester: ");
                        sem = input.nextLine(); //checke if the semester's in the Db
                        sem = dataCheck.semAvail(sem);
                        sem = sem.toUpperCase();
                        //list all courses of a student in a semester
                        //saveOption = 1: only printing
                        enrollManager.printCoursesOfStudentSem(sid, sem, 1);
                        enrollManager.updateEnrolment(sid, cid, sem, option);
                        endProgram(twoChoices);
                        break;
                    }
                }
            }
            case 2 -> {
                optionPrint = inputValidate.validateIntInput(threeChoices, "\nWould you like to see: \n 1: All " +
                        "courses " +
                        "for the student in 1 semester? \n 2. All students of 1 course in 1 semester? \n 3. All " +
                        "courses " +
                        "offered in 1 " +
                        "semester? \nEnter the option number: ");
                /* saveOption = 1: only printing */
                switch (optionPrint) {
                    case 1:
                        /* case1: all courses for 1 student in 1 semester */
                        System.out.println("");
                        System.out.print("Enter student ID or Name: ");
                        sid = input.nextLine();
                        dataCheck.studentAvail(sid);
                        sid = sid.toUpperCase();
                        System.out.print("Enter semester: ");
                        sem = input.nextLine();
                        dataCheck.semAvail(sem);
                        sem = sem.toUpperCase();
                        enrollManager.printCoursesOfStudentSem(sid, sem, 1);
                        break;
                    case 2:
                        /* case 2: all students of 1 course in 1 semester */
                        System.out.print("Enter course ID or Name: ");
                        cid = input.nextLine();
                        dataCheck.courseAvail(cid);
                        cid = cid.toUpperCase();
                        System.out.print("Enter semester: ");
                        sem = input.nextLine();
                        dataCheck.semAvail(sem);
                        sem = sem.toUpperCase();
                        enrollManager.printStudentsOfCourseSem(cid, sem, 1);
                        break;
                    case 3:
                        /* case3: all courses offered in 1 semester */
                        System.out.print("Enter semester: ");
                        sem = input.nextLine();
                        dataCheck.semAvail(sem);
                        sem = sem.toUpperCase();
                        enrollManager.printCoursesOfSem(sem, 1);
                        break;
                }
            }
        }

        if (action == 2) {
            //action = 2: when user choose to Print data
            System.out.print("\nWould you like to save the above data in a CSV report?");
            int choice = inputValidate.validateIntInput(twoChoices, "\n1 is Yes, 2 is No. \nYour choice: ");
            if (choice == 1) {
                System.out.print("\nWould you like to save in the default path: 'resoucres/CsvData/data-exported' or " +
                        "your own Csv Path?");
                int csvChoice = inputValidate.validateIntInput(twoChoices, "\n1 is default path, 2 is your csv path. " +
                        "\nYour choice: ");
                switch (csvChoice) {
                    case 1 -> {
                    }
                    case 2 -> {
                        System.out.println("Paste the csv file path (no need to create the csv file) that you'd like " +
                                "to " +
                                "export to (e.g: D:\\directoryname\\testing\\abc.csv): ");
                        filePathExport = input.nextLine();
                        while (!RegexCheck.csvCheck(filePathExport)) {
                            if (!RegexCheck.csvCheck(filePathExport)) {
                                System.out.println("This is not a CSV file :(");
                            }
                            System.out.print("Paste your Csv file path: ");
                            filePathExport = input.nextLine();
                            System.out.println("Found your required path :)");
                        }
                    }
                }
                System.out.println("Exporting...");
                switch (optionPrint) {
                    /* saveOption = 2: save to csv file*/
                    case 1 -> enrollManager.printCoursesOfStudentSem(sid, sem, 2);
                    case 2 -> enrollManager.printStudentsOfCourseSem(cid, sem, 2);
                    case 3 -> enrollManager.printCoursesOfSem(sem, 2);
                }
                System.out.printf("Data has been saved to:\n  %s", exportedCsvStrPath());
                endProgram(twoChoices);
            } else {
                endProgram(twoChoices);
            }
        } else {
            //Add/Update data, and when printing list is empty
            ;
        }
    }

    public static void endProgram(int[] twoChoices) {
        System.out.println("***************************************");
        int runChoice = inputValidate.validateIntInput(twoChoices, "Would you like to continue running the " +
                "app or exit?  \nEnter 1 for rerun, 2 for exit: ");
        switch (runChoice) {
            case 1 -> menu();
            case 2 -> System.out.println("----------------- End ----------------");
        }
    }
}
