package Bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton fifty, seventy, twofifty, fivehundred, hundred, thousand, back;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT ");
        text.setBounds(220, 300, 300, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        fifty = new JButton("$ 50");
        fifty.setBounds(150, 415, 150, 35);
        fifty.addActionListener(this);
        image.add(fifty);

        seventy = new JButton("$ 70");
        seventy.setBounds(372, 415, 150, 35);
        seventy.addActionListener(this);
        image.add(seventy);

        hundred = new JButton("$ 100");
        hundred.setBounds(150, 450, 150, 35);
        hundred.addActionListener(this);
        image.add(hundred);

        twofifty = new JButton("$ 250");
        twofifty.setBounds(372, 450, 150, 35);
        twofifty.addActionListener(this);
        image.add(twofifty);

        fivehundred = new JButton("$ 500");
        fivehundred.setBounds(150, 485, 150, 35);
        fivehundred.addActionListener(this);
        image.add(fivehundred);

        thousand = new JButton("$ 1000");
        thousand.setBounds(372, 485, 150, 35);
        thousand.addActionListener(this);
        image.add(thousand);

        back = new JButton("BACK");
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
        if (ae.getSource() ==  back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
        else {
           String amount = ((JButton) ae.getSource()).getText().substring(2);
           Conn c = new Conn();

           try{
               ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
               int balance = 0;
               while(rs.next()) {
                   if (rs.getString("type").equals("Deposit")) {
                       balance += Integer.parseInt(rs.getString("amount"));
                   }
                   else {
                       balance -= Integer.parseInt(rs.getString("amount"));
                   }
               }

               if (ae.getSource() != back && balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null,"Insufficient Balance");
                        return;
               }

               Date date = new Date();
               String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Withdrawl', '"+amount+"')";
               c.s.executeUpdate(query);
               JOptionPane.showMessageDialog(null, "$" + amount + "Withdrawn Successfully");

               setVisible(false);
               new Transactions(pinnumber).setVisible(true);
           }
           catch (java.lang.Exception e) {
               e.printStackTrace(); // Use printStackTrace() to print the exception stack trace
           }
        }
    }

    public static void main(String args[]) {
        new FastCash("");
    }
}

