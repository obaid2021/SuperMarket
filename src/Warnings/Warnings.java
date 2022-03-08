/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warnings;

import java.awt.Color;

import javax.swing.JOptionPane;

import javax.swing.UIManager;

/**
 * This class specifies the window of error message and its colour.
 *
 * @author Muhammad Obaid Ullah, Mohammad Ayman, Mubashir Ahmed
 */
public class Warnings {

    /**
     * It Displays the error window on screen.
     *
     * @param error is the error message respective to the error performed.
     */
    public void errorMessage(String error) {

        UIManager.put("OptionPane.background", Color.GRAY);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        Object put = UIManager.put("Panel.background", Color.GRAY);
        JOptionPane.showMessageDialog(null, "  " + error, "ERROR", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * It Displays the error window on screen.
     *
     * @param error is the error message respective to the error performed.
     */
    public void errorWarning(String error) {
        UIManager.put("OptionPane.background", Color.GRAY);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        Object put = UIManager.put("Panel.background", Color.GRAY);
        JOptionPane.showMessageDialog(null, " " + error);

    }

}
