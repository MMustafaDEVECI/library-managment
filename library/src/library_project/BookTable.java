package library_project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookTable {
    public BookTable(){
        
    }
    public static void showBookInfo(){
        try{
            boolean exist = false;
            ResultSet rs = Database.statement.executeQuery("SELECT title,writer,stockNum,bookId FROM books WHERE stockNum <> 0");
            while (rs.next()){
                int id = rs.getInt("bookId");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                int num = rs.getInt("stockNum");
                System.out.println(id + " " + title + " " +writer + " " + num);
                exist = true;
            }
            rs.close();
            if (!exist)
                System.out.println("There is no available book right now");
        }
        catch(Exception e){
            System.out.println("There is no available book right now");
        }
    }
    public static void checkBookTable() throws SQLException{
        if (!Database.tableExists("books")){
            Database.statement.executeUpdate("CREATE TABLE books(bookId INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(30), writer VARCHAR(30), pageNum INT, publisher VARCHAR(20), stockNum INT)");
            System.out.println("Table has been created");
        }
    }
    public static int getBookId(){
        try{
            System.out.println("Enter the title of the book customer wants to borrow");
            String title = Database.scanned.nextLine();
            System.out.println("Enter the writer of the book customer wants to borrow");
            String writer = Database.scanned.nextLine();
            Database.preStat = Database.myCon.prepareStatement("SELECT bookId FROM books WHERE title = ? AND writer = ?");
            Database.preStat.setString(1, title);
            Database.preStat.setString(2, writer);
            ResultSet rs = Database.preStat.executeQuery();
            int id = 1;
            while(rs.next()){
                id = rs.getInt("bookId");
            }
            return id;
        }
        catch(Exception e){
            System.out.println("There is no book in our library right now");
            return 0;
        }
    
    }
    public static String getFeature(){
        System.out.println("Enter feature of book you want to edit");
        String feature = Database.scanned.next(); 
        Database.scanned.nextLine();
        return feature;
    }
}
