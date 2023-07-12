

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class book
 */
public class book extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public book() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String choice=request.getParameter("choice");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid_app","sriram","sriram");
			Statement stmt=con.createStatement();
			PreparedStatement ps=con.prepareStatement("select day_count,count from centers where center_name=?");
			ps.setString(1, choice);
			ResultSet rs=ps.executeQuery();
			
			PrintWriter out=response.getWriter();
			while(rs.next()) {
				String temp=rs.getString("day_count");
				String temp2=rs.getString("count");
				int r1=Integer.parseInt(temp);
				int r2=Integer.parseInt(temp2);
				out.println(r1+r2);
				r1-=1;
				r2-=1;
				if(r1<=0 && r2<=0)
					out.println("<h2>Oops all seats are booked, offline only you can able to book that is your only way");
				else {
				temp=String.valueOf(r1);
				temp2=String.valueOf(r2);
				String query="update centers set day_count=?,count=? where center_name=?";
				PreparedStatement pst=con.prepareStatement(query);
				pst.setString(1, temp);
				pst.setString(2, temp2);
				pst.setString(3, choice);
				int flag=pst.executeUpdate();
					
				pst.close();
				ps.close();
		
				PreparedStatement ps1=con.prepareStatement("insert into booking values(?,?);");
				ps1.setString(1, name);
				ps1.setString(2,choice);
				ps1.executeUpdate();
				ps1.close();
				con.close();
				RequestDispatcher rd=request.getRequestDispatcher("exit.jsp");
				request.setAttribute("user",name);
				rd.forward(request, response);
				}
		
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
