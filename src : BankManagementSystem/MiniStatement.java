package Bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {

        setTitle("MINI STATEMENT");

        setLayout(null);

        JLabel mini = new JLabel();
        add(mini);

        JLabel bank = new JLabel("AMERICAN BANK");
        bank.setFont(new Font("System", Font.BOLD, 12));
        bank.setBounds(150, 20, 150, 20);
        add(bank);

        JLabel card = new JLabel("Card Number:");
        card.setBounds(20, 80, 200, 20);
        add(card);

        JLabel cardNumber = new JLabel(); // Creating a label for displaying the card number
        cardNumber.setBounds(140, 80, 800, 20);
        add(cardNumber);

        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
            while (rs.next()){
                String cardNum = rs.getString("cardnumber");
                // Check if the card number is not null and has at least 16 characters
                if (cardNum != null && cardNum.length() >= 16) {
                    cardNumber.setText(cardNum.substring(0, 4) + "XXXXXXXX" + cardNum.substring(12)); // Display only the first 4 and last 4 digits of the card number
                } else {
                    cardNumber.setText("Invalid card number"); // Display a message if the card number is invalid
                }
            }
        }
        catch (java.lang.Exception e) {
            e.printStackTrace(); // Use printStackTrace() to print the exception stack trace
        }

        try {
            Conn conn = new Conn();
            int bal = 0;

            ResultSet rs = conn.s.executeQuery("Select * from bank where pin = '"+pinnumber+"'");
            while (rs.next()) {
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") +"<br><br><html>");
                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                }
                else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your current account balance is $ " + bal);
        }
        catch (java.lang.Exception e) {
            e.printStackTrace(); // Use printStackTrace() to print the exception stack trace
        }

        mini.setBounds(20, 140, 400, 200);

        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new MiniStatement("");
    }
}
