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

/**
 * Servlet implementation class PieChartDemo
 */
@WebServlet("/PieChartDemo")
public class PieChartDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PieChartDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		try {
		connection=
		DriverManager.getConnection("jdbc:mysql://localhost:3306/mayur","root","codethecode@1");
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}catch (InstantiationException e) {
		e.printStackTrace();
		} catch (IllegalAccessException e) {
		e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			
		e.printStackTrace();
		}
		JDBCPieDataset dataset = new JDBCPieDataset(connection);
		try {
		dataset.executeQuery("Select name,testcase from test");
		JFreeChart chart = ChartFactory.createPieChart
		         ("Instagram Profile Verifier Graph", dataset, true, true, false);
		chart.setBorderPaint(Color.black);
		chart.setBorderStroke(new BasicStroke(10.0f));
		chart.setBorderVisible(true);
		if (chart != null) {
		int width = 500;
		int height = 350;
		final ChartRenderingInfo info = new ChartRenderingInfo
		                               (new StandardEntityCollection());
		response.setContentType("image/png");
		OutputStream out=response.getOutputStream();
		ChartUtilities.writeChartAsPNG(out, chart, width, height,info);
		}
		}catch (SQLException e) {
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
