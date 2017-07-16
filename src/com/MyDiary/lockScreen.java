package com.MyDiary;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.BorderLayout;

public class lockScreen extends JFrame
{
	MyDiaryHomeScreen frame;
	JFrame thisframe=this;
	String password="";
	JLabel passwordlbl = new JLabel("");

	public lockScreen(MyDiaryHomeScreen frame) {
		getContentPane().addKeyListener(keyada);
		
		this.frame=frame;
		getContentPane().setBackground(frame.colorScheme.colorScheme.DBGCOLOR);
		setBounds(frame.getBounds());
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		passwordlbl.setForeground(Color.PINK);
		
		
		passwordlbl.setFont(new Font("Snap ITC", Font.PLAIN, 42));
		passwordlbl.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(passwordlbl,BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Diary Locked");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		this.setVisible(true);
		this.setFocusable(true);
		lblNewLabel.addKeyListener(keyada);
		passwordlbl.addKeyListener(keyada);
		lblNewLabel.grabFocus();
		thisframe.addKeyListener(keyada);
		// TODO Auto-generated constructor stub
	}
	KeyListener keyada=new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {	System.out.println(e.getKeyChar()+"");
			System.out.println(e.getKeyCode()+"");
			if(e.getKeyCode()==(8))
				//if(e.getKeyCode()==(127))
			{
				password="";
				passwordlbl.setText("");
			}
			else
			{
				password=password+e.getKeyChar();
				
				passwordlbl.setText(passwordlbl.getText()+"*");
				try {
					if(password.equals(new DiaryInitializer().getPassword()))
					{
					frame.show();
					thisframe.dispose();
					}
					else
					{
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	};
}
