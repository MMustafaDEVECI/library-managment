package library_project;

import java.sql.ResultSet;
import java.sql.SQLException;


public class myErrorHandler {
    public static boolean checkBookId(int id){
        //how to check if it exists
        boolean check = false;
        try {
            Database.preStat = Database.myCon.prepareStatement("SELECT bookId FROM books WHERE bookId = ? AND stockNum <> 0");
            Database.preStat.setInt(1, id);
            ResultSet rs = Database.preStat.executeQuery();
            while (rs.next()){
                check = true;
                break;
            }
        } catch (SQLException e) {
            return false;
        } 
        return check;
    }
    public static boolean checkId(int id){
        boolean check = false;
        try {
            Database.preStat = Database.myCon.prepareStatement("SELECT id FROM librarians WHERE id = ?");
            Database.preStat.setInt(1, id);
            ResultSet rs = Database.preStat.executeQuery();
            while (rs.next()){
                check = true;
                break;
            }
        } catch (SQLException e) {
            return false;
        } 
        return check;
    }
    public static int getInt(){
        int value = 0;
        while(0 >= value)
        {
            try{
                value = Database.scanned.nextInt();
                Database.scanned.nextLine();
                break;
            }
            catch(Exception e){
                System.out.println("You have to enter integer");
                Database.scanned.nextLine();
            }
        }
        return value;
    }
    public static int getInt(String askInput){
        int value = 0;
        while(0 >= value)
        {
            try{
                System.out.println(askInput);
                value = Database.scanned.nextInt();
                break;
            }
            catch(Exception e){
                System.out.println("You have to enter integer");
                Database.scanned.nextLine();
            }
        }
        return value;
    }
    public static boolean checkAnyBookExist(){
        boolean check = false;
        try {
            Database.preStat = Database.myCon.prepareStatement("SELECT * FROM books WHERE stockNum <> 0");
            ResultSet rs = Database.preStat.executeQuery();
            if (rs.next()){
                check = true;
            }
            else{
                //System.out.println("There is no book in our library right now");
            }
        } catch (SQLException e) {
            return false;
        } 
        return check;
    }
    public static boolean checkAnyLibrarianExist(){
        boolean check = false;
        try {
            Database.preStat = Database.myCon.prepareStatement("SELECT * FROM librarians");
            ResultSet rs = Database.preStat.executeQuery();
            if (rs.next()){
                check = true;
            }
            else{
                System.out.println("There is no librarian in our library right now");
            }
        } catch (SQLException e) {
            return false;
        } 
        return check;
    }
    public static String getString(String askInput){
        String value = "";
        while(value.length() <= 3)
        {
            System.out.println(askInput);
            value = Database.scanned.nextLine();
            if(value.length() <= 3)
                System.out.println("You have to enter string longer than 3 characters");

        }
        return value;    
    }
}
