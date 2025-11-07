import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Read extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        DAO obj = new DAO();
        ResultSet rs = null;

        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body>");
        out.println("<table border='5' align='center'>");
        out.println("<tr>");
        out.println("<td colspan='4' align='center' style='background-color:rgb(3, 102, 0); color:white;'><h2>Students Record</h2></td>");
        out.println("</tr>");
        out.println("<tr><td>Name</td><td>Email</td><td>Course</td><td>Country</td></tr>");

        try {
            rs = obj.readStudent(); 
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String course = rs.getString("course");
                String country = rs.getString("country");

                out.println("<tr><td>" + name + "</td><td>" + email + "</td><td>" + course + "</td><td>" + country + "</td></tr>");
            }
        } catch (Exception e) {
            out.println("<tr><td colspan='4'>Error retrieving records</td></tr>");
            e.printStackTrace(out);
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
       
    }
}
