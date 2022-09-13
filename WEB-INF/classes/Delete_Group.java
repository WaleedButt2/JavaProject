import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class Delete_Group extends HttpServlet {
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
    out.println("Logged in with email "+ (String)session.getAttribute("email")+"<br>");
    DBHandler nc= new DBHandler();
    if(!nc.SearchGroup(Integer.parseInt(request.getParameter("id")))){
        out.println("A Group with this id does not exist<br>");
        out.println("<form action=\"./Manage_Groups/index.html\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"<--Go Back\">");
        out.println("</body></html>"); return;      
    }
    nc.DeleteGroup(Integer.parseInt(request.getParameter("id")));
    out.println("Group Succesfully Purged<br>");
    out.println("<form action=\"./Manage_Groups/index.html\" method=\"post\">");
    out.println("<input type=\"submit\" value=\"<--Go Back\"></form>");
    out.println("</body></html>");
    }
}