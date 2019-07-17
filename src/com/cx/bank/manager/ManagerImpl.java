package com.cx.bank.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cx.bank.dao.FiledaoImpl;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.test.InquiryFrame;
import com.cx.bank.test.LogFrame;
import com.cx.bank.test.LoginFrame;
import com.cx.bank.test.MainFrame;
import com.cx.bank.util.JDBCutil;

public class ManagerImpl implements ManagerInterface {

	@Override
	public boolean Register(String user_name, String user_password) {
		Boolean bool = new FiledaoImpl().Register(user_name, user_password);
		if (bool == true) {
			return true;
		}
		return false;
	}

	@Override
	public boolean Login(String user_name, String user_password) {
		ResultSet rs = new FiledaoImpl().Login(user_name, user_password);
		UserBean user = new UserBean();
		MoneyBean balance = new MoneyBean();
		try {
			if (rs.next()) {
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_password(rs.getString("user_password"));
				user.setUser_flag(rs.getInt("user_flag"));
				balance.setBalance(rs.getDouble("balance"));
				new MainFrame(user, balance);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.release(rs);
			;
		}
		return false;

	}

	@Override
	public boolean Deposit(UserBean user, MoneyBean balance, double money) {
		if (money <= 0) {
			System.out.println("false");
			return false;
		}
		balance.setBalance(balance.getBalance() + money);
		int result = new FiledaoImpl().deposit(balance.getBalance(), user.getUser_name());
		if (result > 0) {
			new FiledaoImpl().Record("deposit", money, user.getUser_id());
			return true;
		}
		return false;
	}

	@Override
	public boolean Withdrawals(UserBean user, MoneyBean balance, double money) {
		
		if (money <= 0 || money > balance.getBalance()) {
			return false;
		}
		
		balance.setBalance(balance.getBalance() - money);
		int result = new FiledaoImpl().withdrawals(balance.getBalance(), user.getUser_name());
		if (result > 0) {
			new FiledaoImpl().Record("Withdrawals", money, user.getUser_id());
			return true;
		}
		return false;
	}

	@Override
	public void Inquiry(UserBean user) {
		ResultSet rs = new FiledaoImpl().getBalance(user.getUser_name());
		try {
			if (rs.next()) {
				double balance = rs.getDouble("balance");
				new InquiryFrame(balance);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.release(rs);
		}
	}

	@Override
	public void ExitSystem() {
		System.exit(0);
	}

	@Override
	public boolean Transfer(UserBean user, MoneyBean balance, int user_id, double money) {
		if(money<=0||money>balance.getBalance()) {
			
			return false;
		} else
			try {
				if (!new FiledaoImpl().selectUser_id(user_id).next()) {
					return false;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		balance.setBalance(balance.getBalance()-money);
		ResultSet rs= new FiledaoImpl().transfer(user.getUser_name(), balance.getBalance(), user_id);
		try {
			if(rs.next()) {
				new FiledaoImpl().Record("Transfer", money, user.getUser_id());
				double getBalance = rs.getDouble("balance");
				double setBalance = getBalance + money ;
				String user_name = rs.getString("user_name");
				int result=new FiledaoImpl().deposit(setBalance,user_name);
				if(result>0) {
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.release(rs);
		}
		return false;
	}

	@Override
	public void getLog(UserBean user) {
		ResultSet rs = new FiledaoImpl().GetLog(user.getUser_id());
		int num = 0;
		// 取出rs里的数据，并依次存放到list集合里
		ArrayList<String> list = new ArrayList<String>();
		try {

			while (rs.next()) {
				int log_id = rs.getInt("log_id");
				String log_type = rs.getString("log_type");
				String log_amount = rs.getString("log_amount");
				String user_id = rs.getString("user_id");
				list.add(log_id + "");
				list.add(log_type);
				list.add(log_amount);
				list.add(user_id);
				num++;// numt条日志
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[][] arr = new String[num + 1][7];// +1行存放每列名称，
		String[] arrColumn = new String[] { "log_id", "log_type", "log_amount", "user_id" };
		for (int i = 0; i < 4; i++) {
			arr[0][i] = arrColumn[i];
		}
		// 将集合里的内容依次存放到arr数组
		int n = 0;
		for (int j = 0; j < num; j++)
			for (int i = 0; i < 4; i++) {
				arr[j + 1][i] = list.get(n);
				n++;
			}
		new LogFrame(  arr, arrColumn );
	}
	
	
}
