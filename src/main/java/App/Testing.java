package App;

import DataProcessing.CsvHandle;
import Models.StudentEnrolment;
import Models.StudentEnrolmentList;
import Utils.RegexCheck;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/* JUST FILE TO TEST SOME FUNCTIONS*/
public class Testing {
//    static String filePath;
final protected static StudentEnrolmentList enrollManager = new StudentEnrolmentList();

    public static void main(String[] args) {
        ArrayList<StudentEnrolment> enrollList = enrollManager.getAll();
        if (enrollList.isEmpty()) {
            System.out.println("enrollList in Menu is empty");
        } else {
            System.out.println("enrollList in Menu is populated");
        }
    }
}
