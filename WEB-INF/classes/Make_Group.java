import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Make_Group extends HttpServlet {

    // Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        HttpSession session = request.getSession(false);
        out.println("<html>");
        out.println("<head><title>SignIn</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        if(session==null){
            out.println("You must be logged in to view this page");
            out.println("<form action=\"./Signin\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</body></html>");
            return;
        }
        String email1=request.getParameter("email1");
        String email2=request.getParameter("email2");
        String email3=request.getParameter("email3");
        DBHandler nc= new DBHandler();
        profile person=  nc.SearchProfile(email1);
        if(email1!=""){
            if(person==null){
            out.println("Enter Valid Emails<br>");
            out.println("<form action=\"./Manage_Friends\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</body></html>");return;
            }
            else{
                if((nc.searchFriend((String)session.getAttribute("email"), email1)).compareTo("")==0){
                    out.println("Not Friend With Email1<br>");
                    out.println("<form action=\"./Manage_Friends\" method=\"post\">");
                    out.println("<input type=\"submit\" value=\"<--Go Back\">");
                    out.println("</body></html>");return;                    
                }
            }
        }
        person=  nc.SearchProfile(email2);
        if(email2!=""){
            if(person==null){
                out.println("Enter Valid Emails<br>");
                out.println("<form action=\"./Manage_Friends\" method=\"post\">");
                out.println("<input type=\"submit\" value=\"<--Go Back\">");
                out.println("</body></html>");return;
            }            
            else{
                if((nc.searchFriend((String)session.getAttribute("email"), email2)).compareTo("")==0){
                    out.println("Not Friend With Email2<br>");
                    out.println("<form action=\"./Manage_Friends\" method=\"post\">");
                    out.println("<input type=\"submit\" value=\"<--Go Back\">");
                    out.println("</body></html>");return;                    
                }
            }
        }            
        person=  nc.SearchProfile(email3);
        if(email3!=""){
            if(person==null){
                out.println("Enter Valid Emails<br>");
                out.println("<form action=\"./Manage_Friends\" method=\"post\">");
                out.println("<input type=\"submit\" value=\"<--Go Back\">");
                out.println("</body></html>");return;
            }            
            else{
                if((nc.searchFriend((String)session.getAttribute("email"), email3)).compareTo("")==0){
                    out.println("Not Friend With Email3<br>");
                    out.println("<form action=\"./Manage_Friends\" method=\"post\">");
                    out.println("<input type=\"submit\" value=\"<--Go Back\">");
                    out.println("</body></html>");return;                    
                }
            }
        }
        if(email1=="") email1="None";
        if(email2=="") email2="None";
        if(email3=="") email3="None";
        String x=(String)session.getAttribute("email");
        if(email1==x||email2==x||email3==x){
            out.println("Same mail as current logged in is not allowed<br>");
            out.println("<form action=\"./Manage_Friends\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"<--Go Back\">");
            out.println("</body></html>");return;      
        }
        nc.InsertGroup(new groups((String)session.getAttribute("email"), email1, email2, email3));
        out.println("Group Created<br>");
        out.println("<form action=\"./Manage_Friends\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"<--Go Back\">");
        out.println("</body></html>");
    }
}