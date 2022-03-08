/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import Main.MainMenu;
import Warnings.Warnings;
import java.awt.Color;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * This class helps admin to change the price of the products. It displays a
 * window where admin can give product Id and its new price to update the
 * product's price.
 *
 * @author Win10 Pro x64
 */
public class Prices extends JFrame implements ActionListener {

    static PreparedStatement stmt;
    Warnings message = new Warnings();
    DefaultTableModel model = new DefaultTableModel();
    Container container = this.getContentPane();
    JTable jtbl = new JTable(model);
    JScrollPane j = new JScrollPane(jtbl);
    static JFrame frame = new JFrame();
    static JTextField text1, text;

    /**
     * This function displays the fields need to be filled by the admin in case
     * of price change and also buttons required to move back or to submit the
     * new price.
     */
    public void priceGUI() {
        frame = new Prices();
        frame.setTitle("Prices Console");
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.GRAY);

        frame.setLayout(null);

        JButton button = new JButton("Change Price");
        button.setBounds(60, 510, 150, 30);
        frame.add(button);

        JButton button1 = new JButton("Back");
        button1.setBounds(250, 510, 100, 30);
        frame.add(button1);

        JLabel label = new JLabel("Type Product ID");
        label.setBounds(70, 430, 100, 30);
        frame.add(label);

        JLabel label1 = new JLabel("Type New Price");
        label1.setBounds(240, 430, 100, 30);
        frame.add(label1);

        text = new JTextField();
        text.setBounds(70, 460, 100, 30);
        frame.add(text);

        text1 = new JTextField();
        text1.setBounds(240, 460, 100, 30);
        frame.add(text1);
        System.out.print("Yes");
        button1.setBackground(Color.CYAN);
        button.setBackground(Color.CYAN);

        button.addActionListener(new Prices());
        button1.addActionListener(new Prices());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This function displays the product ID and prices.
     */
    public Prices() {
        container.setLayout(new FlowLayout(FlowLayout.LEFT));

        model.addColumn("ID ");
        model.addColumn("Price");

        try {

            PreparedStatement pstm = MainMenu.con.prepareStatement("SELECT * FROM prices");
            ResultSet Rs = pstm.executeQuery();
            while (Rs.next()) {
                model.addRow(new Object[]{Rs.getInt(1), Rs.getInt(2)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(jtbl);
        container.add(pg);
        this.pack();
    }

    /**
     * This function performs the price changing process and pops up and error
     * if invalid ID or price is entered.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Change Price")) {
            while (true) {
                String a = text.getText();
                String b = text1.getText();
                int x = 0, y = 0;
                if (a.equals("") || b.equals("")) {
                    message.errorMessage("Enter a valid ID and price");
                    break;
                } else {
                    x = Integer.parseInt(a);
                    y = Integer.parseInt(b);
                }
                if (x < 1 || x > 9) {
                    message.errorMessage("Invalid ID");
                    break;
                } else {
                    int input1 = Integer.parseInt(text.getText());
                    int input2 = Integer.parseInt(text1.getText());

                    try {
                        stmt = MainMenu.con.prepareStatement("update prices set Price=(?) where ID =(?)");
                        stmt.setInt(1, input2);
                        stmt.setInt(2, input1);
                        stmt.executeUpdate();
                        message.errorWarning("Price updated successfully");
                        frame.setVisible(false);

                        priceGUI();
                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(Prices.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
        if (s.equals("Back")) {

            frame.setVisible(false);
            Admin window = new Admin();
            try {
                window.admin();
            } catch (SQLException ex) {
                Logger.getLogger(Prices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
