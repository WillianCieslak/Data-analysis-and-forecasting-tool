/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkcomp1555;

import static courseworkcomp1555.CourseworkComp1555.ts;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

// COMMENTED
public class WriteData implements ActionListener, KeyListener {

    int id = 0;
    JFrame smallFrame;
    String[] pickData = {"Training", "Comparison"};
    GridBagConstraints cons;
    JComboBox input;
    JPanel panelBottom;
    JTextField price;
    JTextField x1;
    JTextField x2;
    JTextField x3;
    JTextField x4;
    JTextField x5;
    JTextField x6;
    JTextField x7;
    JLabel yLable;
    JLabel x1Lable;
    JLabel x2Lable;
    JLabel x3Lable;
    JLabel x4Lable;
    JLabel x5Lable;
    JLabel x6Lable;
    JLabel x7Lable;
    CourseworkComp1555 cw = new CourseworkComp1555();

    public WriteData() throws IOException {

        ts.trainTown.clear();           // When the window opens it cleans the train town data array as well as the comparison array so the new data can get in 
        ts.comparisonTown.clear();
        view();                         //runs the interface

    }

    public void view() {
        smallFrame = new JFrame();                                  // setting up the interface
        JPanel panel = new JPanel(new GridBagLayout());
        smallFrame.getContentPane().add(panel, BorderLayout.NORTH);

        JPanel panelTop = new JPanel(new GridBagLayout());              // adding two panel one on top the other one on the bottom.
        panelTop.setPreferredSize(new Dimension(600, 80));
        panelBottom = new JPanel(new GridBagLayout());
        panelBottom.setPreferredSize(new Dimension(600, 700));

        cons = new GridBagConstraints();                                    // initialising the constraints for the GridBagLayout

        //The combobox to choose between comparison or training data entry
        input = new JComboBox(pickData);
        input.addActionListener(this);
        input.setActionCommand("predictButton");
        input.setPreferredSize(new Dimension(100, 20));
        cons.insets = new Insets(0, 0, 0, 0);
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.gridx = 0;
        cons.gridy = 0;
        panelTop.add(input, cons);

        //Labels to explain the usage of the interface
        JLabel infoLabel = new JLabel("After closing this window, change the variable on the BLUE BOX to populate");
        cons.gridx = 0;
        cons.gridy = 1;
        panelTop.add(infoLabel, cons);
        JLabel infoLabel2 = new JLabel("If you dont want to enter the data through keyboard please close the whole program");
        cons.gridx = 0;
        cons.gridy = 2;
        panelTop.add(infoLabel2, cons);

        //adding textfields and labels   
        price = new JTextField();
        addTextField(0, 0, price);
        x1 = new JTextField();
        addTextField(0, 1, x1);
        x2 = new JTextField();
        addTextField(0, 2, x2);
        x3 = new JTextField();
        addTextField(0, 3, x3);
        x4 = new JTextField();
        addTextField(0, 4, x4);
        x5 = new JTextField();
        addTextField(0, 5, x5);
        x6 = new JTextField();
        addTextField(0, 6, x6);
        x7 = new JTextField();
        addTextField(0, 7, x7);

        yLable = new JLabel();
        yLable.setText("House Price(Double)");
        addLabel(1, 0, yLable);
        x1Lable = new JLabel();
        x1Lable.setText("No of Bathrooms(Double)");
        addLabel(1, 1, x1Lable);
        x2Lable = new JLabel();
        x2Lable.setText("Area of the Site(Double)");
        addLabel(1, 2, x2Lable);
        x3Lable = new JLabel();
        x3Lable.setText("Size of living space(Double)");
        addLabel(1, 3, x3Lable);
        x4Lable = new JLabel();
        x4Lable.setText("No of Garages(Integer)");
        addLabel(1, 4, x4Lable);
        x5Lable = new JLabel();
        x5Lable.setText("No of Rooms(Integer)");
        addLabel(1, 5, x5Lable);
        x6Lable = new JLabel();
        x6Lable.setText("No of Bedrooms(Integer)");
        addLabel(1, 6, x6Lable);
        x7Lable = new JLabel();
        x7Lable.setText("Age of property(Integer) ");
        addLabel(1, 7, x7Lable);

        //the button that adds the values and the button that adds the values +closes the window
        JButton addButton = new JButton();
        addButton.addActionListener(this);
        addButton.setActionCommand("AddButton");
        addButton.setPreferredSize(new Dimension(120, 40));
        addButton.setText("Add");
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridx = 0;
        cons.gridy = 8;
        panelBottom.add(addButton, cons);
        JButton addAndClose = new JButton();
        addAndClose.addActionListener(this);
        addAndClose.setActionCommand("AddAndClose");
        addAndClose.setPreferredSize(new Dimension(120, 40));
        addAndClose.setText("Add & Close");
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridx = 1;
        cons.gridy = 8;
        panelBottom.add(addAndClose, cons);

        //adding the 2 main panels to the frame
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridx = 0;
        cons.gridy = 1;
        panel.add(panelBottom, cons);
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridx = 0;
        cons.gridy = 0;
        panel.add(panelTop, cons);

// set up the frame
        smallFrame.setSize(700, 900);
        smallFrame.setVisible(true);

//on closing the table, it checks whether data were entered or not. If less than 5 sets of data were added than it will not allow the user to close the window
        smallFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        smallFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                if (ts.trainTown.size() <= 2 || ts.comparisonTown.size() <= 2) {
                    JOptionPane.showMessageDialog(null, "Please enter enough data to train and compare", "InfoBox: " + "INPUT ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    smallFrame.dispose();
                    cw.updateCharts();
                    cw.updateComparisonChart();
                    cw.updateTableE();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("AddButton".equals(e.getActionCommand())) {
//             when "ADD" button is clicked
            try {
//             CHECKING if training or comparison is selected and based on that it adds the data to the right arraylist. Data is added as objects of class HOUSE.   

                if (Double.parseDouble(price.getText()) < 0 || Double.parseDouble(x1.getText()) < 0 || Double.parseDouble(x2.getText()) < 0 || Double.parseDouble(x3.getText()) < 0 || Integer.parseInt(x4.getText()) < 0 || Integer.parseInt(x5.getText()) < 0 || Integer.parseInt(x6.getText()) < 0 || Integer.parseInt(x7.getText()) < 0) {
                    throw new NumberFormatException();
                }

                if ((input.getSelectedItem().toString()) == "Training") {
                    House first = new House(id, Double.parseDouble(price.getText()), Double.parseDouble(x1.getText()), Double.parseDouble(x2.getText()),
                            Double.parseDouble(x3.getText()), Integer.parseInt(x4.getText()),
                            Integer.parseInt(x5.getText()), Integer.parseInt(x6.getText()),
                            Integer.parseInt(x7.getText()));

                    ts.trainTown.add(first);
                }
                if ((input.getSelectedItem().toString()) == "Comparison") {
                    House second = new House(id, Double.parseDouble(price.getText()), Double.parseDouble(x1.getText()), Double.parseDouble(x2.getText()),
                            Double.parseDouble(x3.getText()), Integer.parseInt(x4.getText()),
                            Integer.parseInt(x5.getText()), Integer.parseInt(x6.getText()),
                            Integer.parseInt(x7.getText()));
                    ts.comparisonTown.add(second);
                }
//                setting all the text fields to empty and telling the user that the input was successful
                JOptionPane.showMessageDialog(null, "Input Successful", "InfoBox: " + "INPUT Successful", JOptionPane.INFORMATION_MESSAGE);
                price.setText("");
                x1.setText("");
                x2.setText("");
                x3.setText("");
                x4.setText("");
                x5.setText("");
                x6.setText("");
                x7.setText("");

//            this try catch with catch all the data input that are not appropriate, like entering double where integers are requires or entering characters 
            } catch (NumberFormatException ea) {
//               if any fields are left empty than show a error. This doesnt allow the user to enter any other data rather than INTIGERS. This is a problem. 
                JOptionPane.showMessageDialog(null, "PLEASE INPUT CORRECT VALUES IN THE FIELDS", "InfoBox: " + "INPUT ERROR", JOptionPane.INFORMATION_MESSAGE);
            }

        }
//         this action performed if statement does the same but in this case closes the window as well, if the arrays are not populated with at least 5 sets of data it doesnt close and it shows a error
        if ("AddAndClose".equals(e.getActionCommand())) {
            //  It does the same thing as the button ADD but it closes the window as well
            try {

                if (Double.parseDouble(price.getText()) < 0 || Double.parseDouble(x1.getText()) < 0 || Double.parseDouble(x2.getText()) < 0 || Double.parseDouble(x3.getText()) < 0 || Integer.parseInt(x4.getText()) < 0 || Integer.parseInt(x5.getText()) < 0 || Integer.parseInt(x6.getText()) < 0 || Integer.parseInt(x7.getText()) < 0) {
                    throw new NumberFormatException();
                }
                if ((input.getSelectedItem().toString()) == "Training") {
                    House first = new House(id, Double.parseDouble(price.getText()), Double.parseDouble(x1.getText()), Double.parseDouble(x2.getText()),
                            Double.parseDouble(x3.getText()), Integer.parseInt(x4.getText()),
                            Integer.parseInt(x5.getText()), Integer.parseInt(x6.getText()),
                            Integer.parseInt(x7.getText()));

                    ts.trainTown.add(first);
                }
                if ((input.getSelectedItem().toString()) == "Comparison") {
                    House second = new House(id, Double.parseDouble(price.getText()), Double.parseDouble(x1.getText()), Double.parseDouble(x2.getText()),
                            Double.parseDouble(x3.getText()), Integer.parseInt(x4.getText()),
                            Integer.parseInt(x5.getText()), Integer.parseInt(x6.getText()),
                            Integer.parseInt(x7.getText()));

                    ts.comparisonTown.add(second);

                }

                if (ts.trainTown.size() <= 2 || ts.comparisonTown.size() <= 2) {
                    JOptionPane.showMessageDialog(null, "Please enter enough data to train and compare. You need to close the program if you want to go out.", "InfoBox: " + "INPUT ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
//                     after pressing the button and checking if the arraylists were populated with at least 5 sets,
//                      it closes the window and updates the graph and tableE, to update other tables
//                      the user needs to change the variable, this is explained on the interface as well
                    JOptionPane.showMessageDialog(null, "Input Successful", "InfoBox: " + "INPUT Successful", JOptionPane.INFORMATION_MESSAGE);
                    smallFrame.dispose();
                    cw.updateCharts();
                    cw.updateComparisonChart();
                    cw.updateTableE();
                }

            } catch (NumberFormatException ea) {
                JOptionPane.showMessageDialog(null, "PLEASE INPUT CORRECT VALUES IN THE FIELDS", "InfoBox: " + "INPUT ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
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

    //method that adds the textfields on the interface
    private void addTextField(int x, int y, JTextField element) {
        element.setText("");
        element.setPreferredSize(new Dimension(100, 25));
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridx = x;
        cons.gridy = y;

        panelBottom.add(element, cons);
    }

    //method that adds the labes on the interface.
    private void addLabel(int x, int y, JLabel element) {

        element.setPreferredSize(new Dimension(250, 25));
        cons.insets = new Insets(10, 10, 10, 10);
        cons.gridx = x;
        cons.gridy = y;
        panelBottom.add(element, cons);
    }
}
