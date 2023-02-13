package library_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerTable {
    public CustomerTable(){

    }
    public CustomerTable(Statement statement) throws SQLException{
        statement.executeUpdate("CREATE TABLE customers(name VARCHAR(20), surName VARCHAR(20), cell VARCHAR(11) PRIMARY KEY, bookId INT, date DATE)");
    }
    public static boolean CheckCust(PreparedStatement prepStat, Connection myCon, String cell) throws SQLException{
        prepStat = myCon.prepareStatement("SELECT cell FROM customers WHERE cell = ?");
        prepStat.setString(1, cell);
        ResultSet rs = prepStat.executeQuery();
        boolean checkCust = false;
        while(rs.next()){ // better way for existence of element in table?
            if (cell.equals(rs.getString("cell"))){
                checkCust = true;
                break;
            }
        }
        rs.close();
        return checkCust;
    }
}
