package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Lesson_DB_Connection {
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
	private final String DB_URL = "jdbc:mysql://localhost:3306/lesson_db?&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"; //접속할 DB 서버
	private final String USER_NAME = "root"; //DB에 접속할 사용자 이름을 상수로 정의
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
	public String[] search_Lesson(String name) {	// 강좌찾기 - 정보창을 위해
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
	public boolean InsertLesson(Lesson l) {	// 강좌 입력 - 추가
		boolean ok = false;
        PreparedStatement ps = null; //명령
        
        try {
        	//System.out.println("InsertStudent : " + table_name);
        	con = getConnection();
        	String sql = "INSERT INTO lesson (lname, tname, memo)" + 
        				"VALUES(?,?,?)";
        	ps = con.prepareStatement(sql);
        	ps.setString(1, l.getLname());
        	ps.setString(2, l.getTname());
        	ps.setString(3, l.getMemo());
        	int r = ps.executeUpdate(); // 실행 -> 저장
        	con.close();
        	if (r > 0) {
        		System.out.println("강좌 생성 - 성공");
        		ok = true;
        	}
        	else 
        		System.out.println("강좌 생성 - 실패");
        } catch (Exception e) {
			System.out.println("InsertLesson : " + e.getMessage());
		}
        return ok;
	} // end of InsertLesson
	public void CreateLessonTable(Lesson l) {	// student_db에 테이블 생성
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
	
	public void deleteLesson(String name){	// 강좌 삭제
        PreparedStatement ps = null;
        try {
            con = getConnection();
            String sql = "DELETE FROM lesson WHERE lname = '" + name + "';";
            ps = con.prepareStatement(sql);
            int r = ps.executeUpdate(); // 실행 -> 삭제
           
            if (r>0) {
            	System.out.println("강좌삭제 - 성공");
            } else 
            	System.out.println("강좌삭제 - 실패");
           
        } catch (Exception e) {
            System.out.println("deleteLesson : " + e.getMessage());
        }          
    }
	public void DeleteLessonTable(String name) {	// student_db에 테이블 삭제
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
