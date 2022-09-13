import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Search_Friends extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        if(session == null){
            out.println("You must be logged in to view this page");
            out.println("<form action=\"./Signin\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</body></html>");
            return;
        }
        String Email=request.getParameter("email");
        if(Email.compareTo((String)session.getAttribute("email"))==0){
            out.println("Email entered is same as users mail<br>Use Different email<br>");
            out.println("<form action=\"./\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</body></html>");
            return;
        }
        DBHandler nc= new DBHandler();
        profile person=  nc.SearchProfile(Email);
        if(person==null){
            out.println("This Email Does not exist in DataBase<br>");
            out.println("<form action=\"./Manage_Friends\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</body></html>");
            return;
        }
        String Name=nc.searchFriend((String)session.getAttribute("email"), Email);
        if(Name.compareTo("")==0){
                out.println("Your are Not Friends with this person<br>");
                out.println("<form action=\"./Manage_Friends\" method=\"post\">");
                out.println("<input type=\"submit\" value=\"<--Go Back\"></body></html>");
                return;
        }else{
                out.println("Name :- '" + Name + "'<br>");
        }
        out.println("<form action=\"./Manage_Friends\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"<--Go Back\">");
        out.println("</body></html>");
    }
}