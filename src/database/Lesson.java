package database;

public class Lesson {
	private String lname;	// 강좌이름
	private String tname;	// 강사이름
	private String memo;	// 메모
	
	public Lesson(String lname, String tname, String memo) {
		this.lname = lname;
		this.tname = tname;
		this.memo = memo;
	}
	
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}