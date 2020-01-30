package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import database.Lesson;
import database.Lesson_DB_Connection;
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
import javax.swing.JTextArea;

public class AddLesson extends JFrame {

	private JPanel contentPane;
	private JTextField name_txt;
	private JTextField teacher_txt;
	private JTextArea memo_txt;

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
	public AddLesson() {
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
		
		JLabel title = new JLabel("°­ÁÂÃß°¡");
		title.setBounds(216, 10, 150, 50);
		title.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		addClass.add(title);
		
		JLabel Name = new JLabel("ÀÌ¸§");
		Name.setBounds(14, 53, 100, 50);
		Name.setHorizontalAlignment(SwingConstants.CENTER);
		Name.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		addClass.add(Name);
		
		JLabel TeacherName = new JLabel("´ã´ç°­»ç");
		TeacherName.setBounds(23, 152, 100, 50);
		TeacherName.setHorizontalAlignment(SwingConstants.CENTER);
		TeacherName.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		addClass.add(TeacherName);
		
		JLabel Memo = new JLabel("¸Þ¸ð");
		Memo.setHorizontalAlignment(SwingConstants.CENTER);
		Memo.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		Memo.setBounds(14, 238, 100, 50);
		addClass.add(Memo);
		
		name_txt = new JTextField();
		name_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		name_txt.setBounds(38, 94, 200, 50);
		addClass.add(name_txt);
		name_txt.setColumns(10);
		name_txt.setDocument(new JTextFieldLimit(10));
		
		teacher_txt = new JTextField();
		teacher_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		teacher_txt.setColumns(10);
		teacher_txt.setBounds(38, 191, 200, 50);
		teacher_txt.setDocument(new JTextFieldLimit(10));
		addClass.add(teacher_txt);
		
		memo_txt = new JTextArea();
		memo_txt.setFont(new Font("µ¸¿ò", Font.PLAIN, 30));
		memo_txt.setForeground(Color.BLACK);
		memo_txt.setBounds(38, 289, 310, 135);
		memo_txt.setDocument(new JTextFieldLimit(36));
		memo_txt.setLineWrap(true);
		addClass.add(memo_txt);
		
		JButton createLessonBtn = new JButton("»ý¼º");
		createLessonBtn.setForeground(Color.WHITE);
		createLessonBtn.setLocation(465, 385);
		createLessonBtn.setSize(100, 50);
		createLessonBtn.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 25));
		createLessonBtn.setBackground(new Color(0, 129, 86));
		createLessonBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) { 
				String nameTxt = name_txt.getText();
				String teacherTxt = teacher_txt.getText();
				String memoTxt = memo_txt.getText();
				Lesson l = new Lesson(nameTxt, teacherTxt, memoTxt);
				Lesson_DB_Connection c = new Lesson_DB_Connection();
				boolean ok = c.InsertLesson(l);
				c.CreateLessonTable(l);
				if(ok) {
		            System.out.println("°­ÁÂ»ý¼º¿Ï·á");
		            dispose();
				}
		        else
		        	System.out.println("°­ÁÂ»ý¼º½ÇÆÐ");
		        
			}
		});
		addClass.add(createLessonBtn);
		// °­ÁÂ »ý¼º ¹öÆ°
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

