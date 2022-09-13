import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
public class Show_blocked extends HttpServlet {
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
        profile person=  nc.SearchProfile((String)session.getAttribute("email"));
        if(person!=null){
            out.println("Email = "+person.Email+"<br>");
            out.println("Name = "+person.Name+"<br>");
        }
        else{
            //unlikely to get here
            out.println("This Account does not exist<br>");
            out.println("<form action=\"./\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\"></form>");
            out.println("</body></html>");
            return;
        }
    try{
        ResultSet rs=nc.ShowBlock_Users((String)session.getAttribute("email"));
        boolean flag=false;
        out.println("List of Blocks Users<br><table><tr><th>Blocked</th></tr>");
        while(rs.next()){
            flag=true;
            out.println("<tr><td>"+rs.getString("Blocked")+"</td></tr>");
        }
        if(flag==false){
            out.println("No Blocked Users exists against this Account<br>");
        }
    }
    catch(Exception e){
        out.println(e);
    }
    out.println("<form action=\"./Manage_Blocked\" method=\"post\">");
    out.println("<input type=\"submit\" value=\"<--Go Back\"></form>");
    out.println("</body></html>");
    }
}