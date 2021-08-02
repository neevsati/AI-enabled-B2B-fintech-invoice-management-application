package com.higradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class AddData2
 */
@WebServlet("/AddData2")
public class AddData2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	//JDBC driver name and database url
	static final String JDBC_DRIVER="com.mysql.jdbc.Driverr";
	static final String DB_URL="jdbc:mysql://localhost/highradius";
		
	//Database credentials
	static final String USER = "root";
	static final String PASSWORD = "veen";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddData2() {
    	//super();
        // TODO Auto-generated constructor stu
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String cust_number = request.getParameter("customer-number");
		String name_customer = request.getParameter("customer-name");
		int invoice_id = Integer.parseInt(request.getParameter("invoice-number"));
		int total_open_amount = Integer.parseInt(request.getParameter("invoice-amt"));
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("due-date"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Date due_in_date = new java.sql.Date(date.getTime()); //sql date

//		String due_in_date = request.getParameter("due-date");
//		String clear_date = request.getParameter("clear_date");
		
		int executionStatus = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		List<ExecutionResponse> demoList = new ArrayList<>();
		String jsonString = null;
		ExecutionResponse demo = new ExecutionResponse();
		
		
		System.out.print("here");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.prepareStatement("INSERT into mytable (cust_number, name_customer, inovice_id, total_open_amount, due_in_date) values (cust_number, name_customer, invoice_id, total_open_amount, due_in_date)");
			stmt.setString(1,cust_number);
			stmt.setString(2,name_customer);
			stmt.setInt(3,invoice_id);
			stmt.setInt(4,total_open_amount);
			stmt.setDate(5,due_in_date);
			//stmt.setString(6,clear_date);
			executionStatus = stmt.executeUpdate();
			if (executionStatus >=1 ) {
				demo.setExecutionStatus("true");
				demo.setExecutionMessage("Data inserted succesfully.");
			} else {
				demo.setExecutionStatus("false");
				demo.setExecutionMessage("Failed to insert data.");
			}
			demoList.add(demo);
			
			jsonString = getJSONStringFromObject(demoList);
			response.setContentType("application/json");
			try {
				response.getWriter().write(jsonString);
			} catch (IOException e) {
				e.printStackTrace();
			}
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		
	}
	
	private String getJSONStringFromObject(List<ExecutionResponse> filmlist) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(filmlist);
		//System.out.print(json);
		return json;
	}

}
