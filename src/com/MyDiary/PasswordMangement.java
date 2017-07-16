package com.MyDiary;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JRootPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PasswordMangement extends JPanel {

	MyDiaryHomeScreen frame;
	private JTextField oldPassTextfield;
	private JTextField newPassTextfield;
	PasswordMangement thisframe=this;
	
	public PasswordMangement(MyDiaryHomeScreen frame) {
		this.frame=frame;
		this.setBackground(Color.BLUE);
		this.setBounds(0, 0, 463, 348);
		
		newPassTextfield = new JTextField();
		newPassTextfield.setForeground(Color.BLACK);
		newPassTextfield.setFont(new Font("Wingdings 3", Font.BOLD, 29));
		newPassTextfield.setBorder(UIManager.getBorder("ComboBox.border"));
		newPassTextfield.setBackground(Color.YELLOW);
		newPassTextfield.setColumns(10);
		
		oldPassTextfield = new JTextField();
		oldPassTextfield.setForeground(Color.BLUE);
		oldPassTextfield.setFont(new Font("Wingdings 3", Font.BOLD, 29));
		oldPassTextfield.setBounds(new Rectangle(0, 0, 0, 50));
		oldPassTextfield.setBorder(null);
		oldPassTextfield.setBackground(Color.YELLOW);
		oldPassTextfield.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Your Old Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Your New Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(oldPassTextfield.getText().equals(new DiaryInitializer().getPassword()))
					{
						new NoteDataBaseManagement().changePassword(newPassTextfield.getText());
						JOptionPane.showMessageDialog(frame, "password Changed");
						frame.tabbedPane.remove(thisframe);
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Wrong Password");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		btnNewButton.setBackground(Color.YELLOW);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
					.addGap(13))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
					.addGap(3))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(97)
					.addComponent(oldPassTextfield, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
					.addGap(121))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(88)
					.addComponent(newPassTextfield, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
					.addGap(138))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addGap(89))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(oldPassTextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(newPassTextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(59))
		);
		setLayout(groupLayout);
		this.setVisible(true);
	}
}
