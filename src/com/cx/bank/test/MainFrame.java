package com.cx.bank.test;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;



public class MainFrame extends JFrame {
	public MainFrame(UserBean user,MoneyBean balance) {
		setTitle("添加界面");
		setResizable(false);
		// 1、设置窗口位置在屏幕正中央
		int width = 450;
		int height = 650;
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		setLocation(w, h);
		Container c = getContentPane();
		c.setLayout(null);// 设置容器为绝对布局
		
		JLabel lb7=new JLabel("");
	
		JButton bt_1=new JButton("存款");
		JButton bt_2=new JButton("取款");
		JButton bt_3=new JButton("转账");
		JButton bt_4=new JButton("余额");
		JButton bt_5=new JButton("明细");
		JButton bt_6=new JButton("退出");


		
		bt_1.setBounds(180, 100, 90, 30);
		bt_2.setBounds(180, 160, 90, 30);
		bt_3.setBounds(180, 220, 90, 30);
		bt_4.setBounds(180, 280, 90, 30);
		bt_5.setBounds(180, 340, 90, 30);
		bt_6.setBounds(180, 400, 90, 30);
		
		bt_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DepositFrame(user, balance);
			}
		});
		
        bt_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new WithdrawalsFrame(user, balance);
			}
		});
        
        bt_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TransferFrame(user, balance);
			}
		});
        
        bt_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerImpl().Inquiry(user);
			}
		});
        
        bt_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerImpl().getLog(user);
			}
		});
		
		
		
		bt_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ManagerImpl().ExitSystem();
			}
		});

		c.add(bt_1);
		c.add(bt_2);
		c.add(bt_3);
		c.add(bt_4);
		c.add(bt_5);
		c.add(bt_6);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		MoneyBean money = null;
		UserBean user = null;
		
		new MainFrame(user,money);
	}
}
