/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PasswordManager;

import Main.MainMenu;
import Main.login;
import Warnings.Warnings;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import admin.Admin;

/**
 * This class helps the user to change password incase user has forgot his or
 * her password.
 *
 * @author Mohammad Ayman, Mubashir Ahmed, Muhammad Obaidullah
 */
public class ChangePassword implements ActionListener {

    static JFrame frame;
    static JPanel panel;
    static JTextField text, text1;
    static PreparedStatement stmt;
    static String username, updateUsername;
    Admin window = new Admin();
    login window1 = new login();

    public void change_password(String s, String t) {
        username = t;
        updateUsername = s;
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(600, 300);
        frame.setBounds(350, 200, 600, 250);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
        JLabel label = new JLabel("New Password");
        label.setBounds(50, 10, 100, 100);
        label.setForeground(Color.WHITE);
        JLabel label1 = new JLabel("Confirm Password");
        label1.setBounds(50, 40, 150, 100);
        label1.setForeground(Color.WHITE);
        panel.add(label);
        panel.add(label1);
        text = new JTextField(20);
        text.setBounds(200, 50, 200, 20);
        panel.add(text);
        text1 = new JTextField(20);
        text1.setBounds(200, 80, 200, 20);
        panel.add(text1);

        JButton button = new JButton("Confirm");
        button.setBounds(200, 130, 100, 20);
        button.setBackground(Color.CYAN);
        panel.add(button);

        JButton button1 = new JButton("Back");
        button1.setBounds(200, 170, 100, 20);
        button1.setBackground(Color.CYAN);
        panel.add(button1);

        button.addActionListener(new ChangePassword());
        button1.addActionListener(new ChangePassword());

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This function performs the action of changing password of the user and
     * updates it in database. It also checks validity On invalid input it pops
     * up an error message and on successful change it shows confirmation
     * message.
     *
     * @param e it forwards the information depending on the user's selection of
     * button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Back")) {

            frame.setVisible(false);
            if (username.equals("admin")) {
                try {
                    window.admin();
                } catch (SQLException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    window1.login();
                } catch (IOException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        if (s.equals("Confirm")) {
            String input1 = text.getText();

            String input2 = text1.getText();

            Warnings message = new Warnings();
            if (input1.equals("")) {
                message.errorMessage("Please enter a valid password");
            } else if (input1.equals(input2)) {

                try {
                    stmt = MainMenu.con.prepareStatement("update user set password=(?) where username =(?)");
                    stmt.setString(1, input1);
                    stmt.setString(2, updateUsername);
                    stmt.executeUpdate();
                    frame.setVisible(false);
                    message.errorWarning("Password changed successfully");

                    if (username.equals("admin")) {
                        window.admin();
                    } else {
                        login window1 = new login();
                        try {
                            window1.login();
                        } catch (IOException ex) {
                            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                message.errorMessage("Password doesn't match. Please try again.");

            }
        }
    }
}
