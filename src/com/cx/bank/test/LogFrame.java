package com.cx.bank.test;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;

public class LogFrame extends JFrame {
	public LogFrame(String[][] arr ,String[] arrColumn) {
		setTitle("明细界面");
		setResizable(false);
		// 1、设置窗口位置在屏幕正中央
		int width = 438;
		int height = 365;
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		setLocation(w, h);

		Container c = getContentPane();
		c.setLayout(null);// 设置容器为绝对布局
		// c.setBackground(Color.WHITE);

		// 创建窗口所需的各个面板
		JPanel p = new JPanel();// 信息面板
		JButton bt_1 = new JButton("返回");
		JScrollPane sp = new JScrollPane(p);// 滚动面板，将信息面板设为滚动模式

		// 设置面板方位
		sp.setBounds(15, 10, 350, 300);
		bt_1.setBounds(362,308,60,20);

		JTable table = new JTable(arr, arrColumn);// 创建表格对象
		table.setRowHeight(25);// 设置表格间宽度
		
		bt_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		p.add(table);// 将表格添加到面板p
		// 将面板添加到容器中
		c.add(sp);
		c.add(bt_1);

		setVisible(true);

	}

	public static void main(String[] args) {
		String[] arrColumn = null;
		String[][] arr = null;
		new LogFrame(arr, arrColumn);
	}
}