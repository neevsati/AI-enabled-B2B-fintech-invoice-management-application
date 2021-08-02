package com.higradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * Servlet implementation class FetchData
 */
@WebServlet("/FetchData")
public class FetchData extends HttpServlet {
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
    public FetchData() {
        //super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connection conn = null;
		Statement stmt = null;
		List<Response> demoList = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String sql;
			sql = "select cust_number, name_customer, posting_date, due_in_date, invoice_id, clear_date, total_open_amount, cust_payment_terms from mytable";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Response res = new Response();
				res.setCust_number(rs.getString("cust_number"));
				res.setName_customer(rs.getString("name_customer"));
//				demo.setBuckets(rs.getString("Buckets"));
				res.setDue_in_date(rs.getDate("due_in_date"));
//				demo.setInvoice_currency(rs.getString("invoice_currency"));
				res.setInvoice_id(rs.getLong("invoice_id"));
				res.setTotal_open_amount(rs.getInt("total_open_amount"));
				res.setClear_date(rs.getDate("clear_date"));
				
				demoList.add(res);
			}
			String jsonString = getJSONStringFromObject(demoList);
			response.setContentType("application/json");
			try {
				response.getWriter().write(jsonString);
			} catch (IOException e) {
				e.printStackTrace();
			}
			rs.close();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String getJSONStringFromObject(List<Response> filmlist) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(filmlist);
		//System.out.print(json);
		return json;
	}

}
