/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orders;

import static Main.MainMenu.con;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class shows the previous orders of the user by saving the products
 * bought by user previously in database.
 *
 * @author Mohammad Ayman, Muhammad Obaid Ullah, Mubashir Ahmed
 */
public class PreviousOrders {

    JFrame frame = new JFrame();

    /**
     * This function displays the name of products and their quantity depending
     * on the user's previous shopping. It takes the data from a database
     * order_history.
     *
     * @param username is the username of the user who is currently using the
     * application.
     * @throws SQLException it throws an exception that provides information on
     * a database access error or other errors.
     */
    public void previous_order(String username) throws SQLException {

        JPanel panel = new JPanel();
        frame = new JFrame("Last Order");
        frame.setSize(600, 300);
        frame.setBounds(350, 200, 600, 350);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
        JLabel label = new JLabel("Product Name");
        label.setBounds(100, 10, 100, 100);
        panel.add(label);
        label.setForeground(Color.WHITE);
        panel.setBackground(Color.GRAY);

        JLabel label1 = new JLabel("Quantity");
        label1.setBounds(300, 10, 100, 100);
        panel.add(label1);
        label1.setForeground(Color.WHITE);

        Statement stmt = con.createStatement();
        int input = 0;
        ResultSet rs = stmt.executeQuery("select * from order_history where username= '" + username + "'");

        while (rs.next()) {
            input = rs.getInt("orderID");
        }
        rs = stmt.executeQuery("select * from products where ID = '" + input + "'");
        int productOne = 0, productTwo = 0, productThree = 0, productFour = 0, productFive = 0, productSix = 0, productSeven = 0;
        int productEight = 0, productNine = 0;
        while (rs.next()) {
            productOne = rs.getInt("productOne");
            productTwo = rs.getInt("productTwo");
            productThree = rs.getInt("productThree");
            productFour = rs.getInt("productFour");
            productFive = rs.getInt("productFive");
            productSix = rs.getInt("productSix");
            productSeven = rs.getInt("productSeven");
            productEight = rs.getInt("productEight");
            productNine = rs.getInt("productNine");
        }
        JLabel label2 = new JLabel("Laptop");
        label2.setBounds(100, 30, 100, 100);
        panel.add(label2);
        label2.setForeground(Color.WHITE);

        JLabel label3 = new JLabel("Monitor");
        label3.setBounds(100, 50, 100, 100);
        panel.add(label3);
        label3.setForeground(Color.WHITE);

        JLabel label4 = new JLabel("Keyboard");
        label4.setBounds(100, 70, 100, 100);
        panel.add(label4);
        label4.setForeground(Color.WHITE);

        JLabel label5 = new JLabel("Mouse");
        label5.setBounds(100, 90, 100, 100);
        panel.add(label5);
        label5.setForeground(Color.WHITE);

        JLabel label6 = new JLabel("Printer");
        label6.setBounds(100, 110, 100, 100);
        panel.add(label6);
        label6.setForeground(Color.WHITE);

        JLabel label7 = new JLabel("Desktop");
        label7.setBounds(100, 130, 100, 100);
        panel.add(label7);
        label7.setForeground(Color.WHITE);

        JLabel label8 = new JLabel("Mobile");
        label8.setBounds(100, 150, 100, 100);
        panel.add(label8);
        label8.setForeground(Color.WHITE);

        JLabel label9 = new JLabel("Earbuds");
        label9.setBounds(100, 170, 100, 100);
        panel.add(label9);
        label9.setForeground(Color.WHITE);

        JLabel label10 = new JLabel("Scanner");
        label10.setBounds(100, 190, 100, 100);
        panel.add(label10);
        label10.setForeground(Color.WHITE);

        JLabel label11 = new JLabel("" + productOne);
        label11.setBounds(300, 30, 100, 100);
        panel.add(label11);
        label11.setForeground(Color.WHITE);

        JLabel label12 = new JLabel("" + productTwo);
        label12.setBounds(300, 50, 100, 100);
        panel.add(label12);
        label12.setForeground(Color.WHITE);

        JLabel label13 = new JLabel("" + productThree);
        label13.setBounds(300, 70, 100, 100);
        panel.add(label13);
        label13.setForeground(Color.WHITE);

        JLabel label14 = new JLabel("" + productFour);
        label14.setBounds(300, 90, 100, 100);
        panel.add(label14);
        label14.setForeground(Color.WHITE);

        JLabel label15 = new JLabel("" + productFive);
        label15.setBounds(300, 110, 100, 100);
        panel.add(label15);
        label15.setForeground(Color.WHITE);

        JLabel label16 = new JLabel("" + productSix);
        label16.setBounds(300, 130, 100, 100);
        panel.add(label16);
        label16.setForeground(Color.WHITE);

        JLabel label17 = new JLabel("" + productSeven);
        label17.setBounds(300, 150, 100, 100);
        panel.add(label17);
        label17.setForeground(Color.WHITE);

        JLabel label18 = new JLabel("" + productEight);
        label18.setBounds(300, 170, 100, 100);
        panel.add(label18);
        label18.setForeground(Color.WHITE);

        JLabel label19 = new JLabel("" + productNine);
        label19.setBounds(300, 190, 100, 100);
        panel.add(label19);
        label19.setForeground(Color.WHITE);

    }
}
