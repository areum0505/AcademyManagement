package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Lesson_DB_Connection {
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //����̹�
	private final String DB_URL = "jdbc:mysql://localhost:3306/lesson_db?&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"; //������ DB ����
	private final String USER_NAME = "root"; //DB�� ������ ����� �̸��� ����� ����
	private final String PASSWORD = "kimareum123-"; //
	
	private Connection con = null;
	private java.sql.Statement state = null;
	
	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return con;
		
	}
	public ArrayList<Lesson>getLesson() {
		ArrayList<Lesson> lesson = new ArrayList<Lesson>();
		Lesson_DB_Connection c = new Lesson_DB_Connection();    
		con = c .getConnection();
        java.sql.Statement st;       
        ResultSet rs;
        Lesson l;
        
        try {
        	 st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM lesson");
             while(rs.next()){ 
                 l = new Lesson(
                         rs.getString("lname"),
                         rs.getString("tname"),
                         rs.getString("memo")
                 );                 
                 lesson.add(l);
             }
        con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return lesson;
	}
	public String[] search_Lesson(String name) {	// ����ã�� - ����â�� ����
		con = getConnection();
		java.sql.Statement st;
		ResultSet rs;
		String[] info = new String[3];
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM lesson WHERE lname = '" + name + "';");
			while(rs.next()) {
				info[0] = rs.getString("lname");
				info[1] = rs.getString("tname");
				info[2] = rs.getString("memo");
			}
		} catch (Exception e) {
			System.out.println("search_Lesson : " + e.getMessage());
		}
		return info;
	}
	public boolean InsertLesson(Lesson l) {	// ���� �Է� - �߰�
		boolean ok = false;
        PreparedStatement ps = null; //���
        
        try {
        	//System.out.println("InsertStudent : " + table_name);
        	con = getConnection();
        	String sql = "INSERT INTO lesson (lname, tname, memo)" + 
        				"VALUES(?,?,?)";
        	ps = con.prepareStatement(sql);
        	ps.setString(1, l.getLname());
        	ps.setString(2, l.getTname());
        	ps.setString(3, l.getMemo());
        	int r = ps.executeUpdate(); // ���� -> ����
        	con.close();
        	if (r > 0) {
        		System.out.println("���� ���� - ����");
        		ok = true;
        	}
        	else 
        		System.out.println("���� ���� - ����");
        } catch (Exception e) {
			System.out.println("InsertLesson : " + e.getMessage());
		}
        return ok;
	} // end of InsertLesson
	public void CreateLessonTable(Lesson l) {	// student_db�� ���̺� ����
		String sql =
				"create table " + l.getLname() + "(" + 
				"name char(10)," +
				"grade int not null, " +
				"school char(12), " +
				"rDate char(10), " +
				"pName char(10), " +
				"pNum char(11), " +
				"ppNum char(11), " +
				"address char(40));";
		try {
			con = getConnection();
			PreparedStatement change = con.prepareStatement("use student_db;");
			change.executeUpdate();
			PreparedStatement create = con.prepareStatement(sql);
			create.executeUpdate();
			PreparedStatement rechange = con.prepareStatement("use lesson_db;");
			rechange.executeUpdate();
		} catch (Exception e) {
			System.out.println("CreateLessonTable : " + e);
		}
	} // end of CreateLessonTable
	
	public void deleteLesson(String name){	// ���� ����
        PreparedStatement ps = null;
        try {
            con = getConnection();
            String sql = "DELETE FROM lesson WHERE lname = '" + name + "';";
            ps = con.prepareStatement(sql);
            int r = ps.executeUpdate(); // ���� -> ����
           
            if (r>0) {
            	System.out.println("���»��� - ����");
            } else 
            	System.out.println("���»��� - ����");
           
        } catch (Exception e) {
            System.out.println("deleteLesson : " + e.getMessage());
        }          
    }
	public void DeleteLessonTable(String name) {	// student_db�� ���̺� ����
		String sql = "DROP TABLE " + name + ";";
		try {
			con = getConnection();
			PreparedStatement change = con.prepareStatement("use student_db;");
			change.executeUpdate();
			PreparedStatement delete = con.prepareStatement(sql);
			delete.executeUpdate();
			PreparedStatement replace = con.prepareStatement("use lesson_db;");
			replace.executeUpdate();
		} catch (Exception e) {
			System.out.println("DeleteLessonTable : " + e);
		}
	} // end of CreateLessonTable
}
