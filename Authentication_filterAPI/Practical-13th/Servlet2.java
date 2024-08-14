import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;  

public class Servlet2 extends HttpServlet{
     public Servlet2() {
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)  
            throws ServletException, IOException {  
        
        PrintWriter pw= res.getWriter();
        res.setContentType("text/html");
        Cookie cookies[]=req.getCookies();
        pw.println("<html><body>");  
        for(Cookie ck:cookies){
            pw.print("Hello, "+ck.getValue());
        }
        // PrintWriter pw = res.getWriter();
        pw.println("</body></html>");  
        pw.close(); //closing the stream  
    }
}
