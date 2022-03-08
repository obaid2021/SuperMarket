package admin;

import Main.MainMenu;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JScrollPane;

/**
 * This class displays the users that are already registered in the application.
 *
 * @author Mohammad Ayman, Mubashir Ahmed, Muhammad Obaid ullah
 */
public class Table extends JFrame {

    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    JScrollPane j = new JScrollPane(jtbl);

    /**
     * This function displays the provided information of all the registered
     * user in rows.
     */
    public Table() {
        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));

        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Year of birth");
        model.addColumn("Firstname");
        model.addColumn("Lastname");
        model.addColumn("Credit ");

        try {

            PreparedStatement pstm = MainMenu.con.prepareStatement("SELECT * FROM user");
            ResultSet Rs = pstm.executeQuery();
            while (Rs.next()) {
                model.addRow(new Object[]{Rs.getString(1), Rs.getString(2), Rs.getInt(3), Rs.getString(4), Rs.getString(5), Rs.getDouble(6)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
        this.pack();
    }
}
