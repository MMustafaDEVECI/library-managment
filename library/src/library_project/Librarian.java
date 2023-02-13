package library_project;

import java.sql.SQLException;
import java.util.Scanner;



public class Librarian { //primary -> id
    String name;
    String surName;
    String cellNumber;
    int salary;
    public Librarian(){
        try{
            Database.scanned = new Scanner(System.in);
            System.out.println("Enter first name of librarian");
            String name = Database.scanned.next();
            Database.scanned.nextLine();
            System.out.println("Enter last name of librarian");
            String surname = Database.scanned.next();
            Database.scanned.nextLine();
            System.out.println("Enter cell number of librarian");
            String cellNumber = Database.scanned.next();
            Database.scanned.nextLine();
            int salary = myErrorHandler.getInt("Enter salary of librarian");
            Database.preStat = Database.myCon.prepareStatement("INSERT INTO librarians(name,surName,cell,salary) VALUES(?,?,?,?)");
            Database.preStat.setString(1, name);
            Database.preStat.setString(2, surname);
            Database.preStat.setString(3, cellNumber);
            Database.preStat.setInt(4, salary);
            Database.preStat.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void editSalary(int id){
        try{
            int newSal = myErrorHandler.getInt("Enter new value of salary");
            Database.scanned.nextLine();
            Database.preStat = Database.myCon.prepareStatement("UPDATE librarians SET salary = ? WHERE id = ?");
            Database.preStat.setInt(1, newSal);
            Database.preStat.setInt(2, id);
            int i = Database.preStat.executeUpdate();
            System.out.println(i + " salary has been updated");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void editName(int id) throws SQLException{
        System.out.println("Enter new value for name");
        String newS = Database.scanned.next();
        Database.scanned.nextLine();
        Database.preStat = Database.myCon.prepareStatement("UPDATE librarians SET name = ? WHERE id = ?");
        Database.preStat.setString(1, newS);
        Database.preStat.setInt(2, id);
        int i = Database.preStat.executeUpdate();
        System.out.println(i + " name has been updated");
    }
    public static void editSurname(int id) throws SQLException{
        System.out.println("Enter new value for surname");
        String newS = Database.scanned.next();
        Database.scanned.nextLine();
        Database.preStat = Database.myCon.prepareStatement("UPDATE librarians SET surname = ? WHERE id = ?");
        Database.preStat.setString(1, newS);
        Database.preStat.setInt(2, id);
        int i = Database.preStat.executeUpdate();
        System.out.println(i + " surname has been updated");
    }
    public static void editCell(int id) throws SQLException{
        System.out.println("Enter new value for cell phone");
        String newS = Database.scanned.next();
        Database.scanned.nextLine();
        Database.preStat = Database.myCon.prepareStatement("UPDATE librarians SET cell = ? WHERE id = ?");
        Database.preStat.setString(1, newS);
        Database.preStat.setInt(2, id);
        int i = Database.preStat.executeUpdate();
        System.out.println(i + " cell has been updated");
    }
    public static void delete() throws SQLException{
        boolean check1 = LibrarianTable.showLibrarianInfo();
        if (check1){
            int id = myErrorHandler.getInt("Enter id of a person you want to delete");
            Database.scanned.nextLine();
            boolean check = myErrorHandler.checkId(id);
            if (check){
                Database.preStat = Database.myCon.prepareStatement("delete from librarians where id = ?");
                Database.preStat.setInt(1,id);
                int i = Database.preStat.executeUpdate();
                System.out.println(i + " removals has been made");
            }
            else{
                System.out.println("There is no person that has the id " + id + " in our library");
            }
        }
    }
}
