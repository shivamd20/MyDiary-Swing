package com.MyDiary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class WritePaneClass extends JPanel{

	private static final long serialVersionUID = 6119829003647509968L;
	JPanel panel_3 = new JPanel();
	MyDiaryHomeScreen mdhs;

	JButton saveBtn = new JButton("save");

	 JTextField headingtext;
	 final JSlider writePaneSlider = new JSlider();
		 final JButton chageFontSizeWritePanel = new JButton("change Font Size");
		 final JButton btnChangeColor = new JButton("change BackGround color");
		final JButton btnChangeFontColor = new JButton("change Font color");

	TextArea writeNotetextArea = new TextArea();

	JButton clearAllBtn = new JButton("clear all");
	JToolBar toolBar = new JToolBar();

	JPanel panel_5 = new JPanel();
	public WritePaneClass(MyDiaryHomeScreen temp) {
		mdhs=temp;
		setLayout(new BorderLayout(0, 0));

		panel_3.setBorder(null);
		add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		panel_5.setBackground(Color.BLACK);
		panel_5.setBorder(null);
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		saveBtn.setMnemonic('s');
		saveBtn.setBorder(null);

		saveBtn.setBackground(Color.WHITE);
		toolBar.add(saveBtn);

		

		toolBar.setBorder(null);
		writeNotetextArea.addKeyListener(writeKeyLis);
		panel_5.add(toolBar, BorderLayout.SOUTH);

		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mdhs.noteDB.addNotes(headingtext.getText(),writeNotetextArea.getText());
				mdhs.mntmRefresh.doClick();
				mdhs.tabbedPane.setSelectedIndex(1);
				
			}
		});
		
		clearAllBtn.setBorder(null);
		clearAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				writeNotetextArea.setText("");
				//textArea.
			}
		});
		clearAllBtn.setBackground(Color.WHITE);
		toolBar.add(clearAllBtn);
		
		toolBar.add(writePaneSlider);
		chageFontSizeWritePanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mdhs.colorScheme.colorScheme.writePaneFontSize=writePaneSlider.getValue();
				mdhs.initilize();
			}
		});
		btnChangeColor.setBorder(null);
		btnChangeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mdhs.frame.setVisible(false);
				mdhs.colorFrame.setVisible(true);
				mdhs.colorFrame.setBounds(mdhs.frame.getBounds());
				JColorChooser colorChooser=new JColorChooser();
				mdhs.colorFrame.getContentPane().add(colorChooser);
				colorChooser.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						Color c=colorChooser.getColor();
						mdhs.colorScheme.colorScheme.writePaneColor=c;
						mdhs.initilize();
						mdhs.frame.setVisible(true);
					}
					
				});
			}
		});
		btnChangeFontColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mdhs.frame.setVisible(false);
				mdhs.colorFrame.setVisible(true);
				mdhs.colorFrame.setBounds(mdhs.frame.getBounds());
				JColorChooser colorChooser=new JColorChooser();
				mdhs.colorFrame.getContentPane().add(colorChooser);
				colorChooser.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseReleased(MouseEvent e) {

						mdhs.frame.setVisible(true);
						Color c=colorChooser.getColor();
						mdhs.colorScheme.colorScheme.writePaneTextColor=c;
						mdhs.colorFrame.dispose();
						mdhs.initilize();
					}
				
				});
			}
		});
		btnChangeFontColor.setBorder(null);
		
		toolBar.add(btnChangeFontColor);
		
		toolBar.add(btnChangeColor);
		chageFontSizeWritePanel.setBorder(null);
		
		toolBar.add(chageFontSizeWritePanel);
		
		headingtext = new JTextField();
		headingtext.setBorder(null);
		headingtext.setText("Note Heading");
		
		add(headingtext,BorderLayout.NORTH);
		headingtext.setColumns(10);
		
		
		
		// TODO Auto-generated constructor stub
	}
	KeyAdapter writeKeyLis=new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {

			System.out.println(e.getKeyCode());
			if(e.getKeyCode()==33)
			{
				writePaneSlider.setValue(writePaneSlider.getValue()+4);
				chageFontSizeWritePanel.doClick();
			}
			else if(e.getKeyCode()==34)
			{

				writePaneSlider.setValue(writePaneSlider.getValue()-4);
				chageFontSizeWritePanel.doClick();
			}
		}
	};

}
