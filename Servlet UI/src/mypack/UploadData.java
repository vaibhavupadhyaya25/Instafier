package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UploadData")
public class UploadData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UploadData() {
        super();
       
    }

    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String imgPath = request.getParameter("imgpath");
		
		try{
		       
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("class successfully loaded");
            
            Connection conn=DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/mayur","root","admin");
            System.out.println("successfully connected");
           
            PreparedStatement ps=conn.prepareStatement("insert into imagebox(imgid) values(?)");  
         
              
            FileInputStream fin=new FileInputStream(imgPath);  
            ps.setBinaryStream(1,fin,fin.available());  

            
            ps.executeUpdate();  
            
        
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h5>Image have been uploaded!!</h5>");
			out.println("<h6>Go Back to Main Menu <a href = \"first.html\">Click here</a><h6>");
			out.println("</body>");
			out.println("</html>");

            conn.close();
            }
            catch(Exception e)
            { 
                   System.out.println(e);
            }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
