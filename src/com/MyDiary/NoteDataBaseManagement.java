package com.MyDiary;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NoteDataBaseManagement {

	
	Connection con;
	
	void getDBConnection()
	{
		 
		    try {
		      Class.forName("org.sqlite.JDBC");
		      con = DriverManager.getConnection("jdbc:sqlite:"+"Notes");
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     try {
				con.setAutoCommit(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
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
			st.execute("create table notes  "+" (head text not null, notes text not null,date Date primery key );");


	}
	
	void addNotes(String Heading,String notes)
	{
		try {

			getDBConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO  notes (head,notes,date) values (?,?,?);");
			ps.setString(1,Heading);
			ps.setString(2,notes);
			Date date=new Date(Calendar.getInstance().getTime().getTime());
			ps.setDate(3, date);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	void changePassword(String password)
	{
		getDBConnection();
		try {
			PreparedStatement ps=
					con.prepareStatement("update user set password=? ");
			ps.setString(1, password);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	ResultSet getNotes()
	{
		ResultSet rs=null;
		try {

			getDBConnection();
			PreparedStatement ps=con.prepareStatement("SELECT * from  notes ORDER BY date DESC ;");

			 rs=ps.executeQuery();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	ResultSet getPerticularNotes(Date date)
	{
		ResultSet rs=null;
		try {

			getDBConnection();
			PreparedStatement ps=con.prepareStatement("SELECT * from  notes where date="+date.getTime()+";");
			
			 rs=ps.executeQuery();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	void deletePerticularNotes(Date date)
	{
		ResultSet rs=null;
		try {

			getDBConnection();
			PreparedStatement ps=con.prepareStatement("DELETE  from  notes where date="+date.getTime()+";");
			
			ps.execute();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		

		

}
