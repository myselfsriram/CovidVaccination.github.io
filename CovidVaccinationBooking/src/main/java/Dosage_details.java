

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
 * Servlet implementation class Dosage_details
 */
public class Dosage_details extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Dosage_details() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("user");
		try {
		PrintWriter out=response.getWriter();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid_app","sriram","sriram");
		Statement stmt=con.createStatement(); 
		ResultSet rs=stmt.executeQuery("select * from centers;");
		out.println("<html><head></head><body style='background-color:blue; color:white;font-size=19px>'");
		out.println("<h1> Details are asked by:"+user+"</h1>");
		out.println("<table border=1 width=50% height=50px>");
		out.println("<tr><th>Center name</th>");
		out.println("<th>District name</th>");
		out.println("<th>Dose that avaliable in center</th>");
		out.println("<th>Per day count</th></tr>");
		while(rs.next()) {
			String center=rs.getString("center_name");
			String dis=rs.getString("district");
			String count=rs.getString("count");
			String per_Day=rs.getString("day_count");
			out.println("<h3><tr><td>"+center+"</td>"+"<td>"+dis+"</td>"+"<td>"+count+"</td>"+"<td>"+per_Day+"</td></tr></h3>");
			
		}
		out.println("</table></body></html>");
		}
		catch(Exception e)
		{
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
