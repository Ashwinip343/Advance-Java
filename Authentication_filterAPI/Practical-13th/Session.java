
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;  
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class Session extends HttpServlet{
    public Session() {
        // Default constructor
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)  
            throws ServletException, IOException {  
        
        String name = req.getParameter("uname");

        Cookie ck =new Cookie("username",name);
        res.addCookie(ck);

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<html><body>");  
        pw.println("Welcome, "+name);  
        pw.println("</body></html>");  

        pw.print("<form action='SessionMaintained'><input type='submit' value='Go'></form>");
        pw.close(); //closing the stream  
    }
}
