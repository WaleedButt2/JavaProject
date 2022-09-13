import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        if (session != null)
        {
            session.invalidate();
            session = null;

            out.println("You have been logged out successfully<br>");
            out.println(("<a href=\"index.html\">Continue to main page</a>"));
            return;
        }
        out.println("You are not logged in<br>");
        out.println(("<a href=\"./Signin/index.html\">Go to Login</a>"));
        out.println("</body></html>");
    }
}