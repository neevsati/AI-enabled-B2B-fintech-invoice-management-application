package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	Statement stmt;
	String cname, cnumber;
	int invoiceid, invoiceamt;
	Date duedate;
	
	//JDBC driver name and database url
	static final String JDBC_DRIVER="com.mysql.jdbc.Driverr";
	static final String DB_URL="jdbc:mysql://localhost/highradius";
		
	//Database credentials
	static final String USER = "root";
	static final String PASSWORD = "veen";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		try (PrintWriter out = response.getWriter()) {
			cname = request.getParameter("customer-name");
			cnumber = request.getParameter("customer-number");
			invoiceid = Integer.parseInt(request.getParameter("invoice-number"));
			invoiceamt = Integer.parseInt(request.getParameter("invoice-amt"));
			java.util.Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("due-date"));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			java.sql.Date duedate = new java.sql.Date(date.getTime()); 
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				stmt = conn.createStatement();
				String query = "insert into mytable (cust_number, name_customer, inovice_id, total_open_amount, due_in_date) values (cnumber,cname,invoiceid,invoiceamt,duedate)";
				stmt.executeQuery(query);
				
				System.out.println("data inserted.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
