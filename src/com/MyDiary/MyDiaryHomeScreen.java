package com.MyDiary;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.plaf.ColorChooserUI;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import javax.swing.BoxLayout;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JToolBar;
import java.awt.TextArea;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.JSlider;
import java.awt.Component;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.views.AbstractView;

import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;

public class MyDiaryHomeScreen extends JFrame {
	
	JTabbedPane tabbedPane;
	eachLineNotesList linePanel[]=new eachLineNotesList[50];
	JScrollPane AllNotes = new JScrollPane();
	

	JPanel notePanel = new JPanel();

	JFrame colorFrame= new JFrame();
	
	DiaryColorScheme colorScheme;

	WritePaneClass writeNotePanel = new WritePaneClass(this);
	JTextArea textArea = new JTextArea();
	JMenuItem mntmRefresh = new JMenuItem("Refresh");
	JPanel notesNamesPanel = new JPanel();
	JLabel dateNtimelabel = new JLabel("SHIVAM DWIVEDI");
	MyDiaryHomeScreen frame=this;
	Date dateSelected=null;
	NoteDataBaseManagement noteDB=new NoteDataBaseManagement();
	JPanel panel = new JPanel();
	JMenu mnDiary = new JMenu("Diary");
	JMenuItem mntmManageYourPassword = new JMenuItem("Change Password");
	JMenuItem mntmPersonlizeYourDiary = new JMenuItem("Personlize Your Diary");
	JMenuBar menuBar = new JMenuBar();
	
	PasswordMangement passwordManagement=new PasswordMangement(this);

	

	static int diaryDecorationStyle=JRootPane.PLAIN_DIALOG;

	JButton mntmLock = new JButton("lock");

	public MyDiaryHomeScreen() {
		colorScheme =new DiaryColorScheme(frame);
		try {
			ColorScheme colorScheme1=DiaryColorScheme.deserialize();
			colorScheme.colorScheme=colorScheme1;
		} catch (ClassNotFoundException | IOException e2) {
			
			e2.printStackTrace();
			colorScheme=new DiaryColorScheme(frame);
		}
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(getToolkit().getScreenSize().width/2-400,getToolkit().getScreenSize().height/2-250,800,500);

		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		tabbedPane=new JTabbedPane();
		
		menuManegement();
		
		
		tabbedPane.setBackground(colorScheme.colorScheme.DBGCOLOR);
		panel.add(tabbedPane);
		
		tabbedPane.addTab("Write Notes", null, writeNotePanel, null);
		
	
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(null);
	
		writeNotePanel.panel_5.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		noteTextpane.addKeyListener(writeNotePanel.writeKeyLis);
		
		
	
		panel_4.add(writeNotePanel.writeNotetextArea);
		panel_4.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{writeNotePanel.writeNotetextArea}));
		
		
		tabbedPane.addTab("All Notes", null, AllNotes, null);
		
		
		AllNotes.setViewportView(notesNamesPanel);
		notesNamesPanel.setLayout(new BoxLayout(notesNamesPanel, BoxLayout.Y_AXIS));
		
		tabbedPane.addTab("Notes", null, notePanel,"notes");
		notePanel.setLayout(new BorderLayout(0, 0));

		notePanel.add(noteTextpane);
		
		JToolBar toolBar_1 = new JToolBar();
		notePanel.add(toolBar_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("delete Note");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noteDB.deletePerticularNotes(dateSelected);
				mntmRefresh.doClick();
				noteTextpane.setText("");
				tabbedPane.setSelectedIndex(1);
			}
		});
		toolBar_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("save as new Note");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noteDB.addNotes(headingText.getText(),noteTextpane.getText());
				mntmRefresh.doClick();
				tabbedPane.setSelectedIndex(1);
			}
		});
		toolBar_1.add(btnNewButton_3);
		
		headingText = new JTextField();
		toolBar_1.add(headingText);
		headingText.setColumns(10);
		
		JSeparator separator = new JSeparator();
		toolBar_1.add(separator);
		

		dateNtimelabel.setHorizontalAlignment(SwingConstants.TRAILING);
		notePanel.add(dateNtimelabel, BorderLayout.NORTH);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{menuBar, mnDiary, mntmRefresh, mntmManageYourPassword, mntmPersonlizeYourDiary, mntmLock, tabbedPane, writeNotePanel, noteTextpane, writeNotePanel.panel_3, writeNotePanel.panel_5, writeNotePanel.toolBar, writeNotePanel.saveBtn, writeNotePanel.clearAllBtn, writeNotePanel.writePaneSlider, writeNotePanel.chageFontSizeWritePanel, panel_4, writeNotePanel.writeNotetextArea, writeNotePanel.headingtext, AllNotes, notesNamesPanel, notePanel, toolBar_1, btnNewButton_2, btnNewButton_3, headingText, separator, dateNtimelabel}));

		//this.setUndecorated(true);

		colorScheme.colorChooser.setColor(this.colorScheme.colorScheme.DBGCOLOR);
		colorScheme.radBGColor.setSelected(true);
		
		mntmRefresh.doClick();
		initilize();
		
		
	}

	void initilize()
	{
		//slider.setBounds(0, 0, 100, 10);
		writeNotePanel.writePaneSlider.setBackground(colorScheme.colorScheme.DBGCOLOR);

		writeNotePanel.btnChangeColor.setBackground(colorScheme.colorScheme.DBGCOLOR);
		writeNotePanel.btnChangeFontColor.setBackground(colorScheme.colorScheme.DBGCOLOR);
		

		writeNotePanel.btnChangeColor.setForeground(colorScheme.colorScheme.TextColor);
		writeNotePanel.btnChangeFontColor.setForeground(colorScheme.colorScheme.TextColor);
		
		tabbedPane.setBackground(colorScheme.colorScheme.DBGCOLOR);
		tabbedPane.setTabPlacement(colorScheme.TabbedPanePlacement);
		
		writeNotePanel.headingtext.setForeground(colorScheme.colorScheme.TextColor);
		writeNotePanel.headingtext.setBackground(colorScheme.colorScheme.DBGCOLOR);
		writeNotePanel.headingtext.setFont(new Font("Adobe Caslon Pro Bold", Font.BOLD, 21));
		
		writeNotePanel.saveBtn.setBackground(colorScheme.colorScheme.DBGCOLOR);
		writeNotePanel.saveBtn.setForeground(colorScheme.colorScheme.TextColor);
		
		textArea.setBackground(colorScheme.colorScheme.DBGCOLOR);
		textArea.setForeground(colorScheme.colorScheme.TextColor);

		writeNotePanel.clearAllBtn.setBackground(colorScheme.colorScheme.DBGCOLOR);
		writeNotePanel.clearAllBtn.setForeground(colorScheme.colorScheme.TextColor);
		
		writeNotePanel.toolBar.setBackground(colorScheme.colorScheme.DBGCOLOR);
		writeNotePanel.toolBar.setForeground(colorScheme.colorScheme.TextColor);
		

		writeNotePanel.writePaneSlider.setValue(colorScheme.colorScheme.writePaneFontSize);
		writeNotePanel.writeNotetextArea.setBackground(colorScheme.colorScheme.writePaneColor);
		writeNotePanel.writeNotetextArea.setForeground(colorScheme.colorScheme.writePaneTextColor);
		writeNotePanel.writeNotetextArea.setFont(new Font("Dialog", Font.PLAIN, colorScheme.colorScheme.writePaneFontSize));
		
		tabbedPane.setBackground(colorScheme.colorScheme.TABBEDPANECOLOR);
		tabbedPane.setForeground(colorScheme.colorScheme.TextColor);

		tabbedPane.setFont(new Font(tabbedPane.getFont().getFontName(), tabbedPane.getFont().getStyle(), colorScheme.colorScheme.DFONTSIZE));
		textArea.setFont(new Font("Dialog", Font.PLAIN, colorScheme.colorScheme.DFONTSIZE));
		writeNotePanel.headingtext.setFont(new Font("Dialog", Font.PLAIN, colorScheme.colorScheme.DFONTSIZE));

		panel.setBackground(colorScheme.colorScheme.DBGCOLOR);
		panel.setForeground(colorScheme.colorScheme.TextColor);
		

		writeNotePanel.toolBar.setBackground(colorScheme.colorScheme.DBGCOLOR);
		writeNotePanel.toolBar.setForeground(colorScheme.colorScheme.TextColor);
		

		writeNotePanel.chageFontSizeWritePanel.setBackground(colorScheme.colorScheme.DBGCOLOR);
		writeNotePanel.chageFontSizeWritePanel.setForeground(colorScheme.colorScheme.TextColor);
		
		
		menuBar.setBackground(colorScheme.colorScheme.DBGCOLOR);
		menuBar.setForeground(colorScheme.colorScheme.TextColor);
		

		noteTextpane.setBackground(colorScheme.colorScheme.writePaneColor);
		noteTextpane.setForeground(colorScheme.colorScheme.writePaneTextColor);
		noteTextpane.setFont(new Font(noteTextpane.getFont().getName(), noteTextpane.getFont().getStyle(), colorScheme.colorScheme.writePaneFontSize));
		
		
		headingText.setFont(new Font("Stencil Std", Font.BOLD | Font.ITALIC, colorScheme.colorScheme.DFONTSIZE));
		headingText.setForeground(colorScheme.colorScheme.TextColor);
		headingText.setBorder(null);

		headingText.setCaretColor(Color.DARK_GRAY);
		headingText.setSelectionColor(Color.GREEN);
		headingText.setSelectedTextColor(Color.YELLOW);
		headingText.setBackground(colorScheme.colorScheme.DBGCOLOR);
		
		noteTextpane.setBackground(colorScheme.colorScheme.DBGCOLOR);
		noteTextpane.setForeground(colorScheme.colorScheme.TextColor);
		

		mnDiary.setBackground(colorScheme.colorScheme.DBGCOLOR);
		mnDiary.setForeground(colorScheme.colorScheme.TextColor);
		
		textArea.setBackground(colorScheme.colorScheme.DBGCOLOR);
		textArea.setForeground(colorScheme.colorScheme.TextColor);
		

		mntmLock.setBackground(colorScheme.colorScheme.DBGCOLOR);
		mntmLock.setForeground(colorScheme.colorScheme.TextColor);
		
		try {
			colorScheme.serialize(colorScheme.colorScheme);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tabbedPane.setTabPlacement(colorScheme.TabbedPanePlacement);

		//getRootPane().setWindowDecorationStyle(diaryDecorationStyle);
		
	
		
			
	}
	MouseListener lineLis=new MouseAdapter() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			try{
			Date date=((eachLineNotesList)(e.getSource())).date;
			dateSelected=date;
			ResultSet rs=noteDB.getPerticularNotes(date);
			try {
				noteTextpane.setText(rs.getString("notes"));
				headingText.setText(rs.getString("head"));
				setTitle(rs.getString("head"));
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			dateNtimelabel.setText(((eachLineNotesList)e.getSource()).dateLabel.getText());

			tabbedPane.addTab("Notes", null, notePanel,"notes");
			tabbedPane.setSelectedComponent(notePanel);
			}
			catch(ClassCastException e7)
			{
				
			}
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			try{
			((eachLineNotesList)(e.getSource())).headLabel.setBackground(Color.gray);;
			((eachLineNotesList)(e.getSource())).dateLabel.setBackground(Color.white);;
			((eachLineNotesList)(e.getSource())).msgLabel.setBackground(colorScheme.colorScheme.DBGCOLOR);;
			}
			catch(Exception e45)
			{

				((JTextPane)e.getSource()).setBackground(colorScheme.colorScheme.DBGCOLOR);;
			}
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			try{
			((eachLineNotesList)(e.getSource())).headLabel.setBackground(Color.red);;
			((eachLineNotesList)(e.getSource())).dateLabel.setBackground(Color.blue);;
			((eachLineNotesList)(e.getSource())).msgLabel.setBackground(Color.blue);;
			}
			catch(Exception e8)
			{
				((JTextPane)e.getSource()).setBackground(Color.blue);;
			}
			
		}
		
	
	};
	

	
	
	void lock() 
	{
		
		frame.setVisible(false);
		new lockScreen(frame);
	}

	void menuManegement()
	{
		
		panel.add(menuBar, BorderLayout.NORTH);
		
		

		menuBar.add(mnDiary);
		
		
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				ResultSet rs=noteDB.getNotes();
				notesNamesPanel.removeAll();
				try {
					while(rs.next())
					{
						Date date=rs.getDate("date");
						System.out.println(rs.getString("notes")+date);
						linePanel[i]=new eachLineNotesList(frame);

						linePanel[i].msgLabel.setBackground(colorScheme.colorScheme.DBGCOLOR);
						linePanel[i].dateLabel.setText(date.toString());
						for(int j=0;j<((rs.getString("notes").length()));j++)
						{
								linePanel[i].msgLabel.setText(linePanel[i].msgLabel.getText()+rs.getString("notes").charAt(j));
						}
						linePanel[i].headLabel.setText(rs.getString("head"));
						
						notesNamesPanel.add(linePanel[i],BorderLayout.LINE_END);
						linePanel[i].setToolTipText(date.toString()+"");
						linePanel[i].addMouseListener(lineLis);
						linePanel[i].msgLabel.addMouseListener(lineLis);
						linePanel[i].msgLabel.setToolTipText("click on heading to get detailed note");
						linePanel[i].date=date;
						i++;
					}
					noteDB.con.close();
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmRefresh.doClick();
		mnDiary.add(mntmRefresh);
		
		mntmManageYourPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.add("change Password", passwordManagement);
				tabbedPane.setSelectedComponent(passwordManagement);
			}
		});
		mnDiary.add(mntmManageYourPassword);
		
		mntmPersonlizeYourDiary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.addTab("Personilze Your Diary",  colorScheme.colorPanel);
				tabbedPane.setSelectedComponent(colorScheme.colorPanel);
			}
		});
		mnDiary.add(mntmPersonlizeYourDiary);
		mntmHideSelectedTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(tabbedPane.getSelectedIndex());
			}
		});
		
		mnDiary.add(mntmHideSelectedTab);
		mntmShowWriteNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				tabbedPane.addTab("All Notes", null, AllNotes, null);
				tabbedPane.addTab("Write Notes", null, writeNotePanel, null);
			}
		});
		
		mnDiary.add(mntmShowWriteNote);
		mntmLock.setBorder(null);
		
		mntmLock.setMnemonic(KeyEvent.VK_SPACE);
		menuBar.add(mntmLock);
		mntmLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lock();
			}
		});

	}
	
	JTextPane noteTextpane = new JTextPane();
	//private JTextField headingTextPane;
	private JTextField headingText;
		private final JMenuItem mntmHideSelectedTab = new JMenuItem("hide selected tab");
	private final JMenuItem mntmShowWriteNote = new JMenuItem("show write Note and Notes Pane");

}
