/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Warnings.Warnings;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class implements the functions of main menu.<p>
 * Options of login or Register are displayed to user in the form of buttons.
 *
 * @author Muhammad Obaid Ullah , Mubashir Ahmed ,Mohammad Ayman
 */
public class MainMenu implements ActionListener {

    public static Connection con;
    static String input1, input2, input4, input5;
    static int input3, balance;
    static double input6;
    static PreparedStatement stmt;
    private static JFrame frame = new JFrame();

    public static int OrderID = 0;

    private static Image mainmenu;

    /**
     * Main class make a GUI for user and shows two buttons on the screen.<p>
     * By pressing them , another window according to their function will open.
     *
     * @param args an array of command-line arguments for the application
     * @throws SQLException it throws an exception that provides information on
     * a database access error or other errors.
     * @throws IOException it throw the IOException whenever an input or output
     * operation is failed or interpreted.
     */
    public static void main(String[] args) throws SQLException, IOException {
        Warnings error = new Warnings();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Helsinki", "root", "Welcome@123");
        } catch (SQLException e) {
            error.errorWarning("Connection with database was unsuccessful");

            return;
        }

        frame.pack();

        frame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);

        frame.pack();
        frame.setLocation(600, 300);
        frame.setSize(600, 300);
        frame.setBounds(350, 200, 600, 300);

        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);

        JLabel label1 = new JLabel("WELCOME TO ELECTRONIC MARKET");
        label1.setBounds(120, 30, 1000, 100);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Serif", Font.PLAIN, 20));

        panel.add(label1);

        JButton button = new JButton("Login");
        button.setBounds(100, 120, 100, 50);
        button.addActionListener(new MainMenu());
        button.setBackground(Color.CYAN);

        panel.add(button);

        JButton button1 = new JButton("Register");
        button1.setBounds(380, 120, 100, 50);
        panel.add(button1);
        button1.setBackground(Color.CYAN);
        button1.addActionListener(new MainMenu());

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This class takes the user to the respective windows depending on the
     * selection of button, whether it was Login or Register.
     *
     * @param e Tells the application which button is selected by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();
        if (s.equals("Login")) {
            frame.setVisible(false);
            login window = new login();
            try {
                window.login();
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (s.equals("Register")) {
            frame.setVisible(false);
            Register reg = new Register();
            reg.register();
        }

    }
}
