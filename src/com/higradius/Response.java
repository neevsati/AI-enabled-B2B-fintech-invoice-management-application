package com.higradius;

import java.sql.Date;

public class Response {
	private String cust_number;
	private String name_customer;
	private Date due_in_date;
	private int total_open_amount;
	private long invoice_id;
	//private String invoice_currency;
	private Date clear_date;
//	private String Buckets;
	public String getCust_number() {
		return cust_number;
	}
	public void setCust_number(String cust_number) {
		this.cust_number = cust_number;
	}
	public String getName_customer() {
		return name_customer;
	}
	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}
	public Date getDue_in_date() {
		return due_in_date;
	}
	public void setDue_in_date(Date due_in_date) {
		this.due_in_date = due_in_date;
	}
	public int getTotal_open_amount() {
		return total_open_amount;
	}
	public void setTotal_open_amount(int total_open_amount) {
		this.total_open_amount = total_open_amount;
	}
	public long getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(long invoice_id) {
		this.invoice_id = invoice_id;
	}
	public Date getClear_date() {
		return clear_date;
	}
	public void setClear_date(Date clear_date) {
		this.clear_date = clear_date;
	}
	
	
}
