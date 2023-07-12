

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class centers
 */
public class centers extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public centers() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid_app","sriram","sriram");
		Statement stmt=con.createStatement();
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String card=request.getParameter("email");
		String district=request.getParameter("district");
		ResultSet rs=stmt.executeQuery("select *  from centers where district='"+district+"';");
		PrintWriter out=response.getWriter();
		out.println("<html><head><body style='background-color:blue; color:white;font-size=19px>'");
		out.println("<h2>Welcome user:"+name+"</h2>");
		out.println("<h2>The Centers that Are avaliable in "+district+" are</h2><br>");
		out.println("<table><tr><th>Center name</th><th>Working hours</th></tr>");
		while(rs.next()) {
			String temp=rs.getString("center_name");
			String time=rs.getString("Working_hours");
			out.println("<tr><td>"+temp+"</td><td>"+time+"</td></tr>");
			
		}
		out.println("</table>");
		out.println("<form action=\"book\">");
		out.println("<input type='hidden' name='name' value="+name+"'>");
		out.println("<br><h2>choose and enter center  name:<input type='text' name ='choice'></h2>");
		out.println("<br><input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("</body></html>");
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
