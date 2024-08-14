import java.io.*;  
import jakarta.servlet.*;  
import jakarta.servlet.http.*;  
  
  
public class HttpSessionServlet extends HttpServlet {  
  
public void doGet(HttpServletRequest request, HttpServletResponse response){  
        try{  
  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
          
        String n=request.getParameter("userName");  
        out.print("Welcome "+n);  
          
        HttpSession session=request.getSession();  
        // session.setAttribute("uname",n);  
        session.setAttribute("uname",n);
        

  
        out.print("<a href='httpSession'>visit</a>");  
                  
        out.close();  
  
                }catch(Exception e){System.out.println(e);}  
    }  
  
}  