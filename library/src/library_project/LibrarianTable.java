package library_project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianTable {
    public LibrarianTable() throws SQLException{
        if (Database.tableExists("librarians")){
            new Librarian();
        }
        else{
            Database.statement.executeUpdate("CREATE TABLE librarians(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), surName VARCHAR(20), cell VARCHAR(20), salary INT)");
            new Librarian();
        }
    }
    public static boolean showLibrarianInfo(){
        boolean check = false;
        try{
            ResultSet rs = Database.statement.executeQuery("SELECT * FROM librarians");
            while (rs.next()){
                int Id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String num = rs.getString("cell");
                int salary = rs.getInt("salary");
                System.out.println( Id + " " + name + " " + surname + " " + num + " " + salary);
                check = true;
            }
            rs.close();
        }
        catch(Exception e){
            System.out.println("There is librarian registered to system");
        }
        return check;
    }
    public static int getId(){
        int choice = myErrorHandler.getInt("Enter id of employee you want to edit");
        Database.scanned.nextLine();
        return choice;
    }
    public static String getFeature(){
        System.out.println("Enter feature of employee you want to edit");
        String feature = Database.scanned.next(); 
        Database.scanned.nextLine();
        return feature;
    }
}
