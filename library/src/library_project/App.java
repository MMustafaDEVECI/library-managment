package library_project;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException
    {
            new Database();
            int id = 1;
            String cell = "";
            while (Database.input != 0){
                Database.menu();
                switch (Database.input){
                    case (1):
                        cell = Database.checkCustTable();
                        Database.borrow(cell);
                        break;
                    case (2):
                        cell = Database.checkCustTable();
                        id = Customer.getBookId(cell);
                        if (id != 0){
                            Book.setStockNumUp(id);
                            Customer.setBookId(cell, 0);
                        }
                        else{
                            System.out.println("Customer doesn't have a book to return");
                        }
                        break;
                    case (3):
                        BookTable.showBookInfo();
                        break;
                    case (4):
                        BookTable.checkBookTable();
                        Book book = new Book();
                        book.bookInsert();
                        break;
                    case (5):
                        if (Database.tableExists("librarians"))
                        {
                            LibrarianTable.showLibrarianInfo();
                            if(myErrorHandler.checkAnyLibrarianExist()){
                                id = LibrarianTable.getId();
                                if (myErrorHandler.checkId(id))
                                {
                                    String feature = LibrarianTable.getFeature();
                                    switch (feature){
                                        case ("salary"):
                                            Librarian.editSalary(id);
                                            break;
                                        case ("name"):
                                            Librarian.editName(id);
                                            break;
                                        case ("surname"):
                                            Librarian.editSurname(id);
                                            break;
                                        case ("cell"):
                                            Librarian.editCell(id);
                                            break;
                                        default:
                                            System.out.println("Enter a valid input");
                                            break;
                                    }
                                }
                                else{
                                    System.out.println("There is no librarian with id " + id + " in our library");
                                }
                            }
                        }
                        else{
                            System.out.println("There is no librarian to edit");
                        }
                        break;
                    case (6):
                        new LibrarianTable();
                        break;
                    case (7):
                        if (Database.tableExists("librarians"))
                            if (myErrorHandler.checkId(0))
                                Librarian.delete();
                            else{
                                System.out.println("There is no librarian to delete");
                            }
                        else{
                            System.out.println("There is no librarian to delete");
                        }
                        break;
                    case (8):
                        if (Database.tableExists("books")){
                            if(myErrorHandler.checkAnyBookExist()){
                                id = BookTable.getBookId();
                                if(myErrorHandler.checkBookId(id)){
                                    String feature = BookTable.getFeature();     
                                    switch (feature){
                                        case ("pageNum"):
                                            Book.editPageNum(id);
                                            break;
                                        case ("publisher"):
                                            Book.editPublisher(id);
                                            break;
                                        case ("stockNum"):
                                            Book.editStockNum(id);
                                            break;
                                        default:
                                            System.out.println("Enter a valid input");
                                            break;
                                    }
                                }
                            }
                            else{
                                System.out.println("There is no book to edit");
                            }
                        }
                        else{
                            System.out.println("There is no book to edit");
                        }
                        
                        break;
                    case (9):
                        if (Database.tableExists("books"))
                            if(myErrorHandler.checkAnyBookExist()){
                                Book.delete();
                            }
                            else{
                            }
                        else{
                            System.out.println("There is no book to delete");
                        }
                        break;
                    case (0):
                        break;
                }
            }
        Database.close();
    }
}