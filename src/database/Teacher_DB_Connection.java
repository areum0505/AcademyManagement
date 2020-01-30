package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Teacher_DB_Connection {
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //����̹�
	private final String DB_URL = "jdbc:mysql://localhost/teacher_db?&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC"; //������ DB ����
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
	public ArrayList<Teacher>getTeacher() {
		ArrayList<Teacher> teacher = new ArrayList<Teacher>();
		Teacher_DB_Connection c = new Teacher_DB_Connection();    
		con = c .getConnection();
        java.sql.Statement st;       
        ResultSet rs;
        Teacher t;
        
        try {
        	 st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM teacher");
             while(rs.next()){ 
                 t = new Teacher(
                         rs.getString("name"),
                         rs.getString("birth"),
                         rs.getString("address"),
                         rs.getString("pNum"),
                         rs.getString("aNum"),
                         rs.getString("lesson")
                 );                 
                 teacher.add(t);
             }
        con.close();
		} catch (Exception e) {
			System.out.println("getTeacher : " + e.getMessage());
		}
        return teacher;
	}
	public String[] search_Teacher(String name) {	// ����ã�� - ��������â�� ����
		con = getConnection();
		java.sql.Statement st;
		ResultSet rs;
		String[] info = new String[6];
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM teacher WHERE name = '" + name + "';");
			while(rs.next()) {
				info[0] = rs.getString("name");
				info[1] = rs.getString("birth");
				info[2] = rs.getString("address");
				info[3] = rs.getString("pNum");
				info[4] = rs.getString("aNum");
				info[5] = rs.getString("lesson");
			}
		} catch (Exception e) {
			System.out.println("search_Teacher : " + e.getMessage());
		}
		return info;
	}
	public boolean InsertTeacher(Teacher t) {	// ���� �߰�
		boolean ok = false;
        PreparedStatement ps = null; //���
        try {
        	//System.out.println("InsertStudent : " + table_name);
        	con = getConnection();
        	String sql = "INSERT INTO teacher (name, birth, address, pNum, aNum, lesson)" + 
        				 "VALUES(?,?,?,?,?,?)";
        	ps = con.prepareStatement(sql);
        	ps.setString(1, t.getName());
        	ps.setString(2, t.getBirth());
        	ps.setString(3, t.getAddress());
        	ps.setString(4, t.getpNum());
        	ps.setString(5, t.getaNum());
        	ps.setString(6, t.getLesson());
        	int r = ps.executeUpdate(); // ���� -> ����
        	con.close();
        	if (r > 0) 
        		ok = true;
        } catch (Exception e) {
			System.out.println("InsertTeacher : " + e.getMessage());
		}
        return ok;
	}
	public boolean updateTeacher(Teacher t, String name) {	// �������
		boolean ok = false;
        PreparedStatement ps = null;
        
        try {
			//System.out.println("updateStudent : " + table_name);
			con = getConnection();
			String sql = "UPDATE teacher SET name='" + t.getName() + 
					"', birth='" + t.getBirth() +
					"', address='" + t.getAddress() + 
					"', pNum='" + t.getpNum() + 
					"', aNum='" + t.getaNum() + 
					"', lesson='" + t.getLesson() + "' WHERE name = '" + name + "';";
			ps = con.prepareStatement(sql);
        	int r = ps.executeUpdate(); //���� -> ����
        	if(r>0) {
        		System.out.println("���� - ����");
        		ok = true;
        	}
        	else {
        		System.out.println("���� - ����");
        	}
        	
		} catch (Exception e) {
			System.out.println("updateTeacher : " + e.getMessage());
		}
        return ok;
	}
	public boolean deleteTeacher(String name){	// �������
        boolean ok = false ;
        PreparedStatement ps = null;
       
        try {
            con = getConnection();
            String sql = "DELETE FROM teacher WHERE name = '" + name + "';";
            ps = con.prepareStatement(sql);
            int r = ps.executeUpdate(); // ���� -> ����
           
            if (r>0) {
            	System.out.println("���� - ����");
            	ok = true;
            } else 
            	System.out.println("���� - ����");
           
        } catch (Exception e) {
            System.out.println("deleteTeacher : " + e.getMessage());
        }      
        return ok;
    }
}
