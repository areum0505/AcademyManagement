package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.Teacher;
import database.Teacher_DB_Connection;

import javax.swing.JTextField;

public class TeacherInfo extends JFrame {

	private JPanel contentPane;
	private JTextField name_txt;
	private JTextField birth_txt;
	private JTextField lesson_txt;
	private JTextField pNum_txt;
	private JTextField aNum_txt;
	private JTextField address_txt;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TeacherInfo frame = new TeacherInfo();
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
	public TeacherInfo() {
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 482, 553);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("����");
		title.setBounds(211, 11, 60, 35);
		title.setFont(new Font("HY�߰��", Font.PLAIN, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title);
		
		JLabel name = new JLabel("�̸�");
		name.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBounds(14, 48, 100, 35);
		panel.add(name);
		
		JLabel birth = new JLabel("�������");
		birth.setHorizontalAlignment(SwingConstants.CENTER);
		birth.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		birth.setBounds(31, 132, 100, 35);
		panel.add(birth);
		
		JLabel address = new JLabel("�ּ�");
		address.setHorizontalAlignment(SwingConstants.CENTER);
		address.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		address.setBounds(27, 383, 75, 35);
		panel.add(address);
		
		JLabel pNum = new JLabel("����ó");
		pNum.setHorizontalAlignment(SwingConstants.CENTER);
		pNum.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		pNum.setBounds(31, 216, 83, 35);
		panel.add(pNum);
		
		JLabel aNum = new JLabel("���¹�ȣ");
		aNum.setHorizontalAlignment(SwingConstants.CENTER);
		aNum.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		aNum.setBounds(31, 300, 100, 35);
		panel.add(aNum);
		
		JLabel lesson = new JLabel("��� ����");
		lesson.setHorizontalAlignment(SwingConstants.CENTER);
		lesson.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		lesson.setBounds(260, 48, 100, 35);
		panel.add(lesson);
		
		name_txt = new JTextField();
		name_txt.setFont(new Font("�����ٸ����", Font.PLAIN, 19));
		name_txt.setBounds(41, 80, 205, 40);
		panel.add(name_txt);
		name_txt.setColumns(10);
		
		birth_txt = new JTextField();
		birth_txt.setFont(new Font("�����ٸ����", Font.PLAIN, 19));
		birth_txt.setColumns(10);
		birth_txt.setBounds(41, 164, 205, 40);
		panel.add(birth_txt);
		
		lesson_txt = new JTextField();
		lesson_txt.setFont(new Font("�����ٸ����", Font.PLAIN, 19));
		lesson_txt.setColumns(10);
		lesson_txt.setBounds(270, 80, 170, 40);
		panel.add(lesson_txt);
		
		pNum_txt = new JTextField();
		pNum_txt.setFont(new Font("�����ٸ����", Font.PLAIN, 19));
		pNum_txt.setColumns(10);
		pNum_txt.setBounds(41, 247, 300, 40);
		panel.add(pNum_txt);
		
		aNum_txt = new JTextField();
		aNum_txt.setFont(new Font("�����ٸ����", Font.PLAIN, 19));
		aNum_txt.setColumns(10);
		aNum_txt.setBounds(41, 331, 300, 40);
		panel.add(aNum_txt);
		
		address_txt = new JTextField();
		address_txt.setFont(new Font("�����ٸ����", Font.PLAIN, 19));
		address_txt.setColumns(10);
		address_txt.setBounds(41, 414, 399, 40);
		panel.add(address_txt);
		
		JButton change_Btn = new JButton("����");
		change_Btn.setForeground(Color.WHITE);
		change_Btn.setBackground(new Color(0, 129, 86));
		change_Btn.setFont(new Font("����", Font.BOLD, 20));
		change_Btn.setBounds(30, 465, 100, 35);
		change_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		        
				String nameTxt = name_txt.getText();
				String birthTxt = birth_txt.getText();
				String addressTxt = address_txt.getText();
				String pNumTxt = pNum_txt.getText();
				String aNumTxt = aNum_txt.getText();
				String lessonTxt = lesson_txt.getText();
				
				Teacher t = new Teacher(nameTxt, birthTxt, addressTxt, pNumTxt, aNumTxt, lessonTxt);
				Teacher_DB_Connection c = new Teacher_DB_Connection();
				boolean ok = c.updateTeacher(t, name_txt.getText());
				if(ok) {   
		            System.out.println("�����Ϸ�");
		            dispose();
				}
		        else
		        	System.out.println("��������");
		        
			}
		});
		panel.add(change_Btn);
		
		JButton delete_Btn = new JButton("\uC0AD\uC81C");
		delete_Btn.setForeground(Color.WHITE);
		delete_Btn.setBackground(new Color(0, 129, 86));
		delete_Btn.setFont(new Font("����", Font.BOLD, 20));
		delete_Btn.setBounds(30, 505, 100, 35);
		delete_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameTxt = name_txt.getText();
				Teacher_DB_Connection c = new Teacher_DB_Connection();
				//System.out.println(nameTxt);
				boolean ok = c.deleteTeacher(nameTxt);
				if(ok) {
					System.out.println("�����Ϸ�");
					dispose();
				}
				else 
					System.out.println("��������");
				
			}
		});
		panel.add(delete_Btn);
	}	// ���� ��ư
	void viewData(String[] info){	
        //ȭ�鿡 ����
        name_txt.setText(info[0]);
        birth_txt.setText(info[1]);
        address_txt.setText(info[2]);  
        pNum_txt.setText(info[3]);
        aNum_txt.setText(info[4]);
        lesson_txt.setText(info[5]);
        //�̸� ���� �Ұ�
        name_txt.setEditable(false);
    }//viewData
}
