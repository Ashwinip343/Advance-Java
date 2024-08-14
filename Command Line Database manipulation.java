
import java.sql.*;  
import java.util.*;

public class Prac_6{  
public static void main(String args[]){  

try{  
    Class.forName("com.mysql.cj.jdbc.Driver");  
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","heyy897A3");  
    Scanner sc=new Scanner(System.in); 
    Statement stmt=con.createStatement(); 
    CreateDb(stmt, "StudentDB");
    CreateTable(stmt, "StudentDB");
    stmt.executeUpdate("use StudentDB;");
    InsertData(stmt, "Student");
    // stmt.execute("use StudentDB");
    SelectData(stmt, "Student");
    UpdateData(stmt, "Student", "sid");
    SelectData(stmt, "Student");
    DeleteData(stmt, "Student", "sid", 3);
    DeleteTable(stmt, "Student");
    DeleteDb(stmt, "StudentDB");
    con.close();  
    }catch(Exception e){ System.out.println(e);}  
}  
static void CreateDb(Statement s, String dbname)throws SQLException{
    s.execute(String.format("create database %s", dbname));
    s.execute(String.format("use %s;",dbname));
    System.out.print(String.format("Database %s created",dbname));

}
static void DeleteTable(Statement s, String tablename)throws SQLException{
    s.execute(String.format("Drop table %s",tablename));
    System.out.print(String.format("Table %s dropped",tablename));
}

public static String getDataTypeByColumnName(ResultSet rs, String columnName)throws SQLException{
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();
  
    for (int i = 1; i <= columnCount; i++) {
      String currentColumnName = rsmd.getColumnName(i);
      if (currentColumnName.equals(columnName)) {
        return rsmd.getColumnTypeName(i);
      }
    }
  
    throw new SQLException("Column not found: " + columnName);
  }

static void UpdateData(Statement s, String tablename,String primarykey)throws SQLException{
    System.out.println("-------------Update Table-----------");
    Scanner sc=new Scanner(System.in);
    ResultSet rs= s.executeQuery(String.format("select * from %s;",tablename));
    ResultSetMetaData rsmd = rs.getMetaData();

    System.out.println("Enter id of person to be updated :  ");
    String id=sc.next(); 
    System.out.print("Enter Column name: ");
    String coln= sc.next();
   
    String dt= getDataTypeByColumnName(rs, coln); 
    String value="";

    System.out.println("Enter the value: ");
    if(dt.toLowerCase().equals("int")){
        int val= sc.nextInt();
        System.out.println(val);
        value=val+"";
        System.out.println(value);
    }
    else {
        String val=sc.next();
        System.out.println(val);
        value=String.format("'%s'",val);
        System.out.println(value);
    }
    int num=s.executeUpdate(String.format("update %s set %s = %s where %s = %s;",tablename,coln,value,primarykey,id));
    System.out.println(num+" rows affected");

}
static void DeleteDb(Statement s, String databasename)throws SQLException{
    s.execute(String.format("drop database %s",databasename));
    System.out.print(String.format("Database %s dropped",databasename));

}
static void DeleteData(Statement s, String tablename, String primarykey , int value)throws SQLException{
    int num=s.executeUpdate(String.format("delete from %s  where %s = %s",tablename,primarykey,value));
    System.out.print(String.format("Row having %s as %s dropped",primarykey,value));
    System.out.println( num+" rows affected");

}
static void SelectData(Statement st,String tablename)throws SQLException{
    System.out.println("-------------Display Table-----------");
    ResultSet rs=st.executeQuery(String.format("select * from %s;",tablename));
    ResultSetMetaData rsmd = rs.getMetaData();
    int n=rsmd.getColumnCount();
    while(rs.next()){
        for(int i = 1; i < n+1; i++){
            String colt=rsmd.getColumnTypeName(i);
            System.out.print(rs.getString(i)+" ");
        }System.out.println("\n");
    }
}
static void CreateTable(Statement st,String Dbname)throws SQLException{
    System.out.println("-------------Create Table-----------");
    st.execute("use "+Dbname);
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the table name: ");
    String tablename=sc.next();

   
    System.out.print("Enter the number of columns: ");
    int nc=sc.nextInt();
    String q="";

    System.out.print("Enter the column name(primary key): ");
    String cp= sc.next();
    q+="("+cp+" int primary key not null, ";

    for(int i=0;i<nc;i++){
        System.out.print("Enter the column name: ");
        String cname= sc.next();
        q+=cname+" ";
        System.out.println("Enter the column datatype");
        String cdtype=sc.next();
       
        
        if(cdtype.toLowerCase().equals("varchar")){
            System.out.println("Enter the length: ");
            int l=sc.nextInt();
            q+=cdtype+String.format("(%d),",l);

        }
        else{ 
        q+=cdtype+",";}

        }
        q=q.substring(0,q.length()-1)+")";
        int num=st.executeUpdate(String.format("create table %s %s;", tablename,q));
        System.out.println( num+" rows affected");


}
static void InsertData(Statement st,String tablename)throws SQLException{
    System.out.println("-------------Insert Table-----------");
    Scanner sc = new Scanner(System.in);
    ResultSet rs= st.executeQuery(String.format("select * from %s;",tablename));
    ResultSetMetaData rsmd = rs.getMetaData();

    int n=rsmd.getColumnCount();
    int num=0;
   while(true){
    String s="";
    for(int i = 1; i < n+1; i++){
        String colt=rsmd.getColumnTypeName(i);
        String coln=rsmd.getColumnName(i);

        if(colt.toLowerCase().equals("varchar")){
            System.out.println("Enter "+coln+" ("+colt.toLowerCase()+") :");
            String val= sc.next();
            s+=String.format("'%s'",val);
            s+=",";

        }
        else{
            System.out.println("Enter "+coln+" ("+colt.toLowerCase()+") :");
            int val= sc.nextInt();
            s+=String.format("%d",val);
            s+=",";
        }
    }
    String q= String.format("insert into %s values(%s);",tablename,s.substring(0,s.length()-1));
    System.out.println(q);
    int nr =st.executeUpdate(q);
    System.out.println("Enter more data (Y/N):");
    String choice = sc.next();
    if(choice.toLowerCase().equals("n")){
        break;
    }
    num+=nr;

    }
    System.out.println(num+"Rows affected");
}
}  

