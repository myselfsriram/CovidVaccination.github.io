

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 * 
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid_app","sriram","sriram");
			Statement stmt=con.createStatement();
		
		String user=request.getParameter("mailid");
		String password=request.getParameter("password");
		if(user=="") {
			out.println("<script>alert('user mail id cant be empty');</script>");
			
		}	
		if(password=="") {
			out.println("<script>alert('password cant be empty');</script>");
			
		}
	
		if(user!="" && password!="" ) {
			PreparedStatement ps=con.prepareStatement("select Email_id,Password from user_info where Email_id=? and Password=?");
			ps.setString(1, user);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				RequestDispatcher rd=request.getRequestDispatcher("centers.html");
				request.setAttribute("user", user);
				rd.forward(request, response);
			}
			else {
				out.println("<html><head><body style='background-color:blue; color:white;font-size=19px>'");
				out.println("<h2>Your account was not in out database. create your account first</h2>");
				out.println("</head></body></html>");
				
			}
		}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
