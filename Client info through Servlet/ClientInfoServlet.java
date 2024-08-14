import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*; 


public class ClientInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // I. Client Browser
        String userAgent = request.getHeader("User-Agent");

        // II. Client IP address
        String clientIP = request.getRemoteAddr();

        // III. Client Port No
        int clientPort = request.getRemotePort();

        // IV. Server Port No
        int serverPort = request.getServerPort();

        // V. Local Port No
        int localPort = request.getLocalPort();

        // VI. Method used by client for form submission
        String method = request.getMethod();

        // VII. Query String name and values
        String queryString = request.getQueryString();

        out.println("<html><body>");
        out.println("<h2>Client Information:</h2>");
        out.println("<p>Client Browser: " + userAgent + "</p>");
        out.println("<p>Client IP Address: " + clientIP + "</p>");
        out.println("<p>Client Port No: " + clientPort + "</p>");
        out.println("<p>Server Port No: " + serverPort + "</p>");
        out.println("<p>Local Port No: " + localPort + "</p>");
        out.println("<p>Method Used: " + method + "</p>");
        // out.println("<p>Query String: " + queryString + "</p>");
        out.println("</body></html>");

        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
