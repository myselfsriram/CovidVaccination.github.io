

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sign
 */
public class sign extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public sign() {
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
			String password=request.getParameter("password");
			String phonenumber=request.getParameter("phone");
			PrintWriter out=response.getWriter();
			if(name=="") 
				out.println("<script>alert('Name cant be empty');</script>");
			if(email=="")
				out.println("<script>alert('Mail id cant be empty');</script>");
			if(password=="")
				out.println("<script>alert('Password  cant be empty');</script>");
			if(phonenumber=="")
				out.println("<script>alert('Phone number cant be empty');</script>");
			
			if(name!="" && email!="" && password!="" && phonenumber!="") {
				PreparedStatement st=con.prepareStatement("insert into user_info values(?,?,?,?);");
				st.setString(1, name);
				st.setString(2,email);
				st.setString(3, password);
				st.setString(4,phonenumber);
				st.executeUpdate();
				st.close();
				con.close();
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.forward(request, response);
			}
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
