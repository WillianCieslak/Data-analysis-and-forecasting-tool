/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
    Work done by:
        Eugen Mihali - 000962781
        Florin Oprea -001003678
        Ricardo Paiva - 001000118
        Willian Cieslak - 000990795
*/


package courseworkcomp1555;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

// COMMENTED
public class CourseworkComp1555 extends JFrame implements ActionListener, KeyListener {

    JFileChooser fc;
    static ReadFile ts; // this allows the class WriteData to write on the same arrays that are created ln ReadFile class and instanseated here.
    CommonCode cd;
    MathOperations mo;
    GraphApplication ga;
    String[] pickVar = {"No of Bathrooms", "Area of the Site", "Size of living space", "No of Garages", "No of Rooms", "No of Bedrooms", "Age of property "};
    String[] pickTowns = {"", "Upload Town A", "Upload Town B", "Upload Town C"};
    JMenuItem uploadData;
    JPanel table1 = new JPanel(new GridBagLayout());
    JPanel table2;
    JPanel graph2;
    JPanel graph2All;
    JPanel graph1;
    JPanel table2West = new JPanel(new GridBagLayout());
    JPanel table2East = new JPanel(new GridBagLayout());
    JPanel predictPanel;
    JButton correlation = new JButton();
    JList correlationLabel;
    DefaultListModel listModel;
    JButton predict = new JButton();
    JComboBox pickTown = new JComboBox(pickTowns);
    JComboBox pickVariable = new JComboBox(pickVar);
    public int selectedVariable = pickVariable.getSelectedIndex();
    public String selectedVariableTxt = pickVariable.getSelectedItem().toString();
    public static double[] variableValues;
    public static double[] YValues;
    public double[] vvComparison;
    public double[] yvComparison;
    ChartPanel panelGraph;
    ChartPanel panelGraph2;
    JTable XiYtable;
    JTable calculationTable;
    JTable tableC;
    JTable tableD;
    JTable tableE;
    JScrollPane js;
    JScrollPane js1;
    JScrollPane js2;
    JScrollPane js3;
    JScrollPane js4;
    GridBagConstraints cons;
    JFrame frame;
    private JTextField predictTextField;
    private JLabel resultLabel;
    private double inputX, predictedY;

    public final String appDir = System.getProperty("user.dir");
    public final String fileSeparator = System.getProperty("file.separator");

    //for the graph
    private static final long serialVersionUID = 1L;
    //Variables go here
    private XYSeriesCollection dataTraining, dataComparison;
    public JFreeChart chartTraining;
    public JFreeChart chartComparison;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        CourseworkComp1555 run = new CourseworkComp1555();
    }
    private Font Arial;

    public CourseworkComp1555() throws IOException {
        cd = new CommonCode();
        ts = new ReadFile();
        fc = new JFileChooser(cd.appDir + cd.fileSeparator + "datasheet");
        mo = new MathOperations();
        ga = new GraphApplication();
        view();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("variablePick".equals(e.getActionCommand())) {

            // When the variable is changed on the blue combo box, all the tables get deleted, than they get created using the data of the variable chosen 
            selectedVariable = pickVariable.getSelectedIndex();
            table2.remove(js);
            table2.remove(js1);
            table2.remove(js2);
            table2.remove(js3);
            table1.remove(js4);

            XiYtable = createXiYTable(selectedVariable);
            calculationTable = createCalculationTable(selectedVariable);
            tableC = createTableC(selectedVariable);
            tableD = createTableD(selectedVariable);
            tableE = createTableE(selectedVariable);

            XiYtable.setPreferredScrollableViewportSize(new Dimension(300, 50));
            XiYtable.setFillsViewportHeight(true);
            js = new JScrollPane(XiYtable);
            js.setVisible(true);
            cons.insets = new Insets(10, 10, 10, 10);
            cons.gridheight = 3;
            cons.gridwidth = 1;
            cons.weightx = 1.0;
            cons.weighty = 1.0;
            cons.gridx = 0;
            cons.gridy = 0;
            js.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Table A:Input data(X,Y)", TitledBorder.CENTER,
                    TitledBorder.TOP));
            table2.add(js, cons);

            calculationTable.setPreferredScrollableViewportSize(new Dimension(400, 80));
            calculationTable.setFillsViewportHeight(true);
            js1 = new JScrollPane(calculationTable);
            js1.setVisible(true);
            cons.insets = new Insets(10, 10, 5, 10);
            cons.gridheight = 1;
            cons.gridwidth = 1;
            cons.weightx = 1.0;
            cons.weighty = 1.0;
            cons.gridx = 1;
            cons.gridy = 0;
            js1.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Table B: Data Summary(i)", TitledBorder.CENTER,
                    TitledBorder.TOP));
            table2.add(js1, cons);

            tableC.setPreferredScrollableViewportSize(new Dimension(250, 80));
            tableC.setFillsViewportHeight(true);
            js2 = new JScrollPane(tableC);
            js2.setVisible(true);
            cons.insets = new Insets(5, 10, 10, 10);
            cons.gridheight = 1;
            cons.gridwidth = 1;
            cons.weightx = 1.0;
            cons.weighty = 1.0;
            cons.gridx = 1;
            cons.gridy = 1;
            js2.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Table C: Data Summary(ii)", TitledBorder.CENTER,
                    TitledBorder.TOP));
            table2.add(js2, cons);

            tableD.setPreferredScrollableViewportSize(new Dimension(200, 16));
            tableD.setFillsViewportHeight(true);
            js3 = new JScrollPane(tableD);
            js3.setVisible(true);
            cons.insets = new Insets(5, 10, 5, 10);
            cons.gridheight = 1;
            cons.gridwidth = 1;
            cons.weightx = 1.0;
            cons.weighty = 1.0;
            cons.gridx = 1;
            cons.gridy = 2;
            js3.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), " Table D:Liner Regresssion Analysis Results", TitledBorder.CENTER,
                    TitledBorder.TOP));
            table2.add(js3, cons);

            tableE.setPreferredScrollableViewportSize(new Dimension(350, 50));
            tableE.setFillsViewportHeight(true);
            js4 = new JScrollPane(tableE);
            js4.setVisible(true);
            cons.insets = new Insets(10, 10, 10, 10);
            cons.gridheight = 1;
            cons.gridwidth = 1;
            cons.weightx = 1.0;
            cons.weighty = 1.0;
            cons.gridx = 2;
            cons.gridy = 1;
            js4.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Table E:Forecasted Values", TitledBorder.CENTER,
                    TitledBorder.TOP));
            table1.add(js4, cons);

            //Updating the graph after the new variable is chosen 
            updateCharts();
            updateComparisonChart();

//           Refresh the frame so the changes get visible
            frame.revalidate();

        }
        if ("writeData".equals(e.getActionCommand())) {
//          When the button Write Data from the menu is clicked than a new window opens. Than because an instance of this class is created on the Write Data class ,it opens another window. In this case this frame is disposed.   
            WriteData writeWindow = null;
            try {
                writeWindow = new WriteData();

            } catch (IOException ex) {
                Logger.getLogger(CourseworkComp1555.class.getName()).log(Level.SEVERE, null, ex);
            }
            writeWindow.smallFrame.setVisible(true);
            frame.dispose();     //disposing the present frame because it dublicates.

        }
        if ("correlationButton".equals(e.getActionCommand())) {
//       Button to display a list variables based on the correlation
            double[] correlation = new double[7];                     // an array that will save the R of all 7 variables. this array will be sorted later
            double[] tempX = new double[ts.trainTown.size()];        // temporary array that will hold the values of 1 variable , just to do the calculations 
            String[] variables = {"No of Bathrooms", "Area of the Site", "Size of living space", "No of Garages", "No of Rooms", "No of Bedrooms", "Age of property "};
            for (int i = 0; i < 7; i++) {
                tempX = createXiArray(i, ts.trainTown);              // this method creates an array with the values of one of the variables from the data set
                correlation[i] = mo.R(tempX, createYArray(ts.trainTown)); // finding the correlation and saving it on the array
            }
//            Using bubble sorting to sort the array. Meanwhile the array holding the correlation gets sorted, changes apply on the array that holds the variables names so it keeps track of the changes. 
            for (int i = 0; i < correlation.length; i++) {
                for (int j = 0; j < correlation.length - 1; j++) {
                    if (correlation[j] > correlation[j + 1]) {
                        double tmp = correlation[j];
                        String tmpname = variables[j];

                        correlation[j] = correlation[j + 1];
                        variables[j] = variables[j + 1];

                        correlation[j + 1] = tmp;
                        variables[j + 1] = tmpname;
                    }
                }
            }
            // After sorting the array their elements get displayed on the JList designed for this purpose 
            DecimalFormat df2 = new DecimalFormat("0.#####");                   // populating the JList, with 2 decimal point doubles
            if (listModel.isEmpty() && vvComparison.length != 0) {              // population happens only if the list is empty and the data are imported
                listModel.addElement(variables[6] + " : " + df2.format(correlation[6]));
                listModel.addElement(variables[5] + " : " + df2.format(correlation[5]));
                listModel.addElement(variables[4] + " : " + df2.format(correlation[4]));
                listModel.addElement(variables[3] + " : " + df2.format(correlation[3]));
                listModel.addElement(variables[2] + " : " + df2.format(correlation[2]));
                listModel.addElement(variables[1] + " : " + df2.format(correlation[1]));
                listModel.addElement(variables[0] + " : " + df2.format(correlation[0]));
            }

            if (vvComparison.length == 0) {
//                in case there were no comparison data uploaded an error box would show up. 
                JOptionPane.showMessageDialog(null, "Upload comparison data then click the button again to populate the graph and the table", "InfoBox: " + "DATA NOT FOUND", JOptionPane.INFORMATION_MESSAGE);
            }

            frame.revalidate();
        }
        if ("predictButton".equals(e.getActionCommand())) {
//            to do a prediction a value need to be entered in the textfield , that value is taken, calculations are done and its displayed on the graph

            updateTrainingChart();
            try {
                if (Double.parseDouble(predictTextField.getText()) < 0) {
                    throw new NumberFormatException();
                }
                inputX = Double.parseDouble(predictTextField.getText());
            } catch (NumberFormatException ea) {
                JOptionPane.showMessageDialog(null, "Enter a number", "InfoBox: " + "Input Error", JOptionPane.INFORMATION_MESSAGE);
            }

            predictedY = mo.singleForecastedY(variableValues, YValues, inputX);
            dataTraining.addSeries(GraphApplication.createXIPrediction(inputX, predictedY));
            resultLabel.setText("Y = " + String.format("%.4f", predictedY));
            predictTextField.setText("");
        }
        if ("pickTown".equals(e.getActionCommand())) {

//            this is a shortcut to upload comparison towns, when a town is selected the path of the upload is changed and the method that reads the file is called. 
            if (pickTown.getSelectedItem() == "Upload Town A") {
                ts.path = appDir + fileSeparator + "datasheet" + fileSeparator + "townA.txt";;
                ts.readFile();
                JOptionPane.showMessageDialog(null, "Town A was uploaded Correctly", "InfoBox: " + "UPLOAD SUCCESSFULL", JOptionPane.INFORMATION_MESSAGE);
// updating the chart and tables after the upload is done
                updateCharts();
                updateComparisonChart();
                updateTableE();

            } else if (pickTown.getSelectedItem() == "Upload Town B") {
                ts.path = appDir + fileSeparator + "datasheet" + fileSeparator + "townB.txt";
                ts.readFile();
                JOptionPane.showMessageDialog(null, "Town B was uploaded Correctly", "InfoBox: " + "UPLOAD SUCCESSFULL", JOptionPane.INFORMATION_MESSAGE);

                updateCharts();
                updateComparisonChart();
                updateTableE();

            } else if (pickTown.getSelectedItem() == "Upload Town C") {
                ts.path = appDir + fileSeparator + "datasheet" + fileSeparator + "townC.txt";
                ts.readFile();
                JOptionPane.showMessageDialog(null, "Town C was uploaded Correctly", "InfoBox: " + "UPLOAD SUCCESSFULL", JOptionPane.INFORMATION_MESSAGE);

                updateCharts();
                updateComparisonChart();
                updateTableE();

            } else {
                updateCharts();
                updateTableE();
            }

        }
        if ("UploadOther".equals(e.getActionCommand())) {
//            opens a dialog with the file manager, a window that allows file uploads.
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnVal = fc.showOpenDialog(uploadData);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this directory: "
                        + fc.getSelectedFile().getAbsolutePath());
            }
//          the path changes to the one chosen and the read file method is called
            ts.path = fc.getSelectedFile().getAbsolutePath();
            ts.readFile();
//          updating the charts
            updateCharts();
            updateComparisonChart();

        }
        if ("uploadData".equals(e.getActionCommand())) {
            //            opens a dialog with the file manager, a window that allows file uploads.
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnVal = fc.showOpenDialog(uploadData);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this directory: "
                        + fc.getSelectedFile().getAbsolutePath());
            }
//          the path changes to the one chosen and the read file method is called
            ts.path = fc.getSelectedFile().getAbsolutePath();
            ts.readFile();
//          updating the charts
            updateCharts();
            updateComparisonChart();
        }

        if ("info".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(null, "---Change the variable on the BLUE BOX to analyse the training data. \n"
                    + "---Upload a town to compare. \n"
                    + "---Predict by filling up the text field and press the “Predict” button. \n"
                    + "---Find the correlation by pressing the “Show the best Correlation”\n"
                    + "---Get a closer look by clicking a dragging the graph on the right. ", "InfoBox: " + "Information Box", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped not coded yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed not coded yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased not coded yet.");
    }

    private void view() {
        Font fnt = new Font("George", Font.PLAIN, 24);
        cons = new GridBagConstraints();
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.fill = GridBagConstraints.BOTH;

        frame = new JFrame();
        frame.setLayout(new GridBagLayout());

        table1 = new JPanel(new GridBagLayout());
        graph2All = new JPanel(new GridBagLayout());
        // graph1 = new JPanel(new FlowLayout());
        predictPanel = new JPanel(new GridBagLayout());
        table2 = new JPanel(new GridBagLayout());
        //graph2 = new JPanel(new FlowLayout());

        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 0;
        cons.gridy = 0;
        frame.add(table1, cons);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 0;
        cons.gridy = 0;
        graph2All.add(table2, cons);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 1;
        cons.gridy = 0;
        graph2All.add(predictPanel, cons);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 0;
        cons.gridy = 1;
        frame.add(graph2All, cons);

        //this is the menuBar
        Color menuColor = new Color(158, 116, 135);
        JMenuBar menuBar = new JMenuBar();
        JMenu data = new JMenu();

        JMenuItem writeData = new JMenuItem();
        uploadData = new JMenuItem();
        data = new JMenu("Data");
        data.setOpaque(true); // for the color
        data.setBackground(menuColor);
        data.setToolTipText("Insert");
        data.setFont(fnt);
        writeData.setText("Write Data");
        writeData.addActionListener(this);
        writeData.setActionCommand("writeData");
        writeData.setFont(fnt);
        writeData.setToolTipText("Use this to type in the data");
        uploadData.setText("Upload");
        uploadData.addActionListener(this);
        uploadData.setActionCommand("uploadData");
        uploadData.setFont(fnt);
        uploadData.setToolTipText("Upload data from a file");
        data.add(writeData);
        data.addSeparator();
        data.add(uploadData);

        JMenu info = new JMenu("INFO");
        info.setOpaque(true); // for the color
        info.setBackground(menuColor);
        info.setFont(fnt);
        JMenuItem programInfo = new JMenuItem();
        programInfo.setText("Get Information");
        programInfo.setFont(fnt);
        programInfo.addActionListener(this);
        programInfo.setActionCommand("info");
        info.add(programInfo);

        menuBar.add(data);
        menuBar.add(info);
        frame.setJMenuBar(menuBar);

        //the comparison graph 
        createArrayForXi(selectedVariable, ts.trainTown);
        createArrayForY(ts.trainTown);
        dataTraining = GraphApplication.createSeriesAndCollection(variableValues, YValues);
        chartTraining = ChartFactory.createXYLineChart("House price compared to" + selectedVariableTxt, "Xi", "House price(thousands of £)", dataTraining, PlotOrientation.VERTICAL, true, true, false);
        GraphApplication.createPlot(chartTraining);
        panelGraph = new ChartPanel(chartTraining);
        setContentPane(panelGraph);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 1;
        cons.gridy = 1;
        frame.add(panelGraph, cons);

        //the data proting graph
        dataComparison = GraphApplication.createSeriesAndCollection(variableValues, YValues);
        chartComparison = ChartFactory.createXYLineChart("House price compared to" + selectedVariableTxt, "Xi", "House price(thousands of £)", dataComparison, PlotOrientation.VERTICAL, true, true, false);
        GraphApplication.createPlot(chartComparison);
        panelGraph2 = new ChartPanel(chartComparison);
        setContentPane(panelGraph2);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 1;
        cons.gridy = 0;
        frame.add(panelGraph2, cons);

        //a panel that holds a textbox and a combobox, useful to do prediction. 
        // A label showing what the box will be used for
        JLabel predictionLabel = new JLabel();
        predictionLabel.setText("Enter X to predict Y");
        predictionLabel.setToolTipText("Insert Xi and get Y prediction");
        cons.insets = new Insets(80, 50, 10, 10);
        cons.gridx = 0;
        cons.gridy = 2;
        predictPanel.add(predictionLabel, cons);
        //the textfield and combobox created and inserted inside the panel.
        //THE COMBOBOX
        JLabel cbLabel = new JLabel();
        cbLabel.setText("VARIABLE LIST");
        cbLabel.setToolTipText("The heart of the program, change the variables to change the tables and graphs");
        cons.insets = new Insets(0, 50, 0, 10);
        cons.gridx = 0;
        cons.gridy = 0;
        predictPanel.add(cbLabel, cons);

        pickVariable.addActionListener(this);
        pickVariable.setActionCommand("variablePick");
        pickVariable.setBackground(new Color(119, 127, 236));
        pickVariable.setPreferredSize(new Dimension(50, 25));
        cons.insets = new Insets(0, 50, 10, 10);
        cons.gridx = 0;
        cons.gridy = 1;
        predictPanel.add(pickVariable, cons);
        predictTextField = new JTextField();
        predictTextField.setPreferredSize(new Dimension(100, 25));
        cons.insets = new Insets(2, 50, 10, 10);
        cons.gridx = 0;
        cons.gridy = 3;
        predictPanel.add(predictTextField, cons);
        resultLabel = new JLabel();
        resultLabel.setText("Y=");
        resultLabel.setToolTipText("The value of Y when Xi is entered");
        cons.insets = new Insets(10, 50, 200, 10);
        cons.gridx = 0;
        cons.gridy = 5;
        predictPanel.add(resultLabel, cons);
        //using the predict button
        predict.setText("Predict");
        predict.addActionListener(this);
        predict.setActionCommand("predictButton");
        predict.setPreferredSize(new Dimension(100, 25));
        cons.insets = new Insets(2, 50, 5, 10);
        //cons.fill = GridBagConstraints.BOTH;
        cons.gridx = 0;
        cons.gridy = 4;
        predictPanel.add(predict, cons);

        //The panel to hold the tables of the comparison.
        //this panel holds the upload button and combobox
        JPanel uploadPanelNorth = new JPanel(new GridBagLayout());
        cons.gridheight = 1;
        cons.gridwidth = 3;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 0;
        cons.gridy = 0;
        table1.add(uploadPanelNorth, cons);
        // The combobox holds 3 towns
        pickTown.setPreferredSize(new Dimension(50, 25));
        cons.insets = new Insets(10, 100, 10, 10);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 0.5;
        cons.weighty = 0.5;
        cons.gridx = 0;
        cons.gridy = 0;
        pickTown.addActionListener(this);
        pickTown.setActionCommand("pickTown");
        pickTown.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Select a Town to COMPARE", TitledBorder.CENTER,
                TitledBorder.TOP));
        uploadPanelNorth.add(pickTown, cons);

        //Button to upload other file rather than the towns on the combobox
        JButton uploadOther = new JButton();
        uploadOther.setPreferredSize(new Dimension(50, 25));
        uploadOther.setText("Upload Other");
        uploadOther.setToolTipText("Upload another file to compare");
        uploadOther.addActionListener(this);
        uploadOther.setActionCommand("UploadOther");
        uploadOther.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Choose your own Town", TitledBorder.CENTER,
                TitledBorder.TOP));
        cons.insets = new Insets(10, 10, 10, 100);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 0.5;
        cons.weighty = 0.5;
        cons.gridx = 1;
        cons.gridy = 0;

        uploadPanelNorth.add(uploadOther, cons);

        //button to find the best correlation between variables.     
        //correlation button is global
        correlation.setPreferredSize(new Dimension(50, 25));
        correlation.setText("Show the BEST correlation");
        correlation.setToolTipText("The coorrelation list");
        correlation.addActionListener(this);
        correlation.setActionCommand("correlationButton");
        correlation.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Press to display CORRELATION", TitledBorder.CENTER,
                TitledBorder.TOP));
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 0;
        cons.gridy = 1;
        table1.add(correlation, cons);

        //information Label
        JLabel compareDesc = new JLabel();
        compareDesc.setText("Press 'SHOW THE BEST CORRELATION' than change the variable from the blue Box to change the tables and Graph");
        compareDesc.setFont(new Font("Arial", Font.PLAIN, 16));
        compareDesc.setPreferredSize(new Dimension(150, 100));
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridheight = 1;
        cons.gridwidth = 3;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 0;
        cons.gridy = 2;
        table1.add(compareDesc, cons);

        //label to display the list of variables based on the correlation
        listModel = new DefaultListModel();
        correlationLabel = new JList(listModel);
        correlationLabel.setPreferredSize(new Dimension(50, 30));
        correlationLabel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        correlationLabel.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        correlationLabel.setVisibleRowCount(-1);
        correlationLabel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "List of CORRELATION values", TitledBorder.CENTER,
                TitledBorder.TOP));
        JScrollPane listScroller = new JScrollPane(correlationLabel);
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 1;
        cons.gridy = 1;
        table1.add(listScroller, cons);

        //forecast table
        tableE = createTableE(selectedVariable);
        tableE.setPreferredScrollableViewportSize(new Dimension(350, 50));
        tableE.setFillsViewportHeight(true);
        js4 = new JScrollPane(tableE);
        js4.setVisible(true);
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 2;
        cons.gridy = 1;
        table1.add(js4, cons);
        js4.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Table E:Forecasted Values", TitledBorder.CENTER,
                TitledBorder.TOP));

//   JPanel table2 = new JPanel();
        XiYtable = createXiYTable(selectedVariable);
        XiYtable.setPreferredScrollableViewportSize(new Dimension(300, 50));
        XiYtable.setFillsViewportHeight(true);
        js = new JScrollPane(XiYtable);
        js.setVisible(true);
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridheight = 3;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 0;
        cons.gridy = 0;
        js.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Table A:Input data(X,Y)", TitledBorder.CENTER,
                TitledBorder.TOP));
        table2.add(js, cons);

        calculationTable = createCalculationTable(selectedVariable);
        calculationTable.setPreferredScrollableViewportSize(new Dimension(400, 80));
        calculationTable.setFillsViewportHeight(true);
        js1 = new JScrollPane(calculationTable);
        js1.setVisible(true);
        cons.insets = new Insets(10, 10, 5, 10);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 1;
        cons.gridy = 0;
        js1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Table B: Data Summary(i)", TitledBorder.CENTER,
                TitledBorder.TOP));
        table2.add(js1, cons);

        tableC = createTableC(selectedVariable);
        tableC.setPreferredScrollableViewportSize(new Dimension(250, 80));
        tableC.setFillsViewportHeight(true);
        js2 = new JScrollPane(tableC);
        js2.setVisible(true);
        cons.insets = new Insets(5, 10, 10, 10);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 1;
        cons.gridy = 1;
        js2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Table C: Data Summary(ii)", TitledBorder.CENTER,
                TitledBorder.TOP));
        table2.add(js2, cons);

        tableD = createTableD(selectedVariable);
        tableD.setPreferredScrollableViewportSize(new Dimension(200, 16));
        tableD.setFillsViewportHeight(true);
        js3 = new JScrollPane(tableD);
        js3.setVisible(true);
        cons.insets = new Insets(5, 10, 5, 10);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 1;
        cons.gridy = 2;
        js3.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), " Table D:Liner Regresssion Analysis Results", TitledBorder.CENTER,
                TitledBorder.TOP));
        table2.add(js3, cons);

        frame.getContentPane().setBackground(new Color(138, 146, 158));
        uploadPanelNorth.setBackground(new Color(118, 129, 144));
        graph2All.setBackground(new Color(118, 129, 144));
        table1.setBackground(new Color(118, 129, 144));
        table2.setBackground(new Color(118, 129, 144));
        predictPanel.setBackground(new Color(118, 129, 144));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

//  method to create table A  it returns a table and gets as parameter the variables selected on the combobox. 
    public JTable createXiYTable(int selectedVariable) {
        String[] columns = {"Xi", "Y"};
        String[][] data = new String[ts.trainTown.size()][2];
        createArrayForXi(selectedVariable, ts.trainTown);
        createArrayForY(ts.trainTown);

        for (int i = 0; i < ts.trainTown.size(); i++) {
            data[i][0] = Double.toString(variableValues[i]);
        }
        for (int i = 0; i < ts.trainTown.size(); i++) {
            data[i][1] = Double.toString(YValues[i]);
        }
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable tableXiY = new JTable(model);

        return tableXiY;
    }

    //  method to create table B  it returns a table and gets as parameter the variable selected on the combobox. 
    public JTable createCalculationTable(int selectedVariable) {
        String[] columns = {"  ", "Xi", "Y"};
        String[][] data = new String[4][3];
        createArrayForXi(selectedVariable, ts.trainTown);
        data[0][0] = "N";
        data[1][0] = "Mean";
        data[2][0] = "Variance";
        data[3][0] = "St Dev";

//        calculation methods called from the mathOperations class. 
        data[0][1] = variableValues.length + "";
        data[1][1] = Double.parseDouble(String.format("%.5f", mo.mean(variableValues))) + "";
        data[2][1] = Double.parseDouble(String.format("%.5f", mo.Variance(variableValues))) + "";
        data[3][1] = Double.parseDouble(String.format("%.5f", mo.StandardDeviation(variableValues))) + "";

        data[0][2] = YValues.length + "";
        data[1][2] = Double.parseDouble(String.format("%.5f", mo.mean(YValues))) + "";
        data[2][2] = Double.parseDouble(String.format("%.5f", mo.Variance(YValues))) + "";
        data[3][2] = Double.parseDouble(String.format("%.5f", mo.StandardDeviation(YValues))) + "";

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable calculationTable = new JTable(model);
        return calculationTable;
    }

    //  method to create table C  it returns a table and gets as parameter the variable selected on the combobox. 
    public JTable createTableC(int selectedVariable) {
        double sumX = 0;
        double sumX2 = 0;
        double sumY = 0;
        double sumY2 = 0;
        double sumXY = 0;

        //calculations done for sumX and sumX2
        createArrayForXi(selectedVariable, ts.trainTown);
        for (int i = 0; i < variableValues.length; i++) {
            sumX = sumX + variableValues[i];
            sumX2 = sumX2 + (variableValues[i] * variableValues[i]);
        }
        for (int i = 0; i < YValues.length; i++) {
            sumY = sumY + YValues[i];
            sumY2 = sumY2 + (YValues[i] * YValues[i]);
            sumXY = sumXY + (YValues[i] * variableValues[i]);
        }
        String[] columns = {"Labels", "Data"};
        String[][] data = new String[5][2];

        data[0][0] = "sumX";
        data[1][0] = "sumX2";
        data[2][0] = "sumY";
        data[3][0] = "sumY2";
        data[4][0] = "sumXY";

//        calculation methods called from the mathOperations class. 
        data[0][1] = Double.parseDouble(String.format("%.5f", sumX)) + "";
        data[1][1] = Double.parseDouble(String.format("%.5f", sumX2)) + "";
        data[2][1] = Double.parseDouble(String.format("%.5f", sumY)) + "";
        data[3][1] = Double.parseDouble(String.format("%.5f", sumY2)) + "";
        data[4][1] = Double.parseDouble(String.format("%.5f", sumXY)) + "";

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable tableC = new JTable(model);
        return tableC;
    }
//  method to create table D  it returns a table and gets as parameter the variable selected on the combobox. 

    public JTable createTableD(int selectedVariable) {
        String[] columns = {"R", "R2", "Slope", "Y intercept"};
        String[][] data = new String[1][4];

//        calculation methods called from the mathOperations class. 
        data[0][0] = Double.parseDouble(String.format("%.5f", mo.R(variableValues, YValues))) + "";
        data[0][1] = Double.parseDouble(String.format("%.5f", (mo.R(variableValues, YValues) * (mo.R(variableValues, YValues))))) + "";
        data[0][2] = Double.parseDouble(String.format("%.5f", mo.Slope(variableValues, YValues))) + "";
        data[0][3] = Double.parseDouble(String.format("%.5f", mo.yIntercept(variableValues, YValues))) + "";

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable tableD = new JTable(model);
        return tableD;
    }

//  method to create table D  it returns a table and gets as parameter the variable selected on the combobox. 
    public JTable createTableE(int selectedVariable) {
        String[] columns = {"Xi", "Y", "Forecasted Y", "Std.Err. of Estimate"};
        String[][] data = new String[ts.comparisonTown.size()][4];
        createXiForComparison(selectedVariable, ts.comparisonTown);
        createYforComparison(ts.comparisonTown);

//        finding the standard error for each of the values of x comparison
        double[] standardError = mo.StandardErrorOfEstimate(vvComparison, yvComparison);

//        filling up the table with values coming from maths operations
        for (int i = 0; i < ts.comparisonTown.size(); i++) {
            data[i][0] = Double.toString(vvComparison[i]);
            data[i][1] = Double.toString(yvComparison[i]);
            data[i][2] = Double.parseDouble(String.format("%.5f", mo.singleForecastedY(vvComparison, yvComparison, vvComparison[i]))) + "";
            data[i][3] = Double.parseDouble(String.format("%.5f", standardError[i])) + "";

        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable tableE = new JTable(model);
        return tableE;
    }

    //method that creates an array with all the elements of a chosen Xi coming from training data, it will be used to create tables and to do calculations.
    public void createArrayForXi(int selectedVariable, ArrayList<House> list) {
        if (variableValues != null) {
            variableValues = null;
        }
        variableValues = new double[list.size()];
        switch (selectedVariable) {
            case 0:
                for (int i = 0; i < list.size(); i++) {
                    variableValues[i] = list.get(i).getNumberBathrooms();
                }
                break;
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    variableValues[i] = list.get(i).getArea();
                }
                break;
            case 2:
                for (int i = 0; i < list.size(); i++) {
                    variableValues[i] = list.get(i).getLivingSpace();
                }
                break;
            case 3:
                for (int i = 0; i < list.size(); i++) {
                    variableValues[i] = list.get(i).getNumberGarages();
                }
                break;
            case 4:
                for (int i = 0; i < list.size(); i++) {
                    variableValues[i] = list.get(i).getNumberRooms();
                }
                break;
            case 5:
                for (int i = 0; i < list.size(); i++) {
                    variableValues[i] = list.get(i).getNumberBedrooms();
                }
                break;
            case 6:
                for (int i = 0; i < list.size(); i++) {
                    variableValues[i] = list.get(i).getAge();
                }
                break;

        }
    }

//            method that creates an array with all the elements of a chosen Xi coming from comparison data, it will be used to create tables and to do calculations.
    public void createXiForComparison(int selectedVariable, ArrayList<House> list) {
        if (vvComparison != null) {
            vvComparison = null;
        }
        vvComparison = new double[list.size()];
        switch (selectedVariable) {
            case 0:
                for (int i = 0; i < list.size(); i++) {
                    vvComparison[i] = list.get(i).getNumberBathrooms();
                }
                break;
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    vvComparison[i] = list.get(i).getArea();
                }
                break;
            case 2:
                for (int i = 0; i < list.size(); i++) {
                    vvComparison[i] = list.get(i).getLivingSpace();
                }
                break;
            case 3:
                for (int i = 0; i < list.size(); i++) {
                    vvComparison[i] = list.get(i).getNumberGarages();
                }
                break;
            case 4:
                for (int i = 0; i < list.size(); i++) {
                    vvComparison[i] = list.get(i).getNumberRooms();
                }
                break;
            case 5:
                for (int i = 0; i < list.size(); i++) {
                    vvComparison[i] = list.get(i).getNumberBedrooms();
                }
                break;
            case 6:
                for (int i = 0; i < list.size(); i++) {
                    vvComparison[i] = list.get(i).getAge();
                }
                break;

        }
    }

//    same funcionality as the one above but this time the data are saved in a new array. which gets returned, used for the correlation
    public double[] createXiArray(int index, ArrayList<House> list) {

        double[] xArray = new double[list.size()];
        switch (index) {
            case 0:
                for (int i = 0; i < list.size(); i++) {
                    xArray[i] = list.get(i).getNumberBathrooms();
                }
                break;
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    xArray[i] = list.get(i).getArea();
                }
                break;
            case 2:
                for (int i = 0; i < list.size(); i++) {
                    xArray[i] = list.get(i).getLivingSpace();
                }
                break;
            case 3:
                for (int i = 0; i < list.size(); i++) {
                    xArray[i] = list.get(i).getNumberGarages();
                }
                break;
            case 4:
                for (int i = 0; i < list.size(); i++) {
                    xArray[i] = list.get(i).getNumberRooms();
                }
                break;
            case 5:
                for (int i = 0; i < list.size(); i++) {
                    xArray[i] = list.get(i).getNumberBedrooms();
                }
                break;
            case 6:
                for (int i = 0; i < list.size(); i++) {
                    xArray[i] = list.get(i).getAge();
                }
                break;

        }
        return xArray;
    }

    //method that creates an array with all the Y values corrensponding to a chosen Xi coming from training data, it will be used to create tables and to do calculations.
    public void createArrayForY(ArrayList<House> list) {
        if (YValues != null) {
            YValues = new double[list.size()];
            for (int i = 0; i < list.size(); i++) {
                YValues[i] = list.get(i).getPrice();
            }
        } else {
            YValues = null;
            YValues = new double[list.size()];
            for (int i = 0; i < list.size(); i++) {
                YValues[i] = list.get(i).getPrice();
            }
        }
    }

    //method that creates an array with all the Y values corrensponding to a chosen Xi coming from comparison data, it will be used to create tables and to do calculations.
    public void createYforComparison(ArrayList<House> list) {
        if (yvComparison != null) {
            yvComparison = new double[list.size()];
            for (int i = 0; i < list.size(); i++) {
                yvComparison[i] = list.get(i).getPrice();
            }
        } else {
            yvComparison = null;
            yvComparison = new double[list.size()];
            for (int i = 0; i < list.size(); i++) {
                yvComparison[i] = list.get(i).getPrice();
            }
        }
    }

    //    same funcionality as the one above but this time the data are saved in a new array. which gets returned, used for the correlation
    public double[] createYArray(ArrayList<House> list) {
        double[] YArray = new double[list.size()];

        for (int i = 0; i < list.size(); i++) {
            YArray[i] = list.get(i).getPrice();
        }
        return YArray;

    }

//    Updating the chart of comparison data
    public void updateComparisonChart() {

        createArrayForXi(selectedVariable, ts.comparisonTown);
        createArrayForY(ts.comparisonTown);
        chartComparison.setTitle("House price compared to " + pickVariable.getSelectedItem());
        dataComparison.addSeries(GraphApplication.createNewXYSeries(variableValues, YValues));
    }

//    updating both charts at the same time
    public void updateCharts() {

        createArrayForXi(selectedVariable, ts.trainTown);
        createArrayForY(ts.trainTown);

        //Adding series to dataCollection
        dataTraining = GraphApplication.createSeriesAndCollection(variableValues, YValues);
        dataComparison = GraphApplication.createSeriesAndCollection(variableValues, YValues);

        chartTraining.setTitle("House price compared to " + pickVariable.getSelectedItem());

        //Setting a new dataCollection for the chart
        chartTraining.getXYPlot().setDataset(dataTraining);
        chartComparison.getXYPlot().setDataset(dataComparison);
    }

//    this method updates tables E which is updated often. 
    public void updateTableE() {

        table1.remove(js4);
        tableE = createTableE(selectedVariable);
        tableE.setPreferredScrollableViewportSize(new Dimension(350, 50));
        tableE.setFillsViewportHeight(true);
        js4 = new JScrollPane(tableE);
        js4.setVisible(true);
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 2;
        cons.gridy = 1;
        js4.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Table E:Forecasted Values", TitledBorder.CENTER,
                TitledBorder.TOP));
        table1.add(js4, cons);

        frame.revalidate();
    }

//    A method that updates only the training data graph
    private void updateTrainingChart() {

        createArrayForXi(selectedVariable, ts.trainTown);
        createArrayForY(ts.trainTown);

        dataTraining = GraphApplication.createSeriesAndCollection(variableValues, YValues);
        chartTraining.getXYPlot().setDataset(dataTraining);
    }
}
