package Bank.management.system;

import java.sql.*;

//Register the Driver
//Create connection
//Create statement
//Execute Query
//Close connection

public class Conn {

    Connection c;
    Statement s;

    public Conn(){
        try{
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","rahul123");
            s = c.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
