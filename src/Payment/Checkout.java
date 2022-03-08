/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

import Main.Cart;
import static Main.Cart.*;
import Main.MainMenu;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class displays the bill of the user by adding the prices of the products
 * selected by the use. It also has the pay now option that pop ups a message if
 * the payment is successful otherwise if the balance is not enough it throws an
 * error message.
 *
 * @author Muhammad Obaid ulla, Mohammad Ayman, Mubashir Ahmed
 */
public class Checkout extends Cart implements ActionListener {

    public static String name;
    public static JButton button, button1, button3;
    static JFrame frame;
    public static int total;
    public static int[] prices = new int[100];

    /**
     * This function displays the Bill by adding the price of all the selected
     * products. It also shows the logout,Back and Pay Now Buttons.
     *
     * @param username is the username of the user logged in at the moment.
     * @throws SQLException it throws an exception that provides information on
     * a database access error or other errors.
     */

    public void bill(String username) throws SQLException {
        name = username;
        Checkout priceUpdate = new Checkout();
        priceUpdate.get_prices();

        JPanel panel = new JPanel();
        frame = new JFrame();
        frame.setSize(600, 300);
        frame.setBounds(350, 200, 700, 300);
        frame.setVisible(true);
        frame.add(panel);
        //Enter Username and password
        panel.setLayout(null);
        label = new JLabel("                  Bill - >");
        label.setBounds(10, 5, 400, 100);
        label.setForeground(Color.WHITE);
        panel.add(label);
        int price[] = get_prices();
        int a = 5;
        int p = 0, p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0, p6 = 0, p7 = 0, p8 = 0;
        if (count > 0) {
            label1 = new JLabel("Laptop :   " + count);
            label1.setBounds(150, a, 400, 100);

            panel.add(label1);
            p = count * prices[0];
            label2 = new JLabel("price " + price[0] + " =  " + p);
            label2.setBounds(280, a, 400, 100);
            label1.setForeground(Color.WHITE);
            label2.setForeground(Color.WHITE);
            panel.add(label2);
            a = a + 20;
        }
        if (count1 > 0) {
            JLabel label1 = new JLabel("Monitor :   " + count1);
            label1.setBounds(150, a, 400, 100);
            panel.add(label1);
            p1 = count1 * prices[1];
            label2 = new JLabel("price " + price[1] + "  =  " + p1);
            label2.setBounds(280, a, 400, 100);
            panel.add(label2);
            label1.setForeground(Color.WHITE);
            label2.setForeground(Color.WHITE);
            a = a + 20;
        }
        if (count2 > 0) {
            JLabel label1 = new JLabel("Keyboard :   " + count2);
            label1.setBounds(150, a, 400, 100);
            panel.add(label1);
            p2 = count2 * prices[2];
            label2 = new JLabel("price " + price[2] + "  =  " + p2);
            label2.setBounds(280, a, 400, 100);
            panel.add(label2);
            label1.setForeground(Color.WHITE);
            label2.setForeground(Color.WHITE);
            a = a + 20;
        }
        if (count3 > 0) {
            label1 = new JLabel("Mouse :   " + count3);
            label1.setBounds(150, a, 400, 100);
            panel.add(label1);
            p3 = count3 * prices[3];
            JLabel label2 = new JLabel("price " + price[3] + "  =  " + p3);
            label2.setBounds(280, a, 400, 100);
            panel.add(label2);
            label1.setForeground(Color.WHITE);
            label2.setForeground(Color.WHITE);
            a = a + 20;
        }
        if (count4 > 0) {
            label1 = new JLabel("Printer :   " + count4);
            label1.setBounds(150, a, 400, 100);
            panel.add(label1);
            p4 = count4 * prices[4];
            label2 = new JLabel("price " + price[4] + "  =  " + p4);
            label2.setBounds(280, a, 400, 100);
            panel.add(label2);
            label1.setForeground(Color.WHITE);
            label2.setForeground(Color.WHITE);
            a = a + 20;
        }
        if (count5 > 0) {
            JLabel label1 = new JLabel("Desktop:   " + count5);
            label1.setBounds(150, a, 400, 100);
            panel.add(label1);
            p5 = count5 * prices[5];
            JLabel label2 = new JLabel("price " + price[5] + " =  " + p5);
            label2.setBounds(280, a, 400, 100);
            panel.add(label2);
            label1.setForeground(Color.WHITE);
            label2.setForeground(Color.WHITE);
            a = a + 20;
        }
        if (count6 > 0) {
            JLabel label1 = new JLabel("Mobile :   " + count6);
            label1.setBounds(150, a, 400, 100);
            panel.add(label1);
            p6 = count6 * prices[6];
            JLabel label2 = new JLabel("price " + price[6] + "  =  " + p6);
            label2.setBounds(280, a, 400, 100);
            panel.add(label2);
            label1.setForeground(Color.WHITE);
            label2.setForeground(Color.WHITE);
            a = a + 20;
        }
        if (count7 > 0) {
            JLabel label1 = new JLabel("Earbuds:   " + count7);
            label1.setBounds(150, a, 400, 100);
            panel.add(label1);
            p7 = count7 * prices[7];
            JLabel label2 = new JLabel("price " + price[7] + " =  " + p7);
            label2.setBounds(280, a, 400, 100);
            panel.add(label2);
            label1.setForeground(Color.WHITE);
            label2.setForeground(Color.WHITE);
            a = a + 20;
        }
        if (count8 > 0) {
            label1 = new JLabel("Scanner:   " + count8);
            label1.setBounds(150, a, 400, 100);
            panel.add(label1);
            p8 = count8 * prices[8];
            JLabel label2 = new JLabel("price " + price[8] + "  =  " + p8);
            label2.setBounds(280, a, 400, 100);
            panel.add(label2);
            label1.setForeground(Color.WHITE);
            label2.setForeground(Color.WHITE);
            a = a + 20;
        }
        total = p + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;

        button = new JButton("Pay Now");
        button.setBounds(400, 60, 100, 50);
        button.addActionListener(new Checkout());
        panel.add(button);

        JLabel label2 = new JLabel(" Total  =  " + total);
        label2.setBounds(280, a, 400, 100);
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        JButton button2 = new JButton("Back");
        button2.setBounds(10, 10, 70, 20);

        button3 = new JButton("Log Out");
        button3.setBounds(470, 10, 90, 20);

        panel.add(button3);

        panel.add(button2);
        button2.addActionListener(new Checkout());
        button3.addActionListener(new Checkout());

        button.setBackground(Color.CYAN);

        button2.setBackground(Color.CYAN);
        button3.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * It collects the prices of the products from the database.
     *
     * @return the price of the product.
     * @throws SQLException it throws an exception that provides information on
     * a database access error or other errors.
     */
    public int[] get_prices() throws SQLException {
        PreparedStatement pstm = MainMenu.con.prepareStatement("SELECT * FROM prices");
        ResultSet Rs = pstm.executeQuery();
        int i = 0;
        int[] id = new int[100];
        while (Rs.next()) {
            id[i] = Rs.getInt("ID");
            prices[i] = Rs.getInt("Price");
            i++;
        }

        return prices;
    }

    /**
     * This function performs the payment of the products. It confirms the
     * payment if it is completed otherwise the payment is cancelled. It also
     * allows the user to move back to checkout window or logout.
     *
     * @param e It informs about the button selected by user and perform the
     * respective action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        Pay window = new Pay();
        if (e.getSource() == button) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    window.Pay(usrname);
                    frame.setVisible(false);
                    bill(usrname);
                } catch (SQLException ex) {
                    Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Payment Cancelled");

            }

        }
        if (s.equals("Back")) {
            frame.dispose();
            Cart cart = new Cart();
            try {
                cart.basket(name);
            } catch (SQLException ex) {
                Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (s.equals("Log Out")) {
            frame.setVisible(false);
            try {
                MainMenu.main(null);
            } catch (Exception ex) {
                Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
