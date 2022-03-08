/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Topup;

import static Main.MainMenu.con;
import Warnings.Warnings;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *This class contains the Balance option where user can top up his or her account. The amount of money topped up
 * are deducted from the provided users credit card. It also pop ups error messages and successful executions.
 * @author Muhammad obaid Ullad, Mohammad Ayman, Mubashir Ahmed
 */
public class Balance implements ActionListener {

    static JButton button, button1, button2, button3, button4;
    static JLabel label1;
    public static int balance = 0;
    static PreparedStatement stmt;
    static Statement stmt1;
    static String firstname, lastname, usrname, password;
    static int age, initialBalance;
    static double creditcard;
    static JFrame frameW;
/**
 * This function displays the balance of the user and the buttons with amount of money that user can select for top up.
 * @param username is the username of the user currently logged in.
 * @throws SQLException it throws an exception that provides information on a database access error or other errors.
 */
    public static void balance(String username) throws SQLException {
        JPanel panel = new JPanel();
        frameW = new JFrame();
        stmt1 = con.createStatement();

        frameW.setSize(600, 300);
        frameW.setBounds(350, 200, 600, 300);
        frameW.setVisible(true);
        frameW.add(panel);
        panel.setLayout(null);
        JLabel label = new JLabel("CURRENT BALANCE :");
        label.setBounds(10, 10, 1000, 100);
        label.setForeground(Color.WHITE);

        ResultSet rs1 = stmt1.executeQuery("select * from balance where username = '" + username + "'");

        while (rs1.next()) {
            usrname = rs1.getString("username");
            balance = rs1.getInt("balance");

        }
        initialBalance = balance;
        label1 = new JLabel(" " + balance);
        label1.setBounds(300, 10, 1000, 100);
        label1.setForeground(Color.WHITE);

        JLabel label2 = new JLabel("TOP UP:");
        label2.setBounds(10, 140, 110, 50);
        label2.setForeground(Color.WHITE);

        button = new JButton("10");
        button.setBounds(140, 150, 50, 50);
        panel.add(button);
        button.setBackground(Color.CYAN);
        button.addActionListener(new Balance());

        button1 = new JButton("20");
        button1.setBounds(200, 150, 50, 50);
        button1.setBackground(Color.CYAN);
        panel.add(button1);
        button1.addActionListener(new Balance());

        button2 = new JButton("50");
        button2.setBounds(260, 150, 50, 50);
        button2.setBackground(Color.CYAN);
        panel.add(button2);
        button2.addActionListener(new Balance());

        button3 = new JButton("100");
        button3.setBounds(320, 150, 70, 50);
        button3.setBackground(Color.CYAN);
        panel.add(button3);
        button3.addActionListener(new Balance());

        button4 = new JButton("Confirm");
        button4.setBounds(400, 150, 120, 80);
        button4.setBackground(Color.CYAN);
        panel.add(button4);
        button4.addActionListener(new Balance());

        panel.add(label2);
        panel.add(label);
        panel.add(label1);
        ResultSet rs = stmt1.executeQuery("select * from user where username = '" + username + "'");

        while (rs.next()) {
            firstname = rs.getString("Firstname");
            lastname = rs.getString("Lastname");
            age = rs.getInt("yob");
            usrname = rs.getString("username");
            password = rs.getString("password");
            creditcard = rs.getDouble("creditcard");
            System.out.print("CREDITCARD" + creditcard);
        }

    }
/**
 * This function performs the addition of balance to the user's account. Depending on the selection of the amount.
 * @param e represents the button selected by the user.
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            balance = balance + 10;
            label1.setText(" " + balance);
        }
        if (e.getSource() == button1) {
            balance = balance + 20;
            label1.setText(" " + balance);
        }
        if (e.getSource() == button2) {
            balance = balance + 50;
            label1.setText(" " + balance);
        }
        if (e.getSource() == button3) {
            balance = balance + 100;
            label1.setText(" " + balance);
        }
        Warnings check = new Warnings();
        if (e.getSource() == button4) {

            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
               BigDecimal bd = new BigDecimal(creditcard);
                JOptionPane.showMessageDialog(null, (balance - initialBalance) + " euro deducted from Creditcard Number " + bd);
                try {
                    stmt = con.prepareStatement("update balance set balance=(?) where username =(?)");
                    stmt.setInt(1, balance);
                    stmt.setString(2, usrname);
                    stmt.executeUpdate();

                } catch (SQLException ex) {
                    Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
                }
                frameW.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Balance was not credited");

            }

        }
    }

}
