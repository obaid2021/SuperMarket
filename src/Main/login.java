/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import PasswordManager.Userpassword;
import static Main.MainMenu.con;
import Warnings.Warnings;

import admin.Admin;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This class enable the user to give in his credentials if the account is
 * already registered. Otherwise the user has to register his or her account by
 * selecting register option. It also helps user incase user has forgotten
 * password to create a new one.
 *
 * @author Muhammad Obaid ullah, Mubashir Ahmed, Mohammad Ayman
 */
public class login implements ActionListener {

    static JFrame frame;
    static JFrame frame1;
    static JTextField textF;
    static JPasswordField textF1;
    static JButton button1, button2, button3, button4;
    static String input1, input2;
    static String s;
    static double creditcard = 0;

    /**
     * This function displays the login fields like username and password and
     * also displays the register, forgot password, Enter and Back buttons that
     * take the user to respective windows.It is also responsible for the
     * colouring of the window.
     *
     * @throws IOException throws the IOException whenever an input or output
     * operation is failed or interpreted
     */
    public void login() throws IOException {
        {
            JPanel panel = new JPanel();
            frame = new JFrame();

            frame.pack();
            frame.setLocation(600, 300);
            frame.setSize(600, 300);
            frame.setBounds(350, 200, 600, 300);

            frame.setVisible(true);
            frame.add(panel);
            //Enter Username and password
            panel.setLayout(null);
            panel.setBackground(Color.GRAY);
            JLabel label;
            label = new JLabel("Enter Username *");
            label.setBounds(50, 10, 120, 100);
            JLabel label1 = new JLabel("Enter Password *");
            label1.setBounds(50, 40, 1000, 100);
            // create two text field

            textF = new JTextField(20);
            textF.setBounds(180, 50, 200, 20);
            panel.add(textF);

            textF1 = new JPasswordField(20);
            textF1.setBounds(180, 80, 200, 20);
            // Button Enter
            button1 = new JButton("Enter");
            button1.setBounds(180, 110, 200, 20);

            button2 = new JButton("Back");
            button2.setBounds(10, 10, 70, 20);

            button3 = new JButton("Forgot Password");
            button3.setBounds(180, 140, 200, 20);

            button4 = new JButton("Register");
            button4.setBounds(180, 170, 200, 20);
            panel.add(button2);
            panel.add(button1);
            panel.add(button3);
            panel.add(button4);
            panel.add(textF1);
            panel.add(label);
            panel.add(label1);

            label.setForeground(Color.WHITE);
            label1.setForeground(Color.WHITE);

            button4.setBackground(Color.CYAN);
            button1.setBackground(Color.CYAN);
            button2.setBackground(Color.CYAN);
            button3.setBackground(Color.CYAN);
            button1.addActionListener(new login());
            button2.addActionListener(new login());
            button3.addActionListener(new login());
            button4.addActionListener(new login());

            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    /**
     * This function executes the actions depending on the buttons selected by
     * user. It checks the account username and password from the database and
     * give access to the application otherwise a pop up appears an error
     * message on the screen if account doesn't exists.
     *
     * @param e tells that which button is selected by the user and the
     * respective commands are executed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        s = e.getActionCommand();
        if (s.equals("Register")) {
            frame.setVisible(false);
            Register reg = new Register();
            reg.register();

        }
        if (s.equals("Enter")) {              
            frame.setVisible(false);
            String firstname, lastname, username = null, password = null;
            int age;
            input1 = textF.getText();

            input2 = textF1.getText();
            try {
                Statement stmt2 = con.createStatement();

                ResultSet rs = stmt2.executeQuery("select * from user where username = '" + input1 + "'");

                while (rs.next()) {

                    username = rs.getString("username");
                    password = rs.getString("password");
                    age = rs.getInt("yob");
                    firstname = rs.getString("Firstname");
                    lastname = rs.getString("Lastname");
                    creditcard = rs.getDouble("creditcard");
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Warnings throwMessage = new Warnings();
            String a = "Admin";
            String b = "admin";
            if (input1.equals(username) && input2.equals(password)) {

                throwMessage.errorWarning("Login Successful");
                Cart window = new Cart();
                try {

                    System.out.print("username in login " + username);
                    window.basket(username);
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (input1.equals(a) && input2.equals(b)) {
                try {
                    Admin window1 = new Admin();
                    frame.setVisible(false);
                    window1.admin();

                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                throwMessage.errorMessage("Username/Password is wrong. Please login again");
                try {
                    login();
                } catch (IOException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (s.equals("Back")) {
            frame.setVisible(false);
            try {
                MainMenu.main(null);
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (s.equals("Forgot Password")) {
            frame.setVisible(false);
            try {
                Userpassword window2 = new Userpassword();
                window2.check_identity();
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
