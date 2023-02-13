package library_project;

import java.sql.SQLException;

public class Book { // primary id
    String title;
    String writer;
    int pageNum;
    String publisher;
    int stockNum;
    public Book()
    {
        this.title = myErrorHandler.getString("Enter title of book");
        this.writer = myErrorHandler.getString("Enter full name of writer of book");
        this.pageNum = myErrorHandler.getInt("Enter page number of book");
        Database.scanned.nextLine();
        this.publisher = myErrorHandler.getString("Enter publisher of book");
        this.stockNum = myErrorHandler.getInt("Enter stock number of book");
    }
    public void bookInsert(){
        try{
            Database.preStat = Database.myCon.prepareStatement("INSERT INTO books(title,writer,pageNum,publisher,stockNum) VALUES(?,?,?,?,?)");
            Database.preStat.setString(1, this.title);
            Database.preStat.setString(2, this.writer);
            Database.preStat.setInt(3, this.pageNum);
            Database.preStat.setString(4, this.publisher);
            Database.preStat.setInt(5, this.stockNum);
            int i = Database.preStat.executeUpdate();
            System.out.println( this.stockNum + " copy of " + i + " book has been added");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void setStockNumDown(int id) throws SQLException{
        Database.preStat = Database.myCon.prepareStatement("UPDATE books SET stockNum = stockNum - 1 WHERE bookId = ? ");
        Database.preStat.setInt(1, id);
        int i = Database.preStat.executeUpdate();
        System.out.println(i + " borrow has been made");
    }
    public static void setStockNumUp(int id){
        try{
            Database.preStat = Database.myCon.prepareStatement("UPDATE books SET stockNum = stockNum + 1 WHERE bookId = ? ");
            Database.preStat.setInt(1, id);
            int i = Database.preStat.executeUpdate();
            System.out.println(i + "  return has been made");    
        }
        catch(Exception e){
            System.out.println("This book doesn't belong our library, you can't return it");
        }
    }
    public static void editPageNum(int id) throws SQLException{
        int newPage = myErrorHandler.getInt("Enter new value of pageNum");
        Database.scanned.nextLine();
        Database.preStat = Database.myCon.prepareStatement("UPDATE books SET pageNum = ? WHERE bookId = ?");
        Database.preStat.setInt(1, newPage);
        Database.preStat.setInt(2, id);
        int i = Database.preStat.executeUpdate();
        System.out.println(i + " pageNum has been updated");
}
    public static void editPublisher(int id){
        try{
            System.out.println("Enter new value for publisher");
            String newS = Database.scanned.next();
            Database.scanned.nextLine();
            Database.preStat = Database.myCon.prepareStatement("UPDATE books SET publisher = ? WHERE bookId = ?");
            Database.preStat.setString(1, newS);
            Database.preStat.setInt(2, id);
            int i = Database.preStat.executeUpdate();
            System.out.println(i + " publisher has been updated");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void editStockNum(int id) throws SQLException{
        int newS = myErrorHandler.getInt("Enter new value for stock number");
        if (newS != 0){
            Database.scanned.nextLine();
            Database.preStat = Database.myCon.prepareStatement("UPDATE books SET stock number = ? WHERE bookId = ?");
            Database.preStat.setInt(1, newS);
            Database.preStat.setInt(2, id);
            int i = Database.preStat.executeUpdate();
            System.out.println(i + " stock number has been updated");
        }
    }
    public static void delete() throws SQLException{
        if (myErrorHandler.checkAnyBookExist()){
            BookTable.showBookInfo();
            int id = myErrorHandler.getInt("Enter id of a book you want to delete");
            Database.scanned.nextLine();
            Database.preStat = Database.myCon.prepareStatement("DELETE FROM books WHERE bookId = ?");
            Database.preStat.setInt(1,id);
            int i = Database.preStat.executeUpdate();
            System.out.println(i + " removals has been made");
        }
        else{
            System.out.println("There is no book to delete");
        }
    }
}
