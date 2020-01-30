package database;

public class Student {
	private String name;	// 이름
	private String grade;	// 학년
	private String school;	// 학교
	private String rDate;	// 가입일
	private String pName;	// 보호자이름
	private String pNum;	// 연락처
	private String ppNum;	// 보호자 연락처
	private String address;	// 주소
	
	public Student(String name, String grade, String school, String rDate, String pName, String pNum, String ppNum,
			String address) {
		super();
		this.name = name;
		this.grade = grade;
		this.school = school;
		this.rDate = rDate;
		this.pName = pName;
		this.pNum = pNum;
		this.ppNum = ppNum;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpNum() {
		return pNum;
	}
	public void setpNum(String pNum) {
		this.pNum = pNum;
	}
	public String getPpNum() {
		return ppNum;
	}
	public void setPpNum(String ppNum) {
		this.ppNum = ppNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
}
