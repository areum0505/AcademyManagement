package gui;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import database.Student;
import database.Student_DB_Connection;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentInfo extends JFrame {

	private JPanel contentPane;
	private JTextField name_txt;
	private JTextField grade_txt;
	private JTextField school_txt;
	private JTextField pNum_txt;
	private JTextField pName_txt;
	private JTextField ppNum_txt;
	private JTextArea address_txt;
	private JTextField rDate_txt;

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
//					StudentInfo frame = new StudentInfo(table_name);
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
	public StudentInfo(String tname) {
		setTableName(tname);
		//System.out.println("StudentInfo : " + table_name);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 482, 553);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("����");
		title.setBounds(175, 5, 120, 35);
		title.setFont(new Font("HY�߰��", Font.PLAIN, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title);
		
		JLabel Name = new JLabel("�̸�");
		Name.setBounds(50, 50, 50, 30);
		Name.setHorizontalAlignment(SwingConstants.CENTER);
		Name.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		panel.add(Name);
		
		JLabel Grade = new JLabel("�г�");
		Grade.setBounds(50, 105, 50, 30);
		Grade.setHorizontalAlignment(SwingConstants.CENTER);
		Grade.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		panel.add(Grade);
		
		JLabel School = new JLabel("�б�");
		School.setBounds(50, 160, 50, 30);
		School.setHorizontalAlignment(SwingConstants.CENTER);
		School.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		panel.add(School);
		
		JLabel Adress = new JLabel("�ּ�");
		Adress.setHorizontalAlignment(SwingConstants.CENTER);
		Adress.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		Adress.setBounds(50, 430, 50, 30);
		panel.add(Adress);
		
		JLabel PName = new JLabel("��ȣ�� ����");
		PName.setHorizontalAlignment(SwingConstants.CENTER);
		PName.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		PName.setBounds(25, 324, 100, 30);
		panel.add(PName);
		
		JLabel PPNum = new JLabel("��ȣ�� ����ó");
		PPNum.setHorizontalAlignment(SwingConstants.CENTER);
		PPNum.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		PPNum.setBounds(18, 379, 120, 30);
		panel.add(PPNum);
		
		JLabel PNum = new JLabel("����ó");
		PNum.setHorizontalAlignment(SwingConstants.CENTER);
		PNum.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		PNum.setBounds(45, 215, 60, 30);
		panel.add(PNum);
		
		JLabel rDate = new JLabel("������");
		rDate.setHorizontalAlignment(SwingConstants.CENTER);
		rDate.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		rDate.setBounds(45, 272, 60, 30);
		panel.add(rDate);
		
		name_txt = new JTextField();
		name_txt.setFont(new Font("����", Font.PLAIN, 30));
		name_txt.setBounds(180, 45, 285, 40);
		name_txt.setColumns(10);
		name_txt.setDocument(new JTextFieldLimit(10));
		panel.add(name_txt);
		
		grade_txt = new JTextField();
		grade_txt.setFont(new Font("����", Font.PLAIN, 30));
		grade_txt.setColumns(10);
		grade_txt.setBounds(180, 100, 285, 40);
		grade_txt.setDocument(new JTextFieldLimit(1));
		panel.add(grade_txt);
		
		school_txt = new JTextField();
		school_txt.setFont(new Font("����", Font.PLAIN, 30));
		school_txt.setColumns(10);
		school_txt.setBounds(180, 155, 285, 40);
		school_txt.setDocument(new JTextFieldLimit(12));
		panel.add(school_txt);
		
		pNum_txt = new JTextField();
		pNum_txt.setFont(new Font("����", Font.PLAIN, 30));
		pNum_txt.setColumns(10);
		pNum_txt.setBounds(180, 210, 285, 40);
		pNum_txt.setDocument(new JTextFieldLimit(11));
		panel.add(pNum_txt);
		
		rDate_txt = new JTextField();
		rDate_txt.setFont(new Font("����", Font.PLAIN, 30));
		rDate_txt.setColumns(10);
		rDate_txt.setBounds(180, 265, 285, 40);
		panel.add(rDate_txt);
		
		pName_txt = new JTextField();
		pName_txt.setFont(new Font("����", Font.PLAIN, 30));
		pName_txt.setColumns(10);
		pName_txt.setBounds(180, 320, 285, 40);
		pName_txt.setDocument(new JTextFieldLimit(10));
		panel.add(pName_txt);
		
		ppNum_txt = new JTextField();
		ppNum_txt.setFont(new Font("����", Font.PLAIN, 30));
		ppNum_txt.setColumns(10);
		ppNum_txt.setBounds(180, 375, 285, 40);
		ppNum_txt.setDocument(new JTextFieldLimit(11));
		panel.add(ppNum_txt);
		
		address_txt = new JTextArea();
		address_txt.setFont(new Font("����", Font.PLAIN, 30));
		address_txt.setBounds(180, 430, 285, 100);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		address_txt.setBorder(border);
		address_txt.setDocument(new JTextFieldLimit(40));
		address_txt.setLineWrap(true);
		panel.add(address_txt);
		
		JButton change_Btn = new JButton("����");
		change_Btn.setForeground(Color.WHITE);
		change_Btn.setBackground(new Color(0, 129, 86));
		change_Btn.setFont(new Font("����", Font.BOLD, 20));
		change_Btn.setBounds(30, 465, 100, 35);
		change_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		        Calendar c1 = Calendar.getInstance();
		        String strToday = sdf.format(c1.getTime());	// ���� ��¥
		        
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
				boolean ok = c.updateStudent(s, table_name, name_txt.getText());
				if(ok) {   
		            System.out.println("�����Ϸ�");
		            dispose();
				}
		        else
		        	System.out.println("��������");
		        
			}
		});
		panel.add(change_Btn);
		
		JButton delete_Btn = new JButton("����");
		delete_Btn.setForeground(Color.WHITE);
		delete_Btn.setBackground(new Color(0, 129, 86));
		delete_Btn.setFont(new Font("����", Font.BOLD, 20));
		delete_Btn.setBounds(30, 505, 100, 35);
		delete_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameTxt = name_txt.getText();
				Student_DB_Connection c = new Student_DB_Connection();
				//System.out.println(nameTxt);
				boolean ok = c.deleteStudent(table_name, nameTxt);
				if(ok) {
					System.out.println("�����Ϸ�");
					dispose();
				}
				else 
					System.out.println("��������");
				
			}
		});
		panel.add(delete_Btn);
	}
	/**
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
	void viewData(String[] info){	
        // �Է�â�� ����
        name_txt.setText(info[0]);
        grade_txt.setText(info[1]);
        school_txt.setText(info[2]);  
        rDate_txt.setText(info[3]);
        pName_txt.setText(info[4]);
        pNum_txt.setText(info[5]);
        ppNum_txt.setText(info[6]);
        address_txt.setText(info[7]);
        //������ �ȵ�
        name_txt.setEditable(false);
        rDate_txt.setEditable(false);
    }//viewData
}
