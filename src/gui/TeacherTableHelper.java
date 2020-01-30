package gui;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import database.Student_DB_Connection;
import database.Teacher_DB_Connection;

public class TeacherTableHelper {
	public void makeTable(JTable table, Object[] colNames){
        Teacher_DB_Connection con = new Teacher_DB_Connection();
        DefaultTableModel model = new DefaultTableModel(colNames, 0) {
        	public boolean isCellEditable(int i, int c) {
        		if (c == 4) 
					return true;
        		else
        			return false;
            }
        };
		Object[] rowData = new Object[5];
		for (int i = 0, c = 1; i < con.getTeacher().size(); i++) {
			rowData[0] = c++;
			rowData[1] = con.getTeacher().get(i).getName();
            rowData[2] = con.getTeacher().get(i).getpNum();
            rowData[3] = con.getTeacher().get(i).getaNum();
            rowData[4] = "+";
            model.addRow(rowData);
		}
		table.setRowHeight(30);
        table.setModel(model);  
    }	// 표 만들기
	public void textCenter(JTable table) {
		DefaultTableCellRenderer tRenderer = new DefaultTableCellRenderer();
        tRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tColModel = table.getColumnModel();
        for (int i = 0; i < tColModel.getColumnCount(); i++) {
        	tColModel.getColumn(i).setCellRenderer(tRenderer);
        }
	}	// 글자 가운데 정렬
	public void setWidth(JTable table, int[] width) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < width.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
		}
	}	// 셀의 너비 정하기
	public void tableClicked(JTable table) {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	if (evt.getClickCount() == 2) {
		        	//System.out.println("clicked");
		    		int row = table.rowAtPoint(evt.getPoint());
		    		String name = (String) table.getValueAt(row, 1);
		    		//System.out.println(name);
		    		Teacher_DB_Connection c = new Teacher_DB_Connection();
		    		TeacherInfo frame = new TeacherInfo();
		    		frame.setVisible(true);
		    		frame.viewData(c.search_Teacher(name));
		    	}
		    }
		});
	}	// 더블클릭된 표의 내용
	public void makeTableforname(JTable table, Object[] colNames, String[] info){
        Teacher_DB_Connection con = new Teacher_DB_Connection();
        DefaultTableModel model = new DefaultTableModel(colNames, 0) {
        	public boolean isCellEditable(int r, int c) {
        		return false;
            }
        };
		Object[] rowData = new Object[4];
		int c = 1;
		
		rowData[0] = c++;
		rowData[1] = info[0];
        rowData[2] = info[1];
        rowData[3] = info[4];
        
        model.addRow(rowData);
        table.setModel(model);   
    }	// 이름으로 표 만들기
}
