package Enrollment;

import java.util.Scanner;

public class EnrolmentRun {
    public static void main(String[] args) {
//        int opt = 0;
//        boolean catchE = false;
//        Scanner input = new Scanner(System.in);
//        do {
//            System.out.print("Enter 1 for New Enrollment or 2 for Update Enrollment: ");
//            try {
//                opt = input.nextInt();
//            } catch (Exception e) {
//                catchE = true;
//                System.out.println("Invalid input");
//            }
//        } while (catchE = true);
//
//        if (opt == 1) {
//            System.out.print("Enter student ID: ");
//            int sid = input.nextInt();
//            System.out.print("Enter semester: ");
//            String sem = input.nextLine();
//            System.out.print("Enter course ID: ");
//            String cid = input.nextLine();
//
//        } else {
//
//        }
        String csvfile = "C:\\Users\\Admin\\IdeaProjects\\further-prog\\asm1-further-prog\\default.csv";
        CsvHandle.readCSV(csvfile);

    }
}
