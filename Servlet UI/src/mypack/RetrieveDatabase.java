package mypack;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.jdbc.JDBCPieDataset;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection; 


@WebServlet("/RetrieveDatabase")
public class RetrieveDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RetrieveDatabase() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String imgId = request.getParameter("imgid");
		int fl1=0, fl2=0, fl3=0, fl4 = 0, fl5 = 0;
		float f1 = 0;
		
		try{
		       
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("class successfully loaded");
            
            Connection con=DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/mayur","root","admin");
            System.out.println("successfully connected");
			      
			PreparedStatement ps=con.prepareStatement("select * from imagebox where sno=?"); 
			ps.setLong(1,Integer.parseInt(imgId));
			
			ResultSet rs=ps.executeQuery();  
			if(rs.next())
			{
			              
			Blob b=rs.getBlob(2);
			byte barr[]=b.getBytes(1,(int)b.length());
			String name;
			              
			FileOutputStream fout=new FileOutputStream("C:\\Users\\vaibhav upadhyaya\\Desktop\\pics\\new3.jpeg");  
			fout.write(barr); 
			String SQL = "select * from insta_tbl";
			Statement s = con.createStatement();
			ResultSet rs1 = s.executeQuery(SQL);
	
			while(rs1.next())
			{
			//out.println(rs1.getString(1));
			out.println("<center><h3>Username:" +rs1.getString(1)+ "</h3></center>");
			out.println("<table style='width=100%' align='center' border='2'>");
			out.println("<tr>");
			out.println("<th><h4>Posts</h4></th>");
			out.println("<th><h4>Followers</h4></th>");
			out.println("<th>Following</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>" +rs1.getString(4)+ "</td>");
			out.println("<td>" +rs1.getString(3)+ "</td>");
			out.println("<td>" +rs1.getString(2)+ "</td>");
			out.println("</tr>");
			
			out.println("</table>");
			out.println("<html>");
			out.println("<head>");
			
			out.println("</head>");
			out.println("<body>");
			out.println("<h1><center><font color=\"dark magenta\">Instagram Profile Verifier</font></center></h1>\r\n" + 
					"");
			out.println("<hr>");
			out.println("<p><h3><center>This is a complete data based intelligent system that verifies any instagram account. It fetches the data of user at run time and then they are examined through different social media parameters. The output shows complete user details, factor checks and graphs.</center></h3></p>");
			out.println("<hr>");
			out.println("<center><img src=\"C:\\Users\\vaibhav upadhyaya\\eclipse-workspace\\insta\\WebContent\\Instagram-Logo.jpg\" width=\"600\" height=\"600\"></center>\r\n");
			out.println("<br>");
			//out.println("<center><h3>Username:</h3></center>rs.getString(1)");
			out.println("<br>");
			
			out.println("<br>");
			out.println("<br>");
			out.println("<img src =\"C:\\Users\\vaibhav upadhyaya\\Desktop\\pics\\new3.jpeg\" height=\"90%\" width=\"100%\" >");
			out.println("<h6>Go Back to Main Menu <a href = \"first.html\">Click here</a><h6>");
			out.println("<br>");
			
			
			
			
			
			out.println("<p><h2><center>Factor Test 1-Ratio</center></h2></p>");
			out.println("<p><center>( It computes the no of ratio of followers to following. If the ratio is more than 0.30 or above it that means the first flag is true else it if false )</center></p>");
			f1 = Float.parseFloat(rs1.getString(3))/Float.parseFloat(rs1.getString(2));
			out.println("<p><center>"+(float)f1+"</center></p>");
			if(f1<=0.3)
			{
				out.println("<h3><center>It should be a fake</h3></center>");
			}
			else
			{
				fl1= 1;
				out.println("<h3><center>might not be fake account</h3></center>");
			}
			out.println("<p><h3><center><font color=\"blue\">For Polarity Graph</font></center></h3></p>");
			out.println("<center><a href='http://localhost:8080/insta/pieChartDemo'>click here</a></center>");
			out.println("<hr>");
			out.println("<br>");
			
			
			
			
			
			out.println("<p><h2><center>Factor Test 2-Private</center></h2></p>");
			out.println("<p><center>( It checks whether the account is private or public. If it is private that means the first flag is true else it if false )</p></center></p>");
			if(Integer.parseInt(rs1.getString(5))==1)
				{
				fl2 = 1;
				out.println("<h3><center>yes it is a private account</h3></center>");
				}
			else
				out.println("<h3><center>No it is a public account</h3></center>");
			out.println("<p><h3><center><font color=\"blue\">For Polarity Graph</font></center></h3></p>");
			out.println("<center><a href='http://localhost:8080/insta/Chart1'>click here</a></center>");
			out.println("<hr>");
			
			
			
			
			
			
			
			out.println("<p><h2><center>Factor Test 3-Certification</center></h2></p>");
			out.println("<p><center>( It checks whether the account is certified or not. If certified then that means the first flag is true else it if false )</center></p>");
			if(Integer.parseInt(rs1.getString(6))==1)
			{
			fl3 = 1;
			out.println("<h3><center>Yes it is a verified account</h3></center>");
			}
		else
			out.println("<h3><center>Not a verified account</h3></center>");
			out.println("<p><h3><center><font color=\"blue\">For Polarity Graph</font></center></h3></p>");
			out.println("<center><a href='http://localhost:8080/insta/Chart2'>click here</a></center>");
			out.println("<hr>");
			
			
			
			
			
			out.println("<p><h2><center>Factor Test 4-No Profile Picture</center></h2></p>");
			out.println("<p><center>( It checks whether the account has a profile picture or not. It it has then that means the first flag is true else it if false )</center></p>");
			if(Integer.parseInt(rs1.getString(4))==0)
			{
			
			out.println("<h3><center>The account has no posts yet, not even a profile picture. It might be a fake account</center></h3>");
			}
		else
		{
			fl4 = 1;
			out.println("<h3><center>It may not be fake account</center></h3>");
		}
			out.println("<p><h3><center><font color=\"blue\">For Polarity Graph</font></center></h3></p>");
			out.println("<center><a href='http://localhost:8080/insta/Chart3'>click here</a></center>");
			out.println("<hr>");
			
			
			
			
			out.println("<p><h2><center>Factor Test 5-Very less posts</center></h2></p>");
			out.println("<p><center>( It checks whether the account has posts less than 5 then that means the flag is true else it if false )</center></p>");
			if(Integer.parseInt(rs1.getString(4))<=5)
			{
			out.println("<h3><center>The account has very less posts. It might be a fake or in-active account</center><h3>");
			}
		else
		{
			fl5 = 1;
			out.println("<h3><center>The account has decent no of posts, it may not be a fake account</center><h3>");
		}

			out.println("<p><h3><center><font color=\"blue\">For Polarity Graph</font></center></h3></p>");
			out.println("<center><a href='http://localhost:8080/insta/Chart4'>click here</a></center>");
			out.println("<hr>");
			out.println("<hr>");
			out.println("<br>");
			
			out.println("<p><h3><center><font color=\"blue\">For a overall Polarity Graph</font></center></h3></p>");
			out.println("<center><a href='http://localhost:8080/insta/Covers'>click here</a></center>");
			
			
			out.println("</body>");
			out.println("</html>");
			
			
			
			if(fl1==1) {
			Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","admin");
			String SQL1=	"insert into test values(?,?)";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(SQL1);
			pst.setString(1,"Shaded region shows genuinity");
			pst.setLong(2,1);
			pst.executeUpdate();
			}
			else
			{
				Connection conn=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","admin");
				String SQL1=	"insert into test values(?,?)";
				PreparedStatement pst=(PreparedStatement) conn.prepareStatement(SQL1);
				pst.setString(1,"Shaded region shows fakeness");
				pst.setLong(2,1);
				pst.executeUpdate();
				
			}
			
			
			if(fl2==1) {
			Connection conn2=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","admin");
			String SQL2=	"insert into test1 values(?,?)";
			PreparedStatement pst1=(PreparedStatement) conn2.prepareStatement(SQL2);
			pst1.setString(1,"Shaded region shows genuinity");
			pst1.setLong(2,1);
			pst1.executeUpdate();
			}
			else
			{
				Connection conn2=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","admin");
				String SQL2=	"insert into test1 values(?,?)";
				PreparedStatement pst1=(PreparedStatement) conn2.prepareStatement(SQL2);
				pst1.setString(1,"Shaded region shows fakeness");
				pst1.setLong(2,1);
				pst1.executeUpdate();
			}
			
			if(fl3==1) {
			Connection conn3=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","admin");
			String SQL3=	"insert into test2 values(?,?)";
			PreparedStatement pst2=(PreparedStatement) conn3.prepareStatement(SQL3);
			pst2.setString(1,"Shaded region shows genuinity");
			pst2.setLong(2,1);
			pst2.executeUpdate();
			}
			
			else 
			{
				Connection conn3=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","admin");
				String SQL3=	"insert into test2 values(?,?)";
				PreparedStatement pst2=(PreparedStatement) conn3.prepareStatement(SQL3);
				pst2.setString(1,"Shaded region shows fakeness");
				pst2.setLong(2,1);
				pst2.executeUpdate();
			}
			
			if(fl4==1) {
			Connection conn4=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","admin");
			String SQL4=	"insert into test3 values(?,?)";
			PreparedStatement pst3=(PreparedStatement) conn4.prepareStatement(SQL4);
			pst3.setString(1,"Shaded region shows genuinity");
			pst3.setLong(2,1);
			pst3.executeUpdate();
			}
			else
			{
				Connection conn4=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","admin");
				String SQL4=	"insert into test3 values(?,?)";
				PreparedStatement pst3=(PreparedStatement) conn4.prepareStatement(SQL4);
				pst3.setString(1,"Shaded region shows fakeness");
				pst3.setLong(2,1);
				pst3.executeUpdate();
			}
			
			if(fl5==1) {
			Connection conn5=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","admin");
			String SQL5=	"insert into test4 values(?,?)";
			PreparedStatement pst4=(PreparedStatement) conn5.prepareStatement(SQL5);
			pst4.setString(1,"Shaded region shows genuinity");
			pst4.setLong(2,fl5);
			pst4.executeUpdate();
			}
			else {
				Connection conn5=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","admin");
				String SQL5=	"insert into test4 values(?,?)";
				PreparedStatement pst4=(PreparedStatement) conn5.prepareStatement(SQL5);
				pst4.setString(1,"Shaded region shows fakeness");
				pst4.setLong(2,fl5);
				pst4.executeUpdate();
	
			}
			//-----------------------------------
			Connection abc=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","admin");
			String SQLa ="insert into overall values(?,?)";
			PreparedStatement psta=(PreparedStatement) abc.prepareStatement(SQLa);
			PreparedStatement pstb=(PreparedStatement) abc.prepareStatement(SQLa);
			PreparedStatement pstc=(PreparedStatement) abc.prepareStatement(SQLa);
			PreparedStatement pstd=(PreparedStatement) abc.prepareStatement(SQLa);
			PreparedStatement pste=(PreparedStatement) abc.prepareStatement(SQLa);

			if(fl1==1) {
			psta.setString(1,"factor 1: POSITIVE");
			psta.setLong(2,20);
			}
			else
			{
				psta.setString(1,"factor 1: NEGATIVE");
				psta.setLong(2,20);
			}
			//-----------------------------------------------	
			if(fl2==1) {
			pstb.setString(1,"factor 2: POSITIVE");
			pstb.setLong(2,30);
			}
			else
			{
				pstb.setString(1,"factor 2: NEGATIVE");
				pstb.setLong(2,30);
			}
			//------------------------------------------
			if(fl3==1) {
			pstc.setString(1,"factor 3: POSITIVE");
			pstc.setLong(2,10);
			}
			else
			{
				pstc.setString(1,"factor 3:NEGATIVE");
				pstc.setLong(2,10);
			}
			//---------------------------------------
			if(fl4==1) {
			pstd.setString(1,"factor 4: POSITIVE");
			pstd.setLong(2,20);
			}
			else
			{
				pstd.setString(1,"factor 4: NEGATIVE");
				pstd.setLong(2,20);
			}
			//------------------------------------
			if(fl5==1) {
			pste.setString(1,"factor 5: POSITIVE");
			pste.setLong(2,20);
			}
			else
			{
				pste.setString(1,"factor 5:NEGATIVE");
				pste.setLong(2,20);

			}
			//--------------------------------
			
			psta.executeUpdate();
			pstb.executeUpdate();
			pstc.executeUpdate();
			pstd.executeUpdate();
			pste.executeUpdate();

			}              
			fout.close();  
			} 
			System.out.println("ok");  
			              
			con.close();  
	}	
		catch (Exception e) 
		{e.printStackTrace();  }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
