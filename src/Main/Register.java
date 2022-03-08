/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.MainMenu.con;

import Warnings.Warnings;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class displays the window of Register where the user has to give his
 * information to create an account.
 *
 * @author Muhammad Obaid ulla, Mohammad Ayman, Mubashir Ahmed
 */
public class Register extends MainMenu implements ActionListener {

    static JFrame frame;
    static JTextField text, text1, text2, text3, text4, text5;
    public static int currentYear = 2021;

    /**
     * This function displays the fields to be filled by the user for creating
     * an account. It is also assigning colours to the Register window.
     */
    public void register() {
        frame = new JFrame();
        JPanel panel = new JPanel();
        frame.validate();
        frame.setSize(600, 300);
        frame.setBounds(350, 200, 820, 400);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);
        JLabel label = new JLabel("Enter Username *");
        label.setBounds(50, 10, 110, 100);
        JLabel label1 = new JLabel("Enter Password *");
        label1.setBounds(50, 40, 100, 100);
        JLabel label2 = new JLabel("Enter Year of Birth *");
        label2.setBounds(50, 70, 170, 100);
        JLabel label3 = new JLabel("Enter Firstname *");
        label3.setBounds(50, 100, 110, 100);
        JLabel label4 = new JLabel("Enter Lastname *");
        label4.setBounds(50, 130, 100, 100);
        JLabel label5 = new JLabel("Enter Credit Card Number *");
        label5.setBounds(50, 160, 200, 100);

        JButton button2 = new JButton("Back");
        button2.setBounds(10, 10, 70, 20);

        panel.add(button2);

        text = new JTextField(20);
        text.setBounds(210, 50, 200, 20);
        panel.add(text);
        text1 = new JTextField(20);
        text1.setBounds(210, 80, 200, 20);
        text2 = new JTextField(20);
        text2.setBounds(210, 110, 200, 20);
        text3 = new JTextField(20);
        text3.setBounds(210, 140, 200, 20);
        text4 = new JTextField(20);
        text4.setBounds(210, 170, 200, 20);
        text5 = new JTextField(25);
        text5.setBounds(210, 200, 200, 20);

        // Button to register
        JButton button1 = new JButton("Register Now");
        button1.setBounds(210, 230, 200, 20);

        button1.setBackground(Color.CYAN);
        panel.setBackground(Color.GRAY);
        button2.setBackground(Color.CYAN);
        panel.add(button1);
        panel.add(text1);

        panel.add(label);
        panel.add(text2);
        panel.add(label1);
        panel.add(text3);
        panel.add(label2);
        panel.add(text4);
        panel.add(text5);
        panel.add(label4);
        panel.add(label3);
        panel.add(label5);

        label.setForeground(Color.WHITE);
        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        label4.setForeground(Color.WHITE);
        label5.setForeground(Color.WHITE);

        button1.addActionListener(new Register());
        button2.addActionListener(new Register());

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This function throws the error message if the given information is not
     * valid according to the application criteria. It also inserts the
     * information of the account to databank and throws SQL and IO exception if
     * there are any.
     *
     * @param e pass on the information that which button is selected by the
     * user in application.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Back")) {
            frame.setVisible(false);
            try {

                MainMenu.main(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (s.equals("Register Now")) {
            Warnings checkError = new Warnings();
            int a[] = new int[10];
            while (true) {
                try {
                    input1 = text.getText();
                    if (input1.equals("")) {

                        text.setBackground(Color.RED);
                        a[0] = 1;
                    }
                } catch (Exception exp) {

                    text.setBackground(Color.RED);
                    a[0] = 1; // invalid user name
                }

                input2 = text1.getText();
                if (input2.length() < 8 || input2.length() > 16) {

                    text1.setBackground(Color.RED);
                    a[1] = 1; //password length must be between 8 and 16
                }
                try {
                    input3 = Integer.parseInt(text2.getText());

                } catch (Exception ex) {

                    text2.setBackground(Color.RED);
                    a[2] = 1; //Invalid Year of birth
                }
                if (currentYear - input3 <= 18) {
                    a[3] = 1; // Age must be greater than 18
                    text2.setBackground(Color.RED);

                }

                input4 = text3.getText();
                if (input4.equals("")) {

                    text3.setBackground(Color.RED);
                    a[4] = 1; // Invalid firstname
                }

                input5 = text4.getText();
                if (input5.equals("")) {
                    text4.setBackground(Color.RED);
                    a[5] = 1; // Invalid Lastname
                }
                try {
                    input6 = Double.parseDouble(text5.getText());
                    int length = Double.toString(input6).length() - 4;

                    if (length != 16) {
                        a[6] = 1; // Invalid creditcard
                        text5.setBackground(Color.RED);

                    }
                } catch (Exception exp) {
                    a[6] = 1; // Invalid creditcard
                    text5.setBackground(Color.RED);
                    a[6] = 1; // Invalid creditcard
                }
                int countErrors = 0;
                for (int i = 0; i < 7; i++) {
                    if (a[i] == 1) {
                        countErrors++;
                    }
                }
                if (countErrors > 1) {
                    checkError.errorMessage("Invalid Entries");
                    break;
                } else {
                    if (a[0] == 1) {
                        checkError.errorMessage("invalid Username");
                        break;
                    }
                    if (a[1] == 1) {
                        checkError.errorMessage("Invalid Password. Password length must be between 8 and 16 characters");
                        break;
                    }
                    if (a[2] == 1) {
                        checkError.errorMessage("Invalid Year of Birth");
                        break;
                    }
                    if (a[3] == 1) {
                        checkError.errorMessage("Age must be greater than 18");
                        break;
                    }
                    if (a[4] == 1) {
                        checkError.errorMessage("Invalid Firstname");
                        break;
                    }
                    if (a[5] == 1) {
                        checkError.errorMessage("Invalid Lastname");
                        break;
                    }
                    if (a[6] == 1) {
                        checkError.errorMessage("Invalid Credit Card");
                        break;
                    }
                }
                balance = 0;

                try {

                    stmt = con.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?,?)");
                    stmt.setString(1, input1);
                    stmt.setString(2, input2);
                    stmt.setInt(3, input3);
                    stmt.setString(4, input4);
                    stmt.setString(5, input5);
                    stmt.setDouble(6, input6);
                    stmt.execute();

                    stmt = con.prepareStatement("INSERT INTO balance VALUES (?,?)");
                    stmt.setString(1, input1);
                    stmt.setInt(2, balance);
                    stmt.execute();

                    PreparedStatement pstm = MainMenu.con.prepareStatement("SELECT * FROM order_history"
                            + " where orderID = (SELECT MAX(orderID) FROM order_history)");
                    ResultSet rs = pstm.executeQuery();
                    int orderID = 0;
                    while (rs.next()) {
                        orderID = rs.getInt("orderID");
                        System.out.print("orderID" + orderID);
                    }
                    orderID++;

                    stmt = con.prepareStatement("INSERT INTO order_history VALUES (?,?)");
                    stmt.setInt(1, orderID);
                    stmt.setString(2, input1);

                    stmt.execute();

                } catch (SQLException ex) {
                    checkError.errorMessage("Username already exists");
                    break;
                }
                login window = new login();
                checkError.errorWarning("Account has been registered ");
                try {
                    frame.setVisible(false);
                    window.login();
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}
