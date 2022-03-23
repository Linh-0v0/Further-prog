package Enrollment;

import java.util.Arrays;
import java.util.Scanner;

public class StartProgram {

    final protected static StudentEnrolmentList enrollManager = new StudentEnrolmentList();

    public static void main(String[] args) {
        int[] twoChoices = {1, 2};
        int[] threeChoices = {1, 2, 3};
        Scanner input = new Scanner(System.in);

        System.out.println("Would you like to: ");
        System.out.println("1. Add and Update Enrolment. \n2. Print the Enrolment Data.");
        int action = validateIntInput(twoChoices, "1. Add and Update Enrolment. \n2. Print the Enrolment Data.");

        if (action == 1) {
            int opt = validateIntInput(twoChoices, "Enter 1 for New Enrollment or 2 for Update Enrollment: ");
            if (opt == 1) {
                System.out.println("***** Add new enrolment *****");
            } else {
                System.out.println("***** Update enrolment *****");
            }
            System.out.print("Enter student ID or Name: ");
            String sid = input.nextLine();
            System.out.print("Enter course ID or Name: ");
            String cid = input.nextLine();
            System.out.print("Enter semester: ");
            String sem = input.nextLine();
            // 1: New Enrollment , 2(else): Update Enrollment
            if (opt == 1) {
                enrollManager.addEnrolment(sid, cid, sem);
            } else {
                //list all courses of a student in a semester
                enrollManager.printCoursesOfStudentSem(sid, sem);
                int option = validateIntInput(twoChoices, "Enter 1 for Add Courses " +
                        "\n Enter 2 for Delete Courses of a Student in a Semester \nYour Choice: ");
                enrollManager.updateEnrolment(sid, cid, sem, option);
            }

        } else {
            int yesNo = validateIntInput(twoChoices, "Would you like to see: \n 1: All courses for 1 student in 1 " +
                    "semester? \n 2. All students of 1 course in 1 semester? \n 3. All courses offered in 1 semester? \n " + "1 is Yes, 2 is No. \nYour choice: ");
            if (yesNo == 1) {
                int option = validateIntInput(threeChoices, "Would you like to see: \n 1: All courses for the student in " +
                        "1 " +
                        "semester? \n 2. All students of 1 course in 1 semester? \n 3. All courses offered in 1 semester? \n " +
                        "Enter the option number: ");
                if (option == 1) {
                    System.out.print("Enter student ID or Name: ");
                    String sid = input.nextLine();
                    System.out.print("Enter semester: ");
                    String sem = input.nextLine();
                    enrollManager.printCoursesOfStudentSem(sid, sem);
                } else if (option == 2) {
                    System.out.print("Enter course ID or Name: ");
                    String cid = input.nextLine();
                    System.out.print("Enter semester: ");
                    String sem = input.nextLine();
                    enrollManager.printStudentsOfCourseSem(cid, sem);
                } else {
                    System.out.print("Enter semester: ");
                    String sem = input.nextLine();
                    enrollManager.printCoursesOfSem(sem);
                }
            }
        }

        System.out.print("Would you like to save in a Csv report?");
        int choice = validateIntInput(twoChoices, "1 is Yes, 2 is No. \nYour choice: ");
        if (choice == 1) {

        }


//        String csvfile = "C:\\Users\\Admin\\IdeaProjects\\further-prog\\asm1-further-prog\\default.csv";
//        CsvHandle.readCSV(csvfile);

    }

    public static int validateIntInput(int[] option, String msg) {
        int opt = 0;
        Scanner input = new Scanner(System.in);
        boolean catchE = false;
        do {
            System.out.print(msg);
            try {
                opt = input.nextInt();
                catchE = true;
            } catch (Exception e) {
                catchE = false;
                System.out.println("Invalid input. Please try again!\n");
                input.nextLine(); //stop looping infinitely and wait for input
            }

            if (!Arrays.asList(option).contains(opt)) {
                System.out.println("Invalid input! Please try again!\n");
            }
        } while (!Arrays.asList(option).contains(opt) || !catchE);
        return opt;
    }
}
