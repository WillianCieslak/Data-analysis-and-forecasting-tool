package courseworkcomp1555;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonCode {

    public final String appDir = System.getProperty("user.dir");
    public final String fileSeparator = System.getProperty("file.separator");

    // This reads a text file into an ArrayList of Strings.  The path to the
    // file has to be added.  Use appDir if the files are in the application 
    // directory.  Use fileSeperator if the app may be running under a 
    // different OS.
    public static ArrayList<String> readTextFile(String fileName) {
        ArrayList file = new ArrayList();
        String line;

        if ((fileName == null) || (fileName.equals(""))) {
            System.out.println("No file name specified.");
        } else {
            try {
                BufferedReader in = new BufferedReader(new FileReader(fileName));

                if (!in.ready()) {
                    throw new IOException();
                }

                while ((line = in.readLine()) != null) {
                    file.add(line);
                }
                in.close();
            } catch (IOException e) {
                System.out.println(e);
                file.add("File not found");
            }
        }
        return file;
    }
//      method that reads excel files. Libraries are used to make this happen. 

    public static ArrayList<String> readExcelFile(String fileName) throws IOException {

        ArrayList<String> file = new ArrayList<String>();
        try {

            FileInputStream fis = new FileInputStream(new File(fileName));

            //Create workbook instance that refers to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            //Create a sheet object to retrieve the sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            String line = "";
            for (Row row : sheet) {
                for (Cell cell : row) {

                    cell.setCellType(CellType.STRING);
                    line += cell + "\t";

                }
                file.add(line);
                line = "";
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        return file;

    }
//    FINDING the smallest element of an array

    public static double getMin(double[] array) {
        double min = 999999;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

//       FINDING the biggerst element of an array
    public static double getMax(double[] array) {
        double max = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
