import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Menue extends HttpServlet {

    // Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        HttpSession session = request.getSession(false);
        String passwd= request.getParameter("passwd");
        String email = request.getParameter("email");
        
        out.println("<html>");
        out.println("<head><title>SignIn</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        Boolean access=false;
        if((email.compareTo("root")==0)&&(passwd.compareTo("root")==0)){
            access=true;
        }
        if(access==true){
            if(session!=null)
            session.invalidate();
            session=null;
            session = request.getSession(true);
            session.setAttribute("validity", "root");
            out.println("<a href=\"./Admin/Delete.html\">Delete</a><br><a href=\"./Admin/Friendless.html\">Friendless</a><br>"+    
            "<form method=\"post\" action=\"./Admin\"><input type=\"submit\" value=\"<--Go Back\"></form>");
            out.println("</body></html>");
            return;
        }
        else{
            out.println("Username or Password is incorrect");
            out.println("<form action=\"./\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</form>");
        }
        out.println("</body></html>");
    }
}