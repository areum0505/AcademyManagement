package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import database.Student;
import database.Student_DB_Connection;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddStudent extends JFrame {

	private JPanel contentPane;
	private JTextField name_txt;
	private JTextField school_txt;
	private JTextField grade_txt;
	private JTextField address_txt;
	private JTextField pName_txt;
	private JTextField ppNum_txt;
	private JTextField pNum_txt;
	private JTextField day_txt;
	
	private static String table_name;
	
	public void setTableName(String tname) {
		this.table_name = tname;
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddStudent frame = new AddStudent(table_name);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	/**
	 * Create the frame.
	 */
	public AddStudent(String tname) {
		setTableName(tname);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel addClass = new JPanel();
		addClass.setBackground(SystemColor.inactiveCaptionBorder);
		addClass.setBounds(0, 0, 582, 453);
		contentPane.add(addClass);
		addClass.setLayout(null);
		
		JLabel title = new JLabel("ÇÐ»ýÃß°¡");
		title.setBounds(216, 10, 150, 50);
		title.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		addClass.add(title);
		
		JLabel Name = new JLabel("ÀÌ¸§");
		Name.setBounds(14, 53, 100, 50);
		Name.setHorizontalAlignment(SwingConstants.CENTER);
		Name.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		addClass.add(Name);
		
		JLabel Grade = new JLabel("ÇÐ³â");
		Grade.setBounds(14, 152, 100, 50);
		Grade.setHorizontalAlignment(SwingConstants.CENTER);
		Grade.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		addClass.add(Grade);
		
		JLabel School = new JLabel("ÇÐ±³");
		School.setBounds(226, 152, 100, 50);
		School.setHorizontalAlignment(SwingConstants.CENTER);
		School.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		addClass.add(School);
		
		JLabel Adress = new JLabel("ÁÖ¼Ò");
		Adress.setHorizontalAlignment(SwingConstants.CENTER);
		Adress.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		Adress.setBounds(14, 334, 100, 50);
		addClass.add(Adress);
		
		JLabel PName = new JLabel("º¸È£ÀÚ ¼ºÇÔ");
		PName.setHorizontalAlignment(SwingConstants.CENTER);
		PName.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		PName.setBounds(38, 238, 100, 50);
		addClass.add(PName);
		
		JLabel PPNum = new JLabel("º¸È£ÀÚ ¿¬¶ôÃ³");
		PPNum.setHorizontalAlignment(SwingConstants.CENTER);
		PPNum.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		PPNum.setBounds(216, 242, 150, 50);
		addClass.add(PPNum);
		
		JLabel PNum = new JLabel("ÀüÈ­¹øÈ£");
		PNum.setHorizontalAlignment(SwingConstants.CENTER);
		PNum.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		PNum.setBounds(226, 53, 100, 50);
		addClass.add(PNum);
		
		name_txt = new JTextField();
		name_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		name_txt.setBounds(38, 94, 158, 50);
		addClass.add(name_txt);
		name_txt.setColumns(10);
		name_txt.setDocument(new JTextFieldLimit(10));
		
		school_txt = new JTextField();
		school_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		school_txt.setColumns(10);
		school_txt.setBounds(226, 191, 298, 50);
		school_txt.setDocument(new JTextFieldLimit(12));
		addClass.add(school_txt);
		
		grade_txt = new JTextField();
		grade_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		grade_txt.setColumns(10);
		grade_txt.setBounds(38, 191, 158, 50);
		grade_txt.setDocument(new JTextFieldLimit(1));
		addClass.add(grade_txt);
		
		address_txt = new JTextField();
		address_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		address_txt.setColumns(10);
		address_txt.setBounds(38, 373, 413, 50);
		address_txt.setDocument(new JTextFieldLimit(30));
		addClass.add(address_txt);
		
		pName_txt = new JTextField();
		pName_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		pName_txt.setColumns(10);
		pName_txt.setBounds(38, 281, 158, 50);
		pName_txt.setDocument(new JTextFieldLimit(10));
		addClass.add(pName_txt);
		
		ppNum_txt = new JTextField();
		ppNum_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		ppNum_txt.setColumns(10);
		ppNum_txt.setBounds(226, 281, 298, 50);
		ppNum_txt.setDocument(new JTextFieldLimit(11));
		addClass.add(ppNum_txt);
		
		pNum_txt = new JTextField();
		pNum_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		pNum_txt.setColumns(10);
		pNum_txt.setBounds(226, 94, 298, 50);
		pNum_txt.setDocument(new JTextFieldLimit(11));
		addClass.add(pNum_txt);
		
		JButton createStudentBtn = new JButton("\uC0DD\uC131");
		createStudentBtn.setForeground(Color.WHITE);
		createStudentBtn.setLocation(465, 385);
		createStudentBtn.setSize(100, 50);
		createStudentBtn.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 25));
		createStudentBtn.setBackground(new Color(0, 129, 86));
		createStudentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		        Calendar c1 = Calendar.getInstance();
		        String strToday = sdf.format(c1.getTime());
		        
				String nameTxt = name_txt.getText();
				String gradeTxt = grade_txt.getText();
				String schoolTxt = school_txt.getText();
				String rDateTxt = strToday;
				String pNameTxt = pName_txt.getText();
				String pNumTxt = pNum_txt.getText();
				String ppNumTxt = ppNum_txt.getText();
				String adressTxt = address_txt.getText();
				Student s = new Student(nameTxt, gradeTxt, schoolTxt, rDateTxt, pNameTxt, pNumTxt, ppNumTxt, adressTxt);
				Student_DB_Connection c = new Student_DB_Connection();
				boolean ok = c.InsertStudent(table_name, s);
				if(ok) {
		            System.out.println("»ý¼º¿Ï·á");
		            dispose();
				}
		        else
		        	System.out.println("»ý¼º½ÇÆÐ");
		        
			}
		});
		addClass.add(createStudentBtn);
		// ÇÐ»ý »ý¼º ¹öÆ°
	}
	/*
	 * Limit TextField
	 */
	class JTextFieldLimit extends PlainDocument {
		private int limit;
		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;
			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}
}

