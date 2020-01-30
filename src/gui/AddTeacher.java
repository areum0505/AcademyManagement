package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import database.Teacher;
import database.Teacher_DB_Connection;

public class AddTeacher extends JFrame {

	private JPanel contentPane;
	private JTextField teacherName;
	private JTextField teacherPNum;
	private JTextField teacherBirth;
	private JTextField teacherAccountNum;
	private JTextField teacherAddress;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddTeacher frame = new AddTeacher();
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
	public AddTeacher() {
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
		
		JLabel title = new JLabel("강사추가");
		title.setBounds(216, 10, 150, 50);
		title.setFont(new Font("HY견고딕", Font.PLAIN, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		addClass.add(title);
		
		JLabel name = new JLabel("이름");
		name.setBounds(30, 70, 100, 50);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		addClass.add(name);
		
		JLabel phoneNumber = new JLabel("전화번호");
		phoneNumber.setBounds(32, 140, 100, 50);
		phoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
		phoneNumber.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		addClass.add(phoneNumber);
		
		JLabel birthday = new JLabel("생년월일");
		birthday.setBounds(35, 210, 100, 50);
		birthday.setHorizontalAlignment(SwingConstants.CENTER);
		birthday.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		addClass.add(birthday);
		
		JLabel accountNumber = new JLabel("계좌번호");
		accountNumber.setBounds(35, 350, 100, 50);
		accountNumber.setHorizontalAlignment(SwingConstants.CENTER);
		accountNumber.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		addClass.add(accountNumber);
		
		JLabel address = new JLabel("주소");
		address.setHorizontalAlignment(SwingConstants.CENTER);
		address.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		address.setBounds(30, 280, 100, 50);
		addClass.add(address);
		
		teacherName = new JTextField();
		teacherName.setFont(new Font("돋움", Font.PLAIN, 30));
		teacherName.setBounds(160, 70, 250, 50);
		addClass.add(teacherName);
		teacherName.setColumns(10);
		teacherName.setDocument(new JTextFieldLimit(9));
		
		teacherPNum = new JTextField();
		teacherPNum.setFont(new Font("돋움", Font.PLAIN, 30));
		teacherPNum.setColumns(10);
		teacherPNum.setBounds(160, 140, 250, 50);
		addClass.add(teacherPNum);
		teacherPNum.setDocument(new JTextFieldLimit(11));
		
		teacherBirth = new JTextField();
		teacherBirth.setFont(new Font("돋움", Font.PLAIN, 30));
		teacherBirth.setColumns(10);
		teacherBirth.setBounds(160, 210, 250, 50);
		addClass.add(teacherBirth);
		teacherBirth.setDocument(new JTextFieldLimit(8));
		
		teacherAccountNum = new JTextField();
		teacherAccountNum.setFont(new Font("돋움", Font.PLAIN, 30));
		teacherAccountNum.setColumns(10);
		teacherAccountNum.setBounds(160, 350, 300, 50);
		addClass.add(teacherAccountNum);
		teacherAccountNum.setDocument(new JTextFieldLimit(14));
		
		teacherAddress = new JTextField();
		teacherAddress.setFont(new Font("돋움", Font.PLAIN, 30));
		teacherAddress.setColumns(10);
		teacherAddress.setBounds(160, 280, 370, 50);
		addClass.add(teacherAddress);
		teacherAddress.setDocument(new JTextFieldLimit(40));

		
		JButton createTeacherBtn = new JButton("\uC0DD\uC131");
		createTeacherBtn.setBounds(465, 385, 100, 50);
		createTeacherBtn.setForeground(Color.WHITE);
		createTeacherBtn.setFont(new Font("HY견고딕", Font.PLAIN, 25));
		createTeacherBtn.setBackground(new Color(0, 129, 86));
		createTeacherBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = teacherName.getText();
				String pNum = teacherPNum.getText();
				String birth = teacherBirth.getText();
				String aNum = teacherAccountNum.getText();
				String address = teacherAddress.getText();
				String lesson = "A반";
		
				Teacher t = new Teacher(name, birth, address, pNum, aNum, lesson);
				
				Teacher_DB_Connection c = new Teacher_DB_Connection();
				boolean ok = c.InsertTeacher(t);
				if(ok) {
					System.out.println("생성완료");
					dispose();
				} else
					System.out.println("생성실패");
		            
			}
		});
		addClass.add(createTeacherBtn);
		// 강사 생성 버튼
	}}
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
