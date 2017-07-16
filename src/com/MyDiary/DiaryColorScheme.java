package com.MyDiary;

import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;


import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;


class ColorScheme implements Serializable
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7434200778562903903L;
	int DFONTSIZE=16;
	 Color DBGCOLOR=Color.black;
	 Color TextColor=Color.white;
	 Color TABBEDPANECOLOR=Color.blue;
	 int writePaneFontSize=16;
	 Color writePaneColor=Color.black;
	 Color writePaneTextColor=Color.white;
	 int TABBEDPANEDIRECTION='B';
}
	

public class DiaryColorScheme extends JFrame {
	int TabbedPanePlacement=JTabbedPane.TOP;
	MyDiaryHomeScreen mDHS;
	ColorScheme colorScheme=new ColorScheme();
	JColorChooser colorChooser=new JColorChooser();

	JPanel colorPanel = new JPanel();


	public DiaryColorScheme(MyDiaryHomeScreen frame) {
	
		mDHS=frame;
		ButtonGroup btngrp=new ButtonGroup();
		slider.setMaximum(40);
		this.setBounds(0, 0, 500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(colorPanel, BorderLayout.CENTER);
		colorPanel.setBackground(colorScheme.DBGCOLOR);
		colorPanel.setForeground(colorScheme.TextColor);
				colorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				colorPanel.add(tabbedPanePosiotionComboBox);
				
				colorPanel.add(slider);
				btnChangeFontSize.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						colorScheme.DFONTSIZE=slider.getValue();
						frame.initilize();

						System.out.println(slider.getValue());
					}
				});
				
				colorPanel.add(label);
				
				colorPanel.add(label_1);
				
				colorPanel.add(btnChangeFontSize);
				
				colorPanel.add(radText);
				
				colorPanel.add(rdbtnTabbedpanecolor);
				
				colorPanel.add(label_2);
				
				colorPanel.add(radBGColor);
				lblClickOnThis.setForeground(Color.YELLOW);
				
				colorPanel.add(lblClickOnThis);
				
				colorPanel.add(label_3);
				
				colorPanel.add(label_4);
		
				colorPanel.add(colorChooser);
				colorChooser.setVisible(true);
		colorChooser.getColor();
		colorChooser.setBounds(colorPanel.getBounds());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		colorPanel.addMouseListener(mouseAda);
		this.addMouseListener(mouseAda);
		colorChooser.addMouseListener(mouseAda);
		slider.setValue(colorScheme.DFONTSIZE);
		tabbedPanePosiotionComboBox.addItem("TOP");

		tabbedPanePosiotionComboBox.addItem("BOTTOM");

		tabbedPanePosiotionComboBox.addItem("LEFT");

		tabbedPanePosiotionComboBox.addItem("RIGHT");
		
		System.out.println(
				tabbedPanePosiotionComboBox.getItemCount());
		
		btngrp.add(radBGColor);
		btngrp.add(radText);
		btngrp.add(rdbtnTabbedpanecolor);
		
		colorPanel.add(label_5);
		
		colorPanel.add(label_6);
		
		colorPanel.add(label_7);
		
		colorPanel.add(label_8);
		
		colorPanel.add(label_9);
		switch(colorScheme.TABBEDPANEDIRECTION)
		{
		case 'T':
			TabbedPanePlacement=JTabbedPane.TOP;
			break;

		case 'B':
			TabbedPanePlacement=JTabbedPane.BOTTOM;
			tabbedPanePosiotionComboBox.setSelectedItem("BOTTOM");
			break;

		case 'L':

			tabbedPanePosiotionComboBox.setSelectedItem("LEFT");

			TabbedPanePlacement=JTabbedPane.LEFT;
			break;

		case 'R':

			tabbedPanePosiotionComboBox.setSelectedItem("RIGHT");

			TabbedPanePlacement=JTabbedPane.RIGHT;
			break;
		default:
			System.out.println("tabbed pane position not set");
		}
		
		
	}
	
	/*public static void main(String ... args)
	{
		DiaryColorScheme colorScheme;
		new DiaryColorScheme(new MyDiaryHomeScreen()).setVisible(true);
	}*/
	MouseAdapter mouseAda=new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(radBGColor.isSelected())
			colorScheme.DBGCOLOR=colorChooser.getColor();
			else if(radText.isSelected())

				colorScheme.TextColor=colorChooser.getColor();
			else if(rdbtnTabbedpanecolor.isSelected())
				colorScheme.TABBEDPANECOLOR=colorChooser.getColor();
			colorPanel.setBackground(colorScheme.DBGCOLOR);
			colorChooser.setBackground(colorScheme.DBGCOLOR);
			
			System.out.println(tabbedPanePosiotionComboBox.getSelectedItem().toString());
			colorScheme.TABBEDPANEDIRECTION=tabbedPanePosiotionComboBox.getSelectedItem().toString().charAt(0);
			switch(colorScheme.TABBEDPANEDIRECTION)
			{
			case 'T':
				TabbedPanePlacement=JTabbedPane.TOP;

				tabbedPanePosiotionComboBox.setSelectedItem("TOP");
				break;

			case 'B':
				TabbedPanePlacement=JTabbedPane.BOTTOM;
				tabbedPanePosiotionComboBox.setSelectedItem("BOTTOM");
				break;

			case 'L':

				tabbedPanePosiotionComboBox.setSelectedItem("LEFT");

				TabbedPanePlacement=JTabbedPane.LEFT;
				break;

			case 'R':

				tabbedPanePosiotionComboBox.setSelectedItem("RIGHT");

				TabbedPanePlacement=JTabbedPane.RIGHT;
				break;
			}
			
			colorPanel.repaint();
			
			mDHS.initilize();
			mDHS.repaint();
			
			
		}
	};
	
	void serialize(ColorScheme e) throws FileNotFoundException, IOException
	{
		FileOutputStream outputFile=new FileOutputStream("colorscheme.cor");
		ObjectOutputStream oOut=new ObjectOutputStream(outputFile);
		oOut.writeObject(e);
		oOut.close();
		outputFile.close();
		
		System.out.println("serialized ");
	}
	
	static ColorScheme deserialize() throws FileNotFoundException,IOException,ClassNotFoundException
	{
		FileInputStream in=new FileInputStream("colorscheme.cor");
		ObjectInputStream Oin=new ObjectInputStream(in);
		ColorScheme e1=(ColorScheme) Oin.readObject();
		in.close();
		Oin.close();
		return e1;
	}
	
	JComboBox tabbedPanePosiotionComboBox = new JComboBox();
	private final JSlider slider = new JSlider();
	private final JButton btnChangeFontSize = new JButton("change Font Size");
	final JRadioButton radBGColor = new JRadioButton("BackGround Color");
	private final JRadioButton radText = new JRadioButton("Text Color");
	private final JLabel lblClickOnThis = new JLabel("click On this Area to Apply Changes");
	private final JRadioButton rdbtnTabbedpanecolor = new JRadioButton("TabbedPaneColor");
	private final JLabel label = new JLabel("");
	private final JLabel label_1 = new JLabel("");
	private final JLabel label_2 = new JLabel("");
	private final JLabel label_3 = new JLabel("");
	private final JLabel label_4 = new JLabel("");
	private final JLabel label_5 = new JLabel("");
	private final JLabel label_6 = new JLabel("");
	private final JLabel label_7 = new JLabel("");
	private final JLabel label_8 = new JLabel("");
	private final JLabel label_9 = new JLabel("");
	
	
}

