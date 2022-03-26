package DataProcessing;

import App.Menu;
import Models.Course;
import Models.Student;
import Models.StudentEnrolment;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;

public class CsvHandle extends Menu {

    // To get file name/ path from the user input in Menu
    public CsvHandle(String filePath) {
        super(filePath);
    }

    /* Read CSV line */
    /* Read and then return an enrollment list*/
    public static ArrayList<StudentEnrolment> readCSV() {
        ArrayList<StudentEnrolment> enrollList = new ArrayList<>();
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            // read the first line from text file
            String line = br.readLine();
            // loop until all lines are read
            while (line != null) {
                String[] attributes = line.split(",");
                StudentEnrolment enrolment = createEnrolment(attributes);
                //adding enrolment to ArrayList
                enrollList.add(enrolment);
                // read next line before looping // if end of file reached, line would be null
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return enrollList;
    }

    public static void writeToCsv(String[] metadata) {
        File file = new File(filePath);
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            //add data to csv
            writer.writeNext(metadata);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static Student createStudent(String[] metadata) {
       String id = metadata[0];
       String name = metadata[1];
       String birthday = metadata[2];
       // return Student containing the metadata
        return new Student(id, name, birthday);
    }

    private static Course createCourse(String[] metadata) {
        String id = metadata[3];
        String name = metadata[4];
        String credit_num = metadata[5];
        // return Course containing the metadata
        return new Course(id, name, credit_num);
    }

    private static StudentEnrolment createEnrolment(String[] metadata) {
        Student student = createStudent(metadata);
        Course course = createCourse(metadata);
        String semester = metadata[6];
        // return StudentEnrolment containing the metadata
        return new StudentEnrolment(student, course, semester);
    }
}
