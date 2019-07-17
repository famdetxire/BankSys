package com.cx.bank.manager;

import java.sql.SQLException;

import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;

public interface ManagerInterface {
	public  boolean Register(String user_name,String user_password);
	public  boolean Login(String user_name,String user_password);
	public  boolean Deposit(UserBean user, MoneyBean balance, double money);
	boolean Withdrawals(UserBean user, MoneyBean balance, double money);
	public  void Inquiry(UserBean user);
	public  boolean Transfer(UserBean user, MoneyBean balance,int user_id, double money);
	public  void getLog( UserBean user );
	public  void ExitSystem();
}
