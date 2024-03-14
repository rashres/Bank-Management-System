package Bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.event.*;


public class SignupTwo extends JFrame implements ActionListener{

     JTextField pan, SSN;
    JButton next;
    JRadioButton syes, sno, eyes, eno;
    JComboBox religion, category, income, employment, education;
    String formNo;

    SignupTwo(String formNo){
        super();

        this.formNo = formNo;
        setLayout(null);

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel aDetails = new JLabel("Page 2: Additional Details" );
        aDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        aDetails.setBounds(290,80,400,30);
        add(aDetails);

        JLabel relig = new JLabel("Religion: " );
        relig.setFont(new Font("Raleway", Font.BOLD, 20));
        relig.setBounds(100,140,100,30);
        add(relig);

        String valReligion[] = {"Christian", "Hispanic", "Latin", "Hindu", "Muslim", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300,140,400,30);
        religion.setBackground(Color.WHITE);
        add(religion);

        JLabel categ = new JLabel("Category: " );
        categ.setFont(new Font("Raleway", Font.BOLD, 20));
        categ.setBounds(100,190,200,30);
        add(categ);

        String valCategory[] = {"General", "OBC", "SC", "ST", "Other"};
        category = new JComboBox(valCategory);
        category.setBounds(300,190,400,30);
        category.setBackground(Color.WHITE);
        add(category);

        JLabel inc = new JLabel("Income: " );
        inc.setFont(new Font("Raleway", Font.BOLD, 20));
        inc.setBounds(100,240,200,30);
        add(inc);

        String incomeCategory[] = {"Null", "< $20,000", "< $40,000", "< $80,000", "upto $100,000"};
        income = new JComboBox(incomeCategory);
        income.setBounds(300,240,400,30);
        income.setBackground(Color.WHITE);
        add(income);

        JLabel educat = new JLabel("Educational" );
        educat.setFont(new Font("Raleway", Font.BOLD, 20));
        educat.setBounds(100,290,200,30);
        add(educat);


        JLabel email = new JLabel("Qualification: " );
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100,315,200,30);
        add(email);

        String educationalCategory[] = {"High School or Equivalent", "Associates", "Bachelors", "Masters", "PHD", "Others"};
        education = new JComboBox(educationalCategory);
        education.setBounds(300,315,400,30);
        education.setBackground(Color.WHITE);
        add(education);

        JLabel empl = new JLabel("Employment: " );
        empl.setFont(new Font("Raleway", Font.BOLD, 20));
        empl.setBounds(100,390,200,30);
        add(empl);

        String employmentCategory[] = {"Student", "Employed", "Unemployed", "Self-Employed","Others"};
        employment = new JComboBox(employmentCategory);
        employment.setBounds(300,390,400,30);
        employment.setBackground(Color.WHITE);
        add(employment);

        JLabel panNO = new JLabel("PAN Number: " );
        panNO.setFont(new Font("Raleway", Font.BOLD, 20));
        panNO.setBounds(100,440,200,30);
        add(panNO);

        pan = new JTextField();
        pan.setFont(new Font("Raleway", Font.BOLD,14));
        pan.setBounds(300,440,400,30);
        add(pan);

        JLabel szn = new JLabel("SSN: " );
        szn.setFont(new Font("Raleway", Font.BOLD, 20));
        szn.setBounds(100,490,200,30);
        add(szn);

        SSN = new JTextField();
        SSN.setFont(new Font("Raleway", Font.BOLD,14));
        SSN.setBounds(300,490,400,30);
        add(SSN);

        JLabel scitez = new JLabel("Senior Citizen: " );
        scitez.setFont(new Font("Raleway", Font.BOLD, 20));
        scitez.setBounds(100,540,200,30);
        add(scitez);

        syes = new JRadioButton("Yes");
        syes.setBounds(300,540,100,30);
        // syes.setBackground(Color.WHITE);
        add(syes);

        sno = new JRadioButton("No");
        sno.setBounds(450,540,100,30);
        // sno.setBackground(Color.WHITE);
        add(sno);

        ButtonGroup sencit = new ButtonGroup();
        sencit.add(syes);
        sencit.add(sno);

        JLabel exsa = new JLabel("Existing Account: " );
        exsa.setFont(new Font("Raleway", Font.BOLD, 20));
        exsa.setBounds(100,590,200,30);
        add(exsa);

        eyes = new JRadioButton("Yes");
        eyes.setBounds(300,590,100,30);
        // eyes.setBackground(Color.WHITE);
        add(eyes);

        eno = new JRadioButton("No");
        eno.setBounds(450,590,100,30);
        // eno.setBackground(Color.WHITE);
        add(eno);

        ButtonGroup existinacc = new ButtonGroup();
        existinacc.add(syes);
        existinacc.add(sno);

        next = new JButton("Next");
        // next.setBackground(Color.BLACK);
        // next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setLocation(350,10);
        setVisible(true);

    }
    @java.lang.Override
    public void actionPerformed(java.awt.event.ActionEvent actionEvent) {

        String sReligion = (String) religion.getSelectedItem();
        String sCategory = (String) category.getSelectedItem();
        String sIncome = (String) income.getSelectedItem();
        String sEducation = (String) education.getSelectedItem();
        String sEmployment = (String) employment.getSelectedItem();

        java.lang.String seniorcitizen = null;
        if (syes.isSelected()){
            seniorcitizen = "Yes";
        }
        else if(sno.isSelected()){
            seniorcitizen = "No";
        }

        java.lang.String existingaccount =  null;
        if (eyes.isSelected()){
            existingaccount = "Yes";
        }
        else if (sno.isSelected()){
            existingaccount = "No";
        }

        String sPan = pan.getText();
        String sSSN = SSN.getText();

        try {
            Conn c = new Conn();
            String query = "insert into signupTwo values ('"+formNo+"', '"+sReligion+"', '"+sCategory+"','"+sIncome+"','"+sEducation+"','"+sEmployment+"','"+sPan+"', '"+sSSN+"', '"+seniorcitizen+"', '"+existingaccount+"')";
            c.s.executeUpdate(query);

            setVisible(false);
            new SignupThree(formNo).setVisible(true);
        }

        // need to create an object for SignUp 3

        catch (java.lang.Exception e) {
            e.printStackTrace(); // Use printStackTrace() to print the exception stack trace
        }
    }

    public static void main(String args[]){
        new SignupTwo(" ");
    }
}
