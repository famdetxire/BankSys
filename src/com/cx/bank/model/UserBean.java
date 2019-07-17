package com.cx.bank.model;

public class UserBean {
	int user_id;
	String user_name;
	String user_password;
	int user_flag;
	public UserBean(int user_id, String user_name, String user_password, int user_flag) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_flag = user_flag;
	}
	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public int getUser_flag() {
		return user_flag;
	}
	public void setUser_flag(int user_flag) {
		this.user_flag = user_flag;
	}
	
}
