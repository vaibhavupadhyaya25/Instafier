package package1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.io.OutputStream;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.jdbc.JDBCPieDataset;
import java.io.*;

/**
 * Servlet implementation class RtrData
 */
@WebServlet("/RtrData")
public class RtrData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RtrData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//String imgId = request.getParameter("imgid");
		int fl1=0, fl2=0, fl3=0, fl4 = 0, fl5 = 0,fl8=0,normal=0, celeb=0, promo=0, omega1 =0,omega2 =0,omega3 =0,omega4 =0,omega5 =0,omega6 =0, omega7=0;
		float f1 = 0;
		
		try{
		       
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("class successfully loaded");
            
            Connection con=DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/vaibhavdb","root","password");
            System.out.println("successfully connected");
			      
            String SQLZ = "select * from imagebox";
			Statement stz = con.createStatement();
			ResultSet rs = stz.executeQuery(SQLZ);
			while(rs.next())
			{
				System.out.println("img no is" + rs.getInt("sno"));
				Blob b = rs.getBlob("imgid");
				System.out.println("image is: " + b);
			
				byte arr[] = b.getBytes(1,(int)b.length());
				FileOutputStream fos = new FileOutputStream("C:\\Users\\india\\eclipse-workspace\\AiInstaVerifier\\pic3.jpeg");
				fos.write(arr);
				fos.close();
			}
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
			out.println("<center><img src=\"C:\\Users\\india\\eclipse-workspace\\AiInstaVerifier\\WebContent\\Instagram-Logo.jpg\" width=\"600\" height=\"600\"></center>\r\n");
			out.println("<br>");
			//out.println("<center><h3>Username:</h3></center>rs.getString(1)");
			out.println("<br>");
			
			out.println("<br>");
			out.println("<br>");
			out.println("<img src =\"C:\\Users\\india\\eclipse-workspace\\AiInstaVerifier\\pic3.jpeg\" height=\"90%\" width=\"100%\" >");
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
			out.println("<center><a href='http://localhost:8085/AiInstaVerifier/PieChartDemo'>click here</a></center>");
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
out.println("<center><a href='http://localhost:8085/AiInstaVerifier/Chart1'>click here</a></center>");
out.println("<hr>");
out.println("<br>");
			
			
			
			
			
			
			
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
			out.println("<center><a href='http://localhost:8085/AiInstaVerifier/Chart2'>click here</a></center>");
			out.println("<hr>");
			out.println("<br>");
			
			
			
			
			
			
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
			out.println("<center><a href='http://localhost:8085/AiInstaVerifier/Chart3'>click here</a></center>");
			out.println("<hr>");
			out.println("<br>");

			
			
			
			
			
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
			out.println("<center><a href='http://localhost:8085/AiInstaVerifier/Chart4'>click here</a></center>");
			out.println("<hr>");
			out.println("<br>");
			
			
			
			
			
			out.println("</body>");
			out.println("</html>");
			
			if(fl1==1) {
				Connection conn=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
				String SQL1=	"insert into test values(?,?)";
				PreparedStatement pst=(PreparedStatement) conn.prepareStatement(SQL1);
				pst.setString(1,"Shaded region shows genuinity");
				pst.setLong(2,1);
				pst.executeUpdate();
				}
				else
				{
					Connection conn=DriverManager.getConnection(
		                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
					String SQL1=	"insert into test values(?,?)";
					PreparedStatement pst=(PreparedStatement) conn.prepareStatement(SQL1);
					pst.setString(1,"Shaded region shows fakeness");
					pst.setLong(2,1);
					pst.executeUpdate();
					
				}
			
			if(fl2==1) {
				Connection conn2=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
				String SQL2=	"insert into test1 values(?,?)";
				PreparedStatement pst1=(PreparedStatement) conn2.prepareStatement(SQL2);
				pst1.setString(1,"Shaded region shows genuinity");
				pst1.setLong(2,1);
				pst1.executeUpdate();
				}
				else
				{
					Connection conn2=DriverManager.getConnection(
		                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
					String SQL2=	"insert into test1 values(?,?)";
					PreparedStatement pst1=(PreparedStatement) conn2.prepareStatement(SQL2);
					pst1.setString(1,"Shaded region shows fakeness");
					pst1.setLong(2,1);
					pst1.executeUpdate();
				}
			if(fl3==1) {
				Connection conn3=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
				String SQL3=	"insert into test2 values(?,?)";
				PreparedStatement pst2=(PreparedStatement) conn3.prepareStatement(SQL3);
				pst2.setString(1,"Shaded region shows genuinity");
				pst2.setLong(2,1);
				pst2.executeUpdate();
				}
				
				else 
				{
					Connection conn3=DriverManager.getConnection(
		                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
					String SQL3=	"insert into test2 values(?,?)";
					PreparedStatement pst2=(PreparedStatement) conn3.prepareStatement(SQL3);
					pst2.setString(1,"Shaded region shows fakeness");
					pst2.setLong(2,1);
					pst2.executeUpdate();
				}
			if(fl4==1) {
				Connection conn4=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
				String SQL4=	"insert into test3 values(?,?)";
				PreparedStatement pst3=(PreparedStatement) conn4.prepareStatement(SQL4);
				pst3.setString(1,"Shaded region shows genuinity");
				pst3.setLong(2,1);
				pst3.executeUpdate();
				}
				else
				{
					Connection conn4=DriverManager.getConnection(
		                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
					String SQL4=	"insert into test3 values(?,?)";
					PreparedStatement pst3=(PreparedStatement) conn4.prepareStatement(SQL4);
					pst3.setString(1,"Shaded region shows fakeness");
					pst3.setLong(2,1);
					pst3.executeUpdate();
				}
			if(fl5==1) {
				Connection conn5=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
				String SQL5=	"insert into test4 values(?,?)";
				PreparedStatement pst4=(PreparedStatement) conn5.prepareStatement(SQL5);
				pst4.setString(1,"Shaded region shows genuinity");
				pst4.setLong(2,fl5);
				pst4.executeUpdate();
				}
				else {
					Connection conn5=DriverManager.getConnection(
		                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
					String SQL5=	"insert into test4 values(?,?)";
					PreparedStatement pst4=(PreparedStatement) conn5.prepareStatement(SQL5);
					pst4.setString(1,"Shaded region shows fakeness");
					pst4.setLong(2,1);
					pst4.executeUpdate();
		
				}
			
				
			
			
			}
			out.println("<br>");
			out.println("<hr>");


			Connection conq=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
       System.out.println("successfully connected");
		      
       String SQLq = "select * from bio_comparision_table";
		Statement stq = conq.createStatement();
		ResultSet rq = stq.executeQuery(SQLq);
		while(rq.next())
		{
			out.println("<p><h2><center>Factor Test 6-Bio Token Matching</center></h2></p>");

			out.println("<center>( The following table shows the frequency of tokens that match in each category )</center>");
			out.println("<br>");

			out.println("<table style='width=100%' align='center' border='2'>");
			out.println("<tr>");
			out.println("<th><h4>Promotional</h4></th>");
			out.println("<th><h4>celebrity</h4></th>");
			out.println("<th>common</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>" +rq.getString(1)+ "</td>");
			out.println("<td>" +rq.getString(2)+ "</td>");
			out.println("<td>" +rq.getString(3)+ "</td>");
			out.println("</tr>");

			
			out.println("</table>");
			out.println("<br>");

			int count,count1,count2=0;
			count=Integer.parseInt(rq.getString(1));
			count1=Integer.parseInt(rq.getString(2));
			count2=Integer.parseInt(rq.getString(3));
			if((count>count1)&&(count>count2))
			{
				out.println("<center>It should be a promotional account</center>");
				omega1=1;

			}
			if((count1>count)&&(count1>count2))
			{
				out.println("<center>It should be a celebrity account</center>");
				omega2=1;

			}
			if((count2>count)&&(count2>count1))
			{
				out.println("<center>It should be a normal account</center>");
				omega3=1;

			}
			if((count==count1)&&(count==count2))
			{
				out.println("<center>Nothing can be said about account segregation</center>");
				omega4=1;

			}
			if((count==count1)&&(count>count2))
			{
				out.println("<center>It may be promotional or celebrity account</center>");
				omega5=1;

			}
			if((count1==count2)&&(count1>count))
			{
				out.println("<center>It may be celebrity or normal account</center>");
				omega6=1;
			}
			
			if((count==count2)&&(count>count1))
			{
				out.println("<center>It may be promotional or normal account</center>");
				omega7=1;
			}
		}
		
		if(omega1==1) {
			Connection connjj=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLjj=	"insert into test5 values(?,?)";
			PreparedStatement pstjj=(PreparedStatement) connjj.prepareStatement(SQLjj);
			pstjj.setString(1,"promotional account");
			pstjj.setLong(2,1);
			pstjj.executeUpdate();
			}
		if(omega2==1) {
				Connection connsa=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
				String SQLsa=	"insert into test5 values(?,?)";
				PreparedStatement pstsa=(PreparedStatement) connsa.prepareStatement(SQLsa);
				pstsa.setString(1,"celebrity account");
				pstsa.setLong(2,1);
				pstsa.executeUpdate();
				}
		if(omega3==1) {
			Connection connva=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLva=	"insert into test5 values(?,?)";
			PreparedStatement pstva=(PreparedStatement) connva.prepareStatement(SQLva);
			pstva.setString(1,"normal account");
			pstva.setLong(2,1);
			pstva.executeUpdate();
			}
	
		if(omega4==1) {
			Connection connta=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLta=	"insert into test5 values(?,?)";
			PreparedStatement pstta=(PreparedStatement) connta.prepareStatement(SQLta);
			pstta.setString(1,"no commnents");
			pstta.setLong(2,1);
			pstta.executeUpdate();
			}
	
		if(omega5==1) {
			Connection connga=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLga=	"insert into test5 values(?,?)";
			PreparedStatement pstga=(PreparedStatement) connga.prepareStatement(SQLga);
			pstga.setString(1,"promotional or celeb");
			pstga.setLong(2,1);
			pstga.executeUpdate();
			}
	
		if(omega6==1) {
			Connection connia=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLia=	"insert into test5 values(?,?)";
			PreparedStatement pstia=(PreparedStatement) connia.prepareStatement(SQLia);
			pstia.setString(1,"celeb or normal");
			pstia.setLong(2,1);
			pstia.executeUpdate();
			}
	
		if(omega7==1) {
			Connection connya=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLya=	"insert into test5 values(?,?)";
			PreparedStatement pstya=(PreparedStatement) connya.prepareStatement(SQLya);
			pstya.setString(1,"promotional or normal");
			pstya.setLong(2,1);
			pstya.executeUpdate();
			}
	
		
		out.println("<p><h3><center><font color=\"blue\">For Polarity Graph</font></center></h3></p>");
		out.println("<center><a href='http://localhost:8085/AiInstaVerifier/Chart5'>click here</a></center>");
		out.println("<br>");
		out.println("<hr>");


		Connection conw=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
   System.out.println("successfully connected");
	      
   String SQLw = "select * from cmn_com";
	Statement stw = conq.createStatement();
	ResultSet rw = stq.executeQuery(SQLw);
	while(rw.next())
	{
		
		
		
		
		
		out.println("<p><h2><center>Factor Test 7-Common commentators count</center></h2></p>");

		out.println("<center>( The following table shows the frequency )</center>");
		out.println("<table style='width=100%' align='center' border='2'>");
		out.println("<tr>");
		out.println("<th><h4>Common Commentators</h4></th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>" +rw.getString(4)+ "</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<br>");
		
		out.println("<th><h4><center>On comparing the value with threshold value</center></h4></th>");
		int thresh,thresh1;
		thresh=(Integer.parseInt(rw.getString(1))-Integer.parseInt(rw.getString(4)))+(Integer.parseInt(rw.getString(2))-Integer.parseInt(rw.getString(4)))+(Integer.parseInt(rw.getString(3))-Integer.parseInt(rw.getString(4)));
		thresh1=thresh/36;
		if(thresh1<2)
		{
			out.println("<center>The account should be normal account</center>");
			normal=1;
		}
		
		if((thresh1>2)&&(thresh1<8))
		{
			out.println("<center>The account should be celebrity account</center>");
			celeb=1;
		}
		if(thresh1>8)
		{
			out.println("<center>The account should be a promotional account</center>");
			promo=1;
		}
		
		if(normal==1) {
			Connection connll=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLll=	"insert into test6 values(?,?)";
			PreparedStatement pstll=(PreparedStatement) connll.prepareStatement(SQLll);
			pstll.setString(1,"normal account");
			pstll.setLong(2,1);
			pstll.executeUpdate();
			}
		if(celeb==1)
		{
			Connection connpp=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLpp=	"insert into test6 values(?,?)";
			PreparedStatement pstpp=(PreparedStatement) connpp.prepareStatement(SQLpp);
			pstpp.setString(1,"celebbrity account");
			pstpp.setLong(2,1);
			pstpp.executeUpdate();	
		}
		if(promo==1)
		{
			Connection connoo=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLoo=	"insert into test6 values(?,?)";
			PreparedStatement pstoo=(PreparedStatement) connoo.prepareStatement(SQLoo);
			pstoo.setString(1,"promotional account");
			pstoo.setLong(2,1);
			pstoo.executeUpdate();
		}
			
			

		

		
		
	}	
	out.println("<p><h3><center><font color=\"blue\">For Polarity Graph</font></center></h3></p>");
	out.println("<center><a href='http://localhost:8085/AiInstaVerifier/Chart6'>click here</a></center>");
	out.println("<br>");
	out.println("<hr>");
	
	
	

	
	Connection cone=DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
System.out.println("successfully connected");
      
String SQLe = "select * from postdate_rto";
Statement ste = cone.createStatement();
ResultSet re = ste.executeQuery(SQLe);
while(re.next())
{
	
	
	
	
	
	out.println("<p><h2><center>Factor Test 8-Dates and post count</center></h2></p>");

	out.println("<center>( The following table shows the different posts and different dates )</center>");
	out.println("<table style='width=100%' align='center' border='2'>");
	out.println("<tr>");
	out.println("<th><h4>No of posts</h4></th>");
	out.println("<th><h4>No of dates</h4></th>");
	out.println("</tr>");
	out.println("<tr>");
	out.println("<td>" +re.getString(1)+ "</td>");
	out.println("<td>" +re.getString(2)+ "</td>");
	out.println("</tr>");
	out.println("<table>");
	
	int ratio=0;
	ratio=Integer.parseInt(re.getString(1))/Integer.parseInt(re.getString(2));
	fl8=1;
	if(ratio<8)
	{
		out.println("<center>It should be a normal account</center>");

	}
	else
	{
		out.println("<center>It should be a bot or promotional marrketing account</center>");

	}
	if(fl8==1) {
		Connection cond=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
		String SQLD=	"insert into test7 values(?,?)";
		PreparedStatement pstd=(PreparedStatement) cond.prepareStatement(SQLD);
		pstd.setString(1,"normal account");
		pstd.setLong(2,1);
		pstd.executeUpdate();
		}
		else
		{
			Connection cond=DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
			String SQLD=	"insert into test7 values(?,?)";
			PreparedStatement pstd=(PreparedStatement) cond.prepareStatement(SQLD);
			pstd.setString(1,"bot account");
			pstd.setLong(2,1);
			pstd.executeUpdate();
			}
			
		}


	


Connection abc=DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
String SQLa ="insert into overall values(?,?)";
PreparedStatement psta=(PreparedStatement) abc.prepareStatement(SQLa);
PreparedStatement pstb=(PreparedStatement) abc.prepareStatement(SQLa);
PreparedStatement pstc=(PreparedStatement) abc.prepareStatement(SQLa);
PreparedStatement pstd=(PreparedStatement) abc.prepareStatement(SQLa);
PreparedStatement pste=(PreparedStatement) abc.prepareStatement(SQLa);
PreparedStatement pstf=(PreparedStatement) abc.prepareStatement(SQLa);
PreparedStatement pstg=(PreparedStatement) abc.prepareStatement(SQLa);
PreparedStatement psth=(PreparedStatement) abc.prepareStatement(SQLa);




if(fl1==1) {
psta.setString(1,"RATIO TEST: TRUE");
psta.setLong(2,20);
}
else
{
	psta.setString(1,"RATIO TEST: FALSE");
	psta.setLong(2,20);
}
//-----------------------------------------------	
if(fl2==1) {
pstb.setString(1,"PRIVATE: YES");
pstb.setLong(2,30);
}
else
{
	pstb.setString(1,"PRIVATE: NO");
	pstb.setLong(2,30);
}
//------------------------------------------
if(fl3==1) {
pstc.setString(1,"CERTIFICATION: DONE");
pstc.setLong(2,10);
}
else
{
	pstc.setString(1,"CERTIFICATION: NOT DONE");
	pstc.setLong(2,10);
}
//---------------------------------------
if(fl4==1) {
pstd.setString(1,"MISSING DP: NEGATIVE");
pstd.setLong(2,20);
}
else
{
	pstd.setString(1,"MISSING DP: POSITIVE");
	pstd.setLong(2,20);
}
//------------------------------------
if(fl5==1) {
pste.setString(1,"LESS POSTS: NO");
pste.setLong(2,10);
}
else
{
	pste.setString(1,"LESS POSTS: YES");
	pste.setLong(2,10);

}
//--------------------------------
if(fl8==1) {
pstf.setString(1,"ACTIVITY: NORMAL");
pstf.setLong(2,10);
}
else
{
	pstf.setString(1,"ACTIVITY: BOT");
	pstf.setLong(2,10);

}
//-------------------
if(normal==1) {
pstg.setString(1,"PROFILE: NORMAL");
pstg.setLong(2,5);
}
if(celeb==1) {
pstg.setString(1,"PROFILE: CELEB");
pstg.setLong(2,5);
}
if(promo==1) {
pstg.setString(1,"PROFILE: PROMO");
pstg.setLong(2,5);
}
//-----------------
if(omega1==1) {
	psth.setString(1,"TOKEN MATCH: PROMOOTIONAL");
	psth.setLong(2,5);
}
if(omega2==1) {
	psth.setString(1,"TOKEN MATCH: CELEBRITY");
	psth.setLong(2,5);
}
if(omega3==1) {
	psth.setString(1,"TOKEN MATCH: NORMAL");
	psth.setLong(2,5);
}
if(omega4==1) {
	psth.setString(1,"TOKEN MATCH: NO MATCH");
	psth.setLong(2,5);
}
if(omega5==1) {
	psth.setString(1,"TOKEN MATCH: PROMO OR CELEB");
	psth.setLong(2,5);
}
if(omega6==1) {
	psth.setString(1,"TOKEN MATCH: CELEB OR NORMAL");
	psth.setLong(2,5);
}
if(omega7==1) {
	psth.setString(1,"TOKEN MATCH: PROMO OR NORMAL");
	psth.setLong(2,5);
}



psta.executeUpdate();
pstb.executeUpdate();
pstc.executeUpdate();
pstd.executeUpdate();
pste.executeUpdate();
pstf.executeUpdate();
pstg.executeUpdate();
psth.executeUpdate();
	

out.println("<p><h3><center><font color=\"blue\">For Polarity Graph</font></center></h3></p>");
out.println("<center><a href='http://localhost:8085/AiInstaVerifier/Chart7'>click here</a></center>");
	
	
	
	
		
		out.println("<hr>");
		out.println("<hr>");
		out.println("<br>");
		out.println("<p><h3><center><font color=\"blue\">For a overall Polarity Graph</font></center></h3></p>");
		out.println("<center><a href='http://localhost:8085/AiInstaVerifier/Covers'>click here</a></center>");
		
			
			//fout.close();  
			//} 
			System.out.println("ok");  
			              
			con.close();  
	}	
		catch (Exception e) 
		{e.printStackTrace();  }
		
	}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
