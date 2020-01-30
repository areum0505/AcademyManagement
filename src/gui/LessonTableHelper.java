package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import database.Lesson_DB_Connection;

public class LessonTableHelper {
	public void makeTable(JTable table, Object[] colNames){
        Lesson_DB_Connection con = new Lesson_DB_Connection();
        DefaultTableModel model = new DefaultTableModel(colNames, 0) {
        	public boolean isCellEditable(int i, int c) {
        		if (c == 3) 
					return true;
        		else
        			return false;
            }
        };	// 3열이 아니라면 수정이 불가능
		Object[] rowData = new Object[4];
		for (int i = 0, c = 1; i < con.getLesson().size(); i++) {
			rowData[0] = c++;
			rowData[1] = con.getLesson().get(i).getLname();
            rowData[2] = con.getLesson().get(i).getTname();
            rowData[3] = "+";
            model.addRow(rowData);
		}
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
	
	public void btnInTable(JTable table, int num) {
		table.getColumnModel().getColumn(num).setCellRenderer(new ButtonRenderer());;
		table.getColumnModel().getColumn(num).setCellEditor(new ButtonEditer(new JTextField(),table));
	}	// 표 안에 버튼 집어넣기
	
	public void setWidth(JTable table, int[] width) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < width.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
		}
	}	// 셀의 너비 정하기
}

//button renderer class
class ButtonRenderer extends JButton implements TableCellRenderer {
	public ButtonRenderer() {
		setOpaque(true);	// set button properties
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setText((value == null) ? "" : value.toString());	// set passed object as button next
		return this;
	}
}
// button editor class
class ButtonEditer extends DefaultCellEditor {
	protected JButton btn;
	private String lbl;
	private Boolean clicked;
	
	private JTable table;
	
	public ButtonEditer(JTextField txt, JTable table) {
		super(txt);
		btn = new JButton();
		btn.setOpaque(true);
		// 버튼이 클릭되었을 때
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
		this.table = table;
	}
	// 오버라이드
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		lbl = (value == null) ? " ":value.toString();
		btn.setText(lbl);
		clicked = true;
		return btn;
	}
	// 버튼의 값이 변경되는 경우 클릭했을 때
	@Override
	public Object getCellEditorValue() {
		if (clicked) {
			//System.out.println("clicked");
			int row = table.getSelectedRow();
			//System.out.println(table.getValueAt(row, 1));
			String lname = (String) table.getValueAt(row, 1);
			ViewClass les = new ViewClass(lname);
			les.setVisible(true);
		}
		// false로 설정
		clicked = false;
		return new String(lbl);
	}
	@Override
	public boolean stopCellEditing() {
		// false로 설정
		clicked = false;
		return super.stopCellEditing();
	}
	@Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
