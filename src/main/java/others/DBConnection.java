package main.java.others;

import java.sql.*;
public class DBConnection {
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/inventory";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bishal","root","root");
            return connection;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}