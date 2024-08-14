package SQL_PROGRAMS;

import java.sql.*;

public class practical_8_ajt {
    public static void main(String args[]) throws Exception{

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "KES123@pk";
//        String query = "Select * from employees;";


        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers are running pretty goodd....");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully......");
            CallableStatement call = con.prepareCall("{ call get_all_data() }");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String Job_title = rs.getString("job_title");
                Double salary = rs.getDouble("salary");

                System.out.println();
                System.out.println("====================");
                System.out.println("ID: "+id);
                System.out.println("Name: " + name);
                System.out.println("Job_title: " + Job_title);
                System.out.println("salary : " + salary);

                System.out.println();

            }

            rs.close();
            call.close();
            con.close();

            System.out.println("connection closed successfully!!!!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
