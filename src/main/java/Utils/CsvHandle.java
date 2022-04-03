package Utils;

import App.Menu;
import Models.Course;
import Models.Student;
import Models.StudentEnrolment;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;

public class CsvHandle extends Menu{
    /* Read CSV line */
    /* Read and then return an enrollment list*/
    public static ArrayList<StudentEnrolment> readCSV() {
        ArrayList<StudentEnrolment> enrollList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filePathRead);
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

    public static void writeToCsv(ArrayList<String[]> data) {
        try {
            FileWriter outputFile = new FileWriter(filePathExport, true);
            CSVWriter writer = new CSVWriter(outputFile, CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            //add data to csv
            writer.writeAll(data);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    private static StudentEnrolment createEnrolment(String[] metadata) {
        String sid = metadata[0];
        String sname = metadata[1];
        String birthday = metadata[2];
        String cid = metadata[3];
        String cname = metadata[4];
        String credit_num = metadata[5];
        String semester = metadata[6];
        // return StudentEnrolment containing the metadata
        return new StudentEnrolment(new Student(sid, sname, birthday), new Course(cid, cname, credit_num), semester);
    }

}
