import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class Delete extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        String Email = request.getParameter("email");
        DBHandler nc= new DBHandler();
        profile person=  nc.SearchProfile(Email);
        HttpSession session = request.getSession(false);
        if(session==null||(((String)session.getAttribute("validity")).compareTo("root")!=0)){
            session.invalidate();
            session=null;
            out.println("User Has not logged in properly as Admin<br>");
            out.println("<form action=\"./Admin\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</body></html>");
            return;
        }
        if(person==null){
            session.invalidate();
            session=null;
            out.println("This Email Does not exist in DataBase<br>");
            out.println("<form action=\"./Admin\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\"></form>");
            out.println("</body></html>");
            return;
        }
        nc.DeleteFriends(Email);
        nc.DeleteGroup(Email);
        nc.DeleteProfile(Email);
        nc.DeleteBlocked(Email);
        nc.DeleteAuthetnication(Email);
        out.println("User Annihilated <br>");
        out.println("<form action=\"./Admin\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"<--Go Back\"></form>");
        out.println("</body></html>");
        session.invalidate();
        session=null;
    }
}