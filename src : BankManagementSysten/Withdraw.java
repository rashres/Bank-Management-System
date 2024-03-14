package Bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdraw, back;
    String pinnumber;

    Withdraw(String pinnumber){

        this.pinnumber = pinnumber;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(372, 485, 150, 35);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(372, 520, 150, 35);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw){
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you wish to withdraw");
            }
            else {
                try {
                    Conn conn = new Conn();
                    String query = "insert into bank values('" +pinnumber+"', '"+date+"', 'Withdraw', '"+number+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "$"+number+" Withdrawn Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }
                catch (java.lang.Exception e) {
                    e.printStackTrace(); // Use printStackTrace() to print the exception stack trace
                }
            }
        }
        else if (ae.getSource() == back){
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]){
        new Withdraw("");
    }
}
