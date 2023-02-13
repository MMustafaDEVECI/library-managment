package library_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Customer {
    String name;
    String surName;
    public String cell; // primary
    String date;
    public Customer(Scanner scanned){
        System.out.println("Enter the customer's cell phone number (ex. 05051234567)");
        cell = scanned.nextLine();
    }
    public Customer(Connection myCon, String cell){
        System.out.println("Enter customer's name");
        String name = Database.scanned.nextLine();
        System.out.println("Enter customer's surname");
        String surName = Database.scanned.nextLine();
        try {
            PreparedStatement preStat = myCon.prepareStatement("INSERT INTO customers(name,surname,cell,bookId) VALUES(?,?,?,0)");
            preStat.setString(1, name);
            preStat.setString(2, surName);
            preStat.setString(3, cell);
            int i = preStat.executeUpdate();
            System.out.println(i + " customer has been created");
            preStat.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static int getBookId(String cell){
        int id = 0;
        try{
            Database.preStat = Database.myCon.prepareStatement("SELECT bookId FROM customers WHERE cell = ?");
            Database.preStat.setString(1, cell);
            ResultSet rs = Database.preStat.executeQuery();
            while(rs.next()){
                id = rs.getInt("bookId");
                break;
            }
        }
        catch(Exception e){
            System.out.println("This customer isn't registered in system and can't return any book");
        }
        return id;
        
    }
    public static void setBookId(String cell, int id) throws SQLException{
        Database.preStat = Database.myCon.prepareStatement("UPDATE customers SET bookId = ?  WHERE cell = ?");
        Database.preStat.setInt(1, id);
        Database.preStat.setString(2, cell);
        Database.preStat.executeUpdate();
        
    }
}
