package courseworkcomp1555;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ReadFile extends CommonCode {

    private ArrayList<String> readText;
    public ArrayList<House> trainTown;
    public ArrayList<House> comparisonTown;
    public String path;
    public String[] data;

    //Calls contructor with pre-defined path for the file
    public ReadFile() throws IOException {
        trainTown = new ArrayList<>();
        comparisonTown = new ArrayList<>();
        path = appDir + fileSeparator + "datasheet" + fileSeparator + "traintown.txt";
        readFile();
    }

    //Method responsible for reading in txt and excel files
    public void readFile() {

        try {
            readText = new ArrayList<>();
            comparisonTown.clear();

            if (path.endsWith(".xlsx")) {
                readText = readExcelFile(path);
            } else if (path.endsWith(".txt")) {
                readText = readTextFile(path);
            } else {
                System.out.println("File not accepted");
            }

            if (path.contains("traintown.txt") || path.contains("traintown.xlsx")) {
                trainTown.clear();
            }

            System.out.println("\nHouses: ");
            for (int i = 0; i < readText.size(); i++) {
                System.out.println(readText.get(i));
            }

            if (!"File not found".equals(readText.get(0))) {

                //Splits the lines with tab, converts them in the apropriate data type
                //and creates a new object House with the indexes
                //House h = new House(tmp[0],tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],tmp[6],tmp[7],tmp[8]);
                for (String str : readText) {
                    String[] tmp = str.split("\t");

                    if (!tmp[0].isEmpty()) {
                        int id = Integer.parseInt(tmp[0]);
                        double price = Double.parseDouble(tmp[1]);
                        double numberBathrooms = Double.parseDouble(tmp[2]);
                        double area = Double.parseDouble(tmp[3]);
                        double livingSpace = Double.parseDouble(tmp[4]);
                        int numberGarages = Integer.parseInt(tmp[5]);
                        int numberRooms = Integer.parseInt(tmp[6]);
                        int numberBedrooms = Integer.parseInt(tmp[7]);
                        int age = Integer.parseInt(tmp[8]);

                        House h = new House(id, price, numberBathrooms, area, livingSpace, numberGarages,
                                numberRooms, numberBedrooms, age);

                        if (path.contains("traintown.txt") || path.contains("traintown.xlsx")) {
                            trainTown.add(h);
                        } else {
                            comparisonTown.add(h);
                        }
                    }
                }
                System.out.println("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "      Couldn't upload the file correctly. Check text file!" + "\n"
                    + "Please ensure that the input data sets are of equal length.", "InfoBox: " + "UPLOADING ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
