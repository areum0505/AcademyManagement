package database;

public class Student {
	private String name;	// �̸�
	private String grade;	// �г�
	private String school;	// �б�
	private String rDate;	// ������
	private String pName;	// ��ȣ���̸�
	private String pNum;	// ����ó
	private String ppNum;	// ��ȣ�� ����ó
	private String address;	// �ּ�
	
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
