/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

import Main.Cart;
import Main.MainMenu;
import Topup.Balance;
import static Topup.Balance.balance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * This class checks the balance of the user whether he is able to pay the price
 * of selected products or not. After the payment is completed it updates the
 * order history in database. That helps the user to see his previous orders.
 *
 * @author Muhammad Obaid Ullah, Mohammad Ayman, Mubashir Ahmed
 */
public class Pay {

    static Statement stmt1;
    static PreparedStatement stmt;
    static String usrname;

    /**
     * This function performs the payment method of the products and updates the
     * order history respectively.
     *
     * @param username is the username of the user currently logged in.
     * @throws SQLException it throws an exception that provides information on
     * a database access error or other errors.
     */
    public void Pay(String username) throws SQLException {

        if (Checkout.total > Balance.balance) {

            JOptionPane.showMessageDialog(null, "Balance Low. Please top up.");
        }
        if (Checkout.total <= Balance.balance) {
            JOptionPane.showMessageDialog(null, "Payment Successful");
            Pay update = new Pay();
            update_order_history(username);
            Cart.count = 0;
            Cart.count1 = 0;
            Cart.count2 = 0;
            Cart.count3 = 0;
            Cart.count4 = 0;
            Cart.count5 = 0;
            Cart.count6 = 0;
            Cart.count7 = 0;
            Cart.count8 = 0;
            Cart.count9 = 0;
            balance = Balance.balance - Checkout.total;

            stmt = MainMenu.con.prepareStatement("select * from order_history where username=? ");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MainMenu.OrderID = rs.getInt("orderID");
            }

            try {
                stmt = MainMenu.con.prepareStatement("update balance set balance=(?) where username =(?)");
                stmt.setInt(1, balance);
                stmt.setString(2, username);

                stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * This is the function where the updating of the database order history is
     * performed.
     *
     * @param username is the username of the user currently logged in.
     * @throws SQLException it throws an exception that provides information on
     * a database access error or other errors.
     */
    public void update_order_history(String username) throws SQLException {
        Statement stmt2 = MainMenu.con.createStatement();
        int orderID = 0;
        ResultSet resSt = stmt2.executeQuery("select * from order_history where username = '" + username + "'");

        while (resSt.next()) {
            orderID = resSt.getInt("orderID");
        }

        try {
            stmt = MainMenu.con.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, orderID);
            stmt.setInt(2, Cart.count);
            stmt.setInt(3, Cart.count1);
            stmt.setInt(4, Cart.count2);
            stmt.setInt(5, Cart.count3);
            stmt.setInt(6, Cart.count4);
            stmt.setInt(7, Cart.count5);
            stmt.setInt(8, Cart.count6);
            stmt.setInt(9, Cart.count7);
            stmt.setInt(10, Cart.count8);

            stmt.execute();
        } catch (Exception e) {
            stmt = MainMenu.con.prepareStatement("update products set productOne = (?),\n"
                    + " productTwo =(?) , productThree =(?), productFour =(?),\n"
                    + "           productFive=(?), productSix =(?) , productSeven =(?), productEight =(?), productNine =(?)\n"
                    + "           where ID = (?);");
            stmt.setInt(1, Cart.count);
            stmt.setInt(2, Cart.count1);
            stmt.setInt(3, Cart.count2);
            stmt.setInt(4, Cart.count3);
            stmt.setInt(5, Cart.count4);
            stmt.setInt(6, Cart.count5);
            stmt.setInt(7, Cart.count6);
            stmt.setInt(8, Cart.count7);
            stmt.setInt(9, Cart.count8);
            stmt.setInt(10, orderID);
            stmt.executeUpdate();
        }
    }
}
