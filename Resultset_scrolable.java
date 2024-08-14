import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.DatabaseMetaData;

public class Prac_9 {

    public static void main(String[] args) throws SQLException {
        try {
           
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ash", "root", "heyy897A3");

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM student", 
                                                    ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery();
            
            insertRow(resultSet);
            deleteRow(resultSet);
            Display(resultSet);
            FetchMetaData(connection,resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    private static void insertRow(ResultSet resultSet) throws SQLException {
        resultSet.moveToInsertRow();  
        resultSet.updateInt("id", 9);   
        resultSet.updateString("sname", "Ash");
        resultSet.updateInt("sm", 90);
        resultSet.insertRow();       
        resultSet.moveToCurrentRow(); 
    }

    private static void deleteRow(ResultSet resultSet) throws SQLException {
        resultSet.absolute(3);
        resultSet.deleteRow();
        System.out.println("Record deleted successfully");
       
    }
   
    static void Display(ResultSet resultSet)throws SQLException{
        resultSet.beforeFirst();
        while (resultSet.next()) {
            System.out.print("Id: " + resultSet.getInt("id"));
            System.out.println(", Name: " + resultSet.getString("sname"));
            System.out.print("Id: " + resultSet.getInt("sm"));

        }
    }
    static void FetchMetaData(Connection con,ResultSet rs)throws SQLException{
        DatabaseMetaData metaData = con.getMetaData();
       
        System.out.println("TABLE CATALOG: " + metaData.getDatabaseProductName());
        System.out.println("TABLE SCHEMA: " + metaData.getUserName());
      
        ResultSet rsm = metaData.getTables(null, "ash", null, new String[]{"TABLE"});
        // while (tables.next()) {
        //     System.out.println("TABLE TYPE: " + tables.getString("TABLE_TYPE"));
        // }
        // tables.close();
        ResultSet columns = metaData.getColumns(null, "ash", "emp", null);
            System.out.println("Column Count: " + columns.getMetaData().getColumnCount());
            while (columns.next()) {
                System.out.println("Column Label: " + columns.getString("COLUMN_NAME"));
            }
            columns.close();
    }
}
