/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Orders.PreviousOrders;
import Payment.Checkout;

import Topup.Balance;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class "Cart" enables the user to select the required product. It has the
 * specific number of products and their prices that are available in market. It
 * also contains the option of Balance where user can check his balance and can
 * also top up if required. Other than that user is also able to check previous
 * orders and can checkout and logout once the shopping is done.
 *
 * @author Muhammad Obaid Ulla, Mohammad Ayman, Mubahir Ahmed
 */
public class Cart extends MainMenu implements ActionListener {

    public static JLabel text, text1, text2, text3, text4, text5, text6, text7, text8;
    public static JLabel label, label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10;

    static String user;

    public static int count = 0, count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0;
    public static int count8 = 0, count9 = 0, count10 = 0, count11 = 0;
    static JButton button, button1, button2, button3, button4, button5, button6, button7;
    static JButton button8, button9, button10, button11, button12, button13, button14, button15, button16, button17, button18, button19;
    static JButton button20, button21;
    public static String usrname;
    static JFrame frame;

    /**
     * This class displays the adding and removing button with which user can
     * add or remove products from cart.It shows the quantity of products
     * selected and also the name of products and their prices. Moreover, it is
     * also displaying the Checkout,Balance,Logout and Previous order buttons.
     *
     * @param username is the username of the user currently using the cart.
     * @throws SQLException is an exception that provides information on a
     * database access error or other errors.
     */
    public void basket(String username) throws SQLException {
        usrname = username;
        frame = new JFrame("CART");
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        count = 0;
        count1 = 0;
        count2 = 0;
        count3 = 0;
        count4 = 0;
        count5 = 0;
        count6 = 0;
        count7 = 0;
        count8 = 0;
        count9 = 0;
        count10 = 0;
        count11 = 0;
        frame.setSize(600, 500);
        frame.setBounds(300, 10, 660, 660);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
        label = new JLabel("Select a Product");
        label.setBounds(100, 10, 100, 50);
        panel.add(label);
        Checkout prices = new Checkout();
        int price[] = prices.get_prices();
        System.out.print("received ");
        for (int j = 0; j < 10; j++) {
            System.out.print(price[j]);
        }
        label1 = new JLabel("Price");
        label1.setBounds(230, 10, 100, 50);
        panel.add(label1);

        label10 = new JLabel("Quantity");
        label10.setBounds(300, 10, 100, 50);
        panel.add(label10);

        label2 = new JLabel("" + price[0]);
        label2.setBounds(230, 60, 100, 50);
        panel.add(label2);

        label3 = new JLabel("" + price[1]);
        label3.setBounds(230, 120, 100, 50);
        panel.add(label3);

        label4 = new JLabel("" + price[2]);
        label4.setBounds(230, 180, 100, 50);
        panel.add(label4);

        label5 = new JLabel("" + price[3]);
        label5.setBounds(230, 240, 100, 50);
        panel.add(label5);

        label6 = new JLabel("" + price[4]);
        label6.setBounds(230, 300, 100, 50);
        panel.add(label6);

        label7 = new JLabel("" + price[5]);
        label7.setBounds(230, 360, 100, 50);
        panel.add(label7);

        label8 = new JLabel("" + price[6]);
        label8.setBounds(230, 420, 100, 50);
        panel.add(label8);

        label9 = new JLabel("" + price[7]);
        label9.setBounds(230, 480, 100, 50);
        panel.add(label9);

        label0 = new JLabel("" + price[8]);
        label0.setBounds(230, 540, 100, 50);
        panel.add(label0);

        button = new JButton("Laptop");
        button.setBounds(100, 60, 100, 50);
        button.addActionListener(new Cart());
        panel.add(button);
        //
        text = new JLabel(" " + count);
        text.setBounds(300, 70, 50, 30);
        panel.add(text);
        //
        text1 = new JLabel(" " + count1);
        text1.setBounds(300, 130, 50, 30);
        panel.add(text1);

        button1 = new JButton("Monitor");
        button1.setBounds(100, 120, 100, 50);
        button1.addActionListener(new Cart());
        panel.add(button1);

        text2 = new JLabel("" + count2);
        text2.setBounds(300, 190, 50, 30);
        panel.add(text2);

        button2 = new JButton("Keyboard");
        button2.setBounds(100, 180, 100, 50);
        button2.addActionListener(new Cart());
        panel.add(button2);

        text3 = new JLabel(" " + count3);;
        text3.setBounds(300, 250, 50, 30);
        panel.add(text3);

        button3 = new JButton("Mouse");
        button3.setBounds(100, 240, 100, 50);
        button3.addActionListener(new Cart());
        panel.add(button3);

        text4 = new JLabel(" " + count4);;
        text4.setBounds(300, 310, 50, 30);
        panel.add(text4);

        button4 = new JButton("Printer");
        button4.setBounds(100, 300, 100, 50);
        button4.addActionListener(new Cart());
        panel.add(button4);

        text5 = new JLabel(" " + count5);;
        text5.setBounds(300, 370, 50, 30);
        panel.add(text5);

        button5 = new JButton("Desktop");
        button5.setBounds(100, 360, 100, 50);
        button5.addActionListener(new Cart());
        panel.add(button5);

        text6 = new JLabel("" + count6);;
        text6.setBounds(300, 430, 50, 30);
        panel.add(text6);

        button6 = new JButton("Mobile");
        button6.setBounds(100, 420, 100, 50);
        button6.addActionListener(new Cart());
        panel.add(button6);

        text7 = new JLabel(count7 + "");
        text7.setBounds(300, 490, 50, 30);
        panel.add(text7);

        button7 = new JButton("Earbuds");
        button7.setBounds(100, 480, 100, 50);
        button7.addActionListener(new Cart());
        panel.add(button7);

        text8 = new JLabel(" " + count8);
        text8.setBounds(300, 550, 50, 30);
        panel.add(text8);

        button8 = new JButton("Scanner");
        button8.setBounds(100, 540, 100, 50);
        button8.addActionListener(new Cart());
        panel.add(button8);

        button10 = new JButton("-");
        button10.setBounds(370, 70, 40, 20);
        button10.addActionListener(new Cart());
        panel.add(button10);

        button11 = new JButton("-");
        button11.setBounds(370, 130, 40, 20);
        button11.addActionListener(new Cart());
        panel.add(button11);

        button12 = new JButton("-");
        button12.setBounds(370, 190, 40, 20);
        button12.addActionListener(new Cart());
        panel.add(button12);

        button13 = new JButton("-");
        button13.setBounds(370, 250, 40, 20);
        button13.addActionListener(new Cart());
        panel.add(button13);

        button14 = new JButton("-");
        button14.setBounds(370, 310, 40, 20);
        button14.addActionListener(new Cart());
        panel.add(button14);

        button15 = new JButton("-");
        button15.setBounds(370, 370, 40, 20);
        button15.addActionListener(new Cart());
        panel.add(button15);

        button16 = new JButton("-");
        button16.setBounds(370, 430, 40, 20);
        button16.addActionListener(new Cart());
        panel.add(button16);

        button17 = new JButton("-");
        button17.setBounds(370, 490, 40, 20);
        button17.addActionListener(new Cart());
        panel.add(button17);

        button18 = new JButton("-");
        button18.setBounds(370, 550, 40, 20);
        button18.addActionListener(new Cart());
        panel.add(button18);

        button21 = new JButton("Previous Order");
        button21.setBounds(450, 130, 150, 50);
        panel.add(button21);

        button20 = new JButton("Log Out");
        button20.setBounds(450, 200, 150, 50);
        panel.add(button20);

        button9 = new JButton("Checkout");
        button9.setBounds(450, 270, 150, 50);
        panel.add(button9);

        button19 = new JButton("Balance");
        button19.setBounds(450, 340, 150, 50);
        panel.add(button19);
        button19.addActionListener(new Cart());
        button9.addActionListener(new Cart());
        button20.addActionListener(new Cart());
        button21.addActionListener(new Cart());
        coloring();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This function executes the process of adding and removing products.
     * Depending on the buttons user is using to operate the application. It is
     * also directing user to other windows for example checkout,logout or
     * previous orders.
     *
     * @param e is the action command that directs the application to the
     * product that is selected to be removed from cart.
     */
    @Override

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Laptop")) {
            count++;
            text.setText(" " + count);
        }
        if ("Monitor".equals(s)) {
            count1++;
            text1.setText(" " + count1);

        }
        if ("Keyboard".equals(s)) {
            count2++;
            text2.setText(" " + count2);

        }
        if ("Mouse".equals(s)) {
            count3++;
            text3.setText(" " + count3);

        }
        if ("Printer".equals(s)) {
            count4++;
            text4.setText(" " + count4);

        }
        if ("Desktop".equals(s)) {
            count5++;
            text5.setText(" " + count5);

        }
        if ("Mobile".equals(s)) {
            count6++;
            text6.setText(" " + count6);

        }
        if ("Earbuds".equals(s)) {
            count7++;
            text7.setText(" " + count7);

        }
        if ("Scanner".equals(s)) {
            count8++;
            text8.setText(" " + count8);

        }
        if (e.getSource() == button10) {
            if (count > 0) {
                count--;
                text.setText(" " + count);
            }
        }
        if (e.getSource() == button11) {

            if (count1 > 0) {
                System.out.print(count1);
                count1--;
                text1.setText(" " + count1);
            }
        }
        if (e.getSource() == button12) {
            if (count2 > 0) {
                System.out.print(count2);
                count2--;
                text2.setText(" " + count2);
            }
        }
        if (e.getSource() == button13) {
            System.out.print(count3);
            if (count3 > 0) {
                count3--;
                text3.setText(" " + count3);
            }
        }
        if (e.getSource() == button14) {
            if (count4 > 0) {
                count4--;
                text4.setText(" " + count4);
            }
        }
        if (e.getSource() == button15) {
            System.out.print(" " + count5);
            if (count5 > 0) {
                count5--;
                text5.setText(" " + count5);
            }
        }
        if (e.getSource() == button16) {
            if (count6 > 0) {

                count6--;
                text6.setText(" " + count6);
            }
        }
        if (e.getSource() == button17) {
            if (count7 > 0) {
                count7--;
                text7.setText(" " + count7);
            }
        }
        if (e.getSource() == button18) {
            if (count8 > 0) {
                count8--;
                text8.setText(" " + count8);
            }
        }

        if ("Checkout".equals(s)) {
            frame.setVisible(false);
            Checkout window0 = new Checkout();
            try {
                window0.bill(usrname);
            } catch (SQLException ex) {
                Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ("Log Out".equals(s)) {
            frame.setVisible(false);
            try {
                login window = new login();
                window.login();
            } catch (IOException ex) {
                Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == button19) {
            Balance window1 = new Balance();
            try {
                window1.balance(usrname);
            } catch (SQLException ex) {
                Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (s.equals("Previous Order")) {
            PreviousOrders window2;
            window2 = new PreviousOrders();
            try {
                window2.previous_order(usrname);
            } catch (SQLException ex) {
                Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * This function is assigning colours to the window of cart.
     */
    public void coloring() {
        button.setBackground(Color.CYAN);
        button1.setBackground(Color.CYAN);
        button2.setBackground(Color.CYAN);
        button3.setBackground(Color.CYAN);
        button4.setBackground(Color.CYAN);
        button5.setBackground(Color.CYAN);
        button6.setBackground(Color.CYAN);
        button7.setBackground(Color.CYAN);
        button8.setBackground(Color.CYAN);
        button9.setBackground(Color.CYAN);
        button10.setBackground(Color.CYAN);
        button11.setBackground(Color.CYAN);
        button12.setBackground(Color.CYAN);
        button13.setBackground(Color.CYAN);
        button14.setBackground(Color.CYAN);
        button15.setBackground(Color.CYAN);
        button16.setBackground(Color.CYAN);
        button17.setBackground(Color.CYAN);
        button18.setBackground(Color.CYAN);
        button19.setBackground(Color.CYAN);
        button20.setBackground(Color.CYAN);
        button21.setBackground(Color.CYAN);

        label.setForeground(Color.WHITE);
        label0.setForeground(Color.WHITE);
        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        label4.setForeground(Color.WHITE);
        label5.setForeground(Color.WHITE);
        label6.setForeground(Color.WHITE);
        label7.setForeground(Color.WHITE);
        label8.setForeground(Color.WHITE);
        label9.setForeground(Color.WHITE);
        label10.setForeground(Color.WHITE);

        text.setForeground(Color.WHITE);
        text1.setForeground(Color.WHITE);
        text2.setForeground(Color.WHITE);
        text3.setForeground(Color.WHITE);
        text4.setForeground(Color.WHITE);
        text5.setForeground(Color.WHITE);
        text6.setForeground(Color.WHITE);
        text7.setForeground(Color.WHITE);
        text8.setForeground(Color.WHITE);

    }
}
