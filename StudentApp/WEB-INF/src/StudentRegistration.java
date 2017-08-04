import javax.servlet.*;
import java.sql.*;
import java.io.*;

public class StudentRegistration extends GenericServlet
{
	Connection con;
	PreparedStatement ps;
	public void init() throws ServletException
	{
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl.168.0.21", "mydb", "mydb");
			ps = con.prepareStatement("insert into student values(?, ?, ?)");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		try
		{
			String rn = req.getParameter("rno");
			int rno = Integer.parseInt(rn);
			String un = req.getParameter("un");
			String pd = req.getParameter("pd");

			ps.setInt(1, rno);
			ps.setString(2, un);
			ps.setString(3, pd);

			int n = ps.executeUpdate();
			con.commit();
			PrintWriter pw = res.getWriter();

			if(n > 0)
			{
				pw.println("<html><body align = center><h1>Registration Success</h1><h2><a href = StudentLogin.html>Login</h2></body></html>");
			}
			else
			{
				pw.println("<html><body align = center><h1>Sorry, Your Registration has been failed</h1></body></html>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void destroy()
	{
		try
		{
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}