/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javafinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author bunth
 */
public class JavaFinal {
    public static List<Question> questionList = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new login().setVisible(true);
    }
   
}
