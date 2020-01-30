package gui;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import database.Student_DB_Connection;

public class StudentTableHelper {
	private String table_name;
	private String name;
	
	public StudentTableHelper(String table_name) {
		setTableName(table_name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setTableName(String tname) {
		this.table_name = tname;
	}
	
	public void makeTable(JTable table, Object[] colNames){
        Student_DB_Connection con = new Student_DB_Connection();
        DefaultTableModel model = new DefaultTableModel(colNames, 0) {
        	public boolean isCellEditable(int r, int c) {
        			return false;
            }
        };	// �����Ұ�
		Object[] rowData = new Object[4];
		for (int i = 0, c = 1; i < con.getStudent(table_name).size(); i++) {
			rowData[0] = c++;
			rowData[1] = con.getStudent(table_name).get(i).getName();
            rowData[2] = con.getStudent(table_name).get(i).getGrade() + "�г�";
            rowData[3] = con.getStudent(table_name).get(i).getpNum();
            model.addRow(rowData);
		}
        table.setModel(model);            
    }	// ǥ �����
	public void makeTableforname(JTable table, Object[] colNames, String[] info){
        Student_DB_Connection con = new Student_DB_Connection();
        DefaultTableModel model = new DefaultTableModel(colNames, 0) {
        	public boolean isCellEditable(int r, int c) {
        		return false;
            }
        };
		Object[] rowData = new Object[4];
		int c = 1;
		
		rowData[0] = c++;
		rowData[1] = info[0];
        rowData[2] = info[1] + "�г�";
        rowData[3] = info[6];
        
        model.addRow(rowData);
        table.setModel(model);   
    }	// �̸����� ǥ �����
	public void textCenter(JTable table) {
		DefaultTableCellRenderer tRenderer = new DefaultTableCellRenderer();
        tRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tColModel = table.getColumnModel();
        for (int i = 0; i < tColModel.getColumnCount(); i++) {
        	tColModel.getColumn(i).setCellRenderer(tRenderer);
        }
	}	// ���� ��� ����
	public void tableDoubleClicked(JTable table) {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	if (evt.getClickCount() == 2) {
		        	//System.out.println("clicked");
		    		Student_DB_Connection c = new Student_DB_Connection();
		    		StudentInfo frame = new StudentInfo(table_name);
		    		frame.setVisible(true);
		    		frame.viewData(c.search_Student(table_name, name));
		    	}
		    }
		});
	}	// ����Ŭ���� ǥ�� ����
	public void tableOneClicked(JTable table) {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	if (evt.getClickCount() == 1) {
		        	//System.out.println("clicked");
		    		int row = table.rowAtPoint(evt.getPoint());
		    		String name = (String) table.getValueAt(row, 1);
		    		//System.out.println("tableOneClicked : " + name);
		    		setName(name);
		    	}
		    }
		});
	} // �ѹ�Ŭ���� ���� �̸��� �����ϴ� tableOneClicked
	public void setWidth(JTable table, int[] width) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < width.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
		}
	}	// ���� �ʺ� ���ϱ�
}
