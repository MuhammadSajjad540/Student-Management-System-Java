import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Delete extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("sname");
        DAO obj = new DAO();
        int flag = obj.deleteStudent(name);

        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body>");

        if (flag == 1)
            out.println("<h1>Student Deleted Successfully</h1>");
        else    
            out.println("<h1>Student Deletion Failed</h1>"); 

        out.println("</body>");
        out.println("</html>");
        
    }
}
