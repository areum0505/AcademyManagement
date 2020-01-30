package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.Student_DB_Connection;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JCheckBox;

public class ViewClass extends JFrame {
	private JPanel contentPane;
	private JTable class_table;
	private JTextField searchName;

	/**
	 * Create the frame.
	 */
	public ViewClass(String lesson_name) {
		setBounds(135, 130, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel class_List = new JPanel();
		class_List.setBounds(0, 0, 680, 453);
		contentPane.add(class_List);
		class_List.setBackground(Color.WHITE);
		class_List.setLayout(null);	
		
		JLabel classTitle = new JLabel(lesson_name);
		classTitle.setBounds(190, 10, 300, 45);
		classTitle.setBackground(Color.LIGHT_GRAY);
		classTitle.setHorizontalAlignment(SwingConstants.CENTER);
		classTitle.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		class_List.add(classTitle);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(30, 70, 620, 285);
		class_List.add(scroll);

		class_table = new JTable();
		class_table.setFont(new Font("돋움", Font.PLAIN, 20));
		class_table.setRowHeight(30);
		scroll.setViewportView(class_table);
		
		Object[] colNames = new String[] {" ","이름", "학년", "전화번호"};
		StudentTableHelper th = new StudentTableHelper(lesson_name);
		th.makeTable(class_table, colNames);		// 테이블 만들고
		th.textCenter(class_table);				// 글자 가운데정렬
		int[] width = new int[] {40,177,150,250};
		th.setWidth(class_table, width);			// 너비 설정
		th.tableDoubleClicked(class_table);		// 더블클릭하면 정보창이 나타남
		th.tableOneClicked(class_table);			// 한번클릭하면 행의 이름을 얻을 수 있음 - 출석체크를 위해서
		
		JButton addStudentBtn = new JButton("학생추가");
		addStudentBtn.setBounds(545, 5, 125, 50);
		addStudentBtn.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		addStudentBtn.setBackground(new Color(0, 129, 86));
		addStudentBtn.setForeground(Color.WHITE);	
		addStudentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddStudent frame = new AddStudent(lesson_name);
				frame.setVisible(true);
			}
		});	
		class_List.add(addStudentBtn);	
		// 학생생성버튼
		
		JButton update_Btn = new JButton();
        update_Btn.setBackground(new Color(0, 129, 86));
        update_Btn.setBounds(8, 5, 50, 50);
        update_Btn.setIcon(new ImageIcon("picture/새로고침.png"));
        update_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel)class_table.getModel();
				model.setNumRows(0);
				StudentTableHelper th = new StudentTableHelper(lesson_name);
				th.makeTable(class_table, colNames);
				th.textCenter(class_table);
				th.setWidth(class_table, width);
			}
		}); 
        class_List.add(update_Btn);
        // 학생 수정 버튼
        
        JButton searchBtn = new JButton("");
        searchBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Student_DB_Connection c = new Student_DB_Connection();
        		String name = searchName.getText();
                String[] info = c.search_Student(lesson_name, name);
//                for (int i = 0; i < info.length; i++) {
//					System.out.println("info : " + info[i]);
//				}
                DefaultTableModel model = (DefaultTableModel)class_table.getModel();
				model.setNumRows(0);
				StudentTableHelper sth = new StudentTableHelper(lesson_name);
				sth.makeTableforname(class_table, colNames, info);
				sth.setWidth(class_table, width);
				sth.textCenter(class_table);
        	}
        });
        searchBtn.setIcon(new ImageIcon("picture/돋보기.png"));
        searchBtn.setBounds(520, 390, 50, 50);
        class_List.add(searchBtn);
        // 학생 검색
        
        searchName = new JTextField();
        searchName.setFont(new Font("돋움", Font.PLAIN, 30));
        searchName.setBounds(120, 390, 400, 50);
        class_List.add(searchName);
        searchName.setColumns(10);
        
        Action action = new AbstractAction() {
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		searchBtn.doClick();
        	}
        };	// 엔터를 눌렀을때 검색버튼 클릭
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        searchName.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "ENTER");
        searchName.getActionMap().put("ENTER", action);
	}
}
