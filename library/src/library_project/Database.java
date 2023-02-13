package library_project;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Database {
    static Connection myCon;
    public static Scanner scanned;
    static Statement statement;
    static PreparedStatement preStat;
    static int input = -1;
    public Database(){
        try{
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","deveci","password");
            statement = myCon.createStatement();
            scanned = new Scanner(System.in);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void menu(){
        System.out.println("Choose what do you want to do");
        System.out.println("1 - Borrow Book");
        System.out.println("2 - Return Book");
        System.out.println("3 - Show Book Info");
        System.out.println("4 - Add Book");
        System.out.println("5 - Edit Librarian");
        System.out.println("6 - Add Librarian");
        System.out.println("7 - Delete Librarian");
        System.out.println("8 - Edit Book");
        System.out.println("9 - Delete Book");
        System.out.println("0 - End Program");
        input = myErrorHandler.getInt();
    }
    public static String checkCustTable() throws SQLException{
        if (!tableExists("customers")){
            new CustomerTable(statement);
        }
        Customer cust = new Customer(Database.scanned);
        if(!CustomerTable.CheckCust(preStat,myCon,cust.cell)){
            new Customer(myCon, cust.cell);
        }
        return cust.cell;
    }
    public static void borrow(String cell){
        try{
            int id = Customer.getBookId(cell);
            if (id != 0){
                System.out.println("Customer needs to return your book first to borrow new book");
            }
            else{
                if (tableExists("books")){
                    if (myErrorHandler.checkAnyBookExist())
                    {
                        BookTable.showBookInfo();
                        id = myErrorHandler.getInt("Enter the id of book customer wants to borrow");
                        boolean check = myErrorHandler.checkBookId(id);
                        if (check){
                            Customer.setBookId(cell, id);
                            Book.setStockNumDown(id);
                        }
                        else{
                            System.out.println("There is no book that has the id" + " " + id + " in our library");
                        }
                    }
                    else{
                        System.out.println("There is no book that you can borrow");
                    }
                }
                else{
                    System.out.println("Since there is no book in our library, you can't borrow a book right now");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static boolean tableExists(String name){
        try{
            DatabaseMetaData dbm = myCon.getMetaData();
            ResultSet rs = dbm.getTables(null, null, name, null);
            boolean exists = false;
            while (rs.next()) {
                String table = rs.getString("TABLE_NAME");
                if (name.equals(table)) {
                    exists = true;
                    break;
                }
            }
            return exists;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    public static void close() throws SQLException{
        if (myCon != null) myCon.close();
        if (preStat != null) preStat.close();
        if (scanned != null)scanned.close();
        if (statement != null)statement.close();
    }
}