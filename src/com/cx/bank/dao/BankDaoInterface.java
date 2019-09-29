package com.cx.bank.dao;

import java.sql.ResultSet;

public interface BankDaoInterface {
	public boolean Register(String user_name,String user_password);//注册
	public ResultSet Login(String user_name,String user_password);//登入
	public int deposit(double blance,String user);//存款
	public int withdrawals(double balance , String user);//取款
	public ResultSet getBalance(String User_name); //获取余额
	public ResultSet transfer(String user_name,double balance , int user_id );//转账
	public void Record(String log_type , double log_amount ,int user_id);//记录
	public ResultSet GetLog(int user_id);//查看日志
	public ResultSet selectUser_id(int user_id);
}
