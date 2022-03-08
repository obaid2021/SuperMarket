/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PasswordManager;

import static Main.MainMenu.con;
import Main.Register;
import Main.login;
import Warnings.Warnings;
import PasswordManager.ChangePassword;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class displays the required information field and buttons.It also throws
 * an exception when the entry is not valid.
 *
 * @author Muhammad Obaid ullah, Mohammad Ayman, Mubashir Ahmed
 */
public class Userpassword implements ActionListener {

    static JFrame frame;
    static JPanel panel;
    static JTextField text, text1, text2, text3;
    static Statement stmt;
    static ResultSet rs;
    static String input, input1, input2;
    static int input3;
    Register reg = new Register();
    Warnings message = new Warnings();

    /**
     * It displays the required information fields and buttons.
     *
     * @throws SQLException it throws an exception that provides information on
     * a database access error or other errors.
     */
    public void check_identity() throws SQLException {

        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(600, 400);
        frame.setBounds(350, 200, 600, 300);
        frame.setVisible(true);
        frame.add(panel);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
        JLabel label = new JLabel("Enter Username *");
        label.setBounds(50, 10, 100, 100);
        label.setForeground(Color.WHITE);
        JLabel label1 = new JLabel("Enter Firstname *");
        label1.setBounds(50, 40, 150, 100);
        label1.setForeground(Color.WHITE);
        JLabel label2 = new JLabel("Enter Lastname *");
        label2.setBounds(50, 70, 100, 100);
        label2.setForeground(Color.WHITE);
        JLabel label3 = new JLabel("Enter Year of Birth *");
        label3.setBounds(50, 100, 150, 100);
        label3.setForeground(Color.WHITE);
        panel.add(label);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);

        text = new JTextField(20);
        text.setBounds(200, 50, 200, 20);
        panel.add(text);
        text1 = new JTextField(20);
        text1.setBounds(200, 80, 200, 20);
        panel.add(text1);
        text2 = new JTextField(20);
        text2.setBounds(200, 110, 200, 20);
        panel.add(text2);
        text3 = new JTextField(20);
        text3.setBounds(200, 140, 200, 20);
        panel.add(text3);
        JButton button = new JButton("Change Password");
        button.setBounds(200, 180, 200, 30);
        button.setBackground(Color.CYAN);
        panel.add(button);

        JButton button1 = new JButton("Back");
        button1.setBounds(10, 10, 70, 20);
        button1.setBackground(Color.CYAN);
        panel.add(button1);
        button1.addActionListener(new Userpassword());
        button.addActionListener(new Userpassword());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This function throws the exception when wrong information is added.It
     * takes the string of information from the user.
     *
     * @param e refers to the button selected by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        login window = new login();
        if (s.equals("Back")) {
            frame.setVisible(false);
            try {
                window.login();
            } catch (IOException ex) {
                Logger.getLogger(Userpassword.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (s.equals("Change Password")) {
            while (true) {

                try {
                    input = text.getText();
                    if (input.equals("")) {
                        message.errorMessage("Invalid Username");
                        text.setBackground(Color.RED);
                        break;
                    }
                } catch (Exception exp) {
                    message.errorMessage("Invalid Username");
                    text.setBackground(Color.RED);
                    break;
                }

                try {
                    input1 = text1.getText();
                    if (input1.equals("")) {
                        message.errorMessage("Invalid Firstname");
                        text1.setBackground(Color.RED);
                        break;
                    }
                } catch (Exception exp) {
                    message.errorMessage("Invalid Firstname");
                    text.setBackground(Color.RED);
                    break;
                }

                try {
                    input2 = text2.getText();
                    if (input2.equals("")) {
                        message.errorMessage("Invalid Firstname");
                        text2.setBackground(Color.RED);
                        break;
                    }
                } catch (Exception ex) {
                    message.errorMessage("Invalid Lastname");
                    text2.setBackground(Color.RED);
                    break;
                }
                try {
                    input3 = Integer.parseInt(text3.getText());

                    if (input3 > reg.currentYear - 17) {
                        message.errorMessage("Invalid or wrong entry in Age field");
                        text3.setBackground(Color.RED);
                        break;
                    }
                } catch (NumberFormatException ex) {
                    message.errorMessage("Invalid Year of Birth");
                    text3.setBackground(Color.RED);
                    break;
                }

                String username = null;
                String password;
                int age = 0;
                String firstname = null;
                String lastname = null;
                double creditcard;
                try {
                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery("select * from user where username =  '" + input + "'");
                    boolean x = true;
                    while (rs.next()) {
                        username = rs.getString("Username");
                        password = rs.getString("Password");
                        age = rs.getInt("yob");
                        firstname = rs.getString("Firstname");
                        lastname = rs.getString("Lastname");
                        creditcard = rs.getDouble("creditcard");

                    }

                    if ((!username.equals(input))) {
                        message.errorMessage("Wrong Username");
                        text.setBackground(Color.RED);
                        break;
                    } else if (!(firstname.equals(input1))) {
                        message.errorMessage("Wrong Firstname");
                        text1.setBackground(Color.RED);
                        break;
                    } else if (!(lastname.equals(input2))) {
                        message.errorMessage("Wrong Lastname");
                        text2.setBackground(Color.RED);
                        break;
                    } else if (!(age == input3)) {
                        message.errorMessage("Wrong Year of Birth");
                        text2.setBackground(Color.RED);
                        break;
                    } else {
                        ChangePassword window1 = new ChangePassword();
                        window1.change_password(username, " ");
                        frame.setVisible(false);
                        x = false;
                        break;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Userpassword.class.getName()).log(Level.SEVERE, null, ex);
                    message.errorMessage("Unknown error");
                    break;
                }
            }
        }
    }
}
