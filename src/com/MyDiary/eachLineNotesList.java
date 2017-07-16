package com.MyDiary;

import java.awt.LayoutManager;
import java.sql.Date;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class eachLineNotesList extends JPanel {
	JTextPane msgLabel = new JTextPane();
	JLabel dateLabel = new JLabel("Date");
	Date date;
	
	private final JPanel panel_1 = new JPanel();
	 JLabel headLabel = new JLabel("Heading");
	public eachLineNotesList(MyDiaryHomeScreen mdhs) {
		setBackground(mdhs.colorScheme.colorScheme.DBGCOLOR);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		

		msgLabel.setEditable(false);
		msgLabel.setForeground(mdhs.colorScheme.colorScheme.TextColor);
		Dimension d=new Dimension();
		d.setSize(100,20);
		msgLabel.setMaximumSize(d);
		msgLabel.setOpaque(true);
		msgLabel.setBackground(mdhs.colorScheme.colorScheme.DBGCOLOR);
		msgLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, mdhs.colorScheme.colorScheme.DFONTSIZE));
		msgLabel.setPreferredSize(new Dimension(100, 50));
		
	
		//msgLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(msgLabel);
		dateLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(dateLabel,BorderLayout.EAST);
		
		
		dateLabel.setOpaque(true);
		dateLabel.setBackground(Color.WHITE);
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		headLabel.setBackground(Color.GRAY);
		headLabel.setOpaque(true);
		headLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
		
		panel_1.add(headLabel);

	}


	
}
