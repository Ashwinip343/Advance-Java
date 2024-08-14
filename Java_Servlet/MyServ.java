
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;  

public class MyServ extends HttpServlet {  
    public MyServ() {
        // Default constructor
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)  
            throws ServletException, IOException {  
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        //writing html in the stream  
        pw.println("<html><body>");  
        pw.println("Welcome to servlet, this is Advance Java!");  
        pw.println("</body></html>");  

        pw.close(); //closing the stream  
    }
}
