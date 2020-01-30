package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.Lesson;
import database.Lesson_DB_Connection;
import database.Student_DB_Connection;
import database.Teacher_DB_Connection;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;

public class Start extends JFrame {
	private JPanel contentPane;
	private JPanel lesson_Data;
	private JTable lessonTable;
	private JTextField lname_txt;
	private JTextField tname_txt;
	private JTextArea memo_txt;
	private JTable teacherTable;
	private JTextField searchTeacher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
					frame.setTitle("�̸��п��������α׷�");
					//frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);	 // â ��ġ
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		kit.getImage("picture/mirim.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage("picture/mirim.png"));
		// ������
		
		JPanel startPage = new JPanel();
		startPage.setBackground(Color.WHITE);
		startPage.setBounds(0, 0, 680, 453);
		contentPane.add(startPage);
		startPage.setVisible(true);
		startPage.setLayout(null);
		// startPage
		
		JPanel teacherList = new JPanel();
		teacherList.setBackground(Color.WHITE);
		teacherList.setBounds(0, 0, 680, 453);
		contentPane.add(teacherList);
		teacherList.setVisible(false);
		teacherList.setLayout(null);	
		// studentList
		
		JPanel studentList = new JPanel();
		studentList.setBackground(Color.WHITE);
		studentList.setBounds(0, 0, 680, 453);
		contentPane.add(studentList);
		studentList.setVisible(false);
		studentList.setLayout(null);
		// teacherList
		
		/*
		 * Start startPage
		 */
		JLabel title1 = new JLabel("�̸��п�");
		title1.setBounds(50, 90, 300, 50);
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title1.setFont(new Font("����", Font.BOLD, 40));
		startPage.add(title1);	// ���� (�̸��п�)
		
		JLabel title2 = new JLabel("�������α׷�");
		title2.setBounds(50, 140, 300, 50);
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setFont(new Font("����", Font.BOLD, 40));
		startPage.add(title2);	// ���� (�������α׷�)
		
		JButton studentBtn = new JButton("�л�");
		studentBtn.setBounds(45, 285, 166, 100);
		studentBtn.setForeground(Color.WHITE);
		studentBtn.setBackground(new Color(0, 129, 86));
		studentBtn.setFont(new Font("�����ٸ����", Font.PLAIN, 40));
		startPage.add(studentBtn);
		studentBtn.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent e) {
				  studentList.setVisible(true);
				  startPage.setVisible(false);
			  }
		});	// �л���ư
		
		JButton teacherBtn = new JButton("����");
		teacherBtn.setBounds(256, 285, 166, 100);
		teacherBtn.setForeground(Color.WHITE);
		teacherBtn.setBackground(new Color(0, 129, 86));
		teacherBtn.setFont(new Font("�����ٸ����", Font.PLAIN, 40));
		startPage.add(teacherBtn);
		teacherBtn.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent e) {
				  startPage.setVisible(false);
				  teacherList.setVisible(true);
			  }
		});	// �����ư
		
		JButton exitBtn = new JButton("����");
		exitBtn.setBounds(470, 285, 166, 100);
		exitBtn.setForeground(Color.WHITE);
		exitBtn.setBackground(new Color(0, 129, 86));
		exitBtn.setFont(new Font("�����ٸ����", Font.PLAIN, 40));
		startPage.add(exitBtn);
		exitBtn.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent e) {
				  System.exit(0);
			  }
		});	// ������ ��ư
		
		JLabel icon = new JLabel("");
		icon.setBounds(425, 50, 200, 180);
		icon.setIcon(new ImageIcon("picture/mirim.png"));
		startPage.add(icon);	// ����
		/* 
		 * End startPage
		*/
		
		/*
		 * Start studentList
		 */
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		Object[] colNames = new String[] {" ","���¸�", "��簭��", ""};
		LessonTableHelper th = new LessonTableHelper();
		int[] width = new int[] {40,300,197,80};

		JButton update_Lesson = new JButton();
		update_Lesson.setBounds(59, 5, 50, 50);
		studentList.add(update_Lesson);
		update_Lesson.setIcon(new ImageIcon("picture/���ΰ�ħ.png"));
		update_Lesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel)teacherTable.getModel();
				model.setNumRows(0);
				LessonTableHelper tth = new LessonTableHelper();
				tth.makeTable(lessonTable, colNames);
				tth.textCenter(lessonTable);
				tth.setWidth(lessonTable, width);
				tth.btnInTable(lessonTable, 3);
			}
		});	// ���ΰ�ħ ��ư
		update_Lesson.setBackground(new Color(0, 129, 86));
		
		JLabel lessonTitle = new JLabel("����");
		lessonTitle.setBounds(190, 10, 300, 45);
		lessonTitle.setBackground(Color.LIGHT_GRAY);
		lessonTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lessonTitle.setFont(new Font("�����ٸ����", Font.BOLD, 30));
		studentList.add(lessonTitle);
		
		JButton homeButton1 = new JButton("");
		homeButton1.setBounds(5, 5, 50, 50);
		homeButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startPage.setVisible(true);
				studentList.setVisible(false);
			}
		});	
		homeButton1.setIcon(new ImageIcon("picture/����ȭ��ǥ_3.png"));
		homeButton1.setBackground(new Color(0, 129, 86));
		studentList.add(homeButton1);
		
		JButton addLessonBtn = new JButton("�����߰�");
		addLessonBtn.setForeground(Color.WHITE);
		addLessonBtn.setFont(new Font("�����ٸ����", Font.BOLD, 25));
		addLessonBtn.setBackground(new Color(0, 129, 86));
		studentList.add(addLessonBtn);
		addLessonBtn.setBounds(545, 5, 125, 50);
		addLessonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddLesson frame = new AddLesson();
				frame.setVisible(true);
			}
		});
		studentList.setLayout(null);
		
		lesson_Data = new JPanel();
		lesson_Data.setBounds(30, 305, 640, 140);
		studentList.add(lesson_Data);
		lesson_Data.setLayout(null);
		lesson_Data.setBackground(Color.WHITE);
		lesson_Data.setVisible(false);
		// ���¸��� 3�� Ŭ���ϸ� ���¿� ���� ������ ���� (�����̸�, �����̸�, �޸�)
		
		JLabel lname = new JLabel("�̸�");
		lname.setBounds(0, 15, 60, 35);
		lesson_Data.add(lname);
		lname.setHorizontalAlignment(SwingConstants.CENTER);
		lname.setFont(new Font("����", Font.PLAIN, 25));
		
		JLabel tname = new JLabel("����");
		tname.setBounds(0, 80, 60, 35);
		lesson_Data.add(tname);
		tname.setHorizontalAlignment(SwingConstants.CENTER);
		tname.setFont(new Font("����", Font.PLAIN, 25));
		
		JLabel memo = new JLabel("�޸�");
		memo.setBounds(250, 15, 60, 35);
		lesson_Data.add(memo);
		memo.setHorizontalAlignment(SwingConstants.CENTER);
		memo.setFont(new Font("����", Font.PLAIN, 25));
		
		lname_txt = new JTextField();
		lname_txt.setBounds(70, 15, 175, 40);
		lesson_Data.add(lname_txt);
		lname_txt.setFont(new Font("����", Font.PLAIN, 30));
		lname_txt.setColumns(10);
		
		tname_txt = new JTextField();
		tname_txt.setBounds(70, 80, 175, 40);
		lesson_Data.add(tname_txt);
		tname_txt.setFont(new Font("����", Font.PLAIN, 30));
		tname_txt.setColumns(10);
		
		memo_txt = new JTextArea();
		memo_txt.setBounds(320, 15, 230, 105);
		lesson_Data.add(memo_txt);
		memo_txt.setForeground(Color.BLACK);
		memo_txt.setLineWrap(true);
		memo_txt.setWrapStyleWord(true);
		memo_txt.setFont(new Font("����", Font.PLAIN, 30));
		memo_txt.setBorder(border);
		
		JButton save_Lesson = new JButton("����");
		save_Lesson.setFont(new Font("����", Font.BOLD, 20));
		save_Lesson.setBounds(560, 18, 77, 40);
		save_Lesson.setBackground(new Color(0, 129, 86));
		save_Lesson.setForeground(Color.WHITE);
		save_Lesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Lesson_DB_Connection c = new Lesson_DB_Connection();
				// �����ϱ� (�̸��� �Ұ�
			}
		});
		lesson_Data.add(save_Lesson);
		
		JButton delete_Lesson = new JButton("����");
		delete_Lesson.setFont(new Font("����", Font.BOLD, 20));
		delete_Lesson.setBounds(560, 79, 77, 40);
		delete_Lesson.setBackground(new Color(0, 129, 86));
		delete_Lesson.setForeground(Color.WHITE);
		delete_Lesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Lesson_DB_Connection c = new Lesson_DB_Connection();
				c.deleteLesson(lname_txt.getText());
				c.DeleteLessonTable(lname_txt.getText());
			}
		});
		lesson_Data.add(delete_Lesson);
		
		JScrollPane lessonScroll = new JScrollPane();
		lessonScroll.setBounds(30, 70, 620, 230);
		studentList.add(lessonScroll);
		
		lessonTable = new JTable();
		lessonTable.setFont(new Font("����", Font.PLAIN, 20));
		lessonTable.setRowHeight(30);	// �� ����
		lessonScroll.setViewportView(lessonTable);
		th.makeTable(lessonTable, colNames);
		th.textCenter(lessonTable);	// ���� ��� ����
		th.btnInTable(lessonTable, 3);
		th.setWidth(lessonTable, width);
		tableClicked(lessonTable);
		/*
		 * End studentList
		 */
		
		/*
		 * Start teacherList
		 */	
		Object[] tcolNames = new Object[] {" ", "�̸�", "��ȭ��ȣ", "���¹�ȣ"};
		int[] twidth = new int[] {40, 177, 200, 200};
		TeacherTableHelper tth = new TeacherTableHelper();
		JButton addTeacherBtn = new JButton("�����߰�");
		addTeacherBtn.setForeground(Color.WHITE);
		addTeacherBtn.setFont(new Font("�����ٸ����", Font.BOLD, 25));
		addTeacherBtn.setBackground(new Color(0, 129, 86));
		teacherList.add(addTeacherBtn);
		addTeacherBtn.setBounds(545, 5, 125, 50);
		addTeacherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddTeacher frame = new AddTeacher();
				frame.setVisible(true);
			}
		});
		
		JLabel teacherTitle = new JLabel("������");
		teacherTitle.setBounds(190, 10, 300, 45);
		teacherList.add(teacherTitle);
		teacherTitle.setHorizontalAlignment(SwingConstants.CENTER);
		teacherTitle.setFont(new Font("����", Font.BOLD, 30));
		teacherTitle.setBackground(Color.LIGHT_GRAY);
		
		JButton homeButton2 = new JButton("");
		homeButton2.setBounds(5, 5, 50, 50);
		homeButton2.setBackground(new Color(0, 129, 86));
		homeButton2.setIcon(new ImageIcon("picture/����ȭ��ǥ_3.png"));
		homeButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPage.setVisible(true);
				teacherList.setVisible(false);
			}
		});
		teacherList.add(homeButton2);
		
		JScrollPane teacherScroll = new JScrollPane();
		teacherScroll.setBounds(30, 70, 620, 298);
		teacherList.add(teacherScroll);
		
		teacherTable = new JTable();
		teacherTable.setFont(new Font("����", Font.PLAIN, 20));
		teacherScroll.setViewportView(teacherTable);
		tth.makeTable(teacherTable, tcolNames);		// ǥ ����
		tth.textCenter(teacherTable);				// �������
		tth.setWidth(teacherTable, twidth);			// �ʺ� ����
		tth.tableClicked(teacherTable);				// ǥ�� Ŭ���Ǹ� ����â
		
		JButton update_Btn = new JButton();
		update_Btn.setBackground(new Color(0, 129, 86));
		update_Btn.setBounds(60, 5, 50, 50);
		update_Btn.setIcon(new ImageIcon("picture/���ΰ�ħ.png"));
		update_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel)teacherTable.getModel();
				model.setNumRows(0);
				TeacherTableHelper tth = new TeacherTableHelper();
				tth.makeTable(teacherTable, tcolNames);
				tth.textCenter(teacherTable);
				tth.setWidth(teacherTable, twidth);
				tth.tableClicked(teacherTable);
			}
		});	// ���ΰ�ħ ��ư
		teacherList.add(update_Btn);
		
		searchTeacher = new JTextField();
		searchTeacher.setFont(new Font("����", Font.PLAIN, 30));
		searchTeacher.setBounds(100, 380, 400, 50);
		teacherList.add(searchTeacher);
		searchTeacher.setColumns(10);
		JButton searchTeacherBtn = new JButton("");
		searchTeacherBtn.setIcon(new ImageIcon("C:\\Users\\User\\1109\\AcademyManagement2\\picture\\\uB3CB\uBCF4\uAE30.png"));
		searchTeacherBtn.setBounds(500, 380, 50, 50);
		searchTeacherBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Teacher_DB_Connection c = new Teacher_DB_Connection();
        		String name = searchTeacher.getText();
                String[] info = c.search_Teacher(name);
				DefaultTableModel model = (DefaultTableModel)teacherTable.getModel();
				model.setNumRows(0);
				TeacherTableHelper tth = new TeacherTableHelper();
				tth.makeTableforname(teacherTable, tcolNames, info);
				tth.setWidth(teacherTable, twidth);
				tth.textCenter(teacherTable);
        	}
        });
		teacherList.add(searchTeacherBtn);
		
		Action action = new AbstractAction() {
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		searchTeacherBtn.doClick();
        	}
        };	// ���͸� �������� �˻���ư Ŭ��
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        searchTeacher.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "ENTER");
        searchTeacher.getActionMap().put("ENTER", action);
		/*
		 * End teacherList
		 */
	}
	
	void viewData(String[] info){	
//		for (int i = 0; i < info.length; i++) {
//			System.out.println(info[i]);
//		}		
        //ȭ�鿡 ����
        lname_txt.setText(info[0]);
        tname_txt.setText(info[1]);
        memo_txt.setText(info[2]);  
        //���� �ȵǰ�
        lname_txt.setEditable(false);
        lname_txt.setBackground(Color.WHITE);
        tname_txt.setEditable(false);
        tname_txt.setBackground(Color.WHITE);
        memo_txt.setEditable(false);
    }//viewData
	
	public void tableClicked(JTable table) {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	if (evt.getClickCount() == 1) {
		        	//System.out.println("clicked");
		    		int row = table.rowAtPoint(evt.getPoint());
		    		String name = (String) table.getValueAt(row, 1);
		    		//System.out.println(name);
		    		Lesson_DB_Connection c = new Lesson_DB_Connection();
		    		lesson_Data.setVisible(true);
		    		viewData(c.search_Lesson(name));
		    	}
		    }
		});
	}	// 3��Ŭ���� ǥ�� ����
}
