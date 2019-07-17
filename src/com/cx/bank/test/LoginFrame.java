package com.cx.bank.test;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.manager.ManagerInterface;

public class LoginFrame extends JFrame {

	public LoginFrame() {
		setTitle("登入界面");
		setResizable(false);
		int width = 340;
		int height = 350;
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		setLocation(w, h);
		setLayout(null);
		Container c = getContentPane();
		c.setLayout(null);
		
		
		//创建各个组件
		JLabel lb_1 = new JLabel("用户名：");
		JLabel lb_2 = new JLabel("密码：");
		JLabel lb_3 = new JLabel(" ");
		JTextField tf_1 = new JTextField();
		JPasswordField tf_2 = new JPasswordField();
		JButton bt_1 = new JButton("注册");
		JButton bt_2 = new JButton("登入");
		JButton bt_3 = new JButton("退出");
		
		//设置各个组件位置
		lb_1.setBounds(90, 85, 80, 20);//
		tf_1.setBounds(140, 85, 100, 20);
		lb_2.setBounds(90, 120, 80, 20);
		tf_2.setBounds(140, 120, 100, 20);
		bt_1.setBounds(110, 185, 60, 25);
		bt_2.setBounds(175, 185, 60, 25);
		bt_3.setBounds(265, 293, 60, 20);


		bt_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String user_name= tf_1.getText();
				String user_password = tf_2.getText();
				Boolean bool=new ManagerImpl().Register(user_name, user_password);
				if(bool==true) {
					lb_3.setText("  注册成功   ");
					lb_3.setBounds(135, 255, 100, 25);
				}else {
					lb_3.setText("用户名已存在");
					lb_3.setBounds(135, 255, 100, 25);
				}
			}
		});	
		bt_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String user_name= tf_1.getText();
				String user_password = tf_2.getText();
				Boolean bool=new ManagerImpl().Login(user_name, user_password);
				if(bool==true) {
					lb_3.setText("  登入成功   ");
					lb_3.setBounds(135, 255, 100, 25);
					setVisible(false);
				}else {
					
					lb_3.setText("用户名或密码错误");
					lb_3.setBounds(125, 255, 120, 25);
				}
			}
		});	
		bt_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		c.add(lb_1);
		c.add(lb_2);
		c.add(lb_3);
		c.add(tf_1);
		c.add(tf_2);
		c.add(bt_1);
		c.add(bt_2);
		c.add(bt_3);
		setVisible(true);
			}

	public static void main(String[] args) {
		new LoginFrame();
	}
}
