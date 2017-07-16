package com.MyDiary;

import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DiaryInitializer extends JFrame{

	JFrame frame=this;
	Connection con;
	 JTextField nameTextFeield;
	 JTextField passwordTextFeield;
	 JTextField cPasswordTextField;
	 JLabel lblConfirmPassword;
	 
	 
	 public static void main(String ...args)
	 {
		 try {
			if( new DiaryInitializer().getPassword()!=null)
					{
						new MyDiaryHomeScreen().lock();
					}
			else
			{
				new DiaryInitializer().setVisible(true);
			}
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	 }
	public DiaryInitializer() {

		setBounds(0, 0, 500, 500);
		getContentPane().setBackground(new Color(205, 133, 63));
		getContentPane().setLayout(null);
		
		nameTextFeield = new JTextField();
		nameTextFeield.setBounds(245, 50, 86, 20);
		getContentPane().add(nameTextFeield);
		nameTextFeield.setColumns(10);
		
		passwordTextFeield = new JPasswordField();
		passwordTextFeield.setBounds(245, 103, 86, 20);
		getContentPane().add(passwordTextFeield);
		passwordTextFeield.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Diary Holder Name");
		lblNewLabel.setBounds(67, 53, 129, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(67, 106, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		cPasswordTextField = new JPasswordField();
		cPasswordTextField.setColumns(10);
		cPasswordTextField.setBounds(245, 157, 86, 20);
		getContentPane().add(cPasswordTextField);
		

		lblConfirmPassword = new JLabel("confirm password");
		lblConfirmPassword.setBounds(67, 160, 86, 14);
		getContentPane().add(lblConfirmPassword);
		
		JButton btnCreateNewDiary = new JButton("Create New Diary");
		btnCreateNewDiary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				if(passwordTextFeield.getText().equals(cPasswordTextField.getText()))
				{
					try {
						createTable();
						addUser(nameTextFeield.getText(), passwordTextFeield.getText());
						new NoteDataBaseManagement().createTable();
						new MyDiaryHomeScreen().lock();
						
						frame.dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "please check your password");
				}
			}
		});
		btnCreateNewDiary.setBounds(116, 234, 188, 23);
		getContentPane().add(btnCreateNewDiary);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	void getDBConnection()
	{
		 
		    try {
		      
		      con = DriverManager.getConnection("jdbc:sqlite:"+"Notes");
		      File temp=new File("//ramu");
		      System.out.println(temp.getAbsolutePath());
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     try {
				con.setAutoCommit(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    }
		    System.out.println("Opened database successfully");
	}
	void createTable() throws SQLException
	{
		getDBConnection();
		System.out.println(Calendar.getInstance().getTime());
		
			Statement st=con.createStatement();
			st.execute("create table user  "+" (name text not null, password text not null );");
	}
	String getPassword() throws SQLException
	{
			String temp=null;
			ResultSet rs=null;
			try {

				getDBConnection();
				PreparedStatement ps=con.prepareStatement("SELECT * from  user ;");

				 rs=ps.executeQuery();
				 if(rs.next())
				 {
					 temp=rs.getString("password");
						rs.close();
				 }				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con.close();
	
			
		return temp;
		
	}
	void addUser(String name,String password)
	{
		try {

			getDBConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO  user (name,password) values (?,?);");
			ps.setString(1,name);
			ps.setString(2,password);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
