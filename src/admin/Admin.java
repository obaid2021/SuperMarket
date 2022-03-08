/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import Main.MainMenu;
import PasswordManager.ChangePassword;

import Payment.Checkout;
import Warnings.Warnings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import admin.Admin;

/**
 *This class has the special window for the admin. With admin credentials the admin can login into his/her account and
 * use some special features that are not accessible by normal users.
 * @author Muhammad Obaid ullah, Mohammad Ayman, Mubashir Ahmed
 */
public class Admin implements ActionListener {

    Connection con;
    PreparedStatement statement;
    static PreparedStatement stmt;
    
    String firstname, lastname, username, password;
    int age;
    double creditcard;
    JLabel label1, label2, label3, label4, label5, label6, label7;
    static JPanel panel;
    static JFrame frame;
    int i;
    static JTextField text;
    static JButton button2;
    Warnings message = new Warnings();
    static String input1;
/**
 * This function displays the special window for admin.
 * @throws SQLException it throws an exception that provides information on a database access error or other errors.
 */
    public void admin() throws SQLException {

        frame = new Table();
        frame.setTitle("Admin Console");
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setVisible(true);
        frame.setLayout(null);

        read_database();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
/**
 * The function displays the buttons on the admin window and specify its colours.
 * @throws SQLException it throws an exception that provides information on a database access error or other errors.
 */
    
    public void read_database() throws SQLException {
        text = new JTextField();
        text.setBounds(10, 450, 380, 30);
        frame.add(text);

        JButton button = new JButton("Delete");
        button.setBounds(10, 490, 100, 30);
        frame.add(button);

        JButton button1 = new JButton("Log Out");
        button1.setBounds(130, 490, 100, 30);
        frame.add(button1);

        button2 = new JButton("Change Password");
        button2.setBounds(250, 490, 140, 30);
        frame.add(button2);

        JButton button3 = new JButton("View Price Table");
        button3.setBounds(10, 530, 200, 30);
        frame.add(button3);

        button.setBackground(Color.CYAN);
        button1.setBackground(Color.CYAN);
        button2.setBackground(Color.CYAN);
        button3.setBackground(Color.CYAN);

        button.addActionListener(new Admin());
        button1.addActionListener(new Admin());
        button2.addActionListener(new Admin());
        button3.addActionListener(new Admin());

    }
/**
 * This window performs the special function respective to the buttons pressed by admin. The admin has special features 
 * like change password or delete account that are then updated in databank.
 * @param e represents the button selected by the user.
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Log Out")) {
            frame.setVisible(false);

            try {
                MainMenu.main(null);
            } catch (Exception ex) {
                Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (s.equals("Change Password")) {
            input1 = text.getText();
   
            if (input1.equals("")) {
                message.errorMessage("No Account Selected");
            } else {
                try {
                    Statement stmt2 = MainMenu.con.createStatement();
                    ResultSet rs = stmt2.executeQuery("select * from user where username = '" + input1 + "'");
                    username = "not found";
                    while (rs.next()) {

                        username = rs.getString("username");
                        password = rs.getString("password");
                        age = rs.getInt("yob");
                        firstname = rs.getString("Firstname");
                        lastname = rs.getString("Lastname");
                        creditcard = rs.getDouble("creditcard");
                    }
                 
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (username.equals("not found")) {
                    message.errorWarning("Account not found");
                } else {
                    ChangePassword window = new ChangePassword();
                    window.change_password(input1, "admin");
                    frame.setVisible(false);
                }
            }

        }
        if (s.equals("Delete")) {
            while (true) {
                input1 = text.getText();
                if (input1.equals("")) {
                    message.errorWarning("Enter a valid account");
                } else {
                    try {
                        Statement stmt2 = MainMenu.con.createStatement();
                        ResultSet rs = stmt2.executeQuery("select * from user where username = '" + input1 + "'");
                        username = "not found";
                        while (rs.next()) {

                            username = rs.getString("username");
                            password = rs.getString("password");
                            age = rs.getInt("yob");
                            firstname = rs.getString("Firstname");
                            lastname = rs.getString("Lastname");
                            creditcard = rs.getDouble("creditcard");
                        }
                        System.out.print(username);
                        if (username.equals("not found")) {
                            message.errorWarning("Account not found");
                            break;
                        }

                        stmt = MainMenu.con.prepareStatement("INSERT INTO non_active VALUES (?,?,?,?,?,?)");
                        stmt.setString(1, username);
                        stmt.setString(2, password);
                        stmt.setInt(3, age);
                        stmt.setString(4, firstname);
                        stmt.setString(5, lastname);
                        stmt.setDouble(6, creditcard);
                        stmt.execute();
                    } catch (SQLException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        stmt = MainMenu.con.prepareStatement("DELETE from balance where username=(?)");
                        stmt.setString(1, input1);
                        stmt.executeUpdate();

                        stmt = MainMenu.con.prepareStatement("DELETE from order_history where username=(?)");
                        stmt.setString(1, input1);
                        stmt.executeUpdate();

                        stmt = MainMenu.con.prepareStatement("DELETE from user where username=(?)");
                        stmt.setString(1, input1);
                        stmt.executeUpdate();
                        message.errorWarning("Account deleted successfully");

                    } catch (SQLException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        frame.setVisible(false);
                        admin();

                    } catch (SQLException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }

        }
        if (s.equals("View Price Table")) {
            frame.setVisible(false);
            Prices window = new Prices();
            window.priceGUI();
        }
    }
}
