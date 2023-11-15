package cn.entity;

public class Users {
private Integer id;
private String pic;
private Integer role;
private String phone;
private String password;
private String name;
private String sex;
private String grade;
private String school;
private String major;
private Integer isdel;
public Users(){
	
}

public Users(Integer id, String pic, Integer role, String phone, String password, String name, String sex, String grade,
		String school, String major, Integer isdel) {
	super();
	this.id = id;
	this.pic = pic;
	this.role = role;
	this.phone = phone;
	this.password = password;
	this.name = name;
	this.sex = sex;
	this.grade = grade;
	this.school = school;
	this.major = major;
	this.isdel = isdel;
}

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getPic() {
	return pic;
}
public void setPic(String pic) {
	this.pic = pic;
}
public Integer getRole() {
	return role;
}
public void setRole(Integer role) {
	this.role = role;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
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
public String getMajor() {
	return major;
}
public void setMajor(String major) {
	this.major = major;
}
public Integer getIsdel() {
	return isdel;
}
public void setIsdel(Integer isdel) {
	this.isdel = isdel;
}


}
