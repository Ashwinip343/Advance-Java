import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*; 
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String empId = request.getParameter("empId");
        String empName = request.getParameter("empName");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));

        // Save data to database
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","heyy897A3");  
            Scanner sc=new Scanner(System.in); 
            Statement stmt=con.createStatement(); 
            String query = "INSERT INTO Employee (EmpId, Empname, Email, Age) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, empId);
            preparedStatement.setString(2, empName);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, age);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                
                out.println("<html><body><h2>Employee added successfully!</h2></body></html>");
        
                // Display filled details back to the client
                out.println("<html><body>");
                out.println("<h2>Employee Details:</h2>");
                out.println("<p>Employee ID: " + empId + "</p>");
                out.println("<p>Employee Name: " + empName + "</p>");
                out.println("<p>Email: " + email + "</p>");
                out.println("<p>Age: " + age + "</p>");
                out.println("</body></html>");

            } else {
                out.println("<html><body><h2>Failed to add employee!</h2></body></html>");
            }
        } catch (Exception e) {
            out.println("<html><body><h2>Error: " + e.getMessage() + "</h2></body></html>");
        }

        out.close();
    }
}
