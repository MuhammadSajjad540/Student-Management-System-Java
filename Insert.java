import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Insert extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("sname");
        String email = req.getParameter("email");
        String course = req.getParameter("course");
        String country = req.getParameter("country");

        DAO obj = new DAO();
        int flag = obj.insertStudent(name, email, course, country);

        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body>");

        if (flag == 1)
            out.println("<h1>Student Inserted Successfully</h1>");
        else    
            out.println("<h1>Student Insertion Failed</h1>");

        out.println("</body>");
        out.println("</html>");
        
    }
}
