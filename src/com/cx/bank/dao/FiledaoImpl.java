package com.cx.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cx.bank.util.JDBCutil;

public class FiledaoImpl implements BankDaoInterface {


	@Override
	public boolean Register(String user_name, String user_password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCutil.getConn();
			String sql = "select*from t_user where user_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			rs = ps.executeQuery();
			if (rs.next()!=true) {
				sql="insert into t_user values (null,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, user_name);
				ps.setString(2, user_password);
				ps.setInt(3, 1 );
				ps.setFloat(4, 0);
				int num=ps.executeUpdate();
				if (num>0) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.release(rs, ps, conn);

		}
		return false;
	}

	@Override
	public ResultSet Login(String user_name, String user_password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			conn = JDBCutil.getConn();
			String sql = "select*from t_user where user_name=? and user_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, user_password);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
	}

	@Override
	public int deposit(double balance ,String user_name) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = JDBCutil.getConn();
			String sql = "update t_user set balance=? where user_name=?" ;
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setString(2, user_name);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.release(ps, conn);
		}
		return result;
	}

	@Override
	public int withdrawals(double balance ,String user_name) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = JDBCutil.getConn();
			String sql = "update t_user set balance=? where user_name=?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setString(2, user_name);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.release(ps, conn);
		}
		return result;
	}

	@Override
	public ResultSet getBalance(String user_name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCutil.getConn();
			String sql = "select*from t_user where user_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	@Override
	public ResultSet transfer(String user_name,double balance , int user_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCutil.getConn();
			String sql = "update t_user set balance=? where user_name=?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setString(2, user_name);
			ps.executeUpdate();
			sql="select*from t_user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public void Record(String log_type, double log_amount, int user_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		int rs = 0;
		try {
			conn = JDBCutil.getConn();
			String sql="insert into t_log values (null,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, log_type);
			ps.setDouble(2, log_amount);
			ps.setInt(3, user_id );
			rs=ps.executeUpdate();
			if (rs>0) {
				System.out.println("true");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.release(ps, conn);

		}
	}

	@Override
	public ResultSet GetLog( int user_id ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCutil.getConn();
			String sql = "select*from t_log where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet selectUser_id(int user_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCutil.getConn();
			String sql = "select*from t_user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
	}
}
