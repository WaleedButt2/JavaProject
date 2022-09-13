import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignIn extends HttpServlet {

    // Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        HttpSession session = request.getSession(false);
        if(session!=null){
            RequestDispatcher rd = request.getRequestDispatcher("/ShowFriends");
            rd.forward(request, response);
        }
        String passwd= request.getParameter("passwd");
        String email = request.getParameter("email");
        
        out.println("<html>");
        out.println("<head><title>SignIn</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        DBHandler nc= new DBHandler();
        Boolean access=  nc.searchAuthentication(email,passwd);
        if(access==true){
            session = request.getSession(true);
            session.setAttribute("email",email);
            RequestDispatcher rd= request.getRequestDispatcher("/ShowFriends");
            rd.forward(request, response);
        }
        else{
            out.println("Username or Password is incorrect");
            out.println("<form action=\"./Signin\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</form>");
        }
        out.println("</body></html>");
    }
}