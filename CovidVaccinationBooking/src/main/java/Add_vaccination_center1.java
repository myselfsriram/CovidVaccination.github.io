

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add_vaccination_center1
 */
public class Add_vaccination_center1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Add_vaccination_center1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String center=request.getParameter("center_name");
		String district=request.getParameter("district");
		String count=request.getParameter("count");
		String per_day=request.getParameter("online");
		String working=request.getParameter("working");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid_app","sriram","sriram");
			Statement stmt=con.createStatement();
			PreparedStatement st=con.prepareStatement("insert into centers values(?,?,?,?,?);");
			st.setString(1, center);
			st.setString(2,district);
			st.setString(3, count);
			st.setString(4,per_day);
			st.setString(5, working);
			st.executeUpdate();
			st.close();
			con.close();
			PrintWriter out=response.getWriter();
			out.println("<script>alert('Center has been updated');</script>");
			
			
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
