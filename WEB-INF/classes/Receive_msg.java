import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Receive_msg extends HttpServlet {
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
        DBHandler nc= new DBHandler(); 
        profile pass=nc.Recieve_Message((String)session.getAttribute("email"));
        String msg=pass.Name;String sender = pass.Email;
        out.println("<br><table><tr><th>Sender</th><th>Message</th></tr>");
        out.println("<tr><td>"+sender+"</td><td>"+msg+"</td></tr>");
        out.println("<br><form action=\"./Messages\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"<--Go Back\">");
        out.println("</body></html>");
    }
}