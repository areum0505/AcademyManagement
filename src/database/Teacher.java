package database;

public class Teacher {
	private String name;	// 이름
	private String birth;	// 생년월일
	private String address;	// 주소
	private String pNum;	// 연락처
	private String aNum;	// 계좌번호
	private String lesson;	// 담당강좌
	
	public Teacher(String name, String birth, String address, String pNum, String aNum, String lesson) {
		super();
		this.name = name;
		this.birth = birth;
		this.address = address;
		this.pNum = pNum;
		this.aNum = aNum;
		this.lesson = lesson;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getpNum() {
		return pNum;
	}
	public void setpNum(String pNum) {
		this.pNum = pNum;
	}
	public String getaNum() {
		return aNum;
	}
	public void setaNum(String aNum) {
		this.aNum = aNum;
	}
	public String getLesson() {
		return lesson;
	}
	public void setLesson(String lesson) {
		this.lesson = lesson;
	}
	
	
}
