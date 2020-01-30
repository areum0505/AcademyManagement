package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Student_DB_Connection {
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
	private final String DB_URL = "jdbc:mysql://localhost/student_db?&useSSL=false&serverTimezone=UTC"; //접속할 DB 서버
	private final String USER_NAME = "root"; //DB에 접속할 사용자 이름을 상수로 정의
	private final String PASSWORD = "kimareum123-"; //
	
	Connection con = null;
	
	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return con;	
	}
	public ArrayList<Student>getStudent(String table_name) {
		ArrayList<Student> student = new ArrayList<Student>();   
		con = getConnection();
        java.sql.Statement st;       
        ResultSet rs;
        Student s;
        try {
        	 //System.out.println("getStudent : " + table_name);
        	 st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM " + table_name);
             while(rs.next()){ 
                 s = new Student(
                         rs.getString("name"),
                         rs.getString("grade"),
                         rs.getString("school"),
                         rs.getString("rDate"),
                         rs.getString("pName"),
                         rs.getString("pNum"),
                         rs.getString("ppNum"),
                         rs.getString("address")
                 );                 
                 student.add(s);
             }
        con.close();
		} catch (Exception e) {
			System.out.println("getStudent : " + e.getMessage());
		}
        return student;
	}
	public String[] search_Student(String table_name, String name) {	// 학생찾기 - 학생정보창을 위해
		con = getConnection();
		Statement st;
		ResultSet rs;
		String[] info = new String[8];
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM " + table_name + " WHERE name = '" + name + "';");
			while(rs.next()) {
				info[0] = rs.getString("name");
				info[1] = rs.getString("grade");
				info[2] = rs.getString("school");
				info[3] = rs.getString("rDate");
				info[4] = rs.getString("pName");
				info[5] = rs.getString("pNum");
				info[6] = rs.getString("ppNum");
				info[7] = rs.getString("address");
			}
		} catch (Exception e) {
			System.out.println("search_Student : " + e.getMessage());
		}
		return info;
	}
	public boolean InsertStudent(String table_name, Student s) {	// 학생 입력 - 추가
		boolean ok = false;
        PreparedStatement ps = null; //명령
        
        try {
        	//System.out.println("InsertStudent : " + table_name);
        	con = getConnection();
        	String sql = "INSERT INTO " + table_name + 
        				" (name, grade, school, rDate, pName, pNum, ppNum, address)" + 
        				"VALUES(?,?,?,?,?,?,?,?)";
        	ps = con.prepareStatement(sql);
        	ps.setString(1, s.getName());
        	ps.setString(2, s.getGrade());
        	ps.setString(3, s.getSchool());
        	ps.setString(4, s.getrDate());
        	ps.setString(5, s.getpName());
        	ps.setString(6, s.getpNum());
        	ps.setString(7, s.getPpNum());
        	ps.setString(8, s.getAddress());
        	int r = ps.executeUpdate(); // 실행 -> 저장
        	con.close();
        	if (r > 0) {
        		System.out.println("생성 - 성공");
        		ok = true;
        	}
        	else 
        		System.out.println("생성 - 실패");
        } catch (Exception e) {
			System.out.println("InsertStudent : " + e.getMessage());
		}
        return ok;
	} // end of InsertStudent
	public boolean updateStudent(Student s, String table_name, String name) {	// 학생 수정
		boolean ok = false;
        PreparedStatement ps = null;
        
        try {
			//System.out.println("updateStudent : " + table_name);
			con = getConnection();
			String sql = "UPDATE " + table_name + " SET name='" + s.getName() + 
					"', grade='" + s.getGrade() +
					"', school='" + s.getSchool() + 
					"', rDate='" + s.getrDate() + 
					"', pName='" + s.getpName() + 
					"', pNum='" + s.getpNum() +
					"', ppNum='" + s.getPpNum() + 
					"',address='" + s.getAddress() + 
					"' WHERE name = '" + name + "';";
			ps = con.prepareStatement(sql);
        	int r = ps.executeUpdate(); //실행 -> 수정
        	if(r>0) {
        		System.out.println("수정 - 성공");
        		ok = true;
        	}
        	else {
        		System.out.println("수정 - 실패");
        	}
        	
		} catch (Exception e) {
			System.out.println("updateStudent : " + e.getMessage());
		}
        return ok;
	}
	public boolean deleteStudent(String table_name, String name){	// 학생 삭제
        boolean ok =false ;
        PreparedStatement ps =null;
       
        try {
        	//System.out.println("deleteStudent : " + table_name);
            con = getConnection();
            String sql = "DELETE FROM " + table_name + " WHERE name = '" + name + "';";
            ps = con.prepareStatement(sql);
            int r = ps.executeUpdate(); // 실행 -> 삭제
           
            if (r>0) {
            	System.out.println("삭제 - 성공");
            	ok = true;
            } else 
            	System.out.println("삭제 - 실패");
           
        } catch (Exception e) {
            System.out.println("deleteStudent : " + e.getMessage());
        }      
        return ok;
    }
}
