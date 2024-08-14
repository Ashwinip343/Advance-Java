import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import java.sql.ResultSetMetaData;
public class Prac_7 {
    public static void main(String[] args) {
        try{  
    Class.forName("com.mysql.cj.jdbc.Driver");  
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ash","root","heyy897A3");   
    
    CreateDb(con, "EmployeeDB");
    CreateTable(con, "EmployeeDB");
    InsertData(con);
    DisplayData(con, "Employee");
    UpdateData(con);
    DeleteData(con, "Employee", "empid", "4");
    DeteleTable(con,"Employee");
    DeleteDB(con, "EmployeeDB");

}
    catch(Exception e){ System.out.println(e);} 

}
static void CreateDb(Connection con,String dbname)throws SQLException{
    PreparedStatement ps=con.prepareStatement(String.format("create database %s; ",dbname));
    ps.execute();
    ps.addBatch(String.format("use %s;", dbname));
    ps.executeBatch();
    System.out.println("Successfully created database "+dbname);
}
static void DeteleTable(Connection con,String tablename)throws SQLException{
        PreparedStatement ps = con.prepareStatement("drop table "+tablename+";");
        ps.execute();
        System.out.println("Database dropped successfully");
}
static void DeleteDB(Connection con,String dbname)throws SQLException{
    PreparedStatement ps = con.prepareStatement("drop database"+dbname+";");
        ps.execute();
    System.out.println("Table dropped succesfully");

}
static void CreateTable(Connection con,String dbname)throws SQLException{

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter table name :");
    String tablename= sc.next();
    String q="(";
    System.out.print("Enter primary key column name: ");
    String cp = sc.next();
    q+=cp+" int primary key not null,";
    while (true){
        System.out.print("Enter column name: ");
        String cname = sc.next();
        q+=cname+" ";
        System.out.print("Enter column datatype: ");
        String dt = sc.next();
        q+=dt+" ,";
        System.out.print("Enter more colunm (Y/N): ");
        String choice = sc.next();

        if(choice.toLowerCase().equals("n")){
            break;
        }
    
    }
    q=q.substring(0,q.length()-1)+")";
    String query=String.format("create table %s %s ;",tablename,q);
    System.out.println(query);
    PreparedStatement ps=con.prepareStatement(query);
    ps.executeUpdate();
}
 static void InsertData(Connection con) throws SQLException{
    Scanner sc= new Scanner(System.in);
    String query = "INSERT INTO Employee (empid, empname, empage, empsal) VALUES(?, ?, ?, ?)";
    PreparedStatement ps = con.prepareStatement(query) ;
    while (true){
        System.out.print("Enter employee id : ");
        int empid = sc.nextInt();

        System.out.print("Enter employee name : ");
        String empname = sc.next();

        System.out.print("Enter employee age: ");
        int empage= sc.nextInt();

        System.out.print("Enter  employee salary: ");
        int empsal = sc.nextInt();

        System.out.print("Enter more data (Y/N): ");
        String choice = sc.next();
        ps.setInt(1, empid) ; 
        ps.setString(2, empname); 
        ps.setInt(3, empage) ;
        ps.setInt(4, empsal) ;

        ps.addBatch();
        if (choice.toUpperCase().equals ("N")){
            break;}}

        ps.executeBatch();
 }

 static void UpdateData(Connection con)throws SQLException{
    Scanner sc= new Scanner(System.in);
    System.out.print("Enter id of person to be updated :  ");
    String id=sc.next(); 
    System.out.print("Enter column name to be updated: ");
    String coln= sc.next();
    System.out.print("Enter the value:  ");
    String val= sc.next();

    String query= String.format("UPDATE Employee SET %s = ? WHERE empid = ?", coln);
    PreparedStatement ps= con.prepareStatement(query);
    ps.setString(1, val);
    ps.setString(2, id);
    int num=ps.executeUpdate();
    System.out.println(num+" rows affected");

 }

 static void DisplayData(Connection con,String tablename)throws SQLException{
    PreparedStatement ps= con.prepareStatement("select * from "+tablename+";");
    ResultSet rs= ps.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int n=rsmd.getColumnCount();
    while(rs.next()){
        for(int i = 1; i < n+1; i++){
            String colt=rsmd.getColumnTypeName(i);
            System.out.print(rs.getString(i)+" ");
        }System.out.println("\n");
    }
 }

 static void DeleteData(Connection con,String tablename,String primarykey, String value)throws SQLException{
    PreparedStatement ps= con.prepareStatement(String.format("delete from %s  where %s = %s",tablename,primarykey,value));
    System.out.println("Rows dropped suucessfully");
 }

}

