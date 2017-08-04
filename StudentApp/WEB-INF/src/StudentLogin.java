import javax.servlet.*;
import java.sql.*;
import java.io.*;

public class StudentLogin extends GenericServlet
{
	Connection con;
	PreparedStatement ps;
	public void init() throws ServletException
	{
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl.168.0.21", "mydb", "mydb");
			ps = con.prepareStatement("select * from student where uname = ? and pswd = ? ");
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
			String un = req.getParameter("un");
			String pd = req.getParameter("pd");

			ps.setString(1, un);
			ps.setString(2, pd);

			ResultSet rs = ps.executeQuery();
			PrintWriter pw = res.getWriter();

			if(rs.next())
			{
				pw.println("<html><body align = center><h1>Welcome to HomePage</h1></body></html>");
			}
			else
			{
				pw.println("<html><body align = center><h1>Invalid UserName/Password</h1></body></html>");
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