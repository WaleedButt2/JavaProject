import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
public class SignUp extends HttpServlet {

    // Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email= request.getParameter("email");
        String name= request.getParameter("name");
        String passwd= request.getParameter("passwd");
        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        DBHandler nc= new DBHandler();
        profile person=  nc.SearchProfile(email);
        if(person!=null){
            out.println("Email already Exists.<br>");
            out.println("<form action=\"./Signup\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</form></body></html>");
            return ;
        }
        nc.InsertAuthentication(email, passwd);
        nc.InsertProfile(new profile(name,email));
        out.println("<h1>User Registered</h1>");
        out.println("<form action=\"./index.html\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"<--Go Back\">");
        out.println("</body></html>");
    }
}